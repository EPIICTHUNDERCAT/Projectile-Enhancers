package com.epiicthundercat.projectileenhancers.creativetab;

import com.epiicthundercat.projectileenhancers.init.PEItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PECreativeTab extends CreativeTabs{
	public PECreativeTab(int index, String label) {
		super(index, label);
	}

	public static final PECreativeTab PETabs = new PECreativeTab(CreativeTabs.getNextID(), "petabs") {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return PEItems.egg_gun;
		}
	};
	
	@Override
	public Item getTabIconItem() {
		return null;
	}

}
