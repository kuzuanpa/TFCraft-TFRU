package com.bioxx.tfc.ModSupport.NEI.recipeHandlers;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.bioxx.tfc.ModSupport.NEI.NEIIntegration;
import com.bioxx.tfc.api.Crafting.KilnCraftingManager;
import com.bioxx.tfc.api.Crafting.KilnRecipe;
import java.awt.Rectangle;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static com.bioxx.tfc.Core.TFC_Core.l10n;
import static com.bioxx.tfc.Reference.MOD_ID;

public class KilnRecipeHandler extends TemplateRecipeHandler {
	private static List<KilnRecipe> recipeList;

	public String getGuiTexture() {
	  return new ResourceLocation(MOD_ID, "textures/gui/gui_pitkiln_nei.png").toString();
	}

	public String getRecipeName() {
		return l10n("gui.nei.name.pitkiln");
	}

	public String getOverlayIdentifier() {
		return "pitkiln";
	}

	public void loadTransferRects() {
		this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(43, 26, 18, 18), "pitkiln"));
		this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(88, 26, 25, 18), "pitkiln"));
	}

	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("pitkiln") && this.getClass() == KilnRecipeHandler.class) for (KilnRecipe recipe : recipeList) this.arecipes.add(new CachedKilnRecipe(recipe));
		else super.loadCraftingRecipes(outputId, results);
	}

	public TemplateRecipeHandler newInstance() {
		if (recipeList == null) recipeList = KilnCraftingManager.getInstance().getRecipeList();
		return super.newInstance();
	}

	public void loadCraftingRecipes(ItemStack result) {
		for (KilnRecipe recipe : recipeList) if (NEIIntegration.areItemStacksEqual(result, recipe.getCraftingResult())) this.arecipes.add(new CachedKilnRecipe(recipe));
	}

	public void loadUsageRecipes(ItemStack ingredient) {
		for (KilnRecipe recipe : recipeList) if (NEIIntegration.areItemStacksEqual(ingredient, recipe.getInput1())) this.arecipes.add(new CachedKilnRecipe(recipe.getInput1(), recipe.getCraftingResult()));
	}

	public class CachedKilnRecipe extends TemplateRecipeHandler.CachedRecipe {
		final PositionedStack ingred;
		final PositionedStack result;

		public CachedKilnRecipe(ItemStack ingred, ItemStack result) {
			super();
			this.ingred = new PositionedStack(ingred, 43, 44);
			this.result = new PositionedStack(result, 119, 24);
		}

		public CachedKilnRecipe(KilnRecipe recipe) {
		 this(recipe.getInput1(), recipe.getCraftingResult());
		}

		public PositionedStack getResult() {
		 return this.result;
		}

		public PositionedStack getIngredient() {
		 return this.ingred;
		}

		public PositionedStack getOtherStack() {
			return new PositionedStack(new ItemStack(Blocks.fire), 43, 9);
		}
	}
}
