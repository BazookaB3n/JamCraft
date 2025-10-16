package com.retrotechie.MusicJam.Commands;

import com.retrotechie.MusicJam.Utilities.Command;
import com.retrotechie.MusicJam.Utilities.CommandRegistry;
import com.retrotechie.resources.MessageHelper;
import com.retrotechie.resources.SongManager;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Pause {
	public static void register(CommandRegistry registry) {
		Command pause = new Command("pause", "Pauses the song being played, unless it is already paused.", (args) -> {
			if(SongManager.paused) {
				MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "♫ Song resumed! ♫"));
				SongManager.resumeSong();
			} else {
				MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "♫ Song paused! ♫"));
				SongManager.pauseSong();
			}
		});
		registry.register(pause);
	}
}