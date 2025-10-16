package com.retrotechie.MusicJam.Commands;

import java.io.IOException;

import com.retrotechie.MusicJam.MainJam;
import com.retrotechie.MusicJam.Utilities.Command;
import com.retrotechie.MusicJam.Utilities.CommandRegistry;

public class Search {
	public static void register(CommandRegistry registry) {
		Command Search = new Command("search", "Finds a set number of video results for a given prompt", (args) -> {
			if(args.length == 0) {
				return;
			}
			String search = args[0].toString();
			int amount = (int) Double.parseDouble(args[1]);
			ProcessBuilder pb = new ProcessBuilder(MainJam.pathToYTDLP, "\"ytsearch:\"", search);
			pb.redirectErrorStream();
			pb.inheritIO();
			try {
				Process process = pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		});
		
		registry.register(Search);
	}
}
