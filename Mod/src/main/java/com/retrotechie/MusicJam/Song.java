package com.retrotechie.MusicJam;

import java.io.File;

public class Song {
	
	private File songFile;
	private String songTitle;
	
	public Song(File songFile, String songTitle) {
		this.songFile = songFile;
		this.songTitle = songTitle;
	}
	
	public File getSongFile() { 
		return songFile;
	}
	
	public String getSongTitle() {
		return songTitle;
	}
	
}
