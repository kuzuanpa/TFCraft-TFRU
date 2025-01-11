package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.api.TileEntities.TEFireEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.code.TagData;
import gregapi.data.TD;
import gregapi.tileentity.energy.ITileEntityEnergy;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TEBellows extends NetworkTileEntity implements ITileEntityEnergy
{
	private static final int BLOCK_MAP[][] = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
	//private static final int blockMap2[][] = { { 0, 2 }, { -2, 0 }, { 0, -2 }, { 2, 0 } };
	public boolean shouldBlow;
	public int blowTimer;
	public int blowDirection;

	@Override
	public void updateEntity()
	{
		if(shouldBlow)
		{
			if(blowDirection == 0)
			{
				blowTimer++;
				if(worldObj.isRemote)
					generateSmoke();
				if(blowTimer == 5)
				{
					blowDirection = 1;
					moveEntitys();
					if(!worldObj.isRemote)
						giveAir();
				}
			}
			else
			{
				blowTimer--;
				if(blowTimer == -3)
				{
					blowDirection = 0;
					shouldBlow = false;
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
	}

	public void generateSmoke()
	{
		int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		int x = BLOCK_MAP[meta][0];
		int z = BLOCK_MAP[meta][1];
		Random random = new Random();

		float f = (float) xCoord + x + 0.5F;
		float f1 = yCoord + 0.1F + random.nextFloat() * 6F / 16F;
		float f2 = (float) zCoord + z + 0.5F;
		//float f3 = 0.82F;
		float f4 = random.nextFloat() * 0.6F;
		float f5 = random.nextFloat() * -0.6F;
		//float f6 = random.nextFloat() * -0.6F;
		worldObj.spawnParticle("smoke", f + f4 - 0.3F, f1, f2 + f5 + 0.3F, 0.0D, 0.0D, 0.0D);
	}

	public void moveEntitys(){
		int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		int x = BLOCK_MAP[meta][0];
		int z = BLOCK_MAP[meta][1];
		List<Entity> entitys = getWorldObj().getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord-0.2, zCoord,xCoord +1+x, yCoord+1, zCoord +1+z));
		entitys.forEach(entity -> {entity.motionX+=x*0.5;entity.motionZ+=z*0.5;});
	}
	public void giveAir()
	{
		int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		int x = BLOCK_MAP[meta][0];
		int z = BLOCK_MAP[meta][1];
		if (worldObj.blockExists(xCoord + x, yCoord, zCoord + z))
		{
			TileEntity te = worldObj.getTileEntity(xCoord + x, yCoord, zCoord + z);
			TileEntity te2 = worldObj.getTileEntity(xCoord + x, yCoord - 1, zCoord + z);
			TEFireEntity tileentityfirepit = null;

			if (te instanceof TEFireEntity)
				tileentityfirepit = (TEFireEntity) te;
			else if (te2 instanceof TEForge)
				tileentityfirepit = (TEFireEntity) te2;

			if (tileentityfirepit != null)
				tileentityfirepit.receiveAirFromBellows();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		shouldBlow = nbt.getBoolean("shouldBlow");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setBoolean("shouldBlow", shouldBlow);
	}

	@Override
	public void handleInitPacket(NBTTagCompound nbt) {
		shouldBlow = nbt.getBoolean("shouldBlow");
	}

	@Override
	public void handleDataPacket(NBTTagCompound nbt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createDataNBT(NBTTagCompound nbt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createInitNBT(NBTTagCompound nbt) {
		nbt.setBoolean("shouldBlow", shouldBlow);		
	}


	@Override
	public Collection<TagData> getEnergyTypes(byte b) {
		return TD.Energy.KU.AS_LIST;
	}

	@Override
	public boolean isEnergyType(TagData tagData, byte b, boolean b1) {
		return tagData.equals(TD.Energy.KU);
	}

	@Override
	public boolean isEnergyAcceptingFrom(TagData tagData, byte b, boolean b1) {
		return true;
	}

	@Override
	public boolean isEnergyEmittingTo(TagData tagData, byte b, boolean b1) {
		return false;
	}

	@Override
	public long getEnergyDemanded(TagData tagData, byte b, long l) {
		return 4;
	}

	@Override
	public long doEnergyInjection(TagData tagData, byte b, long l, long l1, boolean b1) {
		if(!tagData.equals(TD.Energy.KU)|| l < 8)return 0;
		if(l > 16)return l1;
		if(worldObj.getTotalWorldTime()%4 == 0) worldObj.getBlock(xCoord,yCoord,zCoord).onBlockActivated(worldObj, xCoord, yCoord, zCoord, null, 0, 0, 0, 0);
		return 1;
	}

	@Override
	public long doEnergyExtraction(TagData tagData, byte b, long l, long l1, boolean b1) {
		return 0;
	}

	@Override
	public long getEnergyOffered(TagData tagData, byte b, long l) {
		return 0;
	}

	@Override
	public long getEnergySizeInputMin(TagData tagData, byte b) {
		return 4;
	}

	@Override
	public long getEnergySizeOutputMin(TagData tagData, byte b) {
		return 0;
	}

	@Override
	public long getEnergySizeInputRecommended(TagData tagData, byte b) {
		return 8;
	}

	@Override
	public long getEnergySizeOutputRecommended(TagData tagData, byte b) {
		return 0;
	}

	@Override
	public long getEnergySizeInputMax(TagData tagData, byte b) {
		return 16;
	}

	@Override
	public long getEnergySizeOutputMax(TagData tagData, byte b) {
		return 0;
	}

	@Override
	public boolean isDead() {
		return false;
	}
}
