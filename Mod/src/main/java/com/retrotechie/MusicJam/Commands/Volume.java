package com.retrotechie.MusicJam.Commands;

import com.retrotechie.MusicJam.MainJam;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Volume {
	public static void register(CommandRegistry registry, ICommandSender commandSender) {
		Command volume = new Command("volume", "Changes the volume of the audio being played", (args) -> {
			if(args.length > 1) {
				try {
				    int parsedInt = (int) Double.parseDouble(args[1]) * 10;
				    double vol = (double) parsedInt / 10;
				    if(parsedInt >= 0 && parsedInt <= 1000) {
				    	//Checks if volume is between 0-100.
				    	MainJam.musicVolume = (float)(vol)/100;
				    	System.out.println("New volume level: " + vol + "% volume");
						commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "Volume level has been adjusted! New volume level: " + EnumChatFormatting.BOLD + EnumChatFormatting.ITALIC + (int) (MainJam.musicVolume * 100)));
				    } else {
				    	//Sends a message that the volume was either too high or too low if the values are out of range. 
						commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid Volume Arguments! Please enter a number between 1-100!"));
				    }
				} catch (NumberFormatException e) {
					//Sends a message if the argument is invalid. 
					commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid Volume Arguments! Please enter a numeric value!"));
				}
		} else {
			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter the desired volume level!"));
		}
		});
		registry.register(volume);
	}
	
}
