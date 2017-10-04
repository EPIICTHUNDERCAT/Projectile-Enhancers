package com.epiicthundercat.projectileenhancers.item;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemSmallFireballGun extends GunBase {

	public ItemSmallFireballGun(String name) {
		super(name);
		maxStackSize = 1;

	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entity, ItemStack stack) {
		if (stack.hasTagCompound()) {
			// stack.getTagCompound().setFloat("inaccuracy", 1.0F *
			// this.itemRand.nextFloat() * this.itemRand.nextFloat());
		}
		return false;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		boolean flag = findSFireBallAmmo(playerIn) != null;

		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemStackIn, worldIn,
				playerIn, hand, flag);
		if (ret != null)
			return ret;

		if (!playerIn.capabilities.isCreativeMode && !flag) {
			return !flag ? new ActionResult(EnumActionResult.FAIL, itemStackIn)
					: new ActionResult(EnumActionResult.PASS, itemStackIn);
		} else {
			playerIn.setActiveHand(hand);
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {

	}

	protected boolean isLargeFireball(@Nullable ItemStack stack) {
		return stack != null && stack.getItem() instanceof ItemFireball;
	}

	private ItemStack findSFireBallAmmo(EntityPlayer player) {
		if (this.isLargeFireball(player.getHeldItem(EnumHand.OFF_HAND))) {
			return player.getHeldItem(EnumHand.OFF_HAND);
		} else if (this.isLargeFireball(player.getHeldItem(EnumHand.MAIN_HAND))) {
			return player.getHeldItem(EnumHand.MAIN_HAND);
		} else {
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				if (this.isLargeFireball(itemstack)) {
					return itemstack;
				}
			}

			return null;
		}
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		/*
		 * if (stack.hasTagCompound()) { if
		 * (stack.getTagCompound().hasKey("inaccuracy")) { if
		 * (stack.getTagCompound().getFloat("inaccuracy") > 0.0F) {
		 * stack.getTagCompound().setFloat("inaccuracy",
		 * stack.getTagCompound().getFloat("inaccuracy") - 0.2F); } } else {
		 * stack.getTagCompound().setFloat("inaccuracy", 0.98F); }
		 * 
		 * }
		 */
	}

	public static float getEggVelocity(int charge) {
		float f = (float) charge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;

		if (f > 1.0F) {
			f = 1.0F;
		}

		return f;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {

		/*
		 * float inaccuracy = 1.0F;
		 * 
		 * if (stack.hasTagCompound()) { if
		 * (stack.getTagCompound().hasKey("inacurracy")) { inaccuracy =
		 * stack.getTagCompound().getFloat("inaccuracy"); }
		 * 
		 * } else
		 * 
		 * { stack.setTagCompound(new NBTTagCompound()); }
		 */
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			boolean flag = entityplayer.capabilities.isCreativeMode;
			ItemStack itemstack = findSFireBallAmmo(entityplayer);
			if (itemstack != null || flag) {
				if (itemstack == null) {
					itemstack = new ItemStack(Items.FIRE_CHARGE);
				}
				int i = this.getMaxItemUseDuration(stack) - timeLeft;
				i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, (EntityPlayer) entityLiving,
						i, itemstack != null || flag);
				if (i < 0)
					return;

				float f = getEggVelocity(i);
				if ((double) f >= 0.1D) {

					boolean flag1 = entityplayer.capabilities.isCreativeMode;
					if (!worldIn.isRemote) {
						RayTraceResult raytraceresult = new RayTraceResult(entityLiving);
						Vec3d look = entityplayer.getLookVec();
						EntitySmallFireball entitysmallfireball = new EntitySmallFireball(worldIn,
								(EntityLivingBase) entityLiving, 1,1,1);
						entitysmallfireball.setPosition(
								 entityplayer.posX + look.xCoord * 2,
								 entityplayer.posY + look.yCoord * -4,
								 entityplayer.posZ + look.zCoord *0);
						entitysmallfireball.accelerationX = look.xCoord * 0.3;
						entitysmallfireball.accelerationY = look.yCoord * 0.3;
						entitysmallfireball.accelerationZ = look.zCoord * 0.3;
						worldIn.spawnEntityInWorld(entitysmallfireball);

						// stack.getTagCompound().setFloat("inaccuracy", 1.0F);
					}
					worldIn.playSound((EntityPlayer) null, entityLiving.posX, entityLiving.posY, entityLiving.posZ,
							SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.NEUTRAL, 1.5F, 10.0F);

					if (!flag1) {
						--itemstack.stackSize;

						if (itemstack.stackSize == 0) {
							entityplayer.inventory.deleteStack(itemstack);
						}
					}
				}
			}
		}
	}
}
