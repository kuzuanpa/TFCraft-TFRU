package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.TileEntities.TEWorldItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

public class BlockWorldItem extends BlockTerraContainer
{
	public BlockWorldItem()
	{
		super(Material.circuits);
		this.setBlockBounds(0F, 0.00F, 0F, 1F, 0.05F, 1F);
	}

	@Override
	public boolean getBlocksMovement(IBlockAccess bAccess, int x, int y, int z)
	{
		return true;
	}

	/*@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, int x, int y, int z, int l)
	{
	}*/

	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (!(te instanceof IInventory))return new ArrayList<>();

		ArrayList<ItemStack> ret = new ArrayList<>();
		IInventory inv = (IInventory) te;
		for (int i = 0; i< inv.getSizeInventory(); i++) {
			ret.add(inv.getStackInSlot(i));
		}
		if(!(te instanceof TEWorldItem) || ((TEWorldItem) te).snowLevel == 0)return ret;
		ret.add(new ItemStack(Items.snowball, ((TEWorldItem) te).snowLevel, 0));
		return ret;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player)
	{
		return null;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote){
			world.setBlockToAir(x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (world.isAirBlock(x, y - 1, z))
		{
			world.setBlockToAir(x, y, z);
			return;
		}
		if (!world.getBlock(x, y - 1, z).isSideSolid(world, x, y - 1, z, ForgeDirection.UP))
		{
			world.setBlockToAir(x, y, z);
			return;
		}
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
	{
		return false;
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
	{
		return false;
	}
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		// meta  speed
		//    0  0.98   -  one layer
		//    7  0.10   -  eight layers = like leaves
		TileEntity te = world.getTileEntity(x,y,z);
		if(!(te instanceof TEWorldItem))return;
		int meta = ((TEWorldItem) te).snowLevel + 1;
		double speed = 0.98 - 0.02 * meta;
		entity.motionX *= speed;
		entity.motionZ *= speed;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x,y,z);
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, te instanceof TEWorldItem? y + ((TEWorldItem) te).snowLevel* 0.1F : y + 0.1, z + 1);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x,y,z);
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, te instanceof TEWorldItem? y + ((TEWorldItem) te).snowLevel* 0.125F : y + 0.2, z + 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.blockIcon = TFC_Textures.invisibleTexture; // This gets registered in BlockGrass
	}

	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new TEWorldItem();
	}
}
