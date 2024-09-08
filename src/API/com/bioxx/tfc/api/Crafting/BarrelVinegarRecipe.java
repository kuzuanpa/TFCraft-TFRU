package com.bioxx.tfc.api.Crafting;

import com.bioxx.tfc.Food.ItemFoodTFC;
import net.minecraft.item.ItemStack;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Interfaces.IFood;

public class BarrelVinegarRecipe extends BarrelRecipe
{

	public BarrelVinegarRecipe(FluidStack inputFluid, FluidStack outputFluid)
	{
		super(null, inputFluid, null, outputFluid);
		this.setMinTechLevel(0);
	}

	@Override
	public Boolean matches(ItemStack item, FluidStack fluid)
	{
		if(item != null && item.getItem() instanceof IFood)
		{
			if (fluid.isFluidEqual(recipeFluid) && ((IFood) item.getItem()).getFoodGroup() == EnumFoodGroup.Fruit && Food.getWeight(item) >= 1f * (fluid.amount / 100))
			{
				return true;
			}
		}
		return false;
	}
	public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime)
	{
		if(recipeOutFluid != null)
		{
			FluidStack fs = null;
			// The FluidStack .copy() method does not make a copy of the NBT tag, which may have been the cause of the quantum entanglement
			if (recipeOutFluid.tag != null)
			{
				fs = new FluidStack(recipeOutFluid.getFluid(), recipeOutFluid.amount, (NBTTagCompound) recipeOutFluid.tag.copy());
			}
			else
			{
				fs = new FluidStack(recipeOutFluid.getFluid(), recipeOutFluid.amount);
			}

			if (!removesLiquid && inFS != null)
			{
				fs.amount = inFS.amount;
			}
			else if (inIS != null && inFS != null)
			{
				fs.amount = (int) Math.min(inFS.amount,fs.amount*Food.getWeight(inIS));
			}
			return fs;
		}
		return null;
	}
}
