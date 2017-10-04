package com.epiicthundercat.projectileenhancers.init;

import java.util.ArrayList;
import java.util.List;

import com.epiicthundercat.projectileenhancers.item.ItemDragonFireballGun;
import com.epiicthundercat.projectileenhancers.item.ItemEggGun;
import com.epiicthundercat.projectileenhancers.item.ItemLargeFireballGun;
import com.epiicthundercat.projectileenhancers.item.ItemShulkerGun;
import com.epiicthundercat.projectileenhancers.item.ItemSmallFireballGun;
import com.epiicthundercat.projectileenhancers.item.ItemSnowballGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PEItems {
	public static List<Item> items = new ArrayList();

	public static Item egg_gun = new ItemEggGun("egg_gun");
	
	
	public static Item snowball_gun = new ItemSnowballGun("snowball_gun");
	
	public static Item sfireball_gun = new ItemSmallFireballGun("sfireball_gun");
	
	public static Item lfireball_gun = new ItemLargeFireballGun("lfireball_gun");
	
public static Item dragonfireball_gun = new ItemDragonFireballGun("dragonfireball_gun");
	
	public static Item shulker_gun = new ItemShulkerGun("shulker_gun");
	
	//public static Item lfireball_gun = new ItemLargeFireballGun("lfireball_gun");
	


	
	private static List<Item> getItems() {
		return items;
	}

	
	public static void register(FMLPreInitializationEvent preEvent) {
		for (Item item : getItems()) {
			GameRegistry.register(item);
		}
	}

	public static void registerRender(FMLInitializationEvent event) {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		for (Item item : getItems()) {
			renderItem.getItemModelMesher().register(item, 0,
					new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
		}
	}

}
