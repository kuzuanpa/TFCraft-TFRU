package com.bioxx.tfc.ModSupport.NEI.recipeHandlers;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.bioxx.tfc.ModSupport.NEI.NEIIntegration;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Crafting.QuernManager;
import com.bioxx.tfc.api.Crafting.QuernRecipe;
import java.awt.Rectangle;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.item.ItemStack;

import static com.bioxx.tfc.Core.TFC_Core.l10n;

public class QuernRecipeHandler extends TemplateRecipeHandler {
	private static List<QuernRecipe> recipeList;

	public String getRecipeName() {
		return l10n("gui.nei.name.quern");
	}

	public String getGuiTexture() {
	  return "terrafirmacraft:textures/gui/gui_quern.png";
	}

	public String getOverlayIdentifier() {
	  return "quern";
	}

	public void loadTransferRects() {
		this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(61, 9, 18, 18), "quern"));
	}

	private void addRecipe(Predicate<? super QuernRecipe> condition){
		recipeList.stream().filter(condition).forEach(recipe->this.arecipes.add(new CachedQuernRecipe(recipe)));
	}
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("quern")) addRecipe(r->true);
		else super.loadCraftingRecipes(outputId, results);
	}

	public void loadCraftingRecipes(ItemStack result) {
		addRecipe(recipe-> NEIIntegration.areItemStacksEqual(result, recipe.getResult()));
	}

	public void loadUsageRecipes(ItemStack ingredient) {
		addRecipe(recipe->ingredient.getItem() == TFCItems.quern || NEIIntegration.areItemStacksEqual(ingredient, recipe.getInItem()));
	}

	public TemplateRecipeHandler newInstance() {
		if (recipeList == null) recipeList = QuernManager.getInstance().getRecipes();
		return super.newInstance();
	}

	public class CachedQuernRecipe extends TemplateRecipeHandler.CachedRecipe {
		final PositionedStack ingred;
		final PositionedStack result;

		public CachedQuernRecipe(ItemStack ingred, ItemStack result) {
			 super();
			 this.ingred = new PositionedStack(ingred, 61, 36);
			 this.result = new PositionedStack(result, 88, 36);
		}

		public CachedQuernRecipe(QuernRecipe recipe) {
			 this(recipe.getInItem(), recipe.getResult());
		}

		public PositionedStack getResult() {
			 return this.result;
		}

		public PositionedStack getIngredient() {
			 return this.ingred;
		}

		public PositionedStack getOtherStack() {
			 return new PositionedStack(new ItemStack(TFCItems.quern), 88, 9);
		}
	}
}
