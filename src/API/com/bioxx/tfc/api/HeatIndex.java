package com.bioxx.tfc.api;

import java.util.Random;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.logging.log4j.Level;

public class HeatIndex
{
	public float specificHeat;
	public float meltTemp;
	public boolean keepNBT;

	private ItemStack output;
	private int outputMin;
	private int outputMax;

	private ItemStack morph;
	public ItemStack input;

	public HeatIndex(ItemStack in, double sh, double melt, ItemStack out)
	{
		if(in==null) FMLLog.log(Level.FATAL,"Heat Index Input == null, this is a bug!");
		input = in;
		specificHeat = (float)sh;
		meltTemp = (float)melt;
		output = out;
	}

	public HeatIndex(ItemStack in, HeatRaw raw)
	{
		input = in;
		specificHeat = raw.specificHeat;
		meltTemp = raw.meltTemp;
	}

	public HeatIndex(ItemStack in, HeatRaw raw, ItemStack out)
	{
		this(in, raw);
		output = out;
	}

	/**
	 * set whether output ItemStack's NBTTag will be overwrite by input ItemStack
	 * set to false to use NBTTag in output ItemStack
	 */
	public HeatIndex setKeepNBT(boolean k)
	{
		keepNBT = k;
		return this;
	}

	public boolean hasOutput(){
		return output != null;
	}

	public Item getOutputItem()
	{
		if(output!= null)
			return output.getItem();
		else return null;
	}

	public int getOutputDamage()
	{
		if(output!= null)
			return output.getItemDamage();
		else return 0;
	}

	public HeatIndex setMinMax(int min, int max)
	{
		outputMin = min;
		outputMax = max;
		return this;
	}

	public HeatIndex setMinMax(int amt)
	{
		outputMin = amt;
		outputMax = amt;
		return this;
	}

	public HeatIndex setMorph(ItemStack is)
	{
		morph = is;
		return this;
	}

	public ItemStack getMorph()
	{
		return morph;
	}

	public ItemStack getOutput(Random r)
	{
		if(getOutputItem() == null)
			return null;
		if(output.getItem().getUnlocalizedName().startsWith("gt.")||output.getItem().getUnlocalizedName().startsWith("ktfru.")) return output;
		int rand = 0;
		if(outputMax - outputMin > 0) 
		{
			rand = outputMin + r.nextInt(outputMax - outputMin);
			return new ItemStack(getOutputItem(),output.stackSize, 100-rand);
		}
		else 
		{
			return new ItemStack(getOutputItem(),output.stackSize, outputMin);
		}
	}

	public ItemStack getOutput(ItemStack in, Random r)
	{
		ItemStack out = getOutput(r);
		if(out != null && this.keepNBT)
		{
			if(out.hasTagCompound())
			{
				NBTTagCompound nbt = out.getTagCompound();
				for(Object o : in.getTagCompound().func_150296_c())
				{
					NBTBase n = (NBTBase)o;
					if(nbt.hasKey(n.toString()))
						nbt.removeTag(n.toString());
					nbt.func_150296_c().add(o);
				}
			}
			else
			{
				out.setTagCompound(in.stackTagCompound);
				if(TFC_ItemHeat.hasTemp(out))
					TFC_ItemHeat.setTemp(out, TFC_ItemHeat.getTemp(out)*0.9f);
			}
		}
		return out;
	}

	public boolean matches(ItemStack is)
	{
		if(is == null||input==null)return false;

		boolean b = is.getItem().getHasSubtypes();
		if((is.getItem() != input.getItem()))
			return false;
		else if (b &&input.getItemDamage() != 32767 && is.getItemDamage() != input.getItemDamage())
			return false;
		return true;
	}
}
