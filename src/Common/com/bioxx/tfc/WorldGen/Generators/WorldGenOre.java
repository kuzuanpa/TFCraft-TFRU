package com.bioxx.tfc.WorldGen.Generators;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.bioxx.tfc.WorldGen.TFCProvider;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import cpw.mods.fml.common.IWorldGenerator;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.Util.CaseInsensitiveHashMap;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.WorldGen.TFCWorldChunkManager;

public class WorldGenOre implements IWorldGenerator
{
	//private int Min;
	//private int Max;
	private int chunkX;
	private int chunkZ;
	private World worldObj;
	private Random random;

	public static Map<String, OreSpawnData> oreList = new CaseInsensitiveHashMap<OreSpawnData>();

	public WorldGenOre()
	{
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{		if(!(world.provider instanceof TFCProvider))return;

		chunkX *= 16;
		chunkZ *= 16;
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
		worldObj = world;
		random = rand;

        for (OreSpawnData osd : oreList.values()) {
            if (osd.type == 0) {
                if (osd.size == 0)
                    oreSmall(osd.block, osd.meta, osd.base, osd.rarity, osd.min, osd.max, osd.vDensity, osd.hDensity, osd.flower, osd.flowerMeta);
                else if (osd.size == 1)
                    oreMedium(osd.block, osd.meta, osd.base, osd.rarity, osd.min, osd.max, osd.vDensity, osd.hDensity, osd.flower, osd.flowerMeta);
                else if (osd.size == 2)
                    oreLarge(osd.block, osd.meta, osd.base, osd.rarity, osd.min, osd.max, osd.vDensity, osd.hDensity, osd.flower, osd.flowerMeta);
            } else if (osd.type == 1) {
                if (osd.size == 0)
                    oreSmallVein(osd.block, osd.meta, osd.base, osd.rarity, osd.min, osd.max, osd.vDensity, osd.hDensity, osd.flower, osd.flowerMeta);
                else if (osd.size == 1)
                    oreMediumVein(osd.block, osd.meta, osd.base, osd.rarity, osd.min, osd.max, osd.vDensity, osd.hDensity, osd.flower, osd.flowerMeta);
                else if (osd.size == 2)
                    oreLargeVein(osd.block, osd.meta, osd.base, osd.rarity, osd.min, osd.max, osd.vDensity, osd.hDensity, osd.flower, osd.flowerMeta);
            }
        }
	}

	private WorldGenMinable oreSmallVein(Block block, int meta, Map<Block, List<Integer>> baseRocks, int rarity, int min, int max, int vDensity, int hDensity, Block flower, int flowerMeta)
	{
		return createOreVeinAndGen(block, meta ,baseRocks,
				/*rarity*/rarity,/*veinSize*/20,/*veinAmt*/30,/*height*/5,/*diameter*/40,/*vDensity*/vDensity,/*hDensity*/hDensity,
 worldObj, random, chunkX, chunkZ, min, max, flower, flowerMeta);
	}

	private WorldGenMinable oreMediumVein(Block block, int meta, Map<Block, List<Integer>> baseRocks, int rarity, int min, int max, int vDensity, int hDensity, Block flower, int flowerMeta)
	{
		return createOreVeinAndGen(block, meta ,baseRocks,
				/*rarity*/rarity,/*veinSize*/30,/*veinAmt*/40,/*height*/10,/*diameter*/60,/*vDensity*/vDensity,/*hDensity*/hDensity,
 worldObj, random, chunkX, chunkZ, min, max, flower, flowerMeta);
	}

	private WorldGenMinable oreLargeVein(Block block, int meta, Map<Block, List<Integer>> baseRocks, int rarity, int min, int max, int vDensity, int hDensity, Block flower, int flowerMeta)
	{
		return createOreVeinAndGen(block, meta ,baseRocks,
				/*rarity*/rarity,/*veinSize*/45,/*veinAmt*/45,/*height*/20,/*diameter*/80,/*vDensity*/vDensity,/*hDensity*/hDensity,
 worldObj, random, chunkX, chunkZ, min, max, flower, flowerMeta);
	}

	private WorldGenMinable oreSmall(Block block, int meta, Map<Block, List<Integer>> baseRocks, int rarity, int min, int max, int vDensity, int hDensity, Block flower, int flowerMeta)
	{
		return createOreAndGen(block, meta, baseRocks,
				/*rarity*/rarity,/*veinSize*/20,/*veinAmt*/30,/*height*/5,/*diameter*/80,/*vDensity*/vDensity,/*hDensity*/hDensity,
 worldObj, random, chunkX, chunkZ, min, max, flower, flowerMeta);
	}

	private WorldGenMinable oreMedium(Block block, int meta, Map<Block, List<Integer>> baseRocks, int rarity, int min, int max, int vDensity, int hDensity, Block flower, int flowerMeta)
	{
		return createOreAndGen(block, meta ,baseRocks,
				/*rarity*/rarity,/*veinSize*/25,/*veinAmt*/40,/*height*/10,/*diameter*/120,/*vDensity*/vDensity,/*hDensity*/hDensity,
 worldObj, random, chunkX, chunkZ, min, max, flower, flowerMeta);
	}

	private WorldGenMinable oreLarge(Block block, int meta, Map<Block, List<Integer>> baseRocks, int rarity, int min, int max, int vDensity, int hDensity, Block flower, int flowerMeta)
	{
		return createOreAndGen(block, meta ,baseRocks,
				/*rarity*/rarity,/*veinSize*/45,/*veinAmt*/40,/*height*/5,/*diameter*/120,/*vDensity*/vDensity,/*hDensity*/hDensity,
 worldObj, random, chunkX, chunkZ, min, max, flower, flowerMeta);
	}

	private static WorldGenMinable createOreAndGen(Block block, int j, Map<Block, List<Integer>> layers, int rarity, int veinSize,
												   int veinAmount, int height, int diameter, int vDensity, int hDensity, World world, Random rand, int chunkX, int chunkZ, int min, int max, Block flower, int meta)
	{
		if(world.getWorldChunkManager() instanceof TFCWorldChunkManager)
		{
			for(Block b : layers.keySet())
			{
				for(int metadata : layers.get(b))
				{
					DataLayer rockLayer1 = TFC_Climate.getCacheManager(world).getRockLayerAt(chunkX, chunkZ, 0);
					DataLayer rockLayer2 = TFC_Climate.getCacheManager(world).getRockLayerAt(chunkX, chunkZ, 1);
					DataLayer rockLayer3 = TFC_Climate.getCacheManager(world).getRockLayerAt(chunkX, chunkZ, 2);
					if (rockLayer1.block == b && (rockLayer1.data2 == metadata || metadata == -1) ||
						rockLayer2.block == b && (rockLayer2.data2 == metadata || metadata == -1) ||
						rockLayer3.block == b && (rockLayer3.data2 == metadata || metadata == -1))
					{
						int grade = rand.nextInt(100);
						if(OreSpawnData.isOreGradable(j)){
							if(grade<20)j+=35;
							else if(grade<50)j+=49;
						}
						return new WorldGenMinable(block, j, b, metadata, rarity, veinSize, veinAmount, height, diameter, vDensity, hDensity, false, 0, flower,meta)
								.generate(world, rand, chunkX, chunkZ, min, max);
					}
				}
			}
		}
		return null;
	}

	// new int[]{TFCBlocks.StoneIgEx,-1,Blocks.sandstone,-1}
	private static WorldGenMinable createOreVeinAndGen(Block block, int j, Map<Block, List<Integer>> layers, int rarity, int veinSize,
													   int veinAmount, int height, int diameter, int vDensity, int hDensity, World world, Random rand, int chunkX, int chunkZ, int min, int max, Block flower, int meta)
	{
		if(world.getWorldChunkManager() instanceof TFCWorldChunkManager)
		{
			for(Block b : layers.keySet())
			{
				for (int metadata : layers.get(b))
				{
					DataLayer rockLayer1 = TFC_Climate.getCacheManager(world).getRockLayerAt(chunkX, chunkZ, 0);
					DataLayer rockLayer2 = TFC_Climate.getCacheManager(world).getRockLayerAt(chunkX, chunkZ, 1);
					DataLayer rockLayer3 = TFC_Climate.getCacheManager(world).getRockLayerAt(chunkX, chunkZ, 2);
					if (rockLayer1.block == b && (rockLayer1.data2 == metadata || metadata == -1) ||
						rockLayer2.block == b && (rockLayer2.data2 == metadata || metadata == -1) ||
						rockLayer3.block == b && (rockLayer3.data2 == metadata || metadata == -1))
					{
						int grade = rand.nextInt(100);
						if(OreSpawnData.isOreGradable(j)){
							if(grade<20)j+=35;
							else if(grade<50)j+=49;
						}

						return new WorldGenMinable(block, j, b, metadata, rarity, veinSize, veinAmount, height, diameter, vDensity, hDensity, true, 0, flower, meta)
								.generate(world, rand, chunkX, chunkZ, min, max);
					}
				}
			}
		}
		return null;
	}
}
