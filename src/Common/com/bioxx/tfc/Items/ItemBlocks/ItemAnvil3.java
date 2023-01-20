package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Metal;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemAnvil3 extends ItemAnvil
{
	public ItemAnvil3(Block par1)
	{
		super(par1);
		this.metaNames = new String[]{ "Mana Steel"};
	}
	
	@Override
	public Metal getMetalType(ItemStack is) 
	{
		int meta = is.getItemDamage();
		return Global.STEEL;
	}
}
