package com.retrotechie.MusicJam.Commands;

import java.io.File;

import com.retrotechie.MusicJam.Utilities.Command;
import com.retrotechie.MusicJam.Utilities.CommandRegistry;
import com.retrotechie.resources.MessageHelper;
import com.retrotechie.resources.SongGrabber;
import com.retrotechie.resources.SongRuntime;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Clear {
	public static void register(CommandRegistry registry) {
		Command clear = new Command("clear", "Clears files based on certain parameters.", (args) -> {
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
						MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid Arguments! Please enter a numeric value!"));
					}
	    		}
	    	} else {
	    		MessageHelper.sendChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter the number of cached files to delete. \n" + EnumChatFormatting.DARK_AQUA + "Write \"all\" to delete all cached files."));
	    	}
		});	
		registry.register(clear);
	}
//	public void clearSongs(ICommandSender commandSender, String[] args) {
//    	commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "This command can only delete files from previous sessions! \n" + EnumChatFormatting.BOLD + EnumChatFormatting.RED + "To remove files from this session, relaunch the game. "));
//    	if(args.length > 1) {
//    		if(args[1].toLowerCase().equals("all"))     {
//    			cleanDirectory(SongRuntime.outputDir);
//    			cleanDirectory(SongGrabber.outputDir);
//    		} else {
//    			try {
//				    for (int parsedInt = (int) Double.parseDouble(args[1]); parsedInt > 0; parsedInt--) {
//				    	File[] mp4Files = SongGrabber.outputDir.listFiles();
//				    	File[] oggFiles = SongRuntime.outputDir.listFiles();
//				    	File oldestMp4 = null;
//				    	File oldestOgg = null;
//				    	if(mp4Files.length > 0) {
//				    		oldestMp4 = mp4Files[0];
//					    	for(File file : mp4Files) {
//					    		if(file.lastModified() > oldestMp4.lastModified()) {
//					    			oldestMp4 = file;
//					    		}
//					    	}
//				    	}
//				    	if(oggFiles.length > 0) {
//				    		oldestOgg = oggFiles[0];
//					    	for(File file : oggFiles) {
//					    		if(file.lastModified() > oldestOgg.lastModified()) {
//					    			oldestOgg = file;
//					    		}
//					    	}
//				    	}
//				    	
//				    	if(oldestMp4 != null) {
//				    		oldestMp4.delete();
//				    		System.out.println("Deleting " + oldestMp4.getName());
//				    	}
//				    	
//				    	if(oldestOgg != null) {
//					    	oldestOgg.delete();
//						    System.out.println("Deleting " + oldestOgg.getName());
//				    	}
//				    }
//				} catch (NumberFormatException e) {
//					commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid Arguments! Please enter a numeric value!"));
//				}
//    		}
//    	} else {
//			commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Please enter the number of cached files to delete. \n" + EnumChatFormatting.DARK_AQUA + "Write \"all\" to delete all cached files."));
//    	}
//    }
    
    private static void cleanDirectory(File directory) {
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
}
