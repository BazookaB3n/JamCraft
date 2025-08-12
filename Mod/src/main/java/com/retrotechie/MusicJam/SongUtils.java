package com.retrotechie.MusicJam;

import java.io.File;
import java.util.List;
import java.util.concurrent.Future;

public abstract class SongUtils {

	public abstract Future<File> downloadSong(String videoPrompt);
	public abstract Future<List<File>> downloadPlaylist(String playlistPrompt);
}
