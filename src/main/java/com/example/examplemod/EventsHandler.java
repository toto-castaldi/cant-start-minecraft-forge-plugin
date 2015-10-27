package com.example.examplemod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EventsHandler {

	@SubscribeEvent
	public void interact(PlayerInteractEvent event) {
		event.entityPlayer.setGameType(GameType.SURVIVAL);
		event.entityPlayer.setHealth(-1);
	}
	
}
