package com.bioxx.tfc.ModSupport.NEI.recipeHandlers;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.bioxx.tfc.ModSupport.NEI.NEIIntegration;
import com.bioxx.tfc.api.Crafting.*;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Interfaces.IFood;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.common.Loader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

import static com.bioxx.tfc.Core.TFC_Core.l10n;
import static com.bioxx.tfc.Reference.MOD_ID;

public class BarrelRecipeHandler extends TemplateRecipeHandler {
    private static List<IBarrelRecipe> recipeList;
    private static ItemStack[] foodToBrine;
    private static ItemStack[] fruitForVinegar;

    public String getGuiTexture() {
        return new ResourceLocation(MOD_ID, "textures/gui/gui_barrel_nei.png").toString();
    }

    public String getRecipeName() {
        return l10n("gui.nei.name.barrel");
    }

    public String getOverlayIdentifier() {
        return "barrel";
    }

    public TemplateRecipeHandler newInstance() {
        if (recipeList == null) {
            recipeList = BarrelManager.getInstance().getRecipes();
            List<ItemStack> items = new ArrayList<>();
            List<ItemStack> fruits = new ArrayList<>();

            for (Object o : Item.itemRegistry) {
                if(!(o instanceof IFood))continue;
                Item item= (Item) o;
                item.getSubItems(item, CreativeTabs.tabAllSearch, items);
                if (((IFood) item).getFoodGroup() == EnumFoodGroup.Fruit) {
                    item.getSubItems(item, CreativeTabs.tabAllSearch, fruits);
                }
            }

            foodToBrine = items.toArray(new ItemStack[0]);
            fruitForVinegar = fruits.toArray(new ItemStack[0]);
        }

        return super.newInstance();
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(71, 23, 24, 18), "barrel", new Object[0]));
    }

    private void addRecipe(Predicate<? super IBarrelRecipe> condition) {
        recipeList.stream().filter(condition).forEach(recipe -> this.arecipes.add(new CachedBarrelRecipe(recipe)));
    }

    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("barrel")) addRecipe(r->true);
        else super.loadCraftingRecipes(outputId, results);
    }

    public void loadCraftingRecipes(ItemStack result) {
        addRecipe(recipe->NEIIntegration.areItemStacksEqual(recipe.getRecipeOutIS(),result)||recipe.getRecipeOutFluid()!=null&&recipe.getRecipeOutFluid().isFluidEqual(result));
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        addRecipe(recipe->NEIIntegration.areItemStacksEqual(recipe.getInItemStack(),ingredient)||recipe.getInFluid()!=null&&recipe.getInFluid().isFluidEqual(ingredient));
    }

    public void drawExtras(int recipe) {
        TemplateRecipeHandler.CachedRecipe crecipe = this.arecipes.get(recipe);
        if (crecipe instanceof CachedBarrelRecipe) {
            NEIIntegration.drawCenteredString(Minecraft.getMinecraft().fontRenderer, ((CachedBarrelRecipe) crecipe).getTechName(), 83, 8, 8519827);
            NEIIntegration.drawCenteredString(Minecraft.getMinecraft().fontRenderer, ((CachedBarrelRecipe) crecipe).getSealTimeName(), 83, 48, 5592405);
            if (((CachedBarrelRecipe) crecipe).getInFluid() != null) {
                drawFluidInRect(((CachedBarrelRecipe) crecipe).getInFluid().getFluid(), recipeInFluidRect());
            }

            if (((CachedBarrelRecipe) crecipe).getOutFluid() != null) {
                drawFluidInRect(((CachedBarrelRecipe) crecipe).getOutFluid().getFluid(), recipeOutFluidRect());
            }
        }

    }
    public ItemStack[] getItemStacksForFluid(FluidStack fluidStack) {
        if (fluidStack == null) return null;
        List<ItemStack> itemStacks = new ArrayList<>();
        //Find proper container for a fluid
        for (FluidContainerRegistry.FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData()) {
            if (data.fluid.isFluidEqual(fluidStack)) {
                ItemStack itemStack = data.filledContainer.copy();
                int cap = FluidContainerRegistry.getContainerCapacity(data.fluid, data.emptyContainer);
                if (cap == 0) itemStack.stackSize = 0;
                else itemStack.stackSize = fluidStack.amount / cap;
                itemStacks.add(itemStack);
            }
        }
        //if no container found, use GT display fluid or a vine(which don't have recipe and usage) with custom NBTTag.
        if (itemStacks.isEmpty()) {
            ItemStack itemStack = new ItemStack(fluidStack.getFluid().getBlock(), fluidStack.amount / 1000);
            if (itemStack.getItem() == null) {
                if(Loader.isModLoaded("gregtech")) itemStack = (gregapi.data.FL.display(fluidStack,true,false).setStackDisplayName(fluidStack.getLocalizedName()));
                else {
                    itemStack = (new ItemStack(TFCBlocks.vine, itemStack.stackSize)).setStackDisplayName(fluidStack.getLocalizedName());
                    itemStack.getTagCompound().setString("FLUID", fluidStack.getFluid().getName());
                }
            }

            itemStacks.add(itemStack);
        }

        return itemStacks.toArray(new ItemStack[0]);
    }
    public static void drawFluidInRect(Fluid fluid, Rectangle rect) {
        GL11.glEnable(GL11.GL_BLEND);
        IIcon fluidIcon = fluid.getIcon();
        if(fluidIcon==null)return;
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        int color = fluid.getColor();
        GL11.glColor4ub((byte)(color >> 16 & 255), (byte)(color >> 8 & 255), (byte)(color & 255), (byte)-86);
        GuiDraw.gui.drawTexturedModelRectFromIcon(rect.x, rect.y, fluidIcon, rect.width, rect.height);
    }
    public List<String> handleItemTooltip(GuiRecipe gui, ItemStack stack, List<String> currenttip, int recipe) {
        TemplateRecipeHandler.CachedRecipe irecipe = this.arecipes.get(recipe);
        if (irecipe instanceof CachedBarrelRecipe) {
            Point mousepos = GuiDraw.getMousePosition();
            Point offset = gui.getRecipePosition(recipe);
            Point relMouse = new Point(mousepos.x - gui.guiLeft - offset.x, mousepos.y - gui.guiTop - offset.y);
            if (recipeOutFluidRect().contains(relMouse) && ((CachedBarrelRecipe) irecipe).getOutFluid() != null) {
                FluidStack fluid = ((CachedBarrelRecipe) irecipe).getOutFluid();

                currenttip.add(fluid.getLocalizedName() + ": " + fluid.amount + "L");
            }

            if (recipeInFluidRect().contains(relMouse) && ((CachedBarrelRecipe) irecipe).getInFluid() != null) {
                FluidStack fluid = ((CachedBarrelRecipe) irecipe).getInFluid();

                currenttip.add(fluid.getLocalizedName() + ": " + fluid.amount + "L");
            }
        }

        return currenttip;
    }

    public boolean keyTyped(GuiRecipe gui, char keyChar, int keyCode, int recipe) {
        if (keyCode == NEIClientConfig.getKeyBinding("gui.recipe")) {
            if (this.transferFluid(gui, recipe, false)) {
                return true;
            }
        } else if (keyCode == NEIClientConfig.getKeyBinding("gui.usage") && this.transferFluid(gui, recipe, true)) {
            return true;
        }

        return super.keyTyped(gui, keyChar, keyCode, recipe);
    }

    public boolean mouseClicked(GuiRecipe gui, int button, int recipe) {
        if (button == 0) {
            if (this.transferFluid(gui, recipe, false)) {
                return true;
            }
        } else if (button == 1 && this.transferFluid(gui, recipe, true)) {
            return true;
        }

        return super.mouseClicked(gui, button, recipe);
    }

    private boolean transferFluid(GuiRecipe gui, int recipe, boolean usage) {
        TemplateRecipeHandler.CachedRecipe crecipe = this.arecipes.get(recipe);
        if (!(crecipe instanceof CachedBarrelRecipe)) return false;
        Point mousepos = GuiDraw.getMousePosition();
        Point offset = gui.getRecipePosition(recipe);
        Point relMouse = new Point(mousepos.x - gui.guiLeft - offset.x, mousepos.y - gui.guiTop - offset.y);
        ItemStack fluidStack = null;
        if (recipeOutFluidRect().contains(relMouse) && ((CachedBarrelRecipe) crecipe).getOutFluid() != null) {
            fluidStack = getItemStacksForFluid(((CachedBarrelRecipe) crecipe).getOutFluid())[0];
        }

        if (recipeInFluidRect().contains(relMouse) && ((CachedBarrelRecipe) crecipe).getInFluid() != null) {
            fluidStack = getItemStacksForFluid(((CachedBarrelRecipe) crecipe).getInFluid())[0];
        }

        if (fluidStack != null) {
            if (usage) return GuiUsageRecipe.openRecipeGui("item", new Object[]{fluidStack});
            else return GuiCraftingRecipe.openRecipeGui("item", new Object[]{fluidStack});
        }

        return false;
    }

    private static Rectangle recipeInFluidRect() {
        return new Rectangle(12, 7, 8, 50);
    }

    private static Rectangle recipeOutFluidRect() {
        return new Rectangle(146, 7, 8, 50);
    }

    public class CachedBarrelRecipe extends TemplateRecipeHandler.CachedRecipe {
        final int minTechLevel;
        final int sealTime;
        PositionedStack inItem;
        PositionedStack outItem;
        final FluidStack inFluid;
        FluidStack outFluid;

        public CachedBarrelRecipe(IBarrelRecipe recipe) {
            super();
            this.minTechLevel = recipe.getMinTechLevel();
            this.sealTime = recipe.isSealedRecipe() ? recipe.getSealTime() : 0;
            this.inFluid = recipe.getInFluid();
            this.outFluid = recipe.getRecipeOutFluid();
            this.setInItem(recipe.getInItemStack());
            this.setOutItem(recipe.getRecipeOutIS());
            if (recipe instanceof BarrelLiquidToLiquidRecipe) {
                this.setInItem(getItemStacksForFluid(((BarrelLiquidToLiquidRecipe) recipe).getInputfluid()));
            }
            if (recipe instanceof BarrelVinegarRecipe) {
                this.setInItem(BarrelRecipeHandler.fruitForVinegar);
            }

            if (recipe instanceof BarrelBriningRecipe) {
                this.outFluid = null;
                this.setInItem(BarrelRecipeHandler.foodToBrine);
                this.setOutItem(BarrelRecipeHandler.foodToBrine);
            }

        }

        public PositionedStack getIngredient() {
            if (this.inItem != null) this.randomRenderPermutation(this.inItem, BarrelRecipeHandler.this.cycleticks / 24);
            return this.inItem;
        }

        public PositionedStack getResult() {
            if (this.outItem != null) this.randomRenderPermutation(this.outItem, BarrelRecipeHandler.this.cycleticks / 24);
            return this.outItem;
        }

        public void setInItem(Object inItem) {
            this.inItem = inItem == null ? null : new PositionedStack(inItem, 39, 24);
        }

        public void setOutItem(Object outItem) {
            this.outItem = outItem == null ? null : new PositionedStack(outItem, 111, 24);
        }

        public FluidStack getInFluid() {
            return this.inFluid;
        }

        public FluidStack getOutFluid() {
            return this.outFluid;
        }

        public String getSealTimeName() {
            return this.sealTime == 0 ? "Instant" : this.sealTime + " hours";
        }

        public String getTechName() {
            switch (this.minTechLevel) {
                case 0:
                    return "Vessel or Barrel";
                case 1:
                    return "Barrel only";
                default:
                    return "Unknown";
            }
        }
    }
}
