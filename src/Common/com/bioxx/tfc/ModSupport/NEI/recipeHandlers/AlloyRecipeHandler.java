package com.bioxx.tfc.ModSupport.NEI.recipeHandlers;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.bioxx.tfc.Core.Metal.Alloy;
import com.bioxx.tfc.Core.Metal.AlloyManager;
import com.bioxx.tfc.Core.Metal.AlloyMetal;
import com.bioxx.tfc.Core.Metal.AlloyMetalCompare;
import com.bioxx.tfc.Items.ItemOre;
import com.bioxx.tfc.ModSupport.NEI.NEIIntegration;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFCItems;
import com.google.common.collect.HashMultimap;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static com.bioxx.tfc.Core.TFC_Core.l10n;
import static com.bioxx.tfc.Reference.MOD_ID;

public class AlloyRecipeHandler extends TemplateRecipeHandler {
    private static List<Alloy> alloyList;
    private static final HashMultimap<Metal, ItemStack> metalItemMap = HashMultimap.create();

    public String getGuiTexture() {
        return new ResourceLocation(MOD_ID, "textures/gui/gui_blueprint.png").toString();
    }

    public String getRecipeName() {
        return l10n("gui.nei.name.alloy");
    }

    public String getOverlayIdentifier() {
        return "alloy";
    }

    public TemplateRecipeHandler newInstance() {
        if (alloyList == null) {
            alloyList = AlloyManager.INSTANCE.alloys;
            ItemStack itemStack = new ItemStack(TFCItems.oreChunk);

            Metal metal;
            while ((metal = ((ItemOre) TFCItems.oreChunk).getMetalType(itemStack)) != null) {
                metalItemMap.put(metal, itemStack.copy());
                itemStack.setItemDamage(itemStack.getItemDamage() + 1);
            }
        }

        return super.newInstance();
    }

    private void addRecipe(Predicate<? super Alloy> condition) {
        alloyList.stream().filter(condition).forEach(recipe -> this.arecipes.add(new CachedAlloyRecipe(recipe)));
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(0, 30, 160, 30), "alloy"));
    }

    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("alloy")) addRecipe(r -> true);
        else super.loadCraftingRecipes(outputId, results);
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        addRecipe(alloy -> alloy.alloyIngred.stream().anyMatch(metal -> metal.metalType.meltedItem == ingredient.getItem() || metal.metalType.ingot == ingredient.getItem()
                || (ingredient.getItem() instanceof ItemOre && ((ItemOre) ingredient.getItem()).getMetalType(ingredient) == metal.metalType)));
    }

    public void loadCraftingRecipes(ItemStack result) {
        addRecipe(recipe -> recipe.outputType.ingot == result.getItem() || recipe.outputType.meltedItem == result.getItem());
    }

    public void drawExtras(int recipe) {
        super.drawExtras(recipe);
        TemplateRecipeHandler.CachedRecipe cr = this.arecipes.get(recipe);
        if (cr instanceof CachedAlloyRecipe) ((CachedAlloyRecipe) cr).drawExtras();
    }

    public class CachedAlloyRecipe extends TemplateRecipeHandler.CachedRecipe {
        private final PositionedStack outItem;
        private final ArrayList<PositionedStack> ingredients = new ArrayList<>();
        private final ArrayList<String> min = new ArrayList<>();
        private final ArrayList<String> max = new ArrayList<>();
        private final String tech;

        public CachedAlloyRecipe(Alloy recipe) {
            super();
            this.outItem = new PositionedStack(new ItemStack(recipe.outputType.meltedItem), 10, 10);
            int x = 15;
            for (AlloyMetal alloyMetal : recipe.alloyIngred) {
                List<ItemStack> list = new LinkedList<>();
                list.add(new ItemStack(alloyMetal.metalType.meltedItem));
                list.add(new ItemStack(alloyMetal.metalType.ingot));
                list.addAll(AlloyRecipeHandler.metalItemMap.get(alloyMetal.metalType));
                x += 30;
                this.ingredients.add(new PositionedStack(list, x, 10));
                if (alloyMetal instanceof AlloyMetalCompare) {
                    this.min.add(String.format("%2.0f%%", ((AlloyMetalCompare) alloyMetal).getMetalMin()));
                    this.max.add(String.format("%2.0f%%", ((AlloyMetalCompare) alloyMetal).getMetalMax()));
                } else {
                    this.min.add("100%");
                    this.max.add("");
                }
            }
            this.tech = "gui.nei.alloy.level." + recipe.getFurnaceTier().tier;
        }

        public List<PositionedStack> getIngredients() {
            for (PositionedStack positionedStack : this.ingredients) {
                positionedStack.setPermutationToRender(AlloyRecipeHandler.this.cycleticks / 24 % positionedStack.items.length);
            }
            return this.ingredients;
        }

        public PositionedStack getResult() {
            return this.outItem;
        }

        public void drawExtras() {
            FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
            NEIIntegration.drawCenteredString(fr, l10n("gui.nei.alloy.min"), 20, 30, 0);
            NEIIntegration.drawCenteredString(fr, l10n("gui.nei.alloy.max"), 20, 40, 0);
            NEIIntegration.drawCenteredString(fr, l10n(this.tech), 80, 0, 8519827);
            int x = 16;
            for (String max1 : this.min) {
                x += 30;
                fr.drawString(max1, x, 30, 0);
            }
            x = 16;
            for (String max1 : this.max) {
                x += 30;
                fr.drawString(max1, x, 40, 0);
            }

        }
    }
}
