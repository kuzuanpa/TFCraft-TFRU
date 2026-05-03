package com.bioxx.tfc.Containers.Slots;

import gregapi.data.MT;
import gregapi.data.OP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAnvilFlux extends Slot
{
	public SlotAnvilFlux(IInventory iinventory, int i, int j, int k)
	{
		super(iinventory, i, j, k);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return OP.dust.mat(MT.CaCO3,0).isItemEqual(itemstack);
	}
}
