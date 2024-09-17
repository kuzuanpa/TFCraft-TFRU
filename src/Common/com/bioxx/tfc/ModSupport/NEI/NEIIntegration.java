package com.bioxx.tfc.ModSupport.NEI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import codechicken.lib.gui.GuiDraw;
import com.bioxx.tfc.ModSupport.NEI.recipeHandlers.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import codechicken.nei.api.API;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerTooltipHandler;
import com.bioxx.tfc.GUI.GuiBarrel;
import com.bioxx.tfc.GUI.GuiLargeVessel;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import org.lwjgl.opengl.GL11;

public class NEIIntegration
{
	public static final AnvilRecipeHandler anvilRecipeHandler = new AnvilRecipeHandler();
	public static final QuernRecipeHandler quernRecipeHandler = new QuernRecipeHandler();
	public static final KilnRecipeHandler kilnRecipeHandler = new KilnRecipeHandler();
	public static final KnappingRecipeHandler knappingRecipeHandler = new KnappingRecipeHandler();
	public static final BarrelRecipeHandler barrelRecipeHandler = new BarrelRecipeHandler();
	public static final AlloyRecipeHandler alloyRecipeHandler = new AlloyRecipeHandler();
	public static final HeatRecipeHandler heatRecipeHandler = new HeatRecipeHandler();


	public static void run(){
		API.registerRecipeHandler(anvilRecipeHandler);
		API.registerRecipeHandler(quernRecipeHandler);
		API.registerRecipeHandler(kilnRecipeHandler);
		API.registerRecipeHandler(knappingRecipeHandler);
		API.registerRecipeHandler(barrelRecipeHandler);
		API.registerRecipeHandler(alloyRecipeHandler);
		API.registerRecipeHandler(heatRecipeHandler);
		API.registerUsageHandler(anvilRecipeHandler);
		API.registerUsageHandler(quernRecipeHandler);
		API.registerUsageHandler(kilnRecipeHandler);
		API.registerUsageHandler(knappingRecipeHandler);
		API.registerUsageHandler(barrelRecipeHandler);
		API.registerUsageHandler(alloyRecipeHandler);
		API.registerUsageHandler(heatRecipeHandler);
	}
	//utils

	public static void drawCenteredString(FontRenderer fontrenderer, String s, int i, int j, int k) {
		fontrenderer.drawString(s, i - fontrenderer.getStringWidth(s) / 2, j, k);
	}

	public static boolean areItemStacksEqual(ItemStack input, ItemStack target) {
		return input == target || OreDictionary.itemMatches(target, input, false);
	}

	/**
	 * @author Dries007
	 */
	public static void hideNEIItems()
	{
		GuiContainerManager.addTooltipHandler(new IContainerTooltipHandler()
		{
			@Override
			public List<String> handleTooltip(GuiContainer gui, int mousex, int mousey, List<String> currenttip)
			{
				return currenttip;
			}

			@Override
			public List<String> handleItemDisplayName(GuiContainer gui, ItemStack itemstack, List<String> currenttip)
			{
				return currenttip;
			}

			@Override
			public List<String> handleItemTooltip(GuiContainer gui, ItemStack itemstack, int mousex, int mousey, List<String> currenttip)
			{
				if (gui instanceof GuiLargeVessel || gui instanceof GuiBarrel)
				{
					Slot slot = gui.getSlotAtPosition(mousex, mousey);
					if (slot != null && !slot.func_111238_b()) currenttip.clear();
				}
				return currenttip;
			}
		});

		if (TFCOptions.enableNEIHiding)
		{
			API.hideItem(new ItemStack(TFCBlocks.bloom));
			//codechicken.nei.api.API.hideItem(new ItemStack(TFCItems.writabeBookTFC)); // Book
			API.hideItem(new ItemStack(TFCBlocks.charcoal));
			//codechicken.nei.api.API.hideItem(new ItemStack(TFCItems.Coke));
			API.hideItem(new ItemStack(TFCBlocks.crops));
			API.hideItem(new ItemStack(TFCBlocks.detailed));
			API.hideItem(new ItemStack(TFCBlocks.worldItem)); // Debris
			for (Block door : TFCBlocks.doors)
			{
				API.hideItem(new ItemStack(door));
			}
			API.hideItem(new ItemStack(TFCBlocks.firepit));
			API.hideItem(new ItemStack(TFCItems.flatClay, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCItems.flatLeather));
			API.hideItem(new ItemStack(TFCItems.flatRock, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.foodPrep));
			API.hideItem(new ItemStack(TFCBlocks.forge));
			API.hideItem(new ItemStack(TFCBlocks.fruitTreeLeaves, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.fruitTreeLeaves2, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.fruitTreeWood, 1, OreDictionary.WILDCARD_VALUE));
			//codechicken.nei.api.API.hideItem(new ItemStack(TFCItems.Hemp));
			API.hideItem(new ItemStack(TFCBlocks.ingotPile));
			API.hideItem(new ItemStack(TFCBlocks.leatherRack));
			API.hideItem(new ItemStack(TFCBlocks.leaves, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.leaves2, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.logNatural, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.logNatural2, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.logPile));
			API.hideItem(new ItemStack(TFCBlocks.woodConstruct)); // Lumber Block
			API.hideItem(new ItemStack(TFCBlocks.metalSheet));
			API.hideItem(new ItemStack(TFCBlocks.molten));
			API.hideItem(new ItemStack(TFCBlocks.moss));
			//codechicken.nei.api.API.hideItem(new ItemStack(TFCItems.MudBrick, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.ore));
			API.hideItem(new ItemStack(TFCBlocks.ore2));
			API.hideItem(new ItemStack(TFCBlocks.ore3));
			API.hideItem(new ItemStack(TFCBlocks.pottery));
			//codechicken.nei.api.API.hideItem(new ItemStack(TFCItems.PotteryPot, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.reeds));
			API.hideItem(new ItemStack(TFCItems.salad, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCItems.sandwich, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.sluice));
			API.hideItem(new ItemStack(TFCBlocks.smoke));
			API.hideItem(new ItemStack(TFCBlocks.smokeRack));
			//codechicken.nei.api.API.hideItem(new ItemStack(TFCItems.Soup, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.stoneSlabs));
			API.hideItem(new ItemStack(TFCBlocks.stoneStairs));
			API.hideItem(new ItemStack(TFCBlocks.stoneStalac));
			API.hideItem(new ItemStack(TFCBlocks.strawHideBed));
			API.hideItem(new ItemStack(TFCBlocks.sulfur));
			API.hideItem(new ItemStack(TFCBlocks.torchOff));
			API.hideItem(new ItemStack(TFCBlocks.waterPlant));
			API.hideItem(new ItemStack(TFCBlocks.woodHoriz, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.woodHoriz2, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.woodHoriz3, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.woodHoriz4, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.woodSupportH, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.woodSupportH2, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.woodVert, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.woodVert2, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(TFCBlocks.freshWater));
			API.hideItem(new ItemStack(TFCBlocks.freshWaterStationary));
			API.hideItem(new ItemStack(TFCBlocks.saltWater));
			API.hideItem(new ItemStack(TFCBlocks.saltWaterStationary));
			API.hideItem(new ItemStack(TFCBlocks.hotWater));
			API.hideItem(new ItemStack(TFCBlocks.hotWaterStationary));
			API.hideItem(new ItemStack(TFCBlocks.lava));
			API.hideItem(new ItemStack(TFCBlocks.lavaStationary));
			API.hideItem(new ItemStack(TFCBlocks.freshWater));

		}
	}
}
