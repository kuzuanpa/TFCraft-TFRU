package com.bioxx.tfc.ModSupport.NEI.recipeHandlers;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.bioxx.tfc.Core.Recipes;
import com.bioxx.tfc.GUI.GuiAnvil;
import com.bioxx.tfc.Items.Tools.ItemHammer;
import com.bioxx.tfc.ModSupport.NEI.NEIIntegration;
import com.bioxx.tfc.api.Crafting.AnvilWeldRecipe;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Crafting.AnvilRecipe;
import com.bioxx.tfc.api.Crafting.AnvilReq;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import static com.bioxx.tfc.Core.TFC_Core.l10n;

public class AnvilRecipeHandler extends TemplateRecipeHandler {
    private static List<AnvilRecipe> recipeList;
    private static List<AnvilWeldRecipe> weldRecipeList;
    private static ItemStack[] hammers;

    public String getGuiTexture() {
        return GuiAnvil.texture.toString();
    }

    public String getRecipeName() {
        return l10n("gui.nei.name.anvil");
    }

    public String getOverlayIdentifier() {
        return "tfcanvil";
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(-8, 58, 36, 20), "tfcanvil"));
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(51, 13, 64, 36), "tfcanvil"));
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(48, 69, 70, 34), "tfcanvil"));
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(127, 11, 54, 72), "tfcanvil"));
    }

    private void addCommonRecipe(Predicate<? super AnvilRecipe> condition) {
        recipeList.stream().filter(condition).forEach(recipe -> this.arecipes.add(new CachedAnvilRecipe(recipe)));
    }

    private void addWeldRecipe(Predicate<? super AnvilWeldRecipe> condition) {
        weldRecipeList.stream().filter(condition).forEach(recipe -> this.arecipes.add(new CachedAnvilRecipe(recipe)));
    }

    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("tfcanvil")) {
            addCommonRecipe(r -> true);
            addWeldRecipe(r -> true);
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack stack) {
        addCommonRecipe(r -> AnvilRecipe.isStackEqual(stack, r.result));
        addWeldRecipe(r -> AnvilRecipe.isStackEqual(stack, r.result));
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        if (ingredient.getItem() instanceof ItemHammer) {
            addCommonRecipe(r -> true);
            addWeldRecipe(r -> true);
        } else if (ingredient.getItem() == TFCItems.powder && ingredient.getItemDamage() == 0) {
            addWeldRecipe(r -> true);
        } else {
            addCommonRecipe(anvilRecipe -> AnvilRecipe.isStackEqual(ingredient, anvilRecipe.input1) || AnvilRecipe.isStackEqual(ingredient, anvilRecipe.input2));
            addWeldRecipe(anvilRecipe -> AnvilRecipe.isStackEqual(ingredient, anvilRecipe.input1) || AnvilRecipe.isStackEqual(ingredient, anvilRecipe.input2));
        }
    }

    public TemplateRecipeHandler newInstance() {
        if (recipeList == null || weldRecipeList == null || hammers == null) {
            recipeList = AnvilManager.getInstance().getRecipeList();
            weldRecipeList = AnvilManager.getInstance().getWeldRecipeList();
            hammers = new ItemStack[Recipes.hammers.length];

            for (int i = 0; i < hammers.length; ++i) {
                hammers[i] = new ItemStack(Recipes.hammers[i]);
            }
        }

        return super.newInstance();
    }

    public int recipiesPerPage() {
        return 1;
    }

    public void drawBackground(int recipe) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GuiDraw.changeTexture(this.getGuiTexture());
        GuiDraw.drawTexturedModalRect(-21, 5, 0, 0, 208, 198);
    }

    public void drawForeground(int recipe) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(2896);
        GuiDraw.changeTexture(this.getGuiTexture());
        this.drawExtras(recipe);
    }

    public void drawExtras(int recipe) {
        super.drawExtras(recipe);
        TemplateRecipeHandler.CachedRecipe cr = this.arecipes.get(recipe);
        if (cr instanceof CachedAnvilRecipe) {
            NEIIntegration.drawCenteredString(Minecraft.getMinecraft().fontRenderer, ((CachedAnvilRecipe) cr).anvilReq, 80, -3, 8519827);
        }
    }

    public class CachedAnvilRecipe extends TemplateRecipeHandler.CachedRecipe {
        PositionedStack i1;
        PositionedStack i2;
        final PositionedStack out;
        final boolean isWeld;
        public final String anvilReq;

        public CachedAnvilRecipe(AnvilRecipe recipe) {
            this(false, recipe.getAnvilreq(), recipe.getCraftingResult(), recipe.getInput1(), recipe.getInput2());
        }

        public CachedAnvilRecipe(AnvilWeldRecipe recipe) {
            this(true, recipe.getAnvilreq(), recipe.getCraftingResult(), recipe.input1, recipe.input2);
        }

        public CachedAnvilRecipe(boolean isWeld, int anvilreq, ItemStack out, ItemStack i1, ItemStack i2) {
            super();
            this.isWeld = isWeld;
            StringBuilder key = new StringBuilder();

            Arrays.stream(AnvilReq.RULES).filter(r -> r.Tier == anvilreq).limit(1).forEach(r -> key.append("gui.nei.anvilorbetter.").append(r.Tier));

            this.anvilReq = l10n(key.toString());
            if (i1 != null) {
                this.i1 = new PositionedStack(i1, isWeld ? -7 : 66, isWeld ? 17 : 51);
            }

            if (i2 != null) {
                this.i2 = new PositionedStack(i2, isWeld ? 11 : 84, isWeld ? 17 : 51);
            }

            this.out = new PositionedStack(out, isWeld ? 2 : 103, isWeld ? 39 : 51);
        }


        public List<PositionedStack> getIngredients() {
            ArrayList<PositionedStack> stacks = new ArrayList<>();
            if (this.i1 != null) {
                stacks.add(this.i1);
            }

            if (this.i2 != null) {
                stacks.add(this.i2);
            }

            return stacks;
        }

        public PositionedStack getResult() {
            return this.out;
        }

        public List<PositionedStack> getOtherStacks() {
            List<PositionedStack> stacks = new ArrayList<>();
            stacks.add(new PositionedStack(AnvilRecipeHandler.hammers, -14, 100, false));
            if (this.isWeld) {
                stacks.add(new PositionedStack(new ItemStack(TFCItems.powder), 164, 100));
            }

            return stacks;
        }
    }
}
