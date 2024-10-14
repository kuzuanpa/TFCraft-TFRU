package com.bioxx.tfc.api.Crafting;

import java.util.Stack;

import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class BarrelLiquidToLiquidRecipe implements IBarrelRecipe
{

	public FluidStack recipeInFluid;
	public FluidStack recipeOutFluid;
	public FluidStack inputfluid;
	public int minTechLevel = 1;
	public boolean sealedRecipe = true;
	public boolean removeLiquid = false;
	public BarrelLiquidToLiquidRecipe(FluidStack fluidInBarrel, FluidStack inputfluid, FluidStack outputFluid)
	{
		recipeInFluid = fluidInBarrel;
		recipeOutFluid = outputFluid;
		this.inputfluid = inputfluid;
	}

	@Override
	public Boolean matches(ItemStack item, FluidStack fluid)
	{
		FluidStack itemLiquid = FluidContainerRegistry.getFluidForFilledItem(item);
		if(recipeInFluid != null && recipeInFluid.isFluidEqual(fluid) && itemLiquid != null && itemLiquid.isFluidEqual(inputfluid))
		{
			//Make sure that when we combine the liquids that there is enough room in the barrel for the new liquid to fit
			if(10000-fluid.amount < itemLiquid.amount)
				return false;

			return true;
		}
		return false;
	}

	@Override
	public ItemStack getInItemStack() {
		return null;
	}

	@Override
	public FluidStack getInFluid() {
		return recipeInFluid;
	}

	@Override
	public ItemStack getRecipeOutIS() {
		return null;
	}

	@Override
	public FluidStack getRecipeOutFluid() {
		return recipeOutFluid;
	}

	@Override
	public int getSealTime() {
		return 8;
	}

	@Override
	public IBarrelRecipe setRemovesLiquid(boolean b) {
		removeLiquid=b;
		return this;
	}

	@Override
	public boolean isRemovesLiquid() {
		return false;
	}

	@Override
	public IBarrelRecipe setMinTechLevel(int t) {
		minTechLevel=t;
		return this;
	}

	@Override
	public int getMinTechLevel() {
		return minTechLevel;
	}

	@Override
	public boolean isSealedRecipe() {
		return sealedRecipe;
	}

	@Override
	public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime)
	{
		Stack<ItemStack> result = new Stack<ItemStack>();
		if(inIS != null)
			result.push(inIS.getItem().getContainerItem(inIS));
		else
			result.push(null);

		return result;
	}

	@Override
	public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime)
	{
		if(recipeOutFluid != null)
		{
			FluidStack fs = recipeOutFluid.copy();
			FluidStack itemLiquid = FluidContainerRegistry.getFluidForFilledItem(inIS);
			if(!isRemovesLiquid())
			{
				fs.amount = inFS.amount+itemLiquid.amount;
			}
			else
			{
				fs.amount = ( fs.amount * inFS.amount ) / recipeInFluid.amount;
			}
			return fs;
		}
		return null;
	}

	public FluidStack getInputfluid()
	{
		return inputfluid;
	}
}
