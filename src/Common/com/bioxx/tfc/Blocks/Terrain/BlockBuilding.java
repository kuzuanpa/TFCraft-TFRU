package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.Tools.ItemHammer;
import com.bioxx.tfc.Reference;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Tools.IToolChisel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.minecraftforge.common.util.ForgeDirection.UP;

public class BlockBuilding extends Block
{
	public BlockBuilding(Material material,float hardness,String unlocalizedName)
	{
		super(material);
		this.setCreativeTab(TFCTabs.TFC_BUILDING);
		this.setHardness(hardness);
		this.setBlockName(unlocalizedName);
		blocks=new ArrayList<>();
		metas= new ArrayList<>();
		myIcons= new ArrayList<>();
	}

	protected ArrayList<Block> blocks;
	protected ArrayList<Integer> metas;
	protected ArrayList<IIcon> myIcons;
	protected int id=0;
	public void addBlock(Block block, int meta){
		blocks.add(block);
		metas.add(meta);
	}
	public void addNewBlock(String name,Material material){
		blocks.add(new BlockBuilding(material,4.0F,name));
		metas.add(id++);
	}
	public int findMeta(String name,int meta){
		for(int i = 0; i < blocks.size(); i++) if(metas.get(i)==meta&&blocks.get(i).getUnlocalizedName().equalsIgnoreCase(name))return i;
		return 0;
	}
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List list)
	{
		for(int i = 0; i < blocks.size(); i++)
			list.add(new ItemStack(this,1,i));
	}
	@Override
	public IIcon getIcon(int i, int j)
	{
		return blocks.get(j)instanceof BlockBuilding? getMyIcon(metas.get(j)):blocks.get(j).getIcon(0,metas.get(j));
	}
	public IIcon getMyIcon(int meta){
		return myIcons.get(meta);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegisterer)
	{
		for(int i = 0; i < id; i++)
			myIcons.add(i,iconRegisterer.registerIcon(Reference.MOD_ID + ":" + "buildingBlocks/"+id));
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		if (blocks.stream().allMatch(block->block instanceof BlockTerra)){
		Block plant = plantable.getPlant(world, x, y + 1, z);
		if (plant == Blocks.cactus && this == Blocks.cactus)
		{
			return true;
		}

		if (plant == Blocks.reeds && this == Blocks.reeds)
		{
			return true;
		}

		EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
		switch (plantType)
		{
			case Cave:   return isSideSolid(world, x, y, z, UP);
			case Plains: return TFC_Core.isSoil(this) || TFC_Core.isFarmland(this);
			case Water:  return world.getBlock(x, y, z).getMaterial() == Material.water && world.getBlockMetadata(x, y, z) == 0;
			case Beach:
				boolean isBeach = TFC_Core.isSand(this) || TFC_Core.isGravel(this);
				boolean hasWater = world.getBlock(x - 1, y, z    ).getMaterial() == Material.water ||
						world.getBlock(x + 1, y, z    ).getMaterial() == Material.water ||
						world.getBlock(x,     y, z - 1).getMaterial() == Material.water ||
						world.getBlock(x,     y, z + 1).getMaterial() == Material.water;
				return isBeach && hasWater;
			default: return false;
		}
		}
		return false;
	}

}
