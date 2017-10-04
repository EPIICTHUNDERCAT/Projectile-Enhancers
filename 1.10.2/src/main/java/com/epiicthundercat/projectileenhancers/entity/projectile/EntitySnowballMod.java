package com.epiicthundercat.projectileenhancers.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntitySnowballMod extends EntitySnowball {

	public EntitySnowballMod(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}

	public EntitySnowballMod(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntitySnowballMod(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {
			int i = 0;

			if (result.entityHit instanceof EntityBlaze) {
				i = 15;
			} else if (result.entityHit instanceof EntityLivingBase) {
				i = 10;
			}

			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) i);
		}

		for (int j = 0; j < 8; ++j) {
			this.worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D,
					new int[0]);
		}

		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}
}
