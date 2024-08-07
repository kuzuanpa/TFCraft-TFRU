package com.bioxx.tfc.Core;

import com.bioxx.tfc.Items.ItemIngot;
import cpw.mods.fml.common.Loader;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ModData;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.oredict.OreDictionary;

import com.bioxx.tfc.api.*;
import ua.pp.shurgent.tfctech.core.ModItems;

public class ItemHeat
{

	public static void setupItemHeat()
	{
		HeatRegistry manager = HeatRegistry.getInstance();
		/**
		 * Heat now increases at a base rate of 1C per tick. Specific heat is now just a multiplier on this rate. 
		 * This means that a metlTemp of 100C will be reached in 5 seconds with a Specific Heat of 1.0 and 10 seconds at 2.0
		 */

		final int WILDCARD_VALUE = OreDictionary.WILDCARD_VALUE;

		HeatRaw bismuthRaw = new HeatRaw(0.14, 270);
		HeatRaw bismuthBronzeRaw = new HeatRaw(0.35, 985);
		HeatRaw blackBronzeRaw = new HeatRaw(0.35, 1070);
		HeatRaw blackSteelRaw = new HeatRaw(0.35, 1485);
		HeatRaw blueSteelRaw = new HeatRaw(0.35, 1540);
		HeatRaw brassRaw = new HeatRaw(0.35, 930);
		HeatRaw bronzeRaw = new HeatRaw(0.35, 950);
		HeatRaw copperRaw = new HeatRaw(0.35, 1080);
		HeatRaw goldRaw = new HeatRaw(0.6, 1060);
		HeatRaw ironRaw = new HeatRaw(0.35, 1535);
		HeatRaw leadRaw = new HeatRaw(0.22, 328);
		HeatRaw nickelRaw = new HeatRaw(0.48, 1453);
		HeatRaw pigIronRaw = new HeatRaw(0.35, 1500);
		HeatRaw platinumRaw = new HeatRaw(0.35, 1730);
		HeatRaw redSteelRaw = new HeatRaw(0.35, 1540);
		HeatRaw roseGoldRaw = new HeatRaw(0.35, 960);
		HeatRaw silverRaw = new HeatRaw(0.48, 961);
		HeatRaw steelRaw = new HeatRaw(0.35, 1540);//sh = 0.63F; boil = 3500; melt = 1540;
		HeatRaw sterlingSilverRaw = new HeatRaw(0.35, 900);//sh = 0.72F; boil = 2212; melt = 893;
		HeatRaw tinRaw = new HeatRaw(0.14, 230);
		HeatRaw zincRaw = new HeatRaw(0.21, 420);//sh = 0.66F; boil = 907; melt = 420;
		HeatRaw electrumRaw = new HeatRaw(0.55, 1060);
		HeatRaw cupronickelRaw = new HeatRaw(0.48, 1453);

		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,0), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,1), goldRaw,new ItemStack(TFCItems.goldUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,2), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,3), ironRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,4), silverRaw,new ItemStack(TFCItems.silverUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,5), tinRaw,new ItemStack(TFCItems.tinUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,6), leadRaw,new ItemStack(TFCItems.leadUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,7), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,8), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,9), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,10), ironRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,11), ironRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,12), zincRaw,new ItemStack(TFCItems.zincUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,13), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,35), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,36), goldRaw,new ItemStack(TFCItems.goldUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,37), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,38), ironRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,39), silverRaw,new ItemStack(TFCItems.silverUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,40), tinRaw,new ItemStack(TFCItems.tinUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,41), leadRaw,new ItemStack(TFCItems.leadUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,42), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,43), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,44), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,45), ironRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,46), ironRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,47), zincRaw,new ItemStack(TFCItems.zincUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,48), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,49), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,50), goldRaw,new ItemStack(TFCItems.goldUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,51), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,52), ironRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,53), silverRaw,new ItemStack(TFCItems.silverUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,54), tinRaw,new ItemStack(TFCItems.tinUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,55), leadRaw,new ItemStack(TFCItems.leadUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,56), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,57), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,58), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,59), ironRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,60), ironRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,61), zincRaw,new ItemStack(TFCItems.zincUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk,1,62), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));

		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,0), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,1), goldRaw,new ItemStack(TFCItems.goldUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,2), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,3), ironRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,4), silverRaw,new ItemStack(TFCItems.silverUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,5), tinRaw,new ItemStack(TFCItems.tinUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,6), leadRaw,new ItemStack(TFCItems.leadUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,7), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,8), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,9), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,10), ironRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,11), ironRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,12), zincRaw,new ItemStack(TFCItems.zincUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk,1,13), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));

		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,0), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,2), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,3), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,4), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,5), brassRaw,new ItemStack(TFCItems.brassUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,6), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,7), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,8), goldRaw,new ItemStack(TFCItems.goldUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,9), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,10), leadRaw,new ItemStack(TFCItems.leadUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,11), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,12), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,13), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,14), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,15), roseGoldRaw,new ItemStack(TFCItems.roseGoldUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,16), silverRaw,new ItemStack(TFCItems.silverUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,17), steelRaw,new ItemStack(TFCItems.steelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,18), sterlingSilverRaw,new ItemStack(TFCItems.sterlingSilverUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,19), tinRaw,new ItemStack(TFCItems.tinUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,20), zincRaw,new ItemStack(TFCItems.zincUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,21), electrumRaw,new ItemStack(TFCItems.electrumUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallMetalChunk,1,22), cupronickelRaw,new ItemStack(TFCItems.cupronickelUnshaped,1)));

		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,0), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,2), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,3), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,4), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,5), brassRaw,new ItemStack(TFCItems.brassUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,6), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,7), copperRaw,new ItemStack(TFCItems.copperUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,8), goldRaw,new ItemStack(TFCItems.goldUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,9), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,10), leadRaw,new ItemStack(TFCItems.leadUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,11), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,12), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,13), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,14), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock,1,15), roseGoldRaw,new ItemStack(TFCItems.roseGoldUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock2,1,0), silverRaw,new ItemStack(TFCItems.silverUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock2,1,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock2,1,2), sterlingSilverRaw,new ItemStack(TFCItems.sterlingSilverUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock2,1,3), tinRaw,new ItemStack(TFCItems.tinUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock2,1,4), zincRaw,new ItemStack(TFCItems.zincUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock2,1,5), electrumRaw,new ItemStack(TFCItems.electrumUnshaped,8,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.metalBlock2,1,6), cupronickelRaw,new ItemStack(TFCItems.cupronickelUnshaped,8,0)));

		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.weakSteelUnshaped,1), steelRaw,new ItemStack(TFCItems.weakSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.weakRedSteelUnshaped,1), redSteelRaw,new ItemStack(TFCItems.weakRedSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.weakBlueSteelUnshaped,1), blueSteelRaw,new ItemStack(TFCItems.weakBlueSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonBlackSteelUnshaped,1), blackSteelRaw,new ItemStack(TFCItems.highCarbonBlackSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonBlueSteelUnshaped,1), blueSteelRaw,new ItemStack(TFCItems.highCarbonBlueSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonRedSteelUnshaped,1), redSteelRaw,new ItemStack(TFCItems.highCarbonRedSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonSteelUnshaped,1), steelRaw,new ItemStack(TFCItems.highCarbonSteelUnshaped,1)));

		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.weakSteelIngot,1), steelRaw,new ItemStack(TFCItems.weakSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.weakRedSteelIngot,1), redSteelRaw,new ItemStack(TFCItems.weakRedSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.weakBlueSteelIngot,1), blueSteelRaw,new ItemStack(TFCItems.weakBlueSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonBlackSteelIngot,1), blackSteelRaw,new ItemStack(TFCItems.highCarbonBlackSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonBlueSteelIngot,1), blueSteelRaw,new ItemStack(TFCItems.highCarbonBlueSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonRedSteelIngot,1), redSteelRaw,new ItemStack(TFCItems.highCarbonRedSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonSteelIngot,1), steelRaw,new ItemStack(TFCItems.highCarbonSteelUnshaped,1)));

		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.unknownIngot,1), copperRaw,new ItemStack(TFCItems.unknownUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.unknownUnshaped,1), copperRaw,new ItemStack(TFCItems.unknownUnshaped,1)));

		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronUnshaped,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronIngot,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronIngot2x,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,2)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronSheet,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronSheet2x,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,2)));


		//Food
		//Proteins
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.egg, 1), 1, 600, new ItemStack(TFCItems.eggCooked, 1)).setKeepNBT(true));

		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.porkchopRaw, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.fishRaw, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.beefRaw, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bearRaw, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.chickenRaw, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.soybean, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.eggCooked, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.calamariRaw, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.muttonRaw,1),1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.venisonRaw,1),1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.horseMeatRaw, 1), 1, 1200, null));

		//Dairy
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cheese, 1), 1, 1200, null));

		//Grains
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.maizeEar, 1), 1, 1200, null));

		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wheatDough, 1), 1, 600, new ItemStack(TFCItems.wheatBread, 1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.barleyDough, 1), 1, 600, new ItemStack(TFCItems.barleyBread, 1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oatDough, 1), 1, 600, new ItemStack(TFCItems.oatBread, 1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.ryeDough, 1), 1, 600, new ItemStack(TFCItems.ryeBread, 1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.riceDough, 1), 1, 600, new ItemStack(TFCItems.riceBread, 1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cornmealDough, 1), 1, 600, new ItemStack(TFCItems.cornBread, 1)).setKeepNBT(true));

		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wheatBread, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.barleyBread, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oatBread, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.ryeBread, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.riceBread, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cornBread, 1), 1, 1200, null));

		//Vegetables
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tomato, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.potato, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.onion, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cabbage, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.garlic, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.carrot, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.greenbeans, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.greenBellPepper, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.yellowBellPepper, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redBellPepper, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.squash, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.seaWeed, 1), 1, 1200, null));

		//Fruit
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redApple, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.banana, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.orange, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.greenApple, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.lemon, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.olive, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cherry, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.peach, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.plum, 1), 1, 1200, null));

		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wintergreenBerry, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueberry, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.raspberry, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.strawberry, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackberry, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bunchberry, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cranberry, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.snowberry, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.elderberry, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.gooseberry, 1), 1, 1200, null));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cloudberry, 1), 1, 1200, null));

		//manager.addIndex(new HeatIndex(new ItemStack(TFCItems.MealGeneric, 1, WILDCARD_VALUE), 0.2, 200, new ItemStack(Items.bowl, 1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.anvil3, 1, 0), steelRaw, null));
		//Other
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.stick, 1, WILDCARD_VALUE), 1, 40, new ItemStack(TFCBlocks.torch, 2)));
		if (Loader.isModLoaded("gregtech")) {
			if (Loader.isModLoaded("moegadd")) {
				manager.addIndex(new HeatIndex(ST.make(new ModData("moegadd","MoegAddon"),"CeramicBottleMoldFull",1), 1, 1000, ST.make(new ModData("moegadd","MoegAddon"),"CeramicBottleMoldMoltenGlass",1)));
				manager.addIndex(new HeatIndex(ST.make(new ModData("moegadd","MoegAddon"),"CeramicBlockMoldFull",1), 1, 1000, ST.make(new ModData("moegadd","MoegAddon"),"CeramicBlockMoldMoltenGlass",1)));
			}
			if (MultiTileEntityRegistry.getRegistry("ktfru.multitileentity") != null) manager.addIndex(new HeatIndex(MultiTileEntityRegistry.getRegistry("ktfru.multitileentity").getItem(32762, 1), 0.5, 950, new ItemStack(TFCItems.bronzeUnshaped, 7)).setKeepNBT(true));
			//Compact when GT gives other mod's Copper Ingot
			if (! (OP.ingot.mat(MT.Cu,1).getItem() instanceof ItemIngot))			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperIngot,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));

			manager.addIndex(new HeatIndex(IL.Ceramic_Basin_Raw.get(1), 0.3, 1400, IL.Ceramic_Basin.get(1)).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.get(1)).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Faucet_Raw.get(1), 0.3, 1400, IL.Ceramic_Faucet.get(1)).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Crossing_Raw.get(1), 0.3, 1400, IL.Ceramic_Crossing.get(1)).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Jug_Raw.get(1), 0.3, 1400, IL.Ceramic_Jug.get(1)).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Juicer_Raw.get(1), 0.3, 1400, IL.Juicer.get(1)).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Porcelain_Cup_Raw.get(1), 0.3, 1400, IL.Porcelain_Cup.get(1)).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Measuring_Pot_Raw.get(1), 0.3, 1400, IL.Measuring_Pot.get(1)).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Tap_Raw.get(1), 0.3, 1400, IL.Ceramic_Tap.get(1)).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Funnel_Raw.get(1), 0.3, 1400, IL.Ceramic_Funnel.get(1)).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Bowl_Raw.get(1), 0.3, 1400, IL.Ceramic_Bowl.get(1)).setKeepNBT(false));

			manager.addIndex(new HeatIndex(IL.Ceramic_Ingot_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_01110_01110_01110_01110_01110))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Billet_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_01100_11110_11110_01100_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Chunk_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_11000_11000_00000_00000_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Plate_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_11111_11111_11111_11111_11111))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Tiny_Plate_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_01110_01110_01110_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Bolt_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_00000_00100_00100_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Rod_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_00000_11111_00000_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Long_Rod_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_10000_01000_00100_00010_00001))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Item_Casing_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_11101_11101_11101_00001_11100))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Ring_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_01110_01010_01110_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Gear_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_10101_01110_11011_01110_10101))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Small_Gear_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_01010_11111_01010_11111_01010))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Sword_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00100_01110_01110_01110_01110))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Pickaxe_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_01110_10001_00000_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Spade_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_01110_01110_01110_01010_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Shovel_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00100_01110_01110_01110_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Universal_Spade_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00100_01110_01100_01110_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Axe_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_01110_01110_01000_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Double_Axe_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_11111_11111_10001_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Saw_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_11111_11111_00000_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Hammer_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_01110_01110_01010_01110_01110))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_File_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_01110_01110_01110_00100_00100))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Screwdriver_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_00100_00100_00100_00100))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Chisel_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_01110_00100_00100_00100_00100))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Arrow_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_00100_00100_01110_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Hoe_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_00110_01110_00000_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Sense_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_01111_11111_00000_00000))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Plow_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_11111_11111_11111_11111_00100))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Builderwand_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_00100_11111_01110_01010))).setKeepNBT(false));
			manager.addIndex(new HeatIndex(IL.Ceramic_Nugget_Mold_Raw.get(1), 0.3, 1400, IL.Ceramic_Mold.getWithNBT(1, UT.NBT.make("gt.mold", 0b0_00000_00000_00100_00000_00000))).setKeepNBT(false));


			for (OreDictMaterial mat : new OreDictMaterial[]{MT.RedSteel,MT.BlueSteel,MT.BlackSteel,MT.Pt,MT.Pb, MT.Steel, MT.Cu, MT.WroughtIron, MT.Bronze, MT.Bi, MT.BlackBronze, MT.BismuthBronze, MT.Cupronickel, MT.Ni, MT.Sn, MT.Au, MT.Brass, MT.Electrum, MT.Ag, MT.RoseGold, MT.SterlingSilver, MT.Invar, MT.Al, MT.TinAlloy, MT.Fe,MT.RedAlloy}) {
				long temp = getMeltPoint(mat);
				Item unshaped = getUnshaped(mat);
				manager.addIndex(new HeatIndex(OP.casingMachine.mat(mat, 1),          0.5, temp, new ItemStack(unshaped, 8 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(mat, 1),    0.5, temp, new ItemStack(unshaped, 14)).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(mat, 1), 0.5, temp, new ItemStack(unshaped, 26)).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.casingSmall.mat(mat, 1),            0.5, temp, new ItemStack(unshaped, 1 )).setMinMax(50).setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.foil.mat(mat, 1),                   0.5, temp, new ItemStack(unshaped, 1 )).setMinMax(75).setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.chain.mat(mat, 1),                  0.5, temp, new ItemStack(unshaped, 1 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.ring.mat(mat, 1),                   0.5, temp, new ItemStack(unshaped, 1 )).setMinMax(75).setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.spring.mat(mat, 1),                 0.5, temp, new ItemStack(unshaped, 1 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.springSmall.mat(mat, 1),            0.5, temp, new ItemStack(unshaped, 1 )).setMinMax(75).setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.gearGt.mat(mat, 1),                 0.5, temp, new ItemStack(unshaped, 4 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.stickLong.mat(mat, 1),              0.5, temp, new ItemStack(unshaped, 1 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.plateCurved.mat(mat, 1),            0.5, temp, new ItemStack(unshaped, 1 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(mat, 1),            0.5, temp, new ItemStack(unshaped, 1 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.stick.mat(mat, 1),                  0.5, temp, new ItemStack(unshaped, 1 )).setMinMax(50).setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.plate.mat(mat, 1),                  0.5, temp, new ItemStack(unshaped, 1 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.ingot.mat(mat, 1),                  0.5, temp, new ItemStack(unshaped, 1 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.ingotDouble.mat(mat, 1),            0.5, temp, new ItemStack(unshaped, 2 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.plateDouble.mat(mat, 1),            0.5, temp, new ItemStack(unshaped, 2 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.ingotTriple.mat(mat, 1),            0.5, temp, new ItemStack(unshaped, 3 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(mat, 1),         0.5, temp, new ItemStack(unshaped, 4 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(mat, 1),         0.5, temp, new ItemStack(unshaped, 5 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.plateTriple.mat(mat, 1),            0.5, temp, new ItemStack(unshaped, 3 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(mat, 1),         0.5, temp, new ItemStack(unshaped, 4 )).setMinMax(0) .setKeepNBT(true));
				manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(mat, 1),         0.5, temp, new ItemStack(unshaped, 5 )).setMinMax(0) .setKeepNBT(true));
				if(getUnshaped(mat)!=TFCItems.unknownUnshaped)manager.addIndex(new HeatIndex(new ItemStack(getUnshaped(mat),1), 0.5,temp,new ItemStack(getUnshaped(mat),1)));
			}

		}else{
			//Bismuth
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthIngot,1), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthIngot2x,1), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthSheet,1), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthSheet2x,1), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,2,0)));
			//Bismuth Bronze
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeIngot,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeIngot2x,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeSheet,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeSheet2x,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,2,0)));
			//Black Bronze
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeIngot,1), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeIngot2x, 1), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 2, 0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeSheet,1), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeSheet2x,1), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,2,0)));
			//Black Steel
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelIngot,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelIngot2x,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelSheet,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelSheet2x,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
			//Blue Steel
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelIngot,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelIngot2x,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelSheet,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelSheet2x,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
			//Brass
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassIngot,1), brassRaw,new ItemStack(TFCItems.brassUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassIngot2x,1), brassRaw,new ItemStack(TFCItems.brassUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassSheet,1), brassRaw,new ItemStack(TFCItems.brassUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassSheet2x,1), brassRaw,new ItemStack(TFCItems.brassUnshaped,2,0)));
			//Bronze
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeIngot,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeIngot2x,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeSheet,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeSheet2x,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
			//Copper
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperIngot,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperIngot2x,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperSheet,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperSheet2x,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
			//Gold
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldIngot,1), goldRaw,new ItemStack(TFCItems.goldUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldIngot2x,1), goldRaw,new ItemStack(TFCItems.goldUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldSheet,1), goldRaw,new ItemStack(TFCItems.goldUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldSheet2x,1), goldRaw,new ItemStack(TFCItems.goldUnshaped,2,0)));
			//Wrought Iron
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronIngot,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronIngot2x,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronSheet,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronSheet2x,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
			//Lead
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadIngot,1), leadRaw,new ItemStack(TFCItems.leadUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadIngot2x,1), leadRaw,new ItemStack(TFCItems.leadUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadSheet,1), leadRaw,new ItemStack(TFCItems.leadUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadSheet2x,1), leadRaw,new ItemStack(TFCItems.leadUnshaped,2,0)));
			//Nickel
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelIngot,1), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelIngot2x,1), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelSheet,1), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelSheet2x,1), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,2,0)));
			//Platinum
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumIngot,1), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumIngot2x,1), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumSheet,1), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumSheet2x,1), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,2,0)));
			//Red Steel
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelIngot,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelIngot2x,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelSheet,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelSheet2x,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
			//Rose Gold
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldIngot,1), roseGoldRaw,new ItemStack(TFCItems.roseGoldUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldIngot2x,1), roseGoldRaw,new ItemStack(TFCItems.roseGoldUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldSheet,1), roseGoldRaw,new ItemStack(TFCItems.roseGoldUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldSheet2x,1), roseGoldRaw,new ItemStack(TFCItems.roseGoldUnshaped,2,0)));
			//Silver
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverIngot,1), silverRaw,new ItemStack(TFCItems.silverUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverIngot2x,1), silverRaw,new ItemStack(TFCItems.silverUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverSheet,1), silverRaw,new ItemStack(TFCItems.silverUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverSheet2x,1), silverRaw,new ItemStack(TFCItems.silverUnshaped,2,0)));
			//Steel
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelIngot,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelIngot2x,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelSheet,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelSheet2x,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
			//Sterling Silver
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverIngot,1), sterlingSilverRaw,new ItemStack(TFCItems.sterlingSilverUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverIngot2x,1), sterlingSilverRaw,new ItemStack(TFCItems.sterlingSilverUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverSheet,1), sterlingSilverRaw,new ItemStack(TFCItems.sterlingSilverUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverSheet2x,1), sterlingSilverRaw,new ItemStack(TFCItems.sterlingSilverUnshaped,2,0)));
			//Tin
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinIngot,1), tinRaw,new ItemStack(TFCItems.tinUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinIngot2x,1), tinRaw,new ItemStack(TFCItems.tinUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinSheet,1), tinRaw,new ItemStack(TFCItems.tinUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinSheet2x,1), tinRaw,new ItemStack(TFCItems.tinUnshaped,2,0)));
			//Zinc
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincIngot,1), zincRaw,new ItemStack(TFCItems.zincUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincIngot2x,1), zincRaw,new ItemStack(TFCItems.zincUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincSheet,1), zincRaw,new ItemStack(TFCItems.zincUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincSheet2x,1), zincRaw,new ItemStack(TFCItems.zincUnshaped,2,0)));
			//Electrum
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.electrumIngot,1), electrumRaw,new ItemStack(TFCItems.electrumUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.electrumIngot2x,1), electrumRaw,new ItemStack(TFCItems.electrumUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.electrumSheet,1), electrumRaw,new ItemStack(TFCItems.electrumUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.electrumSheet2x,1), electrumRaw,new ItemStack(TFCItems.electrumUnshaped,2,0)));
			//Cupronickel
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cupronickelIngot,1), cupronickelRaw,new ItemStack(TFCItems.cupronickelUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cupronickelIngot2x,1), cupronickelRaw,new ItemStack(TFCItems.cupronickelUnshaped,2,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cupronickelSheet,1), cupronickelRaw,new ItemStack(TFCItems.cupronickelUnshaped,1,0)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cupronickelSheet2x,1), cupronickelRaw,new ItemStack(TFCItems.cupronickelUnshaped,2,0)));

			//Unshaped
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnshaped,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronUnshaped,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassUnshaped,1), brassRaw,new ItemStack(TFCItems.brassUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 0), goldRaw, null));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldUnshaped,1), goldRaw,new ItemStack(TFCItems.goldUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnshaped,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelUnshaped,1), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnshaped,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadUnshaped,1), leadRaw,new ItemStack(TFCItems.leadUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumUnshaped,1), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverUnshaped,1), silverRaw,new ItemStack(TFCItems.silverUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnshaped,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldUnshaped,1), roseGoldRaw,new ItemStack(TFCItems.roseGoldUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnshaped,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthUnshaped,1), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnshaped,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnshaped,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnshaped,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnshaped,1), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverUnshaped,1), sterlingSilverRaw,new ItemStack(TFCItems.sterlingSilverUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinUnshaped,1), tinRaw,new ItemStack(TFCItems.tinUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincUnshaped,1), zincRaw,new ItemStack(TFCItems.zincUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.electrumUnshaped,1), electrumRaw,new ItemStack(TFCItems.electrumUnshaped,1)));
			manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cupronickelUnshaped,1), cupronickelRaw,new ItemStack(TFCItems.cupronickelUnshaped,1)));


		}
		//Cu

		//Bi
		//Bismuth Bronze
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedHelmet,1,0), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedHelmet,1,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedChestplate,1,0), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedChestplate,1,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedGreaves,1,0), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedGreaves,1,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedBoots,1,0), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedBoots,1,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.anvil2, 1, 1), bismuthBronzeRaw, null));
		//Black Bronze
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedHelmet,1,0), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedHelmet,1,1), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedChestplate,1,0), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedChestplate,1,1), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedGreaves,1,0), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedGreaves,1,1), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedBoots,1,0), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedBoots,1,1), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.anvil2, 1, 2), blackBronzeRaw, null));
		//Black Steel
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedHelmet,1,0), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedHelmet,1,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedChestplate,1,0), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedChestplate,1,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedGreaves,1,0), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedGreaves,1,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedBoots,1,0), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedBoots,1,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		//Blue Steel
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedHelmet,1,0), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedHelmet,1,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedChestplate,1,0), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedChestplate,1,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedGreaves,1,0), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedGreaves,1,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedBoots,1,0), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedBoots,1,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 5), blueSteelRaw, null));
		//Pig Iron
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronIngot,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronIngot2x,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronSheet,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronSheet2x,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,2,0)));
		//Brass
		//Bronze
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedHelmet,1,0), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedHelmet,1,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedChestplate,1,0), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedChestplate,1,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedGreaves,1,0), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedGreaves,1,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedBoots,1,0), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedBoots,1,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		//Cu
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedHelmet,1,0), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedHelmet,1,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedChestplate,1,0), copperRaw,new ItemStack(TFCItems.copperUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedChestplate,1,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedGreaves,1,0), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedGreaves,1,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedBoots,1,0), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedBoots,1,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		//Au
		//Fe
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedHelmet,1,0), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedHelmet,1,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedChestplate,1,0), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedChestplate,1,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedGreaves,1,0), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedGreaves,1,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedBoots,1,0), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedBoots,1,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronKnifeHead, 1), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bloom, 1, WILDCARD_VALUE), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.rawBloom, 1, WILDCARD_VALUE), ironRaw, new ItemStack(TFCItems.unknownUnshaped, 1)));
		//Pt
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 1), platinumRaw, null));
		//RedSteel
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedHelmet,1,0), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedHelmet,1,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedChestplate,1,0), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedChestplate,1,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedGreaves,1,0), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedGreaves,1,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedBoots,1,0), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedBoots,1,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		//RoseGold
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 2), roseGoldRaw, null));
		//silver
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 3), silverRaw, null));
		//steel
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedHelmet,1,0), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedHelmet,1,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedChestplate,1,0), steelRaw,new ItemStack(TFCItems.steelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedChestplate,1,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedGreaves,1,0), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedGreaves,1,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedBoots,1,0), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedBoots,1,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		//Sterling Silver
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 4), sterlingSilverRaw, null));


	}
	protected static long getMeltPoint(OreDictMaterial mat){
		return mat.mMeltingPoint-300;
	}
	protected static Item getUnshaped(OreDictMaterial mat){
		switch (mat.mID){
			case 820 /*Pb*/            :return TFCItems.leadUnshaped;
			case 8630/*Steel*/         :return TFCItems.steelUnshaped;
			case 290 /*Cu*/            :return TFCItems.copperUnshaped;
			case 260 /*Fe*/            :
			case 8643/*WroughtIron*/   :return TFCItems.wroughtIronUnshaped;
			case 8610/*Bronze*/        :return TFCItems.bronzeUnshaped;
			case 830 /*Bi*/            :return TFCItems.bismuthUnshaped;
			case 8611/*BlackBronze*/   :return TFCItems.blackBronzeUnshaped;
			case 8612/*BismuthBronze*/ :return TFCItems.bismuthBronzeUnshaped;
			case 8662/*Cupronickel*/   :return TFCItems.cupronickelUnshaped;
			case 280 /*Ni*/            :return TFCItems.nickelUnshaped;
			case 500 /*Sn*/            :return TFCItems.tinUnshaped;
			case 790 /*Au*/            :return TFCItems.goldUnshaped;
			case 8620/*Brass*/         :return TFCItems.brassUnshaped;
			case 8600/*Electrum*/      :return TFCItems.electrumUnshaped;
			case 470 /*Ag*/            :return TFCItems.silverUnshaped;
			case 8602/*RoseGold*/      :return TFCItems.roseGoldUnshaped;
			case 8601/*SterlingSilver*/:return TFCItems.sterlingSilverUnshaped;
			case 780 /*Pt*/            :return TFCItems.platinumUnshaped;
			case 8661/*Invar*/         :return ModItems.invarUnshaped;
			case 130 /*Al*/            :return ModItems.aluminumUnshaped;
			case 8660/*RedAlloy*/      :return ModItems.redAlloyUnshaped;
			case 8667/*TinAlloy*/      :
			default:return TFCItems.unknownUnshaped;
		}
	}
}
