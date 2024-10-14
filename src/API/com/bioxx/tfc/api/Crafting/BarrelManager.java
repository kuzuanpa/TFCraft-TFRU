package com.bioxx.tfc.api.Crafting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidStack;

import com.bioxx.tfc.TileEntities.TEBarrel;

public class BarrelManager
{
	private static final BarrelManager INSTANCE = new BarrelManager();
	public static BarrelManager getInstance()
	{
		return INSTANCE;
	}

	private List<IBarrelRecipe> recipes;
	private List<BarrelPreservativeRecipe> preservativeRecipes;

	private BarrelManager()
	{
		recipes = new ArrayList<>();
		preservativeRecipes = new ArrayList<BarrelPreservativeRecipe>();
	}

	public void addRecipe(IBarrelRecipe recipe)
	{
		if(recipe!=null)recipes.add(recipe);
	}
	@Deprecated
	public void addRecipe(BarrelRecipe recipe)
	{
		if(recipe!=null)recipes.add(recipe);
	}
	public void addPreservative(BarrelPreservativeRecipe recipe) {
		preservativeRecipes.add(recipe);
	}

	public IBarrelRecipe findMatchingRecipe(ItemStack item, FluidStack fluid, boolean sealed, int techLevel) {
		for (IBarrelRecipe br : recipes) {
			if (/*item != null && */fluid != null &&/*(br.inItemStack != null && item != null) && (br.inFluid != null && fluid != null) &&*/br!=null&& br.matches(item, fluid))
				if (br.isSealedRecipe() == sealed && br.getMinTechLevel() <= techLevel)
					return br;
		}
		return null;
	}
	public BarrelPreservativeRecipe findMatchingPreservativeRepice(TEBarrel barrel, ItemStack item, FluidStack fluid, boolean sealed)
	{
		for(BarrelPreservativeRecipe recipe : preservativeRecipes){
			if(recipe.checkForPreservation(barrel, fluid, item, sealed))
				return recipe;
		}
		return null;
	}

	public List<IBarrelRecipe> getRecipes()
	{
		return recipes;
	}
	
	public List<BarrelPreservativeRecipe> getPreservatives()
	{
		return preservativeRecipes;
	}


}
