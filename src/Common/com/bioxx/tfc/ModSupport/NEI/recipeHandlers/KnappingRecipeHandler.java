package com.bioxx.tfc.ModSupport.NEI.recipeHandlers;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.bioxx.tfc.Items.ItemFlatGeneric;
import com.bioxx.tfc.Items.ItemLooseRock;
import com.bioxx.tfc.ModSupport.NEI.NEIIntegration;
import com.bioxx.tfc.api.Crafting.LoomRecipe;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Crafting.CraftingManagerTFC;
import com.bioxx.tfc.api.Crafting.ShapedRecipesTFC;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import static com.bioxx.tfc.Core.TFC_Core.l10n;

public class KnappingRecipeHandler extends TemplateRecipeHandler {
	private static List<ShapedRecipesTFC> recipeList;

	public String getGuiTexture() {
		return "terrafirmacraft:textures/gui/gui_knapping.png";
	}

	public String getRecipeName() {
		return l10n("gui.nei.name.knapping");
	}

	public String getOverlayIdentifier() {
		return "knapping";
	}

	public TemplateRecipeHandler newInstance() {
		if (recipeList != null) return super.newInstance();
		recipeList = new ArrayList<>();
		List<IRecipe> allRecipes = CraftingManagerTFC.getInstance().getRecipeList();
		for(IRecipe recipe : allRecipes) {
			if(recipe.getRecipeSize() < 9 || !(recipe instanceof ShapedRecipesTFC))continue;
			recipeList.add((ShapedRecipesTFC) recipe);
		}
		return super.newInstance();
	}

	private void addRecipe(Predicate<? super ShapedRecipesTFC> condition) {
		recipeList.stream().filter(condition).forEach(recipe -> this.arecipes.add(new CachedKnappingRecipe(recipe)));
	}

	public int recipiesPerPage() {
		return 1;
	}

	public void loadTransferRects() {
		this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(0, 0, 80, 80), "knapping", new Object[0]));
	}

	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("knapping")) addRecipe(r->true);
		else super.loadCraftingRecipes(outputId, results);
	}

	public void loadCraftingRecipes(ItemStack result) {
		addRecipe(recipe->NEIIntegration.areItemStacksEqual(result, recipe.getRecipeOutput()));
	}

	public void loadUsageRecipes(ItemStack ingredient) {
		if (!(ingredient.getItem() instanceof ItemLooseRock))return;
		Item type = ((ItemLooseRock) ingredient.getItem()).getSpecialCraftingType();
		addRecipe(recipe -> Arrays.stream(recipe.getRecipeItems()).filter(itemStack -> itemStack != null && itemStack.getItem() == type).anyMatch(is -> {
			int inMeta = ingredient.getItemDamage();
			int outMeta=is.getItemDamage();
			return (type == TFCItems.flatClay && ((inMeta == 0 && outMeta == 1) || (inMeta == 1 && outMeta == 3))) ||
					(outMeta == 32767 || inMeta == outMeta);
		}));
	}

	public class CachedKnappingRecipe extends TemplateRecipeHandler.CachedRecipe {
		final List<PositionedStack> inputs;
		final PositionedStack result;
		PositionedStack actualInput;

		public CachedKnappingRecipe(ShapedRecipesTFC recipe) {
			super();
			int recipeWidth = recipe.getRecipeWidth();
			int recipeHeight = recipe.getRecipeHeight();
			ItemStack off = null;
			ItemStack[] inputItems = recipe.getRecipeItems();
			for (ItemStack inStack : inputItems) {
				if (inStack == null) continue;
				if (inStack.getItem() == TFCItems.flatClay) {
					if (inStack.getItemDamage() == 1) {
						off = new ItemStack(inStack.getItem(), 1, 0);
						this.setActualInput(new ItemStack(TFCItems.clayBall, 5, 0));
					} else if (inStack.getItemDamage() == 3) {
						off = new ItemStack(inStack.getItem(), 1, 2);
						this.setActualInput(new ItemStack(TFCItems.clayBall, 5, 1));
					}
				} else if (inStack.getItem() == TFCItems.flatLeather) {
					this.setActualInput(new ItemStack(TFCItems.leather));
				} else if (inStack.getItem() == TFCItems.flatRock) {
					this.setActualInput(new ItemStack(TFCItems.looseRock, 1, inStack.getItemDamage()));
				}
				break;
			}

			this.inputs = new ArrayList<>();

			for(int h = 0; h < recipeHeight; ++h) {
				for(int w = 0; w < recipeWidth; ++w) {
					if (inputItems[h * recipeWidth + w] != null) {
						this.inputs.add(new PositionedStack(inputItems[h * recipeWidth + w], 16 * w, 16 * h));
					} else if (off != null) {
						this.inputs.add(new PositionedStack(off, 16 * w, 16 * h));
					}
				}
			}

			this.result = new PositionedStack(recipe.getRecipeOutput(), 123, 33);
		}

		public List<PositionedStack> getIngredients() {
			return this.inputs;
		}

		public PositionedStack getResult() {
			return this.result;
		}

		public PositionedStack getOtherStack() {
			return this.actualInput;
		}

		public void setActualInput(ItemStack itemStack) {
			this.actualInput = new PositionedStack(itemStack, 123, 10);
		}
	}
}
