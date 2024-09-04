package com.bioxx.tfc.Entities;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.*;
import net.minecraft.world.World;

import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Interfaces.ICausesDamage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

import java.nio.ByteBuffer;
import java.util.List;

public class EntityJavelin extends EntityProjectileTFC implements ICausesDamage
{
	private int field_145791_d = -1;
	private int field_145792_e = -1;
	private int field_145789_f = -1;
	public double returnToPosX, returnToPosY, returnToPosZ;
	private Block inGroundBlock;
	private int inGroundBlockMeta;
	private boolean inGround;
	private int ticksInGround;
	private int ticksInAir;
	private int knockbackStrength;
	public byte piercingCount=0;
	public byte fidelityLevel=0;
	public EntityJavelin(World par1World)
	{
		super(par1World);
	}

	public EntityJavelin(World par1World, double i, double j, double k)
	{
		super(par1World, i , j, k);
	}
	public EntityJavelin(World world, EntityLivingBase shooter, EntityLivingBase victim, float force, float forceVariation)
	{
		super(world, shooter, victim, force, forceVariation);
	}
	public EntityJavelin(World par1World, EntityLivingBase shooter, float force)
	{
		super(par1World, shooter, force);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(12,"false");
		this.dataWatcher.addObject(13,0);
		this.dataWatcher.addObject(14,0);
		this.dataWatcher.addObject(15,0);
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player)
	{
		if (!this.worldObj.isRemote)
		{
			NBTTagCompound nbt = new NBTTagCompound();
			this.writeToNBT(nbt);

			if(!inGround&&!isReturning || (pickupItemStack==null|| pickupItemStack.stackSize<=0))return;

			ItemStack is = pickupItemStack;
			if (duraBuff != 0)
				AnvilManager.setDurabilityBuff(is, duraBuff);
			if (damageBuff != 0)
				AnvilManager.setDamageBuff(is, damageBuff);

			EntityItem ei = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, is);

			if (this.canBePickedUp == 1)
			{
				EntityItemPickupEvent event = new EntityItemPickupEvent(player, ei);

				if (MinecraftForge.EVENT_BUS.post(event))
					return;
			}

			ItemStack itemstack = ei.getEntityItem();

			boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && player.capabilities.isCreativeMode;
			if (itemstack.stackSize <= 0)
				flag = true;
			else if (this.canBePickedUp == 1 && !player.inventory.addItemStackToInventory(itemstack))
				flag = false;

			if (flag)
			{
				this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);

				player.onItemPickup(this, 1);
				this.setDead();
			}

		}
	}

	public void onUpdate() {
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0 / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f) * 180.0 / Math.PI);
		}
		Block block = this.worldObj.getBlock(this.field_145791_d, this.field_145792_e, this.field_145789_f);

		updateInGroundState(block);

		if (this.arrowShake > 0) {
			--this.arrowShake;
		}

		if (this.inGround) {
			if(fidelityLevel>0&&ticksInGround>20&&!isReturning) {
				isReturning=true;
				this.syncData();
			}

			int j = this.worldObj.getBlockMetadata(this.field_145791_d, this.field_145792_e, this.field_145789_f);
			if (block == this.inGroundBlock && j == this.inGroundBlockMeta) {
				++this.ticksInGround;
				if (this.ticksInGround == 1200) {
					this.setDead();
				}
			} else {
				this.inGround = false;
				this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
				this.ticksInGround = 0;
				this.ticksInAir = 0;
			}
		}
		if(worldObj.isRemote&&!isReturning) isReturning = Boolean.parseBoolean(this.dataWatcher.getWatchableObjectString(12));
		if(!inGround || isReturning)onFlying();

	}

	public void updateInGroundState(Block block){
		if (block.getMaterial() != Material.air) {
			block.setBlockBoundsBasedOnState(this.worldObj, this.field_145791_d, this.field_145792_e, this.field_145789_f);
			AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, this.field_145791_d, this.field_145792_e, this.field_145789_f);
			if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ))) {
				this.inGround = true;
			}
		}
	}

	public void onFlying(){
			++this.ticksInAir;
			Vec3 vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3 vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec31, vec3, false, true, false);
			vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			if (movingobjectposition != null) {
				vec3 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
			}

			Entity entity = null;
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0, 1.0, 1.0));
			double d0 = 0.0;

			int i;
			float f1;
			for(i = 0; i < list.size(); ++i) {
				Entity entity1 = (Entity)list.get(i);
				if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5 && this.fidelityLevel <= 0)) {
					f1 = 0.3F;
					AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand((double)f1, (double)f1, (double)f1);
					MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);
					if (movingobjectposition1 != null) {
						double d1 = vec31.distanceTo(movingobjectposition1.hitVec);
						if (d1 < d0 || d0 == 0.0) {
							entity = entity1;
							d0 = d1;
						}
					}
				}
			}

			if (entity != null) {
				movingobjectposition = new MovingObjectPosition(entity);
			}

			if (movingobjectposition != null && movingobjectposition.entityHit instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;
				if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer)) {
					movingobjectposition = null;
				}
			}


			float f4;
			if (movingobjectposition != null) {
				if (movingobjectposition.entityHit != null) onHitEntity(movingobjectposition);
				else onHitBlock(movingobjectposition);
			}

			if (this.getIsCritical()) {
				for(i = 0; i < 4; ++i) {
					this.worldObj.spawnParticle("crit", this.posX + this.motionX * (double)i / 4.0, this.posY + this.motionY * (double)i / 4.0, this.posZ + this.motionZ * (double)i / 4.0, -this.motionX, -this.motionY + 0.2, -this.motionZ);
				}
			}

			handleFidelity();

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;

			this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0 / Math.PI);


			while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
				this.prevRotationPitch += 360.0F;
			}

			while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
				this.prevRotationYaw -= 360.0F;
			}

			while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
			float f3 = 0.99F;
			f1 = 0.05F;
			if (this.isInWater()) {
				for(int l = 0; l < 4; ++l) {
					f4 = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)f4, this.posY - this.motionY * (double)f4, this.posZ - this.motionZ * (double)f4, this.motionX, this.motionY, this.motionZ);
				}
				f3 = 0.8F;
			}

			if (this.isWet()) this.extinguish();

			this.motionX *= f3;
			this.motionY *= f3;
			this.motionZ *= f3;
			this.motionY -= f1;
			this.setPosition(this.posX, this.posY, this.posZ);
			//Return from void
			if(fidelityLevel>=3&&posY<0)isReturning=true;
			this.func_145775_I();
	}

	public void onHitBlock(MovingObjectPosition hitBlockPos){
		this.field_145791_d = hitBlockPos.blockX;
		this.field_145792_e = hitBlockPos.blockY;
		this.field_145789_f = hitBlockPos.blockZ;
		this.inGroundBlock = this.worldObj.getBlock(this.field_145791_d, this.field_145792_e, this.field_145789_f);
		this.inGroundBlockMeta = this.worldObj.getBlockMetadata(this.field_145791_d, this.field_145792_e, this.field_145789_f);
		this.motionX = ((float)(hitBlockPos.hitVec.xCoord - this.posX));
		this.motionY = ((float)(hitBlockPos.hitVec.yCoord - this.posY));
		this.motionZ = ((float)(hitBlockPos.hitVec.zCoord - this.posZ));
		float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		this.posX -= this.motionX / (double)f2 * 0.05000000074505806;
		this.posY -= this.motionY / (double)f2 * 0.05000000074505806;
		this.posZ -= this.motionZ / (double)f2 * 0.05000000074505806;
		this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
		this.inGround = true;
		this.arrowShake = 7;
		this.setIsCritical(false);
		if (this.inGroundBlock.getMaterial() != Material.air) {
			this.inGroundBlock.onEntityCollidedWithBlock(this.worldObj, this.field_145791_d, this.field_145792_e, this.field_145789_f, this);
		}
	}

	public void onHitEntity(MovingObjectPosition hitEntityPos){

		float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		int k = MathHelper.ceiling_double_int((double)f2 * this.getDamage());
		if (this.getIsCritical()) {
			k += this.rand.nextInt(k / 2 + 2);
		}

		DamageSource damagesource = null;
		if (this.shootingEntity == null) {
			damagesource = DamageSource.causeArrowDamage(this, this);
		} else {
			damagesource = DamageSource.causeArrowDamage(this, this.shootingEntity);
		}

		if (this.isBurning() && !(hitEntityPos.entityHit instanceof EntityEnderman)) {
			hitEntityPos.entityHit.setFire(5);
		}

		if (hitEntityPos.entityHit.attackEntityFrom(damagesource, (float)k)) {
			if (hitEntityPos.entityHit instanceof EntityLivingBase) {
				EntityLivingBase entitylivingbase = (EntityLivingBase)hitEntityPos.entityHit;
				//if (!this.worldObj.isRemote) {
				//	entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
				//}

				if (this.knockbackStrength > 0) {
					float f4 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
					if (f4 > 0.0F) {
						hitEntityPos.entityHit.addVelocity(this.motionX * (double)this.knockbackStrength * 0.6000000238418579 / (double)f4, 0.1, this.motionZ * (double)this.knockbackStrength * 0.6000000238418579 / (double)f4);
					}
				}

				if (this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
					EnchantmentHelper.func_151384_a(entitylivingbase, this.shootingEntity);
					EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, entitylivingbase);
				}

				if (this.shootingEntity != null && hitEntityPos.entityHit != this.shootingEntity && hitEntityPos.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
					((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
				}
			}

			this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

		} else {
			if(piercingCount==0) {
				this.motionX *= -0.10000000149011612;
				this.motionY *= -0.10000000149011612;
				this.motionZ *= -0.10000000149011612;
				this.rotationYaw += 180.0F;
				this.prevRotationYaw += 180.0F;
			}else {
				piercingCount--;
			}
			this.ticksInAir = 0;
		}
	}
	public boolean isReturning =false;

	public void handleFidelity(){
		if(!isReturning)return;
		if(shootingEntity!=null){
			returnToPosX=shootingEntity.posX;
		 	returnToPosY=shootingEntity.posY;
			returnToPosZ=shootingEntity.posZ;
		}
		else
		{
			returnToPosX = this.dataWatcher.getWatchableObjectInt(13) +0.5;
			returnToPosY = this.dataWatcher.getWatchableObjectInt(14)+0.5;
			returnToPosZ = this.dataWatcher.getWatchableObjectInt(15)+0.5;
		}
		this.motionX+=(returnToPosX - this.posX)/200;
		this.motionY+=0.06+(returnToPosY - this.posY)/200;
		this.motionZ+=(returnToPosZ - this.posZ)/200;
		if(!worldObj.isRemote)syncData();
	}
	public void syncData()
	{
		if(dataWatcher != null)
		{
			if(!this.worldObj.isRemote)
			{
				this.dataWatcher.updateObject(12, Boolean.toString(isReturning));
				this.dataWatcher.updateObject(13, (int)returnToPosX);
				this.dataWatcher.updateObject(14, (int)returnToPosY);
				this.dataWatcher.updateObject(15, (int)returnToPosZ);
			}
		}
	}
	@Override
	public EnumDamageType getDamageType()
	{
		return EnumDamageType.PIERCING;
	}

	public void setPiercingCount(int count) {
		this.piercingCount = (byte) count;
	}
	public void setFidelityLevel(int level) {
		this.fidelityLevel = (byte) level;
	}
	public void setKnockbackStrength(int p_70240_1_) {
		this.knockbackStrength = p_70240_1_;
	}
	public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		p_70014_1_.setShort("xTile", (short)this.field_145791_d);
		p_70014_1_.setShort("yTile", (short)this.field_145792_e);
		p_70014_1_.setShort("zTile", (short)this.field_145789_f);
		p_70014_1_.setShort("life", (short)this.ticksInGround);
		p_70014_1_.setByte("shake", (byte)this.arrowShake);
		p_70014_1_.setByte("inGround", (byte)(this.inGround ? 1 : 0));
		p_70014_1_.setByte("pickup", (byte)this.canBePickedUp);
		p_70014_1_.setInteger("returnToPosX", (int)returnToPosX);
		p_70014_1_.setInteger("returnToPosY", (int)returnToPosY);
		p_70014_1_.setInteger("returnToPosZ", (int)returnToPosZ);
		if(pickupItemStack!=null) {
			p_70014_1_.setString("pickupItemID", Item.itemRegistry.getNameForObject(pickupItemStack.getItem()));
			p_70014_1_.setInteger("pickupItemDamage", pickupItemStack.getItemDamage());
			p_70014_1_.setTag("pickupItemNBT", pickupItemStack.getTagCompound());
		}
	}

	public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		this.field_145791_d = p_70037_1_.getShort("xTile");
		this.field_145792_e = p_70037_1_.getShort("yTile");
		this.field_145789_f = p_70037_1_.getShort("zTile");
		this.ticksInGround = p_70037_1_.getShort("life");
		this.returnToPosX = p_70037_1_.getShort("returnToPosX");
		this.returnToPosY = p_70037_1_.getShort("returnToPosY");
		this.returnToPosZ = p_70037_1_.getShort("returnToPosZ");
		this.arrowShake = p_70037_1_.getByte("shake") & 255;
		this.inGround = p_70037_1_.getByte("inGround") == 1;
		if(p_70037_1_.hasKey("pickupItemID")) {
			this.pickupItemStack = new ItemStack((Item) Item.itemRegistry.getObject(p_70037_1_.getString("pickupItemID")),1, p_70037_1_.getInteger("pickupItemDamage"));
			pickupItemStack.setTagCompound(p_70037_1_.getCompoundTag("pickupItemNBT"));
		}
		if (p_70037_1_.hasKey("pickup", 99)) {
			this.canBePickedUp = p_70037_1_.getByte("pickup");
		} else if (p_70037_1_.hasKey("player", 99)) {
			this.canBePickedUp = p_70037_1_.getBoolean("player") ? 1 : 0;
		}

	}
}
