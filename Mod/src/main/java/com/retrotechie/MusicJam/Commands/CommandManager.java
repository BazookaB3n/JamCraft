package com.retrotechie.MusicJam.Commands;

import java.util.Arrays;
import java.util.List;

import com.retrotechie.MusicJam.Utilities.CommandRegistry;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;



public class CommandManager extends CommandBase {
	
	static boolean isRegistered = false;
	String[] cmdAliases = {"jc", "jam", "jcraft"};

    static CommandRegistry registry = new CommandRegistry();
    
    public void registerSubcommands() {
        //Register all subcommands for overarching command
        Clear.register(registry);
        Play.register(registry);
        Volume.register(registry);
        Pause.register(registry);
        Resume.register(registry);
        Config.register(registry);
        Shuffle.register(registry);
        Search.register(registry);
        isRegistered = true;
    }
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
	//	getCommandSender(icommandsender);
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
		if(args.length >= 1 && isRegistered) {	
			registry.execute(args, commandSender);
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


}


