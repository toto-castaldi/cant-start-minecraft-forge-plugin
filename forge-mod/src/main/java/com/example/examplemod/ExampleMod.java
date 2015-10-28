package com.example.examplemod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "stopPlayMod";
    public static final String VERSION = "1.0";

    private EventsHandler events = new EventsHandler();

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {

        ExternalSemaphore.start();

    	FMLCommonHandler.instance().bus().register(events);
    	MinecraftForge.EVENT_BUS.register(events);
    }
}
