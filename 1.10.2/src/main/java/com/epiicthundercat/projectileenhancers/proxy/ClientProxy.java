package com.epiicthundercat.projectileenhancers.proxy;

import com.epiicthundercat.projectileenhancers.init.PEItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	public void preInit(FMLPreInitializationEvent preEvent) {
		super.preInit(preEvent);
		// ModEntities.initModels();
		// RBlocks.registerModels();
		// REventHandler.registerItemRenderers();

	}

	public void init(FMLInitializationEvent event) {
		super.init(event);
		RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
		RenderManager rm = Minecraft.getMinecraft().getRenderManager();

		// rm.entityRenderMap.put(EntitySharkFemale.class, new
		// RenderEntitySharkFemale(rm));

	}

	@Override
	public void registerRenders(FMLInitializationEvent event) {
		 PEItems.registerRender(event);
		// RBlocks.registerRender(event);
	}

	@Override
	public void registerEntities(FMLPreInitializationEvent preEvent) {
		super.registerEntities(preEvent);
		/*
		 * RenderingRegistry.registerEntityRenderingHandler(EntityHook.class,
		 * new IRenderFactory<EntityHook>() {
		 * 
		 * @Override public RenderHook createRenderFor (RenderManager manager) {
		 * return new RenderHook(manager, RItems.hook_part); } });
		 */
	}

}
