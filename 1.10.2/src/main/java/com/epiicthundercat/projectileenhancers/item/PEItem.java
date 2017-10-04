package com.epiicthundercat.projectileenhancers.item;

import com.epiicthundercat.projectileenhancers.creativetab.PECreativeTab;
import com.epiicthundercat.projectileenhancers.init.PEItems;

import net.minecraft.item.Item;

public class PEItem extends Item {
	public PEItem(String name, int maxStack) {

		setRegistryName(name.toLowerCase());
		setUnlocalizedName(name.toLowerCase());
		setCreativeTab(PECreativeTab.PETabs);
		addToItems(this);
	}

	private void addToItems(Item item) {

		PEItems.items.add(item);

	}

}
