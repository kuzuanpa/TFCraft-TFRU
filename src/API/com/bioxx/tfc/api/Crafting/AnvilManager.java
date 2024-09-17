package com.bioxx.tfc.api.Crafting;

import java.util.*;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class AnvilManager
{
	private static final AnvilManager INSTANCE = new AnvilManager();
	public static AnvilManager getInstance()
	{
		return INSTANCE;
	}
	public static World world;

	private List<AnvilRecipe> recipes;
	private List<AnvilWeldRecipe> recipesWeld;
	private Map<String, PlanRecipe> plans;

	private AnvilManager()
	{
		recipes = new ArrayList<>();
		recipesWeld = new ArrayList<>();
		plans = new HashMap<>();
	}

	public void addRecipe(AnvilRecipe recipeToAdd)
	{
		if(recipes.stream().noneMatch(a->a.equals(recipeToAdd)))recipes.add(recipeToAdd);
	}

	public void addWeldRecipe(AnvilWeldRecipe recipeToAdd)
	{
		if(recipesWeld.stream().noneMatch(a->a.equals(recipeToAdd)))recipesWeld.add(recipeToAdd);
	}
	public void clearRecipes()
	{
		recipes.clear();
		recipesWeld.clear();
		plans.clear();
	}

	/**
	 * Adds a name for a plan type to the plans list. If it already exists then it will not be added. All types are not case sensative as it
	 * autoconverts to lowercase when adding to prevent bugs due to case.
	 */
	public void addPlan(String s, PlanRecipe r)
	{
		s = s.toLowerCase();
		if(!plans.containsKey(s)){
			plans.put(s, r);
		}
	}
	public PlanRecipe getPlan(String s)
	{
		return plans.get(s);
	}

	public AnvilRecipe findMatchingRecipe(ItemStack in1, ItemStack in2, String thePlan, int req)
	{
		for (int k = 0; k < recipes.size(); k++)
		{
			AnvilRecipe irecipe = recipes.get(k);
			if (irecipe != null && irecipe.matches(in1, in2, thePlan, req))
				return irecipe;
		}

		return null;
	}

	public Object[] findCompleteRecipe(ItemStack in1, ItemStack in2, String thePlan, int req, int craftingValue, int[] rules)
	{
        for (AnvilRecipe irecipe : recipes) {
            if (irecipe != null && irecipe.isComplete(INSTANCE, in1,in2,thePlan,req,craftingValue, rules))
                return new Object[]{irecipe, irecipe.getCraftingResult(in1)};
        }

		return null;
	}

	public ItemStack findCompleteWeldRecipe(ItemStack in1, ItemStack in2, int req)
	{
		for (int k = 0; k < recipesWeld.size(); k++)
		{
			AnvilWeldRecipe recipe = recipesWeld.get(k);
			if (recipe != null && recipe.matches(in1,in2,req))
				return recipe.getCraftingResult(recipe.input1);
		}

		return null;
	}

	public List<AnvilRecipe> getRecipeList()
	{
		return recipes;
	}

	public List<AnvilWeldRecipe> getWeldRecipeList()
	{
		return recipesWeld;
	}

	public Map<String, PlanRecipe> getPlans()
	{
		return plans;
	}

	public static NBTTagCompound getCraftTag(ItemStack is)
	{
		if(is.hasTagCompound() && is.getTagCompound().hasKey("craftingTag"))
		{
			return (NBTTagCompound) is.getTagCompound().getTag("craftingTag");
		}
		else
			return new NBTTagCompound();
	}

	public static void setCraftTag(ItemStack is, NBTTagCompound nbt)
	{
		if(!is.hasTagCompound())
			is.setTagCompound(new NBTTagCompound());
		is.getTagCompound().setTag("craftingTag", nbt);
	}

	public static float getDurabilityBuff(ItemStack is)
	{
		NBTTagCompound nbt = getCraftTag(is);
		return nbt.getFloat("durabuff");
	}

	public static void setDurabilityBuff(ItemStack is, float value)
	{
		NBTTagCompound nbt = getCraftTag(is);
		nbt.setFloat("durabuff", value);
		setCraftTag(is, nbt);
	}

	public static float getDamageBuff(ItemStack is)
	{
		NBTTagCompound nbt = getCraftTag(is);
		return nbt.getFloat("damagebuff");
	}

	public static void setDamageBuff(ItemStack is, float value)
	{
		NBTTagCompound nbt = getCraftTag(is);
		nbt.setFloat("damagebuff", value);
		setCraftTag(is, nbt);
	}
	public static void setPerfect(ItemStack is)
	{
		NBTTagCompound nbt = getCraftTag(is);
		nbt.setBoolean("perfectCrafted", true);
		setCraftTag(is, nbt);
	}

	public static boolean getPerfect(ItemStack is)
	{
		NBTTagCompound nbt = getCraftTag(is);
		return nbt.getBoolean("perfectCrafted");
	}
}
