package com.retrotechie.MusicJam.Commands;

import com.retrotechie.resources.MessageHelper;
import com.retrotechie.resources.SongManager;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Resume {
	public static void register(CommandRegistry registry) {
		Command resume = new Command("resume", "Resumes song playback", (args) -> {
			if(SongManager.paused) {
			
				MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "♫ Song resumed! ♫"));
				SongManager.resumeSong();
			} else {
				MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "♫ Song paused! ♫"));
				SongManager.pauseSong();
			}
		});
		registry.register(resume);
	}
}