package com.bioxx.tfc.Blocks.Liquids;

import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class BlockFreshWater extends BlockCustomLiquid
{
	public BlockFreshWater(Fluid fluid)
	{
		super(fluid, Material.water);
	}

	@Override
	public void convertFlowingToSource(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlock(x, y, z, TFCBlocks.freshWaterStationary, meta, 2);
	}
}
