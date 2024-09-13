package com.bioxx.tfc.api.Crafting;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Level;

public class AnvilWeldRecipe {
	public ItemStack result;
	public ItemStack input1;
	public ItemStack input2;
	public int anvilreq;
	public AnvilWeldRecipe(ItemStack in, ItemStack in2, int req, ItemStack result)
	{
		input1 = in;
		input2 = in2;
		anvilreq = req;
		this.result = result;
	}

	public AnvilWeldRecipe(ItemStack mat, ItemStack mat1, AnvilReq req, ItemStack result) {
		this(mat,mat1,req.Tier,result);
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */    
	public boolean matches(ItemStack in1, ItemStack in2, int req)
	{
        return AnvilReq.matches(anvilreq, req)&& (
				(isStacksEqual(input1, in1) && isStacksEqual(input2, in2))||
				(isStacksEqual(input2, in1) && isStacksEqual(input1, in2))
		);
    }

	private boolean isStacksEqual(ItemStack is1, ItemStack is2)
	{
        // XOR, if both are null return true
        if (is1 != null && is2 != null)
		{
			if (is1.getItem() != is2.getItem())
				return false;

            return is1.getItemDamage() == 32767 || is1.getItemDamage() == is2.getItemDamage();
		}
		else return is1 == null && is2 == null;
    }

	/**
	 * Returns an Item that is the result of this recipe
	 */
	public ItemStack getCraftingResult()
	{
		return result;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	public ItemStack getCraftingResult(ItemStack input)
	{
		if (result==null) FMLLog.log(Level.FATAL,"Null Output Item at Anvil Recipe: "+input1.toString()+input2.toString());
		ItemStack is = result.copy();
		return is;
	}


	public boolean isFlux()
	{
		return true;
	}

	public int getAnvilreq()
	{
		return anvilreq;
	}

}


