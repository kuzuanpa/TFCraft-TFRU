package com.bioxx.tfc.api.Crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import java.util.Stack;

public interface IBarrelRecipe {

    Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime);

    default FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime){
        if(getRecipeOutFluid() == null)return null;
        FluidStack fs;
        if (getRecipeOutFluid().tag != null) fs = new FluidStack(getRecipeOutFluid().getFluid(), getRecipeOutFluid().amount, (NBTTagCompound) getRecipeOutFluid().tag.copy());
        else fs = new FluidStack(getRecipeOutFluid().getFluid(), getRecipeOutFluid().amount);

        if (!isRemovesLiquid() && inFS != null) fs.amount = inFS.amount;
        else if (inIS != null) fs.amount *= inIS.stackSize;

        return fs;
    }

    Boolean matches(ItemStack itemstack, FluidStack inFluid);
    ItemStack getInItemStack();

    FluidStack getInFluid();

    ItemStack getRecipeOutIS();

    FluidStack getRecipeOutFluid();

    int getSealTime();

    boolean isRemovesLiquid();

    default IBarrelRecipe setRemovesLiquid(boolean b){return this;}

    default IBarrelRecipe setMinTechLevel(int t){return this;}

    default IBarrelRecipe setSealedRecipe(boolean b){return this;}

    int getMinTechLevel();

    default int getRecipeRepeatTimes(ItemStack inIS, FluidStack inFS)
    {
        int runs = 0;
        int div = 0;
        if(inIS != null && getInItemStack() != null)
        {
            runs = inIS.stackSize/ getInItemStack().stackSize;
            div = inFS.amount/this.getInFluid().amount;
        }
        return Math.min(runs, div);
    }

    boolean isSealedRecipe();

    default String getRecipeName()
    {
        String s = "";
        if(this.getRecipeOutIS() != null)
        {
            if(getRecipeOutIS().stackSize > 1)
                s += getRecipeOutIS().stackSize+"x ";
            s += getRecipeOutIS().getDisplayName();
        }
        if(getRecipeOutFluid() != null)
            s = getRecipeOutFluid().getFluid().getLocalizedName(getRecipeOutFluid());
        return s;
    };
}
