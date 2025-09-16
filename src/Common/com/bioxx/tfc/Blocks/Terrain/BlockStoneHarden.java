package com.bioxx.tfc.Blocks.Terrain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class BlockStoneHarden extends BlockStone
{
	public BlockStoneHarden(Material material) {
		super(material);
	}

	@Override
	public Boolean hasNaturalSupport(World world, int x, int y, int z, int range, float chance) {
		return true;
	}

	@Override
	public Boolean tryToCollapse(World world, int x, int y, int z, float collapseChance) {
		return false;
	}
	@SideOnly(Side.CLIENT)
	@Override
	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List list)
	{
		for(int i = 0; i < names.length; i++)
			list.add(new ItemStack(this,1,i));
	}
}
