package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Reference;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEOre;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

import static com.bioxx.tfc.Render.TFC_CoreRender.getRockTexture;

public class BlockOre extends BlockCollapsible
{
	public static final String[] blockNames = Global.ORES;

	public BlockOre(Material mat)
	{
		super(mat);
		this.setTickRandomly(true);
		this.setCreativeTab(null);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
	{
		if(TFCOptions.enableDebugMode && world.isRemote)
		{
			int metadata = world.getBlockMetadata(x, y, z);
            TerraFirmaCraft.LOG.info("Meta = {}:{}", getUnlocalizedName(), metadata);
			TEOre te = (TEOre)world.getTileEntity(x, y, z);
			if(te != null)
                TerraFirmaCraft.LOG.info("Ore  BaseID = {}| BaseMeta ={}", te.baseBlockID, te.baseBlockMeta);
		}
		return false;
	}

	@Override
	public int[] getDropBlock(World world, int x, int y, int z)
	{
		int[] data = new int[]{ -1, -1 };
		DataLayer dl = TFC_Climate.getCacheManager(world).getRockLayerAt(x, z, TFC_Core.getRockLayerFromHeight(world, x, y, z));

		if(dl != null)
		{
			BlockStone stone = null;
			if (dl.block instanceof BlockStone)
				stone = (BlockStone) dl.block;

			if (stone != null)
			{
				data[0] = Block.getIdFromBlock(stone.dropBlock); // Cobblestone version of rock in that layer.
				data[1] = dl.data2; // Metadata
			}
		}
		return data;
	}
	public boolean isOreGradable(int meta){
		return meta<14;
	}
	@Override
	public int damageDropped(int dmg)
	{
		if (dmg == 14 || dmg == 15) // coal
			return 0;
		return dmg;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return (fortune > 0 && new Random().nextInt(5) < fortune)?2:1;
	}

	@Override
	public IIcon getIcon(IBlockAccess p_149673_1_, int x, int y, int z, int p_149673_5_) {
		return getRockTexture(Minecraft.getMinecraft().theWorld, x, y, z);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return TFCBlocks.stoneIgIn.getIcon(side,meta);
	}

	public IIcon getOreIcon(int side, int meta)
	{
		return icons[meta];
	}

	public static IIcon[] icons = new IIcon[blockNames.length];

	@Override
	public void registerBlockIcons(IIconRegister iconRegisterer)
	{
		for(int i = 0; i < blockNames.length; i++)
			icons[i] = iconRegisterer.registerIcon(Reference.MOD_ID + ":" + "ores/"+ blockNames[i] + " Ore");
	}

	@Override
	public int getRenderType()
	{
		return TFCBlocks.oreRenderId;
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
	{
		if(!world.isRemote)
		{
			if(player != null)
			{
				TFC_Core.addPlayerExhaustion(player, 0.001f);
				player.addStat(StatList.mineBlockStatArray[getIdFromBlock(this)], 1);
			}
			dropBlockAsItem(world, x, y, z, getDrop(world,x,y,z, player==null?0:EnchantmentHelper.getFortuneModifier(player)));
		}
		return world.setBlockToAir(x, y, z);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, int x, int y, int z, int meta)
	{
		//Intentionally empty so that mining ore blocks cannot trigger a cave in.
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<>();

		ret.add(getDrop(world, x, y, z, fortune));

		return ret;
	}

	public ItemStack getDrop(World world, int x, int y, int z, int fortune){
		TEOre te = (TEOre) world.getTileEntity(x, y, z);
		int ore = te.droppedOreID;

		ItemStack itemstack;
		if (ore == 14 || ore == 15)
			itemstack = new ItemStack(TFCItems.coal);
		else
			itemstack = new ItemStack(TFCItems.oreChunk, 1, damageDropped(ore));

		itemstack.stackSize = quantityDropped(ore, fortune, world.rand);
		return itemstack;
	}

	public static Item getDroppedItem(int meta)
	{
		if(meta == 14 || meta == 15) return TFCItems.coal;
		else if(meta > 15 && meta < 35)return TFCItems.oreChunk;
		return TFCItems.smallOreChunk;
	}

	@Override
	public boolean canDropFromExplosion(Explosion exp)
	{
		return false;
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion exp)
	{
		world.setBlockToAir(x, y, z);
	}

	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion exp)
	{
		if(!world.isRemote)
		{
			dropBlockAsItem(world, x, y, z, getDrop(world,x,y,z, 0));
			onBlockDestroyedByExplosion(world, x, y, z, exp);
		}
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player)
	{
		return null;
	}

	@Override
	public TileEntity createTileEntity(World w, int meta)
	{
		return new TEOre();
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		//TODO: For old oreGen Compact, will remove at later version
		if (!world.isRemote && world.getBlockMetadata(x,y,z) > 0){
			((TEOre) world.getTileEntity(x, y, z)).droppedOreID = world.getBlockMetadata(x,y,z);
			world.setBlockMetadataWithNotify(x,y,z,0, 0);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
	{
		if(!world.isRemote)
		{
			TEOre te = (TEOre)world.getTileEntity(x, y, z);
			te.setVisible();
		}
	}
}
