package com.epiicthundercat.projectileenhancers.proxy;

import com.epiicthundercat.projectileenhancers.init.PEItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent preEvent) {
		register(preEvent);
		// TileEntityRegistry.registerTile();

		// NGConfig.config(preEvent);

	}

	public void init(FMLInitializationEvent event) {
		registerRenders(event);
		// ModEntities.init();

	}

	private void register(FMLPreInitializationEvent preEvent) {
		 PEItems.register(preEvent);
		// RBlocks.register(preEvent);
		// RRecipes.register(preEvent);
		registerEntities(preEvent);
		// GameRegistry.registerWorldGenerator(new WorldGenOreOnReef(),1);
		// MinecraftForge.EVENT_BUS.register(new REventHandler());

		// NetworkRegistry.INSTANCE.registerGuiHandler(Raft.instance, new
		// RGuiHandler());

	}

	public void registerRenders(FMLInitializationEvent event) {

	}

	public void registerRender(FMLInitializationEvent event) {
	}

	public void registerEntities(FMLPreInitializationEvent preEvent) {
	}
}
