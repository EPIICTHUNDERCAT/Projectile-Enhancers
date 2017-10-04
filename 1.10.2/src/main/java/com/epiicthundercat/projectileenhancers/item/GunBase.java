package com.epiicthundercat.projectileenhancers.item;

import com.epiicthundercat.projectileenhancers.creativetab.PECreativeTab;
import com.epiicthundercat.projectileenhancers.init.PEItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GunBase extends ItemBow {
	public static boolean entity;
	public static boolean player;
	public static boolean block;
	public EntityLivingBase shootingEntity;
	public DamageSource sourceDamage;
	BlockPos posB;
	EntityPlayer playerIn;
	

	public GunBase(String name) {
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(PECreativeTab.PETabs);
		addToItems(this);
		}

		private void addToItems(Item item) {

			PEItems.items.add(item);

		}
		 
	public GunBase(World worldIn, EntityLivingBase shooter) {
		this(worldIn, shooter.posX, shooter.posY + (double) shooter.getEyeHeight() - 0.10000000149011612D,
				shooter.posZ);
		this.shootingEntity = shooter;
	}

	public GunBase(World worldIn, DamageSource source, BlockPos pos, EntityPlayer player) {
		super();
		sourceDamage = source;
		posB = pos;
		playerIn = player;

	}

	@SideOnly(Side.CLIENT)
	public GunBase(World worldIn, double x, double y, double z) {
		super();
	}

	@Override
	public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos,
			EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		super.onItemUseFirst(stack, player, world, pos, side, hitX, hitY, hitZ, hand);

		return EnumActionResult.SUCCESS;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {

		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}

	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {

	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entity, ItemStack stack) {
		if (stack.hasTagCompound()) {
			stack.getTagCompound().setFloat("inaccuracy", 1.0F * this.itemRand.nextFloat());

		}
		return false;
	}

	protected void onImpact(RayTraceResult result, World world, Entity entity, EntityLivingBase Ebase) {

	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {

	}
}
