package com.bioxx.tfc.api.Crafting;

import java.util.Stack;

import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Interfaces.IFood;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class BarrelCommonRecipe implements IBarrelRecipe
{
	public ItemStack recipeIS;
	public FluidStack recipeInFluid;
	public ItemStack recipeOutIS;
	public FluidStack recipeOutFluid;
	public int sealTime = 8;
	public boolean removesLiquid = true;
	/**Outputting mode,
	 * &#064;value=true: output fluid, requires item more than fluid, outputFS!=null
	 * &#064;value=false: output item, requires fluid more than item, will remove fluid when outputFS==null
	 **/
	public boolean outputtingFluid =false;
	public boolean sealedRecipe = true;
	public int minTechLevel = 1;

	public BarrelCommonRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid)
	{
		this.recipeIS = inputItem;
		recipeInFluid = inputFluid;
		this.recipeOutIS = outIS;
		recipeOutFluid = outputFluid;
		this.outputtingFluid = outIS==null;
	}

	public BarrelCommonRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid, int seal)
	{
		this(inputItem, inputFluid, outIS, outputFluid);
		this.sealTime = seal;
	}

	public IBarrelRecipe setSealedRecipe(boolean b){
		this.sealedRecipe=b;
		return this;
	}

	public IBarrelRecipe setRemovesLiquid(boolean b)
	{
		this.removesLiquid = b;
		return this;
	}

	public IBarrelRecipe setMinTechLevel(int t)
	{
		this.minTechLevel = t;
		return this;
	}

	public Boolean matches(ItemStack item, FluidStack fluid)
	{
		boolean isFluidEnoughForItemMake = !outputtingFluid || recipeOutIS==null || recipeIS != null && item != null && fluid != null && recipeInFluid != null && item.stackSize >= (int) Math.ceil(fluid.amount *1F/ recipeInFluid.amount);
		boolean isItemEnoughForFluidMake = outputtingFluid || recipeOutFluid == null || recipeInFluid != null && item != null && fluid != null && fluid.amount >= recipeOutFluid.amount;

		boolean itemsEqual = (item == null && recipeIS == null) || OreDictionary.itemMatches(recipeIS, item, false);

		return (recipeIS == null || (itemsEqual && isFluidEnoughForItemMake) ) && (recipeInFluid == null || (recipeInFluid.isFluidEqual(fluid) && isItemEnoughForFluidMake));
	}

	public Boolean isInFluid(FluidStack item)
	{
		return recipeInFluid.isFluidEqual(item);
	}

	public ItemStack getInItemStack()
	{
		return recipeIS;
	}

	public FluidStack getInFluid()
	{
		return recipeInFluid;
	}

	public ItemStack getRecipeOutIS()
	{
		return recipeOutIS;
	}

	public FluidStack getRecipeOutFluid()
	{
		return recipeOutFluid;
	}

	public int getSealTime()
	{
		return sealTime;
	}

	public boolean isRemovesLiquid()
	{
		return removesLiquid;
	}

	public int getMinTechLevel()
	{
		return minTechLevel;
	}

	public boolean isSealedRecipe() {
		return this.sealedRecipe;
	}

	public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime)
	{
		Stack<ItemStack> stackList = new Stack<>();
		ItemStack outStack = null;

		if (recipeOutIS != null)
		{
            outStack = recipeOutIS.copy();
			int repeatTime = this.getRecipeRepeatTimes(inIS, inFS);
			int outputCount = outStack.stackSize * repeatTime;
			if(inIS.stackSize - recipeIS.stackSize * repeatTime > 0) {
				ItemStack inISOut = inIS.copy();
				inISOut.stackSize-=recipeIS.stackSize * repeatTime;
				stackList.push(inISOut);
			}
			int maxStackSize = outStack.getMaxStackSize();
			Item item = outStack.getItem();
			int damage = outStack.getItemDamage();

			int remainder = outputCount % maxStackSize; // The amount remaining after making full-sized stacks.
			if (remainder > 0)
			{
				stackList.push(new ItemStack(item, remainder, damage)); // Push this on first, so it doesn't end up in the input slot.
				outputCount -= remainder;
			}

			while (outputCount >= maxStackSize) // Add as many full-sized stacks as possible to stackList.
			{
				stackList.push(new ItemStack(item, maxStackSize, damage));
				outputCount -= maxStackSize;
			}
			return stackList;

		}
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

	@Override
	public int getRecipeRepeatTimes(ItemStack inIS, FluidStack inFS) {
		return (int) Math.floor(inFS.amount*1F/recipeInFluid.amount);
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
			else if (inIS != null)
			{
				int multiplier = inIS.stackSize;
				if (inIS.getItem() instanceof IFood)multiplier = (int) Food.getWeight(inIS);
				int inFluidAmount=inFS==null?0:inFS.amount;
				fs.amount = Math.min(inFluidAmount, fs.amount*multiplier);
			}
			return fs;
		}
		return null;
	}
}
