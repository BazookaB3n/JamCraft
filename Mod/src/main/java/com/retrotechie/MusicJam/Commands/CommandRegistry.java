package com.retrotechie.MusicJam.Commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class CommandRegistry {
    private Map<String, Command> commands = new HashMap<>();

    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    public void execute(String input) {
        String[] parts = input.trim().split("\\s+");
        if (parts.length == 0) return;

        Command cmd = commands.get(parts[0]);
        if (cmd != null) {
            cmd.execute(Arrays.copyOfRange(parts, 1, parts.length));
        } else {
            System.out.println("Unknown command: " + parts[0]);
        }
    }

    public void listCommands() {
        System.out.println("Available commands: " + commands.keySet());
    }
}