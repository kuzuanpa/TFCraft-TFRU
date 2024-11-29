package com.bioxx.tfc.Blocks.Terrain;

import java.util.ArrayList;
import java.util.Random;

import com.bioxx.tfc.TileEntities.TEOre;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Constant.Global;

public class BlockOre3 extends BlockOre
{
	public BlockOre3(Material material)
	{
		super(material);
	}
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		//TODO: For old oreGen Compact, will remove at later version
		if (!world.isRemote && world.getBlockMetadata(x,y,z) > 0){
			((TEOre) world.getTileEntity(x, y, z)).droppedOreID = 32+world.getBlockMetadata(x,y,z);
			world.setBlock(x,y,z, TFCBlocks.ore);
			world.setBlockMetadataWithNotify(x,y,z,0, 0);
		}
	}
}
