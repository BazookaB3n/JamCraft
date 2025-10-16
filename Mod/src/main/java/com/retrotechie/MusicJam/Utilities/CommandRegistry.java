package com.retrotechie.MusicJam.Utilities;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.command.ICommandSender;

import java.util.Arrays;

public class CommandRegistry {
    private Map<String, Command> commands = new HashMap<>();

    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    public void execute(String[] input, ICommandSender commandSender) {
        if (input.length == 0) return;

        Command cmd = commands.get(input[0].toLowerCase());
        if (cmd != null) {
            cmd.execute(Arrays.copyOfRange(input, 1, input.length));
        } else {
            System.out.println("Unknown command: " + input[0].toLowerCase());
        }
    }

    public void listCommands() {
        System.out.println("Available commands: " + commands.keySet());
    }
}