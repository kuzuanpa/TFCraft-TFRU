package com.bioxx.tfc.api.Crafting;

import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidStack;

import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Interfaces.IFood;

import java.util.Stack;

public class BarrelBriningRecipe implements IBarrelRecipe
{
	public FluidStack recipeInFluid;
	public FluidStack recipeOutFluid;
	public boolean sealedRecipe = true;
	public BarrelBriningRecipe(FluidStack inputFluid, FluidStack outputFluid)
	{
		recipeInFluid=inputFluid;
		recipeOutFluid=outputFluid;
	}

	@Override
	public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime) {
		return null;
	}

	@Override
	public Boolean matches(ItemStack item, FluidStack fluid)
	{
		if(item != null && item.getItem() instanceof IFood && !Food.isBrined(item))
		{
			float w = Food.getWeight(item);
            return fluid.isFluidEqual(recipeInFluid) && w <= (fluid.amount * 1F / this.recipeInFluid.amount);
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
		return 4;
	}

	@Override
	public boolean isRemovesLiquid() {
		return false;
	}

	@Override
	public IBarrelRecipe setMinTechLevel(int t) {
		return null;
	}

	@Override
	public int getMinTechLevel() {
		return 0;
	}

	@Override
	public boolean isSealedRecipe() {
		return sealedRecipe;
	}
}
