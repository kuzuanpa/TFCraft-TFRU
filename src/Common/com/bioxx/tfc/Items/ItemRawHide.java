package com.bioxx.tfc.Items;

import java.util.List;

import com.bioxx.tfc.Blocks.Flora.BlockLogVert;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;

import com.bioxx.tfc.Reference;
import com.bioxx.tfc.Blocks.Flora.BlockLogHoriz;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TileEntities.TELeatherRack;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;

public class ItemRawHide extends ItemLooseRock
{
	public ItemRawHide()
	{
		super();
		this.hasSubtypes = true;
		this.setMaxDamage(0);
		setCreativeTab(TFCTabs.TFC_MATERIALS);
		this.metaNames = new String[]{"small","medium","large"};
		this.setWeight(EnumWeight.LIGHT);
		this.setSize(EnumSize.MEDIUM);
	}


	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			if(itemstack.getItem() == TFCItems.soakedHide && side != ForgeDirection.DOWN.ordinal())
			{
				if((world.getBlock(x, y, z) instanceof BlockLogHoriz || world.getBlock(x, y, z) instanceof BlockLogVert) && world.isAirBlock( x, y + 1, z ) && world.setBlock(x, y+1, z, TFCBlocks.leatherRack))
				{
					TELeatherRack te = (TELeatherRack)world.getTileEntity(x, y+1, z);
					te.setLeather(itemstack);
				}

			}
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World par2World, EntityPlayer entityplayer)
	{
		return itemstack;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return this.itemIcon;
	}

	@Override
	public void registerIcons(IIconRegister registerer)
	{
		this.itemIcon = registerer.registerIcon(Reference.MOD_ID + ":" + textureFolder + this.getUnlocalizedName().replace("item.", ""));
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List list)
	{
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		list.add(new ItemStack(this, 1, 2));
	}

	@Override
	public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist)
	{
		// Blank to override ItemLooseRock's help tooltip.
	}
}
