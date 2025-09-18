package com.retrotechie.MusicJam.Commands;

import com.retrotechie.resources.SongManager;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Resume {
	public static void register(CommandRegistry registry, ICommandSender commandSender) {
		Command resume = new Command("resume", "Resumes song playback", (args) -> {
			if(SongManager.paused) {
				commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "♫ Song resumed! ♫"));
				SongManager.resumeSong();
			} else {
				commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "♫ Song paused! ♫"));
				SongManager.pauseSong();
			}
		});
	}
}