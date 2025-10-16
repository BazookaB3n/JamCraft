package com.retrotechie.MusicJam.Commands;

import com.retrotechie.resources.SongRuntime;
import com.retrotechie.MusicJam.Utilities.Command;
import com.retrotechie.MusicJam.Utilities.CommandRegistry;
import com.retrotechie.resources.MessageHelper;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Play {
	public static void register(CommandRegistry registry) {
		Command play = new Command("play", "Adds a song to the queue!", (args) -> {
			for(int i = 0; i < args.length; i++) {
				System.out.println("Arg #" + i + ": " + args[i]);
			}
			if(args.length >= 1) {
				if(args[0].toLowerCase().contains("youtube.com/watch") && !args[0].contains("&")) {
					SongRuntime.getOGG(args[0]);
					return;
				} else {
						MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter a valid youtube video URL! \nNote that it should not include any & Symbols."));
						return;
				} 
			} else {
				MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter the URL to the video you want to listen to!"));
				return;
			}
		});
		registry.register(play);
	}
}
