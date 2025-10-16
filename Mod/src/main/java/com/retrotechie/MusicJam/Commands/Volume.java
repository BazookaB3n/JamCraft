package com.retrotechie.MusicJam.Commands;

import com.retrotechie.MusicJam.MainJam;
import com.retrotechie.MusicJam.Utilities.Command;
import com.retrotechie.MusicJam.Utilities.CommandRegistry;
import com.retrotechie.resources.MessageHelper;
import com.retrotechie.resources.SongManager;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Volume {
	public static void register(CommandRegistry registry) {
		Command volume = new Command("volume", "Changes the volume of the audio being played", (args) -> {
			if(args.length >= 1) {
				try {
				    int parsedInt = (int) Double.parseDouble(args[0]);
				    if(parsedInt >= 0 && parsedInt <= 100) {
				    	//Checks if volume is between 0-100.
				    	MainJam.musicVolume = (float)(parsedInt);
				    	System.out.println("New volume level: " + parsedInt + "% volume");
				    	SongManager.setVolume(parsedInt);
						MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "Volume level has been adjusted! New volume level: " + EnumChatFormatting.BOLD + EnumChatFormatting.ITALIC + (parsedInt)));
				    } else {
				    	//Sends a message that the volume was either too high or too low if the values are out of range. 
						MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid Volume Arguments! Please enter a number between 1-100!"));
				    }
				} catch (NumberFormatException e) {
					//Sends a message if the argument is invalid. 
					MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid Volume Arguments! Please enter a numeric value!"));
				}
		} else {
			MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter the desired volume level!"));
		}
		});
		registry.register(volume);
	}
	
}
