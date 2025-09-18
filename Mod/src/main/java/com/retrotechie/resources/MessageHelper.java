package com.retrotechie.resources;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class MessageHelper {
	public static void sendChatMessage(String message) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(
                new ChatComponentText(message)
            );
	}
	public static void sendChatMessage(ChatComponentText message) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(message);
	}
}
