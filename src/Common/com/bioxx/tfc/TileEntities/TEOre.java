package com.bioxx.tfc.TileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;

public class TEOre extends NetworkTileEntity
{
	public int baseBlockID = -1000;
	public int baseBlockMeta = -1000;
	public byte extraData;
	public int droppedOreID = 0;
	public int timer = 0;
	public boolean isScanned = false;

	public TEOre()
	{
		this.shouldSendInitData = true;
	}

	@Override
	public boolean canUpdate()
	{
		return true;
	}

	public void setVisible()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		createDataNBT(nbt);
		this.broadcastPacketInRange(this.createDataPacket(nbt));

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if(isScanned)return;
		timer++;
		if(timer!=10)return;
		scanVisible(worldObj,xCoord,yCoord,zCoord);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		baseBlockID = nbt.getInteger("baseBlockID");
		baseBlockMeta = nbt.getInteger("baseBlockMeta");
		extraData = nbt.getByte("extraData");
		droppedOreID = nbt.getInteger("oreID");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) 
	{
		super.writeToNBT(nbt);
		nbt.setInteger("baseBlockID", baseBlockID);
		nbt.setInteger("baseBlockMeta", baseBlockMeta);
		nbt.setByte("extraData", extraData);
		nbt.setInteger("oreID", droppedOreID);
	}

	@Override
	public Packet getDescriptionPacket()
	{
		if((extraData & 8) != 0 || this.yCoord > 100)
		{
			NBTTagCompound nbt = new NBTTagCompound();
			createInitNBT(nbt);
			return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
		}
		return null;
	}

	@Override
	public void handleInitPacket(NBTTagCompound nbt)
	{
		baseBlockID = nbt.getInteger("id");
		baseBlockMeta = nbt.getInteger("meta");
		extraData = nbt.getByte("extraData");
		if(nbt.hasKey("oreID")) {
			droppedOreID = nbt.getInteger("oreID");
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			worldObj.markBlockRangeForRenderUpdate(xCoord,yCoord,zCoord, xCoord,yCoord,zCoord);
		}

	}

	@Override
	public void handleDataPacket(NBTTagCompound nbt)
	{
		droppedOreID = nbt.getInteger("oreID");
	}

	@Override
	public void createDataNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("oreID", droppedOreID);
	}

	@Override
	public void createInitNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("id", baseBlockID);
		nbt.setInteger("meta", baseBlockMeta);
		nbt.setByte("extraData", extraData);
		if(isExposedToAir(worldObj, xCoord, yCoord, zCoord))nbt.setInteger("oreID", droppedOreID);

	}

	public void scanVisible(World world, int x, int y, int z)
	{
		if (!world.isRemote)
		{
			if( y < 255 && y > 0)
			{
				if(isExposedToAir(world, x, y, z))setVisible();
			}
		}
		isScanned = true;
	}

	public boolean isExposedToAir(World world, int x, int y, int z){
		return!(world.blockExists(x, y-1, z) && world.getBlock(x, y - 1, z).isOpaqueCube() &&
				world.blockExists(x, y+1, z) && world.getBlock(x, y + 1, z).isOpaqueCube() &&
				world.blockExists(x-1, y, z) && world.getBlock(x - 1, y, z).isOpaqueCube() &&
				world.blockExists(x+1, y, z) && world.getBlock(x + 1, y, z).isOpaqueCube() &&
				world.blockExists(x, y, z-1) && world.getBlock(x, y, z - 1).isOpaqueCube() &&
				world.blockExists(x, y, z+1) && world.getBlock(x, y, z + 1).isOpaqueCube() );
	}
}
