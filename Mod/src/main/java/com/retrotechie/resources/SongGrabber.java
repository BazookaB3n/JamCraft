package com.retrotechie.resources;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.retrotechie.MusicJam.SongUtils;
import com.retrotechie.MusicJam.MainJam;

public class SongGrabber extends SongUtils {

	public static File outputDir = new File("assets/retrotechie/mp4/");
	static List<File>result = null;

	@Override
	public Future<File> downloadSong(String videoPrompt) {
        return executor.submit(() -> {
        	//Download Video using YT-DLP
        	String videoID = videoPrompt.replace("https://www.youtube.com/watch?v=", "");
        	SongGrabber.videoID = videoID;
        	System.out.println("Video File Name: " + videoID);
		    //uses YT-DLP To grab the video based on the url.
		    ProcessBuilder pb = new ProcessBuilder(MainJam.pathToYTDLP, "-x", "-f", "mp4", "-o", new File(outputDir, videoID + ".mp4").getAbsolutePath(), videoPrompt);  

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
                if (file.getName().contains(videoID)) {
                    result = file;
                }
            }

            return result; // return the .mp4 file 
        });
	}
	
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    static String videoID;
    
    @Override
    public Future<List<File>> downloadPlaylist(String playlistPrompt) {
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
}

