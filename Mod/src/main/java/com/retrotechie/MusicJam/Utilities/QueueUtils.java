package com.retrotechie.MusicJam.Utilities;

import java.util.LinkedList;
import java.util.Queue;

import com.retrotechie.MusicJam.Song;

public class QueueUtils {
    public static Queue<Song> queue;
    
	public QueueUtils() {
       QueueUtils.queue = new LinkedList<>();
    }
	
    // Peek at the next song without removing
    public Song peekNext() {
        return queue.peek();
    }
	
    //Check if there are any songs in the queue
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
