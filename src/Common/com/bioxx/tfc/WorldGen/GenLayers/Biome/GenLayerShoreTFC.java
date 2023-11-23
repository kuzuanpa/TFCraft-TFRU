package com.bioxx.tfc.WorldGen.GenLayers.Biome;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;

public class GenLayerShoreTFC extends GenLayerTFC
{
	public GenLayerShoreTFC(long par1, GenLayer par3GenLayer)
	{
		super(par1);
		this.parent = (GenLayerTFC) par3GenLayer;
	}

	/**
	 * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
	 * amounts, or biomeList[] indices based on the particular GenLayer subclass.
	 */
	@Override
	public int[] getInts(int par1, int par2, int par3, int par4)
	{
		int[] var5 = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
		int[] var6 = IntCache.getIntCache(par3 * par4);

		for (int var7 = 0; var7 < par4; ++var7)
		{
			for (int var8 = 0; var8 < par3; ++var8)
			{
				this.initChunkSeed(var7 + par1, var8 + par2);
				int var9 = var5[var8 + 1 + (var7 + 1) * (par3 + 2)];
				int var10;
				int var11;
				int var12;
				int var13;

				if ( !TFC_Core.isOceanicBiome(var9) && var9 != TFCBiome.RIVER.biomeID && var9 != TFCBiome.SWAMPLAND.biomeID && var9 != TFCBiome.HIGH_HILLS.biomeID)
				{
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (!TFC_Core.isOceanicBiome(var10) && !TFC_Core.isOceanicBiome(var11) && !TFC_Core.isOceanicBiome(var12) && !TFC_Core.isOceanicBiome(var13))
						var6[var8 + var7 * par3] = var9;
					else
					{
						int beachid = TFCBiome.BEACH.biomeID;
						if(TFC_Core.isMountainBiome(var9))
							beachid = TFCBiome.GRAVEL_BEACH.biomeID;
						var6[var8 + var7 * par3] = beachid;
					}
				}
				else
				{
					var6[var8 + var7 * par3] = var9;
				}

			}
		}
		return var6;
	}
}
