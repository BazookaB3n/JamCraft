package com.retrotechie.MusicJam.Commands;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retrotechie.MusicJam.MainJam;
import com.retrotechie.resources.SongGrabber;
import com.retrotechie.resources.SongManager;
import com.retrotechie.resources.SongRuntime;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ModCommandManager extends CommandBase {
	
    String[] cmdAliases = {"musicmod", "mm", "music"};

    private final Map<String, ICommandHandler> subcommands = new HashMap<>();
    

    public ModCommandManager() {
        subcommands.put("play", this::playSong);
        subcommands.put("list", this::playPlaylist);
        subcommands.put("resume", this::resumeSong);
        subcommands.put("pause", this::pauseSong);
        subcommands.put("volume", this::setVolume);
        subcommands.put("config", this::getConfig);
        subcommands.put("clear", this::clearSongs);
        subcommands.put("shuffle", this::toggleShuffle);
    }
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String getCommandName() {
		// What is the command name (what do you put after '/'?"
		return "musicmod";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "Allows you to configure the Mod \nand any other features available through it";
	}

	@Override
	public void processCommand(ICommandSender commandSender, String[] args) throws CommandException {
		if (args.length == 0) {
			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "Welcome To MusicMod! \n" + EnumChatFormatting.DARK_RED + "No arguments were entered, please try again!"));
			return;
		}
		
        ICommandHandler handler = subcommands.get(args[0].toLowerCase());
		if(args.length >= 1 && handler != null) {	
			handler.execute(commandSender, args);
		} else {
			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "Welcome To MusicMod! \n" + EnumChatFormatting.DARK_RED + "The following arguments were not recognized: " + EnumChatFormatting.BOLD + args[0]));
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
    
    private void setVolume(ICommandSender commandSender, String[] args) {
			if(args.length > 1) {
				try {
				    int parsedInt = (int) Double.parseDouble(args[1]) * 10;
				    double volume = (double) parsedInt / 10;
				    if(volume >= 0 && volume <= 1000) {
				    	//Checks if volume is between 0-100.
				    	MainJam.musicVolume = (float)(volume)/100;
				    	System.out.println("New volume level: " + volume + "% volume");
						commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "Volume level has been adjusted! New volume level: " + EnumChatFormatting.BOLD + EnumChatFormatting.ITALIC + MainJam.musicVolume));
				    } else {
				    	//Sends a message that the volume was either too high or too low if the values are out of range. 
						commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid Volume Arguments! Please enter a number between 1-100!"));
				    }
				} catch (NumberFormatException e) {
					//Sends a message if the argument is invalid. 
					commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid Volume Arguments! Please enter a numeric value!"));
				}
		} else {
			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter the desired volume level!"));
		}
    }
    
    private void playSong(ICommandSender commandSender, String[] args) {			
    		if(args.length > 1) {
			if(args[1].toLowerCase().contains("youtube.com/watch") && !args[1].contains("&")) {
				SongRuntime.getOGG(args[1]);
				return;
			} else {
					commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter a valid youtube video URL! \nNote that it should not include any & Symbols."));
					return;
			} 
    		} else {
				commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter the URL to the video you want to listen to!"));
				return;
		}
	}
   
    private void playPlaylist(ICommandSender commandSender, String[] args) {
		if(args.length > 1) {
			if(args[1].toLowerCase().contains("youtube.com/watch?v=") && args[1].toLowerCase().contains("&list=")) {
				SongRuntime.getOGGPlaylist(args[1]);
			} else {
				commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter a valid youtube playlist URL!"));
				return;
			} 
		} else {
			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter the URL to the playlist you want to listen to!"));
			return;
		}		
    }
    
    private void pauseSong(ICommandSender commandSender, String[] args) {
		if(SongManager.paused) {
			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "♫ Song resumed! ♫"));
			SongManager.resumeSong();
		} else {
			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "♫ Song paused! ♫"));
			SongManager.pauseSong();
		}
    }
    
    private void resumeSong(ICommandSender commandSender, String[] args) {
    	if(SongManager.paused) {
    		commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "♫ Song resumed! ♫"));
    		SongManager.resumeSong();
    	} else {
    		commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GRAY + "♫ Song is already playing! ♫"));
    	}
    }
    
    private void clearSongs(ICommandSender commandSender, String[] args) {
    	commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "This command can only delete files from previous sessions! \n" + EnumChatFormatting.BOLD + "To remove files from this session, relaunch the game. "));
    	if(args.length > 1) {
    		if(args[1].toLowerCase().equals("all"))     {
    			cleanDirectory(SongRuntime.outputDir);
    			cleanDirectory(SongGrabber.outputDir);
    		} else {
    			try {
				    for (int parsedInt = (int) Double.parseDouble(args[1]); parsedInt > 0; parsedInt--) {
				    	File[] mp4Files = SongGrabber.outputDir.listFiles();
				    	File[] oggFiles = SongRuntime.outputDir.listFiles();
				    	File oldestMp4 = null;
				    	File oldestOgg = null;
				    	if(mp4Files.length > 0) {
				    		oldestMp4 = mp4Files[0];
					    	for(File file : mp4Files) {
					    		if(file.lastModified() > oldestMp4.lastModified()) {
					    			oldestMp4 = file;
					    		}
					    	}
				    	}
				    	if(oggFiles.length > 0) {
				    		oldestOgg = oggFiles[0];
					    	for(File file : oggFiles) {
					    		if(file.lastModified() > oldestOgg.lastModified()) {
					    			oldestOgg = file;
					    		}
					    	}
				    	}
				    	
				    	if(oldestMp4 != null) {
				    		oldestMp4.delete();
				    		System.out.println("Deleting " + oldestMp4.getName());
				    	}
				    	
				    	if(oldestOgg != null) {
					    	oldestOgg.delete();
						    System.out.println("Deleting " + oldestOgg.getName());
				    	}
				    }
				} catch (NumberFormatException e) {
					commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid Arguments! Please enter a numeric value!"));
				}
    		}
    	} else {
			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter the number of cached files to delete. \n" + EnumChatFormatting.DARK_AQUA + "Write \"all\" to delete all cached files."));
    	}
    }
    
    private void cleanDirectory(File directory) {
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // Recursively clean subdirectories
                        cleanDirectory(file);
                        // After cleaning, delete the empty subdirectory
			        	file.delete();
				    	System.out.println("Deleting " + file.getName());
                    } else {
                        // Delete individual files
			    		file.delete();
				    	System.out.println("Deleting " + file.getName());
                    }
                }
            }
        }
    }
    
    private void toggleShuffle(ICommandSender commandSender, String[] args) {
	    	if(args.length > 1) {	
	    		if(args[1].equals("true")) {
	    			SongManager.shuffle = true;
				commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Shuffle is now Enabled!"));
	    		} else if(args[1].equals("false")) {
	    			SongManager.shuffle = false;
	    			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Shuffle is now Disabled!"));
	    		}
	    	} else {
	    		SongManager.shuffle = !SongManager.shuffle;
	    		if(SongManager.shuffle) {
	    			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Shuffle is now Enabled!"));
		    	} else {
		    		commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Shuffle is now disabled!"));
	   		}
	    	} 
	}
}


