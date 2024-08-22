package com.bioxx.tfc.Blocks.Liquids;

import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.material.Material;

import com.bioxx.tfc.api.TFCFluids;
import net.minecraft.world.World;

public class BlockLava extends BlockCustomLiquid
{
	public BlockLava()
	{
		super(TFCFluids.LAVA, Material.lava);
	}
	@Override
	public void convertFlowingToSource(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlock(x, y, z, TFCBlocks.lavaStationary, meta, 2);
	}
}
