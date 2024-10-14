package com.bioxx.tfc.api.Crafting;

import net.minecraft.item.ItemStack;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Interfaces.IFood;

import java.util.Stack;

public class BarrelVinegarRecipe implements IBarrelRecipe
{
	public FluidStack recipeInFluid;
	public FluidStack recipeOutFluid;
	public boolean removesLiquid = true;
	public boolean sealedRecipe = true;

	public BarrelVinegarRecipe(FluidStack inputFluid, FluidStack outputFluid) {
		recipeInFluid = inputFluid;
		recipeOutFluid = outputFluid;
		this.setMinTechLevel(0);
	}

	@Override
	public Boolean matches(ItemStack item, FluidStack fluid) {
		if(item != null && item.getItem() instanceof IFood)
		{
            return fluid.isFluidEqual(recipeInFluid) && ((IFood) item.getItem()).getFoodGroup() == EnumFoodGroup.Fruit && Food.getWeight(item) >= (fluid.amount / 100F);
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
	public boolean isRemovesLiquid() {
		return false;
	}

	@Override
	public IBarrelRecipe setRemovesLiquid(boolean b) {
		return this;
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
	public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime) {
		Stack<ItemStack> stackList = new Stack<>();
		ItemStack outStack = null;
		if (!removesLiquid && inIS != null && inFS != null)
		{
			outStack = inIS.copy();
			outStack.stackSize -= inFS.amount / this.recipeOutFluid.amount;
			stackList.push(outStack);
		}
		if (outStack == null)
		{
			stackList.push(outStack);
		}
		return stackList;
	}

	public boolean isSealedRecipe() {
		return this.sealedRecipe;
	}

	public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime) {
		if(recipeOutFluid == null)return null;
		FluidStack fs;
		// The FluidStack .copy() method does not make a copy of the NBT tag, which may have been the cause of the quantum entanglement
		if (recipeOutFluid.tag != null) fs = new FluidStack(recipeOutFluid.getFluid(), recipeOutFluid.amount, (NBTTagCompound) recipeOutFluid.tag.copy());
		else fs = new FluidStack(recipeOutFluid.getFluid(), recipeOutFluid.amount);

		if (!removesLiquid && inFS != null) fs.amount = inFS.amount;
		else if (inIS != null && inFS != null) fs.amount = (int) Math.min(inFS.amount,fs.amount*Food.getWeight(inIS));

		return fs;
	}
}
