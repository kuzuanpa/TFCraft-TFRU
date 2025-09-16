package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

public class BlockIgExHarden extends BlockStoneHarden
{
	public BlockIgExHarden(Material material)
	{
		super(material);
		this.dropBlock = TFCBlocks.stoneIgExCobble;
		names = Global.STONE_IGEX;
		icons = new IIcon[names.length];
		looseStart = Global.STONE_IGEX_START;
		gemChance = 0;
	}
}
