package com.retrotechie.MusicJam.Utilities;

import java.util.LinkedList;
import java.util.Queue;

import com.retrotechie.MusicJam.Song;

public abstract class SongQueue {
    
	// Shared data structure (all subclasses will use it)
    private Queue<Song> queue = new LinkedList<>();

    // Add a song to the queue
    public void addSong(Song song) {
        queue.offer(song);
    }

    // Remove and return the next song
    public Song playNext() {
        return queue.poll();
    }

    // Peek at the next song without removing
    public Song peekNext() {
        return queue.peek();
    }

    // Abstract method – subclasses must define how the queue is displayed
    public abstract void displayQueue();
}

