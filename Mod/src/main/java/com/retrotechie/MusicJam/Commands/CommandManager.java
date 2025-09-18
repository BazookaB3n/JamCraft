package com.retrotechie.MusicJam.Commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;



public class CommandManager extends CommandBase {
	
	static boolean isRegistered = false;
	
	String[] cmdAliases = {"jc", "jam", "jcraft"};

    static CommandRegistry registry = new CommandRegistry();
    
    private final Map<String, ICommandHandler> subcommands = new HashMap<>();
    

    // List all registered commands
    public void listCommands() {
    	System.out.println("Available Commands: " + subcommands.keySet());
    }
    
    public void registerSubcommands() {
    	ICommandSender commandSender = null;
        
        //Register all subcommands for overarching command
        Clear.register(registry, commandSender);
        Play.register(registry, commandSender);
        Volume.register(registry, commandSender);
        Pause.register(registry, commandSender);
        Resume.register(registry, commandSender);
        isRegistered = true;
    }

    
//    
//    
//    public CommandManager() {
//        subcommands.put("play", this::playSong);
//        subcommands.put("list", this::playPlaylist);
//        subcommands.put("resume", this::resumeSong);
//        subcommands.put("pause", this::pauseSong);
//        subcommands.put("volume", this::setVolume);
//        subcommands.put("config", this::getConfig);
//        subcommands.put("clear", this::clearSongs);
//        subcommands.put("shuffle", this::toggleShuffle);
//    }
//	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		return true;
	}
	
	@Override
	public String getCommandName() {
		// What is the command name (what do you put after '/'?"
		return "jamcraft";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "Allows you to use all of the mods features.\nalso allows you to configure the mod \nand any other features available through it";
	}

	@Override
	public void processCommand(ICommandSender commandSender, String[] args) throws CommandException {
		if (args.length == 0) {
			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "Welcome To JamCraft! \n" + EnumChatFormatting.DARK_RED + "No arguments were entered, please try again!"));
			return;
		}
		System.out.println("Args: " + args[0].toString());

		if(args.length >= 1 && isRegistered) {	
			System.out.println("Args: " + args.toString());
			registry.execute(args);
		} else {
			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "Welcome To JamCraft! \n" + EnumChatFormatting.DARK_RED + "The following arguments were not recognized: " + EnumChatFormatting.BOLD + args[0]));
		}
	}
	
	@Override
	public List<String> getCommandAliases()
    {    
		//Add all possible Aliases to command registry. 
        return Arrays.asList(cmdAliases);
    }
	
    @FunctionalInterface
    private interface ICommandHandler {
        void execute(ICommandSender sender, String[] args);
    }
    
    private void getConfig(ICommandSender commandSender, String[] args) {
		commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "====================================\n" + EnumChatFormatting.DARK_AQUA + "Config is currently unavailable!\n" + EnumChatFormatting.GRAY + "===================================="));
    }
    

    

    


}


