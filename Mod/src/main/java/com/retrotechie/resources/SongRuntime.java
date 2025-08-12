package com.retrotechie.resources;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import com.retrotechie.MusicJam.Utilities.SongUtils;

import com.retrotechie.MusicJam.MainJam;
import com.retrotechie.MusicJam.Song;

import net.minecraft.client.Minecraft;

public class SongRuntime {

    public static File outputDir = new File("assets/retrotechie/music/");
    public static File playlistOutputDir;
    
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
	
	public static void getOGG(String URL) {
		SongUtils utils = new SongGrabber();
    	String videoTitle = SongGrabber.getVideoTitle(URL);
		Future<File> futureMP4 = utils.downloadSong(URL, videoTitle);
		
		new Thread(() -> {
			    try {
			        File mp3File = futureMP4.get(); // wait for download to finish
			        System.out.println("MP3 downloaded to: " + mp3File.getAbsolutePath());
			        
		    		Future<File> futureOGG = SongRuntime.convertFile(mp3File);
		    		File oggFile = futureOGG.get();
		    		System.out.println("OGG downloaded to: " + oggFile.getAbsolutePath());
		    		
			        //Pass song file to next method, which will invoke the song into the sound system. 
			        Minecraft.getMinecraft().addScheduledTask(() -> {
			        	SongManager.PlaySong(new Song(oggFile, oggFile.getName()));
			        });

			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}).start();
	}
	
	public static void getOGGPlaylist(String URL) {
		SongUtils utils = new SongGrabber();
		Future<List<File>> futureMP4s = utils.downloadPlaylist(URL,);
		
		new Thread(() -> {
			try {
				List<File> mp4Files = futureMP4s.get();
				System.out.println("MP4s Downloaded!");
				
			Future<List<File>> futureOGGs = SongRuntime.convertPlaylist(mp4Files);
			List<File> oggFiles = futureOGGs.get();
			System.out.println("OGGs Downloaded!");
			
			//Setup FilePaths to pass to Song Queue:
			List<String> FilePaths = null;
			for(File file : oggFiles) {
				FilePaths.add(file.getAbsolutePath().toString());
			}
				//Pass files to next method, so it can play the songs through the sound system. 
			Minecraft.getMinecraft().addScheduledTask(() -> {
				SongManager.queueSongs(FilePaths);
			});
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	
	public static Future<File> convertFile(File inputFile) {
        return executor.submit(() -> {
	    	String videoID = inputFile.getName();
	    	videoID = videoID.replace(".mp4", "");
	    	System.out.println("Video file name (FFMPEG): " + videoID);
				
	        ProcessBuilder pb = new ProcessBuilder(
	            MainJam.pathToFFMPEG,
	            "-i", inputFile.getAbsolutePath(),
	            "-vn",
	            "-y",
	            "-acodec", "libvorbis",
	            "-ar", "44100",
	            "-ac", "2",
	            new File(outputDir, videoID + ".ogg").getAbsolutePath()
	        );
	        pb.inheritIO();
	        Process p = pb.start();
			p.waitFor();
	        System.out.println("Conversion done!");
            
	        File[] files = outputDir.listFiles((dir, name) -> name.endsWith(".ogg"));
            if (files == null || files.length == 0) {
                throw new RuntimeException("No .ogg files found in output folder.");
            }
            
            File result = null;
            for (File file : files) {
                if (file.getName().contains(videoID)) {
                    result = file;
                }
            }

            return result; //Return the .ogg file
		});
	}
	
	public static Future<List<File>> convertPlaylist(List<File> inputFiles) {
        return executor.submit(() -> {
        	List<String> VideoIDs = null;
        	for(File inputFile : inputFiles) {
        		String videoID = inputFile.getName();
        		videoID = videoID.replace(".mp4", "");
        		//Add video ID to list for verification later. 
        		VideoIDs.add(videoID);
        		System.out.println("Video file name (FFMPEG): " + videoID);
        		
    	        ProcessBuilder pb = new ProcessBuilder(
    	            MainJam.pathToFFMPEG,
    	            "-i", inputFile.getAbsolutePath(),
    	            "-vn",
    	            "-y",
    	            "-acodec", "libvorbis",
    	            "-ar", "44100",
    	            "-ac", "2",
    	            new File(playlistOutputDir, videoID + ".ogg").getAbsolutePath()
    	        );
    	        pb.inheritIO();
    	        Process p = pb.start();
    			p.waitFor();
        	}
    	        System.out.println("Conversion done!");
                
    	        File[] files = playlistOutputDir.listFiles((dir, name) -> name.endsWith(".ogg"));
                if (files == null || files.length == 0) {
                    throw new RuntimeException("No .ogg files found in playlist output folder.");
                }
                
                List<File> results = null;
                for (File file : files) {
                		for(String id : VideoIDs) {
	                    if (file.getName().contains(id)) {
	                        results.add(file);
	                    }
                    }
                }
                return results; //Return the .ogg files
        	});
	    

	}
}

