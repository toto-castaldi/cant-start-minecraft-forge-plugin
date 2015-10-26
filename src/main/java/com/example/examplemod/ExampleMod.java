package com.example.examplemod;

import net.minecraft.init.Blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		File file = new File("C:\\Users\\goto10\\Documents\\tmp\\test.txt");
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file,true);
			fileWriter.write(String.valueOf(new Date()));
			fileWriter.write("\r\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
