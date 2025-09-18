package com.retrotechie.MusicJam.Commands;

import com.retrotechie.resources.MessageHelper;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Config {
	public static void register(CommandRegistry registry) {
		Command config = new Command("config", "Allows you to edit the configuration of JamCraft.", (args) -> {
			MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "====================================\n" + EnumChatFormatting.DARK_AQUA + "Config is currently unavailable!\n" + EnumChatFormatting.GRAY + "===================================="));
		});
		registry.register(config);
	}
	
}
