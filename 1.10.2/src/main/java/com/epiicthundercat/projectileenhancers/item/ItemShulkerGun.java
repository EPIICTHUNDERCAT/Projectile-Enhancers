package com.epiicthundercat.projectileenhancers.item;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemChorusFruit;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemShulkerGun extends GunBase {

	public ItemShulkerGun(String name) {
		super(name);
		maxStackSize = 1;

	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entity, ItemStack stack) {
		if (stack.hasTagCompound()) {
			stack.getTagCompound().setFloat("inaccuracy", 1.0F * this.itemRand.nextFloat() * this.itemRand.nextFloat());
		}
		return false;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		boolean flag = findShulkerAmmo(playerIn) != null;

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

	protected boolean isEgg(@Nullable ItemStack stack) {
		return stack != null && stack.getItem() instanceof ItemChorusFruit;
	}

	private ItemStack findShulkerAmmo(EntityPlayer player) {
		if (this.isEgg(player.getHeldItem(EnumHand.OFF_HAND))) {
			return player.getHeldItem(EnumHand.OFF_HAND);
		} else if (this.isEgg(player.getHeldItem(EnumHand.MAIN_HAND))) {
			return player.getHeldItem(EnumHand.MAIN_HAND);
		} else {
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				if (this.isEgg(itemstack)) {
					return itemstack;
				}
			}

			return null;
		}
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		if (stack.hasTagCompound()) {
			if (stack.getTagCompound().hasKey("inaccuracy")) {
				if (stack.getTagCompound().getFloat("inaccuracy") > 0.0F) {
					stack.getTagCompound().setFloat("inaccuracy", stack.getTagCompound().getFloat("inaccuracy") - 0.2F);
				}
			} else {
				stack.getTagCompound().setFloat("inaccuracy", 0.98F);
			}

		}
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

		float inaccuracy = 1.0F;

		if (stack.hasTagCompound()) {
			if (stack.getTagCompound().hasKey("inacurracy")) {
				inaccuracy = stack.getTagCompound().getFloat("inaccuracy");
			}

		} else

		{
			stack.setTagCompound(new NBTTagCompound());
		}
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			boolean flag = entityplayer.capabilities.isCreativeMode;
			ItemStack itemstack = findShulkerAmmo(entityplayer);
			if (itemstack != null || flag) {
				if (itemstack == null) {
					itemstack = new ItemStack(Items.CHORUS_FRUIT);
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

						Vec3d look = entityplayer.getLookVec();
						EntityShulkerBullet entityShulkerBullet = new EntityShulkerBullet(worldIn);
						entityShulkerBullet.setPosition(
								 entityplayer.posX + look.xCoord * 3,
								 entityplayer.posY + look.yCoord * -5,
								 entityplayer.posZ + look.zCoord *3);
						entityShulkerBullet.motionX = look.xCoord * 0.8;
						entityShulkerBullet.motionY = look.yCoord * 0.8;
						entityShulkerBullet.motionZ = look.zCoord * 0.8;
						RayTraceResult raytraceresult = new RayTraceResult(entityLiving);
					

						worldIn.spawnEntityInWorld(entityShulkerBullet);

						stack.getTagCompound().setFloat("inaccuracy", 1.0F);
					}
					worldIn.playSound((EntityPlayer) null, entityLiving.posX, entityLiving.posY, entityLiving.posZ,
							SoundEvents.ENTITY_SHULKER_SHOOT, SoundCategory.NEUTRAL, 1.5F, 10.0F);

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
