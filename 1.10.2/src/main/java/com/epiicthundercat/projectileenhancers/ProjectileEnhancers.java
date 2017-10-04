package com.epiicthundercat.projectileenhancers;

import com.epiicthundercat.projectileenhancers.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PEGlobals.ID, version = PEGlobals.VER, name = PEGlobals.NAME)
public class ProjectileEnhancers {
	
	@SidedProxy(clientSide = PEGlobals.CLIENT_PROXY, serverSide = PEGlobals.SERVER_PROXY)
	public static CommonProxy proxy;
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
		
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
		
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
