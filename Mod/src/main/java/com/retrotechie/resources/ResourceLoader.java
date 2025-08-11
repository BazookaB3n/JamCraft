package com.retrotechie.resources;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import com.retrotechie.MusicJam.MainJam;

public class ResourceLoader {
	
    public static String extractFFmpeg() throws IOException {
    
    	
    	String binaryName = MainJam.os.contains("win") ? "ffmpeg.exe" : "ffmpeg";
    	InputStream in = ResourceLoader.class.getResourceAsStream("FFmpeg/" + binaryName);
        if (in == null) {
            throw new FileNotFoundException("yt-dlp binary not found in JAR");
        }
        File tempFile = File.createTempFile("FFMpeg", MainJam.os.contains("win") ? ".exe" : "");

        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        // On Unix, set executable
        if (!System.getProperty("os.name").toLowerCase().contains("win")) {
            tempFile.setExecutable(true);
        }

        return tempFile.getAbsoluteFile().toString();
    }
    
	public static String extractYTDLP() throws IOException, InterruptedException  {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println(os);
	
		String binaryName = MainJam.os.contains("win") ? "yt-dlp.exe" : "yt-dlp";
	    InputStream in = SongGrabber.class.getResourceAsStream("yt-dlp/" + binaryName);
	    if (in == null) {
	        throw new FileNotFoundException("yt-dlp binary not found in JAR");
	    }
	    Path tempFile;
		tempFile = Files.createTempFile("yt-dlp", os.contains("win") ? ".exe" : "");
		Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);
		tempFile.toFile().setExecutable(true);
		in.close();
		return tempFile.toAbsolutePath().toString();
	}
}