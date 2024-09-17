package com.bioxx.tfc.api.Crafting;

import java.util.Stack;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.api.Food;

public class BarrelAlcoholRecipe implements IBarrelRecipe
{

	public BarrelAlcoholRecipe(ItemStack inputItem, FluidStack inputFluid,
			ItemStack outIS, FluidStack outputFluid) {
		this.recipeInItemStack = inputItem;
		recipeInFluid = inputFluid;
		this.recipeOutItemStack = outIS;
		recipeOutFluid = outputFluid;
	}

	public ItemStack recipeInItemStack;
	public FluidStack recipeInFluid;
	public ItemStack recipeOutItemStack;
	public FluidStack recipeOutFluid;
	@Override
	public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime)
	{
		Stack<ItemStack> result = new Stack<ItemStack>();
		result.push(recipeOutItemStack);
		return result;
	}

	@Override
	public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime)
	{
		float amt = inFS.amount/10000f;
		FluidStack out = recipeOutFluid.copy();
		if(out.tag == null)
			out.tag = new NBTTagCompound();
		float weight = Food.getWeight(inIS);
		out.tag.setFloat("potency", (weight/Food.getWeight(recipeInItemStack))/amt);
		return recipeOutFluid;
	}

	@Override
	public Boolean matches(ItemStack itemstack, FluidStack inFluid)
	{
		if(recipeInItemStack.hasTagCompound())
		{
			if(itemstack == null || !itemstack.hasTagCompound())
			{
				return false;
			}
			if(recipeInItemStack.getItem() instanceof ItemFoodTFC)
			{
				if(!(itemstack.getItem() instanceof ItemFoodTFC))
				{
					return false;
				}
				float recipeWeight = Food.getWeight(recipeInItemStack);
				float itemstackWeight = Food.getWeight(itemstack);
				float percent = itemstackWeight/(recipeWeight * ((float)inFluid.amount/(float) recipeInFluid.amount));
				if (percent < 0.25f || percent > 0.75f)
					return false;
			}
		}
		return OreDictionary.itemMatches(recipeInItemStack, itemstack, false) && inFluid.isFluidEqual(recipeInFluid);
	}

	@Override
	public ItemStack getInItemStack() {
		return recipeInItemStack;
	}

	@Override
	public FluidStack getInFluid() {
		return recipeInFluid;
	}

	@Override
	public ItemStack getRecipeOutIS() {
		return recipeOutItemStack;
	}

	@Override
	public FluidStack getRecipeOutFluid() {
		return recipeOutFluid;
	}

	@Override
	public int getSealTime() {
		return 72;
	}

	@Override
	public boolean isRemovesLiquid() {
		return false;
	}

	@Override
	public IBarrelRecipe setMinTechLevel(int t) {
		return this;
	}

	@Override
	public int getMinTechLevel() {
		return 0;
	}

	@Override
	public boolean isSealedRecipe() {
		return false;
	}
}
