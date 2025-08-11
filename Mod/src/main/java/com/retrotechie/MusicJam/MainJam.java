package com.retrotechie.MusicJam;



import java.io.IOException;

import com.retrotechie.MusicJam.Commands.ModCommandManager;
import com.retrotechie.resources.ResourceLoader;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MainJam.MODID, version = MainJam.VERSION)
public class MainJam
{
	//Create Mod Instance
	@Instance("Music Mod")
	public static MainJam instance;
	
    public static final String MODID = "Music Mod";
    public static final String VERSION = "1.0.1";
    
    public static boolean doPlay = true;
    public static float musicVolume = 1;
    
    public static String pathToYTDLP;
    public static String pathToFFMPEG;
    
    public static int queuePos = 0;
    
	//Check OS type:
    public static String os = System.getProperty("os.name").toLowerCase();



    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	//Run the setup for YTDLP
    	try {
    		pathToYTDLP = ResourceLoader.extractYTDLP();
    		System.out.println("YT-DLP Loaded Successfully!");
    	} catch(IOException | InterruptedException e) {
    		e.printStackTrace();
    	}
    	
    	//Run the setup for FFMPEG
    	try {
    		pathToFFMPEG = ResourceLoader.extractFFmpeg();
    		System.out.println("FFMPEG Loaded Successfully!");
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	System.out.println("PreInit Complete!");
    }
    
    
    
    
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        //System.out.println("[" + MODID + " " + VERSION + " - LOADING" +  "]" + " Initializing " + MODID + " On Version " + VERSION);
        FMLLog.getLogger().info("[" + MODID + " " + VERSION + " - LOADING" +  "]" + " Initializing " + MODID + " On Version " + VERSION);
        ClientCommandHandler.instance.registerCommand(new ModCommandManager());
    }
    
    
}
