package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

public class BlockSedHarden extends BlockStoneHarden
{
	public BlockSedHarden(Material material)
	{
		super(material);
		this.dropBlock = TFCBlocks.stoneSedCobble;
		names = Global.STONE_SED;
		icons = new IIcon[names.length];
		looseStart = Global.STONE_SED_START;
		gemChance = 1;
	}
}