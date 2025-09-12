package com.retrotechie.MusicJam.Commands;

import net.minecraft.command.ICommandSender;

public class Pause {
	public static void register(CommandRegistry registry, ICommandSender commandSender) {
		Command volume = new Command("volume", "Changes the volume of the audio being played", (args) -> {
		
		});
	}
//    private void pauseSong(ICommandSender commandSender, String[] args) {
//		if(SongManager.paused) {
//			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "♫ Song resumed! ♫"));
//			SongManager.resumeSong();
//		} else {
//			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "♫ Song paused! ♫"));
//			SongManager.pauseSong();
//		}
//    }
//    
//    private void resumeSong(ICommandSender commandSender, String[] args) {
//    	if(SongManager.paused) {
//    		commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "♫ Song resumed! ♫"));
//    		SongManager.resumeSong();
//    	} else {
//    		commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GRAY + "♫ Song is already playing! ♫"));
//    	}
//    }
}
