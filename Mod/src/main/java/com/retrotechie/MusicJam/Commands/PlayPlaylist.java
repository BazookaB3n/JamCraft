package com.retrotechie.MusicJam.Commands;

import net.minecraft.command.ICommandSender;

public class PlayPlaylist {
	public static void register(CommandRegistry registry, ICommandSender commandSender) {
		Command volume = new Command("volume", "Changes the volume of the audio being played", (args) -> {
		
		});
	}

	   
//    private void playPlaylist(ICommandSender commandSender, String[] args) {
//		if(args.length > 1) {
//			if(args[1].toLowerCase().contains("youtube.com/watch?v=") && args[1].toLowerCase().contains("&list=")) {
//				SongRuntime.getOGGPlaylist(args[1]);
//			} else {
//				commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter a valid youtube playlist URL!"));
//				return;
//			} 
//		} else {
//			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter the URL to the playlist you want to listen to!"));
//			return;
//		}		
//    }
}
