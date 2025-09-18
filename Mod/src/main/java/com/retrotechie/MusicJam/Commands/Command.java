package com.retrotechie.MusicJam.Commands;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import java.util.Arrays;

public class Command {
    private String name;
    private String description;
    private Consumer<String[]> action;  // what to do if executed
    private Map<String, Command> subcommands = new HashMap<>();

    public Command(String name, String description, Consumer<String[]> action) {
        this.name = name;
        this.description = description;
        this.action = action;
    }

    // Add a subcommand
    public void addSubcommand(Command sub) {
        subcommands.put(sub.name, sub);
    }

    // Execute command or delegate to subcommands
    public void execute(String[] args) {
        if (args.length > 0) {
            Command sub = subcommands.get(args[0]);
            if (sub != null) {
                sub.execute(Arrays.copyOfRange(args, 1, args.length));
                return;
            }
        }

        if (action != null) {
            action.accept(args);// Pass remaining args to this command
        } else if (!subcommands.isEmpty()) {
            System.out.println("Available subcommands for " + name + ": " + subcommands.keySet());
        } else {
            System.out.println("Nothing to execute for command: " + name);
        }
    }

    public String getName() {
        return name;
    }
    
    public String getDescription() {
    	return description;
    }
}