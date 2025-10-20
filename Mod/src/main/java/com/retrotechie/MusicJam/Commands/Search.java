package com.retrotechie.MusicJam.Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.retrotechie.MusicJam.MainJam;
import com.retrotechie.MusicJam.Utilities.Command;
import com.retrotechie.MusicJam.Utilities.CommandRegistry;

public class Search {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

	public static void register(CommandRegistry registry) {
		Command Search = new Command("search", "Finds a set number of video results for a given prompt", (args) -> {
			executor.submit(() -> {
			if(args.length == 0) {
				return;
			}
			String search = args[0].toString();
			ProcessBuilder pb = new ProcessBuilder(MainJam.pathToYTDLP, "ytsearch10:" + search, "--get-title");
			pb.inheritIO();
			List<String> urls = null;
			try {
				Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String url = "https://www.youtube.com/watch?v=" + line.trim();
                    urls.add(url);
                process.waitFor();
    			for(int n = 0; n < urls.size(); n++) {
    				System.out.println("URL #" + n + ": " + urls.indexOf(n));
    				}
                }
            } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			} catch (IOException e) {
				e.printStackTrace();
			}

		});
		});
		registry.register(Search);
	}
	
}
