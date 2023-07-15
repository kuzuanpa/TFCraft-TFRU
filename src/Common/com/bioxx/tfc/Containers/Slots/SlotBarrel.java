package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.Items.Tools.ItemTerraTool;
import com.bioxx.tfc.Items.Tools.ItemWeapon;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Interfaces.ISize;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.ArrayList;
import java.util.List;

public class SlotBarrel extends Slot
{
	private EnumSize size = EnumSize.LARGE;

	private List<Item> exceptions;

	public SlotBarrel(IInventory iinventory, int i, int j, int k)
	{
		super(iinventory, i, j, k);
		exceptions = new ArrayList<Item>();
	}
	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		if (itemstack.getItem()!=null&&itemstack.getItem().getUnlocalizedName().equals("gt.multitileentity"))return false;
		if((itemstack.getItem() instanceof ItemTool || itemstack.getItem() instanceof ItemTerraTool || itemstack.getItem() instanceof ItemWeapon ||
				itemstack.getItem() instanceof ItemHoe) && itemstack.getItem() instanceof ISize &&
				((ISize)itemstack.getItem()).getSize(itemstack).stackSize < EnumSize.SMALL.stackSize ) {
			return false;
		}

		boolean except = exceptions.contains(itemstack.getItem());
		if(itemstack.getItem() instanceof ISize && ((ISize)itemstack.getItem()).getSize(itemstack).stackSize >= size.stackSize && !except)
		{
			return true;
		}
		else if (!(itemstack.getItem() instanceof ISize) && !except) {
			return true;
		}

		return false;
	}

	public SlotBarrel setSize(EnumSize s)
	{
		size = s;
		return this;
	}

	public SlotBarrel addItemException(List<Item> ex)
	{
		exceptions.addAll(ex);
		return this;
	}
}
