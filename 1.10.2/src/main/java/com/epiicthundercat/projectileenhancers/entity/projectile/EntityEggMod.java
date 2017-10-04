package com.epiicthundercat.projectileenhancers.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityEggMod extends EntityEgg {

	public EntityEggMod(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}

	public EntityEggMod(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntityEggMod(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {
			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 10.0F);
		}

		if (!this.worldObj.isRemote && this.rand.nextInt(8) == 0) {
			int i = 1;

			if (this.rand.nextInt(32) == 0) {
				i = 4;
			}

			for (int j = 0; j < i; ++j) {
				EntityChicken entitychicken = new EntityChicken(this.worldObj);
				entitychicken.setGrowingAge(-24000);
				entitychicken.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
				this.worldObj.spawnEntityInWorld(entitychicken);
			}
		}

		double d0 = 0.08D;

		for (int k = 0; k < 8; ++k) {
			this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ,
					((double) this.rand.nextFloat() - 0.5D) * 0.08D, ((double) this.rand.nextFloat() - 0.5D) * 0.08D,
					((double) this.rand.nextFloat() - 0.5D) * 0.08D, new int[] { Item.getIdFromItem(Items.EGG) });
		}

		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}
}
