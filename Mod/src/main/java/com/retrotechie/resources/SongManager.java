package com.retrotechie.resources;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import paulscode.sound.SoundSystem;

public class SongManager {
	
	//If the song is paused or not
    public static boolean paused = false;
    public static String queueID = "JamCraft_Music";
    public static boolean shuffle = false;
    
	//Gets minecrafts sound system and returns it. 
	public static SoundSystem getSoundSystem() {
		try {
			Object soundHandler = Minecraft.getMinecraft().getSoundHandler();
			
			//Set the sound manager as a public variable so the class can play sounds through it without using resource packs. 
			Field sndManagerField = soundHandler.getClass().getDeclaredField("sndManager");
			sndManagerField.setAccessible(true);
            Object sndManager = sndManagerField.get(soundHandler);
			
			//Set the sound System as a public variable as well. 
            Field sndSystemField = sndManager.getClass().getDeclaredField("sndSystem");
            sndSystemField.setAccessible(true);
            return (SoundSystem) sndSystemField.get(sndManager);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//Adds a song to the song queue
	public static void PlaySong(String filePath) {
		File ogg = new File(filePath);
		SoundSystem soundSystem = getSoundSystem();
		System.out.println("Does the file Exist: " + ogg);
		
		if(soundSystem == null) {
			System.err.println("Could not access SoundSystem Instance!");
			return;
		}
		
		try {
			if(soundSystem.playing(queueID)) {
	            soundSystem.queueSound(queueID, ogg.toURI().toURL(), ogg.getName());
			} else {
            soundSystem.newStreamingSource(true, queueID, ogg.toURI().toURL(), ogg.getName(), false, 0, 0, 0, paulscode.sound.SoundSystemConfig.ATTENUATION_NONE, 0);
            
            soundSystem.play(queueID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error in loading song into SoundSystem Instance!");
		}
	}
	
	public static void queueSongs(List<String> FilePaths) {	
		SoundSystem soundSystem = getSoundSystem();
		if(soundSystem == null) {
			System.err.println("Could not access SoundSystem Instance!");
			return;
		}
		int FileCount = FilePaths.size();
		if(shuffle == true) {
			Random random = new Random();
			for(int i = 0; i < FileCount; i++) {
				int index = random.nextInt(FilePaths.size());
				File ogg = new File(FilePaths.get(index));
	            try {
					soundSystem.queueSound(queueID, ogg.toURI().toURL(), ogg.getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
	            FilePaths.remove(index);
			}
		}
	}
	
	//Pauses the song that is currently playing. 
	public static void pauseSong() {
		SoundSystem soundSystem = getSoundSystem();
		paused = true;
		soundSystem.pause(queueID);
	}
	
	//Resumes the song if it is not currently playing. 
	public static void resumeSong() {
		if(paused) {
			SoundSystem soundSystem = getSoundSystem();
			paused = false;
			soundSystem.play(queueID);
		}
	}
}
