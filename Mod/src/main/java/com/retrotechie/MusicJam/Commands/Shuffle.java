package com.retrotechie.MusicJam.Commands;

import com.retrotechie.MusicJam.Utilities.Command;
import com.retrotechie.MusicJam.Utilities.CommandRegistry;
import com.retrotechie.resources.MessageHelper;
import com.retrotechie.resources.SongManager;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ChatComponentText;

public class Shuffle {
	public static void register(CommandRegistry registry) {
		Command shuffle = new Command("shuffle", "Enables or Disables Shuffle Mode", (args) -> {
			if(args.length > 1) {	
				if(args[1].equals("true")) {
					SongManager.shuffle = true;
				MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Shuffle is now Enabled!"));
				} else if(args[1].equals("false")) {
					SongManager.shuffle = false;
					MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Shuffle is now Disabled!"));
				}
			} else {
				SongManager.shuffle = !SongManager.shuffle;
				if(SongManager.shuffle) {
					MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Shuffle is now Enabled!"));
		    	} else {
		    		MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Shuffle is now disabled!"));
				}
			} 
		});
		registry.register(shuffle);
	}
}
