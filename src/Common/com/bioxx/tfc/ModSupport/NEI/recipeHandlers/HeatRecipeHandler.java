package com.bioxx.tfc.ModSupport.NEI.recipeHandlers;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.bioxx.tfc.Containers.Slots.SlotCookableFoodOnly;
import com.bioxx.tfc.Containers.Slots.SlotFirepitIn;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.Items.ItemMeltedMetal;
import com.bioxx.tfc.Items.ItemOre;
import com.bioxx.tfc.ModSupport.NEI.NEIIntegration;
import com.bioxx.tfc.api.HeatIndex;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFC_ItemHeat;
import com.bioxx.tfc.api.Interfaces.ISmeltable;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static com.bioxx.tfc.Core.TFC_Core.l10n;
import static com.bioxx.tfc.Reference.MOD_ID;

public class HeatRecipeHandler extends TemplateRecipeHandler {
	private static List<HeatIndex> recipeList;
	private static Item firepit;
	private static Item grill;
	private static Item forge;
	private static Item crucible;
	private static final Slot firepitSlot = new SlotFirepitIn(null, null, 0, 0, 0);
	private static final Slot grillSlot = new SlotCookableFoodOnly(null, 0, 0, 0);

	public String getGuiTexture() {
	  return new ResourceLocation(MOD_ID, "textures/gui/gui_blueprint.png").toString();
	}

	public String getRecipeName() {
	  return l10n("gui.nei.name.heating");
	}

	public String getOverlayIdentifier() {
	  return "heating";
	}

	public void loadTransferRects() {
		this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(25, 27, 18, 10), "heating"));
	}

	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("heating")) for (HeatIndex recipe : recipeList) this.arecipes.add(new CachedHeatRecipe(recipe));
		else super.loadCraftingRecipes(outputId, results);
	}

	public TemplateRecipeHandler newInstance() {
		if (recipeList == null) {
			recipeList = HeatRegistry.getInstance().getHeatList();
			firepit = Item.getItemFromBlock(TFCBlocks.firepit);
			grill = Item.getItemFromBlock(TFCBlocks.grill);
			forge = Item.getItemFromBlock(TFCBlocks.forge);
			crucible = Item.getItemFromBlock(TFCBlocks.crucible);
		}
		return super.newInstance();
	}

	public void loadCraftingRecipes(ItemStack result) {
		for (HeatIndex recipe : recipeList) if (NEIIntegration.areItemStacksEqual(result, recipe.getOutput(new Random()))) this.arecipes.add(new CachedHeatRecipe(recipe));
	}

	public void loadUsageRecipes(ItemStack ingredient) {
		Item item = ingredient.getItem();
		for (HeatIndex recipe : recipeList) {
			if (item == firepit && this.isValidForFirepit(recipe) || item == grill && this.isValidForGrill(recipe) || item == forge && this.isValidForForge(recipe) || item == crucible && this.isValidForCrucible(recipe)) {
				this.arecipes.add(new CachedHeatRecipe(recipe));
			} else if (recipe.matches(ingredient)) {
				this.arecipes.add(new CachedHeatRecipe(recipe, ingredient));
			}
		}

	}

	public void drawExtras(int recipe) {
		super.drawExtras(recipe);
		TemplateRecipeHandler.CachedRecipe cr = this.arecipes.get(recipe);
		if (cr instanceof CachedHeatRecipe) {
		 ((CachedHeatRecipe)cr).drawExtras();
		}

	}

	private boolean isValidForFirepit(HeatIndex recipe) {
		return firepitSlot.isItemValid(recipe.input) && recipe.meltTemp < 1360.0F;
	}

	private boolean isValidForGrill(HeatIndex recipe) {
		return grillSlot.isItemValid(recipe.input) && recipe.meltTemp < 1360.0F;
	}

	private boolean isValidForForge(HeatIndex recipe) {
		return !(recipe.input.getItem() instanceof ItemOre) && !(recipe.input.getItem() instanceof ItemFoodTFC);
	}

	private boolean isValidForCrucible(HeatIndex recipe) {
		Item item = recipe.input.getItem();
		return (item instanceof ISmeltable && ((ISmeltable)item).isSmeltable(recipe.input) || item instanceof ItemMeltedMetal) && item != TFCItems.rawBloom && (item != TFCItems.bloom || recipe.input.getItemDamage() <= 100) && !TFC_Core.isOreIron(recipe.input);
	}

	public class CachedHeatRecipe extends TemplateRecipeHandler.CachedRecipe {
		final PositionedStack ingred;
		final PositionedStack result;
		final String temp;
		final List<PositionedStack> heatingItems;

		public CachedHeatRecipe(HeatIndex recipe) {
		 this(recipe, recipe.input);
		}

		public CachedHeatRecipe(HeatIndex recipe, ItemStack input) {
		 super();
		 this.heatingItems = new ArrayList<>(5);
		 this.ingred = new PositionedStack(recipe.input, 25, 9);
		 ItemStack result = this.getActualResult(recipe.getOutput(input, new Random()), input);
		 this.result = new PositionedStack(result, 25, 37);
		 this.temp = TFC_ItemHeat.getHeatColor(recipe.meltTemp, 2.14748365E9F);
		 if (HeatRecipeHandler.this.isValidForFirepit(recipe)) {
			this.heatingItems.add(new PositionedStack(new ItemStack(HeatRecipeHandler.firepit), 50, 20));
		 }

		 if (HeatRecipeHandler.this.isValidForGrill(recipe)) {
			this.heatingItems.add(new PositionedStack(new ItemStack(HeatRecipeHandler.grill), 70, 20));
		 }

		 if (HeatRecipeHandler.this.isValidForForge(recipe)) {
			this.heatingItems.add(new PositionedStack(new ItemStack(HeatRecipeHandler.forge), 90, 20));
		 }

		 if (HeatRecipeHandler.this.isValidForCrucible(recipe)) {
			this.heatingItems.add(new PositionedStack(new ItemStack(HeatRecipeHandler.crucible), 110, 20));
		 }

		}

		private ItemStack getActualResult(ItemStack result, ItemStack ingred) {
		 if (result != null) {
			return result;
		 } else if (ingred.getItem() instanceof ISmeltable) {
			ISmeltable smelt = (ISmeltable)ingred.getItem();
			ItemStack smeltedItem = new ItemStack(smelt.getMetalType(ingred).meltedItem);
			int units = smelt.getMetalReturnAmount(ingred);
			smeltedItem.stackSize = units / 100;
			return smeltedItem;
		 } else {
			return ingred;
		 }
		}

		public PositionedStack getResult() {
		 return this.result;
		}

		public PositionedStack getIngredient() {
		 return this.ingred;
		}

		public List<PositionedStack> getOtherStacks() {

			for (PositionedStack stack : this.heatingItems) {
				stack.setPermutationToRender(HeatRecipeHandler.this.cycleticks / 24 % stack.items.length);
			}

		 return this.heatingItems;
		}

		public void drawExtras() {
		 FontRenderer renderer = Minecraft.getMinecraft().fontRenderer;
		 renderer.drawString(l10n("gui.nei.heat.heatPlace"), 54, 9, 0);
		 renderer.drawString(l10n("gui.nei.heat.temp"), 54, 40, 0);
		 renderer.drawString(this.temp, 84, 40, 0);
		}
	}
}
