package com.retrotechie.MusicJam.Commands;

import net.minecraft.command.ICommandSender;

public class Shuffle {
	public static void register(CommandRegistry registry, ICommandSender commandSender) {
		Command volume = new Command("volume", "Changes the volume of the audio being played", (args) -> {
		
		});
	}
//	    private void toggleShuffle(ICommandSender commandSender, String[] args) {
//	if(args.length > 1) {	
//		if(args[1].equals("true")) {
//			SongManager.shuffle = true;
//		commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Shuffle is now Enabled!"));
//		} else if(args[1].equals("false")) {
//			SongManager.shuffle = false;
//			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Shuffle is now Disabled!"));
//		}
//	} else {
//		SongManager.shuffle = !SongManager.shuffle;
//		if(SongManager.shuffle) {
//			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Shuffle is now Enabled!"));
//    	} else {
//    		commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Shuffle is now disabled!"));
//		}
//	} 
//}
}
