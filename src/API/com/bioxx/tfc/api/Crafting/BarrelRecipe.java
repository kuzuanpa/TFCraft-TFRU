package com.bioxx.tfc.api.Crafting;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Stack;

public class BarrelRecipe extends BarrelCommonRecipe
{
	@Deprecated
	public BarrelRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid)
	{
        super(inputItem, inputFluid, outIS, outputFluid);
	}
	@Deprecated
	public BarrelRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid, int seal)
	{
		super(inputItem, inputFluid, outIS, outputFluid,seal);
	}

	@Deprecated
	@Override
	public BarrelRecipe setMinTechLevel(int t) {
		return (BarrelRecipe) super.setMinTechLevel(t);
	}

	@Deprecated
	public BarrelRecipe setSealedRecipe(boolean b){
		return (BarrelRecipe) super.setSealedRecipe(b);
	}

	@Deprecated
	public BarrelRecipe setRemovesLiquid(boolean b)
	{
		this.removesLiquid = b;
		return (BarrelRecipe)super.setRemovesLiquid(b);
	}
	@Deprecated
	public BarrelRecipe setAllowAnyStack(boolean b){
		return this;
	}
	@Deprecated
	public BarrelRecipe setKeepStackSize(boolean b)
	{
		return this;
	}
}
