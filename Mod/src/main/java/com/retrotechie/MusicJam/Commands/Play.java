package com.retrotechie.MusicJam.Commands;

import com.retrotechie.resources.SongRuntime;
import com.retrotechie.resources.MessageHelper;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Play {
	public static void register(CommandRegistry registry) {
		Command play = new Command("play", "Adds a song to the queue!", (args) -> {
			if(args.length > 1) {
				System.out.println(args[0]);
				System.out.println(args[1]);
				if(args[1].toLowerCase().contains("youtube.com/watch") && !args[1].contains("&")) {
					SongRuntime.getOGG(args[1]);
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
//    private static void playSong(ICommandSender commandSender, String[] args) {			
//		if(args.length > 1) {
//		if(args[1].toLowerCase().contains("youtube.com/watch") && !args[1].contains("&")) {
//			SongRuntime.getOGG(args[1]);
//			return;
//		} else {
//				commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter a valid youtube video URL! \nNote that it should not include any & Symbols."));
//				return;
//		} 
//		} else {
//			System.out.println("Unknown Video Link");
//			//commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter the URL to the video you want to listen to!"));
//			return;
//	}
//	}
}
