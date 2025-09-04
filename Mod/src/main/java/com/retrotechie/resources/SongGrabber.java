package com.retrotechie.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.retrotechie.MusicJam.MainJam;
import com.retrotechie.MusicJam.Utilities.SongUtils;

public class SongGrabber extends SongUtils {

	public static File outputDir = new File("assets/retrotechie/mp4/");
	static List<File>result = null;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    static String videoTitle;

	@Override
	public Future<File> downloadSong(String videoPrompt, String videoTitle) {
        return executor.submit(() -> {
        	//Download Video using YT-DLP
        	String videoID = videoPrompt.replace("https://www.youtube.com/watch?v=", "");
        	System.out.println("Video Title: " + videoTitle);

		    //uses YT-DLP To grab the video based on the url.
		    ProcessBuilder pb = new ProcessBuilder(MainJam.pathToYTDLP, "-x", "-f", "mp4", "-o", new File(outputDir, videoTitle.replace("/", "") + ".mp4").getAbsolutePath(), videoPrompt);  

            pb.redirectErrorStream(true);// combine stderr and stdout
            Process process = pb.start();
            int exitCode = process.waitFor();
            if (exitCode != 0 && exitCode != 1) {
                throw new RuntimeException("yt-dlp failed with code " + exitCode);
            }

            // Option 1: Find the most recent .ogg file in outputDir
            File[] files = outputDir.listFiles((dir, name) -> name.endsWith(".mp4"));
            if (files == null || files.length == 0) {
                throw new RuntimeException("No .MP4 files found in output folder.");
            }

            File result = null;
            for (File file : files) {
                if (file.getName().contains(videoTitle.replace("/", ""))) {
                    result = file;
                }
            }

            return result; // return the .mp4 file 
        });
	}
    
    @Override
    public Future<List<File>> downloadPlaylist(String playlistPrompt, String playlistTitle) {
    	return executor.submit(() -> {
    	    final Pattern PLAYLIST_ID_PATTERN = Pattern.compile("[?&]list=([A-Za-z0-9_-]+)");
    		String playlistID = extractID(playlistPrompt, PLAYLIST_ID_PATTERN);
    		File playlistOutputDir = new File(outputDir + "%(playlist_id)s/");
    		SongRuntime.playlistOutputDir = new File(SongRuntime.outputDir + playlistID);
    		String[] playlistDownloadLine = {MainJam.pathToYTDLP, "-x", "-f", "mp4", "-o", new File(playlistOutputDir, "%(playlist_id)s-%(id)s.%(ext)s").getAbsolutePath(), playlistPrompt};
    		System.out.println("Playlist ID: " + playlistID);
    		ProcessBuilder pb = new ProcessBuilder(playlistDownloadLine);
    		
    		pb.redirectErrorStream(true);
    		Process process = pb.start();
    		int exitCode = process.waitFor();
    		
            if (exitCode != 0) {
                throw new RuntimeException("yt-dlp failed with code " + exitCode);
            }
            File[] files = playlistOutputDir.listFiles((dir, name) -> name.endsWith(".mp4"));
            if (files == null || files.length == 0) {
                throw new RuntimeException("No .MP4 files found in output folder.");
            }
            
            for (File file : files) {
                if (file.getName().contains(playlistID)) {
                    result.add(file);
                } 
            }
            return result;
    	});
    }
    
    public static String extractID(String input, Pattern p) {
    	if(input == null || input.trim().isEmpty()) return null;
    	Matcher matcher = p.matcher(input);
    	
    	if(matcher.find()) {
    		return matcher.group(1);
    	}
    	
    	if(input.matches("^[A-Za-z0-9_-]{10,}$")) {
            return input;
        }
    	
    	return null;
    }
    
    public static String getVideoTitle(String URL) {
		 StringBuilder title = new StringBuilder();
		 try {
			 ProcessBuilder pb = new ProcessBuilder(MainJam.pathToYTDLP, "--get-title", "--no-warnings", URL);
             pb.redirectErrorStream(true);
             Process process = pb.start();

             try (BufferedReader reader = new BufferedReader(
            		 new InputStreamReader(process.getInputStream()))) {
            	 String line;
            	 while ((line = reader.readLine()) != null) {
            		 title.append(line);
            	 }
             }
             process.waitFor();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return title.toString();
    }
}	

