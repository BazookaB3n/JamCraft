package com.retrotechie.MusicJam.Commands;

import com.retrotechie.resources.SongRuntime;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Play {
	public static void register(CommandRegistry registry, ICommandSender commandSender) {
		Command play = new Command("play", "Adds a song to the queue!", (args) -> {
			if(args.length > 1) {
				if(args[1].toLowerCase().contains("youtube.com/watch") && !args[1].contains("&")) {
					SongRuntime.getOGG(args[1]);
					return;
				} else {
						commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter a valid youtube video URL! \nNote that it should not include any & Symbols."));
						return;
				} 
				} else {
					commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter the URL to the video you want to listen to!"));
					return;
				}
		});
	}
//    private void playSong(ICommandSender commandSender, String[] args) {			
//		if(args.length > 1) {
//		if(args[1].toLowerCase().contains("youtube.com/watch") && !args[1].contains("&")) {
//			SongRuntime.getOGG(args[1]);
//			return;
//		} else {
//				commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter a valid youtube video URL! \nNote that it should not include any & Symbols."));
//				return;
//		} 
//		} else {
//			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter the URL to the video you want to listen to!"));
//			return;
//	}
//	}
}
