package com.bioxx.tfc.Core;

import cpw.mods.fml.common.FMLLog;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictMaterial;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import net.minecraftforge.oredict.OreDictionary;

import com.bioxx.tfc.api.*;
import org.apache.logging.log4j.Level;

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

		//Bismuth
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthIngot,1), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthIngot2x,1), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthUnshaped,1), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthSheet,1), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthSheet2x,1), bismuthRaw,new ItemStack(TFCItems.bismuthUnshaped,2,0)));
		//Bismuth Bronze
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeIngot,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeIngot2x,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnshaped,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeSheet,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeSheet2x,1), bismuthBronzeRaw,new ItemStack(TFCItems.bismuthBronzeUnshaped,2,0)));
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
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeIngot,1), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeIngot2x, 1), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 2, 0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnshaped,1), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeSheet,1), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeSheet2x,1), blackBronzeRaw,new ItemStack(TFCItems.blackBronzeUnshaped,2,0)));
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
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelIngot,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelIngot2x,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnshaped,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelSheet,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelSheet2x,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedHelmet,1,0), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedHelmet,1,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedChestplate,1,0), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedChestplate,1,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedGreaves,1,0), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedGreaves,1,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedBoots,1,0), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedBoots,1,1), blackSteelRaw,new ItemStack(TFCItems.blackSteelUnshaped,2,0)));
		
		//Blue Steel
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelIngot,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelIngot2x,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnshaped,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelSheet,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelSheet2x,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedHelmet,1,0), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedHelmet,1,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedChestplate,1,0), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedChestplate,1,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedGreaves,1,0), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedGreaves,1,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedBoots,1,0), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedBoots,1,1), blueSteelRaw,new ItemStack(TFCItems.blueSteelUnshaped,2,0)));
		
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 5), blueSteelRaw, null));
		//Brass
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassIngot,1), brassRaw,new ItemStack(TFCItems.brassUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassIngot2x,1), brassRaw,new ItemStack(TFCItems.brassUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassUnshaped,1), brassRaw,new ItemStack(TFCItems.brassUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassSheet,1), brassRaw,new ItemStack(TFCItems.brassUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassSheet2x,1), brassRaw,new ItemStack(TFCItems.brassUnshaped,2,0)));
		//Bronze
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeIngot,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeIngot2x,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnshaped,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeSheet,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeSheet2x,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedHelmet,1,0), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedHelmet,1,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedChestplate,1,0), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedChestplate,1,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedGreaves,1,0), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedGreaves,1,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedBoots,1,0), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedBoots,1,1), bronzeRaw,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
		
		//Copper
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperIngot,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperIngot2x,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnshaped,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperSheet,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperSheet2x,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedHelmet,1,0), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedHelmet,1,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedChestplate,1,0), copperRaw,new ItemStack(TFCItems.copperUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedChestplate,1,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedGreaves,1,0), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedGreaves,1,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedBoots,1,0), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedBoots,1,1), copperRaw,new ItemStack(TFCItems.copperUnshaped,2,0)));
		
		//Gold
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldIngot,1), goldRaw,new ItemStack(TFCItems.goldUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldIngot2x,1), goldRaw,new ItemStack(TFCItems.goldUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldUnshaped,1), goldRaw,new ItemStack(TFCItems.goldUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldSheet,1), goldRaw,new ItemStack(TFCItems.goldUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldSheet2x,1), goldRaw,new ItemStack(TFCItems.goldUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 0), goldRaw, null));
		//Wrought Iron
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronIngot,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,1)));

		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bloom, 1, WILDCARD_VALUE), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.rawBloom, 1, WILDCARD_VALUE), ironRaw, new ItemStack(TFCItems.unknownUnshaped, 1)));

		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronIngot2x,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnshaped,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronSheet,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronSheet2x,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedHelmet,1,0), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedHelmet,1,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedChestplate,1,0), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedChestplate,1,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedGreaves,1,0), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedGreaves,1,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedBoots,1,0), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedBoots,1,1), ironRaw,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronKnifeHead, 1), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 1)));
		
		//Lead
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadIngot,1), leadRaw,new ItemStack(TFCItems.leadUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadIngot2x,1), leadRaw,new ItemStack(TFCItems.leadUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadUnshaped,1), leadRaw,new ItemStack(TFCItems.leadUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadSheet,1), leadRaw,new ItemStack(TFCItems.leadUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadSheet2x,1), leadRaw,new ItemStack(TFCItems.leadUnshaped,2,0)));
		//Nickel
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelIngot,1), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelIngot2x,1), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelUnshaped,1), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelSheet,1), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelSheet2x,1), nickelRaw,new ItemStack(TFCItems.nickelUnshaped,2,0)));
		//Pig Iron
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronIngot,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronIngot2x,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronUnshaped,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronSheet,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronSheet2x,1), pigIronRaw,new ItemStack(TFCItems.pigIronUnshaped,2,0)));
		//Platinum
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumIngot,1), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumIngot2x,1), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumUnshaped,1), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumSheet,1), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumSheet2x,1), platinumRaw,new ItemStack(TFCItems.platinumUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 1), platinumRaw, null));
		//Red Steel
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelIngot,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelIngot2x,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnshaped,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelSheet,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelSheet2x,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedHelmet,1,0), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedHelmet,1,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedChestplate,1,0), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedChestplate,1,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedGreaves,1,0), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedGreaves,1,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedBoots,1,0), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedBoots,1,1), redSteelRaw,new ItemStack(TFCItems.redSteelUnshaped,2,0)));
		
		//Rose Gold
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldIngot,1), roseGoldRaw,new ItemStack(TFCItems.roseGoldUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldIngot2x,1), roseGoldRaw,new ItemStack(TFCItems.roseGoldUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldUnshaped,1), roseGoldRaw,new ItemStack(TFCItems.roseGoldUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldSheet,1), roseGoldRaw,new ItemStack(TFCItems.roseGoldUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldSheet2x,1), roseGoldRaw,new ItemStack(TFCItems.roseGoldUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 2), roseGoldRaw, null));
		//Silver
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverIngot,1), silverRaw,new ItemStack(TFCItems.silverUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverIngot2x,1), silverRaw,new ItemStack(TFCItems.silverUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverUnshaped,1), silverRaw,new ItemStack(TFCItems.silverUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverSheet,1), silverRaw,new ItemStack(TFCItems.silverUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverSheet2x,1), silverRaw,new ItemStack(TFCItems.silverUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 3), silverRaw, null));
		//Steel
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelIngot,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelIngot2x,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnshaped,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelSheet,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelSheet2x,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedHelmet,1,0), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedHelmet,1,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedChestplate,1,0), steelRaw,new ItemStack(TFCItems.steelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedChestplate,1,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,4,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedGreaves,1,0), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedGreaves,1,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedBoots,1,0), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedBoots,1,1), steelRaw,new ItemStack(TFCItems.steelUnshaped,2,0)));
		
		//Sterling Silver
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverIngot,1), sterlingSilverRaw,new ItemStack(TFCItems.sterlingSilverUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverIngot2x,1), sterlingSilverRaw,new ItemStack(TFCItems.sterlingSilverUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverUnshaped,1), sterlingSilverRaw,new ItemStack(TFCItems.sterlingSilverUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverSheet,1), sterlingSilverRaw,new ItemStack(TFCItems.sterlingSilverUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverSheet2x,1), sterlingSilverRaw,new ItemStack(TFCItems.sterlingSilverUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 4), sterlingSilverRaw, null));
		//Tin
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinIngot,1), tinRaw,new ItemStack(TFCItems.tinUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinIngot2x,1), tinRaw,new ItemStack(TFCItems.tinUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinUnshaped,1), tinRaw,new ItemStack(TFCItems.tinUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinSheet,1), tinRaw,new ItemStack(TFCItems.tinUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinSheet2x,1), tinRaw,new ItemStack(TFCItems.tinUnshaped,2,0)));
		//Zinc
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincIngot,1), zincRaw,new ItemStack(TFCItems.zincUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincIngot2x,1), zincRaw,new ItemStack(TFCItems.zincUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincUnshaped,1), zincRaw,new ItemStack(TFCItems.zincUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincSheet,1), zincRaw,new ItemStack(TFCItems.zincUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincSheet2x,1), zincRaw,new ItemStack(TFCItems.zincUnshaped,2,0)));
		//Electrum
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.electrumIngot,1), electrumRaw,new ItemStack(TFCItems.electrumUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.electrumIngot2x,1), electrumRaw,new ItemStack(TFCItems.electrumUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.electrumUnshaped,1), electrumRaw,new ItemStack(TFCItems.electrumUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.electrumSheet,1), electrumRaw,new ItemStack(TFCItems.electrumUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.electrumSheet2x,1), electrumRaw,new ItemStack(TFCItems.electrumUnshaped,2,0)));
		//Cupronickel
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cupronickelIngot,1), cupronickelRaw,new ItemStack(TFCItems.cupronickelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cupronickelIngot2x,1), cupronickelRaw,new ItemStack(TFCItems.cupronickelUnshaped,2,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cupronickelUnshaped,1), cupronickelRaw,new ItemStack(TFCItems.cupronickelUnshaped,1)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cupronickelSheet,1), cupronickelRaw,new ItemStack(TFCItems.cupronickelUnshaped,1,0)));
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cupronickelSheet2x,1), cupronickelRaw,new ItemStack(TFCItems.cupronickelUnshaped,2,0)));

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

		//Other
		manager.addIndex(new HeatIndex(new ItemStack(TFCItems.stick, 1, WILDCARD_VALUE), 1, 40, new ItemStack(TFCBlocks.torch, 2)));

		manager.addIndex(new HeatIndex(OP.plate.mat(MT.Invar,1),1,1643,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plate.mat(MT.Ge,1),1,938,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plate.mat(MT.Co,1),1,1495,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plate.mat(MT.AluminiumBrass,1),1,766,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plate.mat(MT.Al,1),1,663,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plate.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plate.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.Invar,1),1,1643,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.Ge,1),1,938,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.Co,1),1,1495,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.AluminiumBrass,1),1,766,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.Al,1),1,663,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.StainlessSteel,1),1,1670,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.Magnalium,1),1,656,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.TinAlloy,1),1,985,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,8 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,8 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,8 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,8 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,8 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,8 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,8 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,8 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,8 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,8 )).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachine.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,8)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,14 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,14 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,14 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,14 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,14 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,14 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,14 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,14 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,14 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,14 )).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,14)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineDouble.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,14)).setKeepNBT(true));



		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,26 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,26 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,26 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,26 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,26 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,26 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,26 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,26 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,26 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,26 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingMachineQuadruple.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,26)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Pb,2),0.5,328,new ItemStack(TFCItems.leadUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Steel,2),0.5,1540,new ItemStack(TFCItems.steelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Cu,2),0.5,1080,new ItemStack(TFCItems.copperUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.WroughtIron,2),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Bronze,2),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Bi,2),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.BlackBronze,2),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.BismuthBronze,2),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Constantan,2),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Ni,2),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Sn,2),0.5,230,new ItemStack(TFCItems.tinUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Au,2),0.5,1060,new ItemStack(TFCItems.goldUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Brass,2),0.5,930,new ItemStack(TFCItems.brassUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Electrum,2),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Ag,2),0.5,961,new ItemStack(TFCItems.silverUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.RoseGold,2),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.SterlingSilver,2),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Invar,2),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Ge,2),0.5,938,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Co,2),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.AluminiumBrass,2),0.5,766,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Al,2),0.5,663,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.StainlessSteel,2),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.Magnalium,2),0.5,656,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.casingSmall.mat(MT.TinAlloy,2),0.5,985,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));


		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Pb,4),0.5,328,new ItemStack(TFCItems.leadUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Steel,4),0.5,1540,new ItemStack(TFCItems.steelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Cu,4),0.5,1080,new ItemStack(TFCItems.copperUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.WroughtIron,4),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Bronze,4),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Bi,4),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.BlackBronze,4),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.BismuthBronze,4),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Constantan,4),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Ni,4),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Sn,4),0.5,230,new ItemStack(TFCItems.tinUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Au,4),0.5,1060,new ItemStack(TFCItems.goldUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Brass,4),0.5,930,new ItemStack(TFCItems.brassUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Electrum,4),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Ag,4),0.5,961,new ItemStack(TFCItems.silverUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.RoseGold,4),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.SterlingSilver,4),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Invar,4),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Ge,4),0.5,938,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Co,4),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.AluminiumBrass,4),0.5,766,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Al,4),0.5,663,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.StainlessSteel,4),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.Magnalium,4),0.5,656,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.foil.mat(MT.TinAlloy,4),0.5,985,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.chain.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Pb,4),0.5,328,new ItemStack(TFCItems.leadUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Steel,4),0.5,1540,new ItemStack(TFCItems.steelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Cu,4),0.5,1080,new ItemStack(TFCItems.copperUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.WroughtIron,4),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Bronze,4),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Bi,4),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.BlackBronze,4),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.BismuthBronze,4),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Constantan,4),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Ni,4),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Sn,4),0.5,230,new ItemStack(TFCItems.tinUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Au,4),0.5,1060,new ItemStack(TFCItems.goldUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Brass,4),0.5,930,new ItemStack(TFCItems.brassUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Electrum,4),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Ag,4),0.5,961,new ItemStack(TFCItems.silverUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.RoseGold,4),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.SterlingSilver,4),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Invar,4),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Ge,4),0.5,938,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Co,4),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.AluminiumBrass,4),0.5,766,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Al,4),0.5,663,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.StainlessSteel,4),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.Magnalium,4),0.5,656,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ring.mat(MT.TinAlloy,4),0.5,985,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.spring.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));


		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Pb,4),0.5,328,new ItemStack(TFCItems.leadUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Steel,4),0.5,1540,new ItemStack(TFCItems.steelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Cu,4),0.5,1080,new ItemStack(TFCItems.copperUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.WroughtIron,4),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Bronze,4),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Bi,4),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.BlackBronze,4),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.BismuthBronze,4),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Constantan,4),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Ni,4),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Sn,4),0.5,230,new ItemStack(TFCItems.tinUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Au,4),0.5,1060,new ItemStack(TFCItems.goldUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Brass,4),0.5,930,new ItemStack(TFCItems.brassUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Electrum,4),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Ag,4),0.5,961,new ItemStack(TFCItems.silverUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.RoseGold,4),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.SterlingSilver,4),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Invar,4),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Ge,4),0.5,938,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Co,4),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.AluminiumBrass,4),0.5,766,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Al,4),0.5,663,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.StainlessSteel,4),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.Magnalium,4),0.5,656,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.springSmall.mat(MT.TinAlloy,4),0.5,985,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));


		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGt.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));



		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stickLong.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateCurved.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.gearGtSmall.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Pb,2),0.5,328,new ItemStack(TFCItems.leadUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Steel,2),0.5,1540,new ItemStack(TFCItems.steelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Cu,2),0.5,1080,new ItemStack(TFCItems.copperUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.WroughtIron,2),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Bronze,2),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Bi,2),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.BlackBronze,2),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.BismuthBronze,2),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Constantan,2),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Ni,2),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Sn,2),0.5,230,new ItemStack(TFCItems.tinUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Au,2),0.5,1060,new ItemStack(TFCItems.goldUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Brass,2),0.5,930,new ItemStack(TFCItems.brassUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Electrum,2),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Ag,2),0.5,961,new ItemStack(TFCItems.silverUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.RoseGold,2),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.SterlingSilver,2),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Invar,2),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Ge,2),0.5,938,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Co,2),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.AluminiumBrass,2),0.5,766,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Al,2),0.5,663,new ItemStack(TFCItems.unknownUnshaped,1 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.StainlessSteel,2),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.Magnalium,2),0.5,656,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.stick.mat(MT.TinAlloy,2),0.5,985,new ItemStack(TFCItems.unknownUnshaped,1)).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.plate.mat(MT.Invar,1),1,1643,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plate.mat(MT.Ge,1),1,938,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plate.mat(MT.Co,1),1,1495,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plate.mat(MT.AluminiumBrass,1),1,766,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plate.mat(MT.Al,1),1,663,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plate.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plate.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.Invar,1),1,1643,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.Ge,1),1,938,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.Co,1),1,1495,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.AluminiumBrass,1),1,766,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.Al,1),1,663,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.StainlessSteel,1),1,1670,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.Magnalium,1),1,656,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingot.mat(MT.TinAlloy,1),1,985,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.ingotDouble.mat(MT.Invar,1),1,1643,new ItemStack(TFCItems.unknownUnshaped,2)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotDouble.mat(MT.Ge,1),1,938,new ItemStack(TFCItems.unknownUnshaped,2 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotDouble.mat(MT.Co,1),1,1495,new ItemStack(TFCItems.unknownUnshaped,2 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotDouble.mat(MT.AluminiumBrass,1),1,766,new ItemStack(TFCItems.unknownUnshaped,2)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotDouble.mat(MT.Al,1),1,663,new ItemStack(TFCItems.unknownUnshaped,2 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotDouble.mat(MT.StainlessSteel,1),1,1670,new ItemStack(TFCItems.unknownUnshaped,2)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotDouble.mat(MT.Magnalium,1),1,656,new ItemStack(TFCItems.unknownUnshaped,2)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotDouble.mat(MT.TinAlloy,1),1,985,new ItemStack(TFCItems.unknownUnshaped,2)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateDouble.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,2)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateDouble.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,2 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateDouble.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,2 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateDouble.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,2)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateDouble.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,2 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateDouble.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,2)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateDouble.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,2)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateDouble.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,2)).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotTriple.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,3)).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuadruple.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,5 )).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.ingotQuintuple.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,3 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,3 )).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateTriple.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,3)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,4 )).setKeepNBT(true));

		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuadruple.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,4)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Pb,1),0.5,328,new ItemStack(TFCItems.leadUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Steel,1),0.5,1540,new ItemStack(TFCItems.steelUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Cu,1),0.5,1080,new ItemStack(TFCItems.copperUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.WroughtIron,1),0.5,1535,new ItemStack(TFCItems.wroughtIronUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Bronze,1),0.5,950,new ItemStack(TFCItems.bronzeUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Bi,1),0.5,270,new ItemStack(TFCItems.bismuthUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.BlackBronze,1),0.5,1070,new ItemStack(TFCItems.blackBronzeUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.BismuthBronze,1),0.5,985,new ItemStack(TFCItems.bismuthBronzeUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Constantan,1),0.5,1300,new ItemStack(TFCItems.cupronickelUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Ni,1),0.5,1453,new ItemStack(TFCItems.nickelUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Sn,1),0.5,230,new ItemStack(TFCItems.tinUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Au,1),0.5,1060,new ItemStack(TFCItems.goldUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Brass,1),0.5,930,new ItemStack(TFCItems.brassUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Electrum,1),0.5,1030,new ItemStack(TFCItems.electrumUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Ag,1),0.5,961,new ItemStack(TFCItems.silverUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.RoseGold,1),0.5,960,new ItemStack(TFCItems.roseGoldUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.SterlingSilver,1),0.5,900,new ItemStack(TFCItems.sterlingSilverUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Invar,1),0.5,1643,new ItemStack(TFCItems.unknownUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Ge,1),0.5,938,new ItemStack(TFCItems.unknownUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Co,1),0.5,1495,new ItemStack(TFCItems.unknownUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.AluminiumBrass,1),0.5,766,new ItemStack(TFCItems.unknownUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Al,1),0.5,663,new ItemStack(TFCItems.unknownUnshaped,5 )).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.StainlessSteel,1),0.5,1670,new ItemStack(TFCItems.unknownUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.Magnalium,1),0.5,656,new ItemStack(TFCItems.unknownUnshaped,5)).setKeepNBT(true));
		manager.addIndex(new HeatIndex(OP.plateQuintuple.mat(MT.TinAlloy,1),0.5,985,new ItemStack(TFCItems.unknownUnshaped,5)).setKeepNBT(true));








	}
}
