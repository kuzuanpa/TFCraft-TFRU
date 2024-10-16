package com.bioxx.tfc.Items.Tools;

import java.util.*;

import com.bioxx.tfc.Blocks.BlockTerra;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import net.minecraftforge.common.ForgeHooks;

import com.bioxx.tfc.Reference;
import com.bioxx.tfc.Blocks.Terrain.BlockOre;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.Core.Player.SkillStats.SkillRank;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.TileEntities.TEOre;
import com.bioxx.tfc.WorldGen.Generators.OreSpawnData;
import com.bioxx.tfc.WorldGen.Generators.WorldGenOre;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;

public class ItemProPick extends ItemTerra
{
	private Map<String, ProspectResult> results = new HashMap<String, ProspectResult>();
	private Random random;

	public ItemProPick()
	{
		super();
		maxStackSize = 1;
		setCreativeTab(TFCTabs.TFC_TOOLS);
		this.setWeight(EnumWeight.LIGHT);
		this.setSize(EnumSize.SMALL);
	}

	@Override
	public void registerIcons(IIconRegister registerer)
	{
		this.itemIcon = registerer.registerIcon(Reference.MOD_ID + ":" + "tools/" + this.getUnlocalizedName().replace("item.", ""));
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		if(pass == 1 && nbt != null && nbt.hasKey("broken"))
			return TFC_Textures.brokenItem;
		else
			return getIconFromDamageForRenderPass(stack.getItemDamage(), pass);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
		return searchOre(itemStack,player,world,x,y,z, false);
	}
	@Override
	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
		if(!(entity instanceof EntityPlayer)|| !(block instanceof BlockTerra))return false;
		else return searchOre(itemStack,(EntityPlayer) entity,world,x,y-50,z, true);
	}
	public boolean searchOre(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, boolean isDeepSearch)
	{
		if (world.isRemote)return true;
		Block block = world.getBlock(x, y, z);
		// Negated the old condition and exiting the method here instead.
		if (block == TFCBlocks.toolRack)
			return true;

		// Getting the metadata only when we actually need it.
		int meta = world.getBlockMetadata(x, y, z);

		SkillRank rank = TFC_Core.getSkillStats(player).getSkillRank(Global.SKILL_PROSPECTING);

		if(rank.equals(SkillRank.Novice)&&isDeepSearch){
			tellCannotDeepSearch(player);
			return true;
		}
			// If an ore block is targeted directly, it'll tell you what it is.
		if ((block == TFCBlocks.ore ||	block == TFCBlocks.ore2 || block == TFCBlocks.ore3) &&
				world.getTileEntity(x, y, z) instanceof TEOre)
		{
			TEOre te = (TEOre) world.getTileEntity(x, y, z);
			if (block == TFCBlocks.ore && rank == SkillRank.Master)
				meta = ((BlockOre) block).getOreGrade(te, meta);
			if (block == TFCBlocks.ore2) meta = meta + Global.ORE_METAL.length;
			if (block == TFCBlocks.ore3) meta = meta + Global.ORE_METAL.length + Global.ORE_MINERAL.length;
			tellResult(player, new ItemStack(TFCItems.oreChunk, 1, meta));
			return true;
		}
		else if (!TFC_Core.isGround(block)) // Exclude ground blocks to help with performance
		{
			for (OreSpawnData osd : WorldGenOre.oreList.values()) {
				if (osd != null && block == osd.block) {
					tellResult(player, new ItemStack(block));
					return true;
				}
			}
		}

		random = new Random(x * z + y);
		int chance = 60 + ((rank.ordinal()+1)*10);

		results.clear();
		// If random(100) is less than 60, it used to succeed. we don't need to
		// gather the blocks in a 25x25 area if it doesn't.
		if (random.nextInt(100) >= chance && rank != SkillRank.Master)
		{
			tellNothingFound(player);
			return true;
		}

		results.clear();

		// Check all blocks in the 25x25 area, centered on the targeted block.
		for (int i = -12; i < 12; i++)
		{
			for (int j = -12; j < 12; j++)
			{
				for(int k = -12; k < 12; k++)
				{
					int blockX = x + i;
					int blockY = y + j;
					int blockZ = z + k;

					block = world.getBlock(blockX, blockY, blockZ);
					meta = world.getBlockMetadata(blockX, blockY, blockZ);
					ItemStack ore = null;
					if (block == TFCBlocks.ore && world.getTileEntity(blockX, blockY, blockZ) instanceof TEOre)
					{
						TEOre te = (TEOre) world.getTileEntity(blockX, blockY, blockZ);
						if (rank == SkillRank.Master)
							ore = new ItemStack(TFCItems.oreChunk, 1, ((BlockOre) block).getOreGrade(te, meta));
						else
							ore = new ItemStack(TFCItems.oreChunk, 1, meta);
					}
					else if (block == TFCBlocks.ore2)
						ore = new ItemStack(TFCItems.oreChunk, 1, meta + Global.ORE_METAL.length);
					else if (block == TFCBlocks.ore3)
						ore = new ItemStack(TFCItems.oreChunk, 1, meta + Global.ORE_METAL.length + Global.ORE_MINERAL.length);
					else if (!TFC_Core.isGround(block)) // Exclude ground blocks to help with performance
					{
						for (OreSpawnData osd : WorldGenOre.oreList.values()) if (osd != null && block == osd.block) {
							ore = new ItemStack(block);
							break;
						}

					}
					else
						continue;

					if (ore != null)
					{
						String oreName = ore.getDisplayName();

						if (results.containsKey(oreName)){
							results.get(oreName).count++;
							if( rank == SkillRank.Master){
								results.get(oreName).oreExistsArea[blockY-y<-2?0:blockY-y>2?2:1][blockX-x<-2?0:blockX-x>2?2:1][blockZ-z<-2?0:blockZ-z>2?2:1]=true;
							} else {
								results.get(oreName).averageCalculateX += blockX;
								results.get(oreName).averageCalculateY += blockY;
								results.get(oreName).averageCalculateZ += blockZ;
							}
						}
						else
							results.put(oreName, new ProspectResult(ore, 1,blockX,blockY,blockZ));

						ore = null;
						oreName = null;
					}
				}
			}
		}

		// Tell the player what was found.
		if (results.isEmpty()) tellNothingFound(player);
		else tellResult(player, isDeepSearch);


		results.clear();
		random = null;

		// Damage the item on prospecting use.
		itemStack.damageItem(isDeepSearch?4:1, player);
		if (itemStack.getItemDamage() >= itemStack.getMaxDamage()) player.destroyCurrentEquippedItem();
		return true;
	}


	/*
	 * Tells the player nothing was found.
	 */
	private void tellNothingFound(EntityPlayer player)
	{
		TFC_Core.sendInfoMessage(player, new ChatComponentTranslation("gui.ProPick.FoundNothing"));
	}
	private void tellCannotDeepSearch(EntityPlayer player)
	{
		TFC_Core.sendInfoMessage(player, new ChatComponentTranslation("gui.ProPick.cannotDeepSearch"));
	}

	/*
	 * Tells the player what block of ore he found, when directly targeting an ore block.
	 */
	private void tellResult(EntityPlayer player, ItemStack ore)
	{
		String oreName = ore.getUnlocalizedName() + ".name";
		TFC_Core.sendInfoMessage(player,
				new ChatComponentTranslation("gui.ProPick.Found")
				.appendText(" ")
				.appendSibling(new ChatComponentTranslation(oreName)));

	}
	
	/*
	 * Tells the player what ore has been found, randomly picked off the HashMap.
	 */
	private void tellResult(EntityPlayer player, boolean isDeepSearch)
	{

		TFC_Core.getSkillStats(player).increaseSkill(Global.SKILL_PROSPECTING, isDeepSearch?10 : 1);
		SkillRank rank = TFC_Core.getSkillStats(player).getSkillRank(Global.SKILL_PROSPECTING);
		int index = random.nextInt(results.size());
		ProspectResult result = results.values().toArray(new ProspectResult[0])[index];
		String oreName = result.itemStack.getUnlocalizedName() + ".name";

		String quantityMsg;
		if (result.count < 5)
			quantityMsg = "gui.ProPick.FoundTraces";
		else if (result.count < 15)
			quantityMsg = "gui.ProPick.FoundSmall";
		else if (result.count < 40)
			quantityMsg = "gui.ProPick.FoundMedium";
		else if (result.count < 90)
			quantityMsg = "gui.ProPick.FoundLarge";
		else
			quantityMsg = "gui.ProPick.FoundVeryLarge";
		String LocationMsg ="";
		String DistanceMsg1 = "";
		String DistanceMsg2 = "";
		ArrayList<String> AreaMsgs = new ArrayList<>();
		StringBuilder AreaMsg = new StringBuilder();

		if (rank != SkillRank.Novice&&rank != SkillRank.Master){
		//计算矿物中心
		int aX =result.averageCalculateX / result.count;
		int aY =result.averageCalculateY / result.count;
		int aZ =result.averageCalculateZ / result.count;

		if (!player.worldObj.isRemote) {
			//变为相对距离
			aX -= player.posX;
			aY -= player.posY;
			aZ -= player.posZ;

			int max = Math.abs(aX);
			int res = 0;
			if (Math.abs(aY) > max) {
				max = Math.abs(aY);
				res = 1;
			}
			if (Math.abs(aZ) > max) res = 2;
			switch (res) {
				case 0:
					if (aX < 0) LocationMsg = "gui.ProPick.AtWest";
					else LocationMsg = "gui.ProPick.AtEast";
					break;
				case 1:
					if (aY < 0) LocationMsg = "gui.ProPick.Down";
					else LocationMsg = "gui.ProPick.Up";
					break;
				case 2:
					if (aZ < 0) LocationMsg = "gui.ProPick.AtNorth";
					else LocationMsg = "gui.ProPick.AtSouth";
					break;
			}
			if (rank == SkillRank.Expert){
				DistanceMsg1="gui.ProPick.About";
			 DistanceMsg2 = (Math.floorDiv((int)Math.sqrt(Math.pow(Math.abs(aX),2)+Math.pow(Math.abs(aY),2)+Math.pow(Math.abs(aZ),2)) ,10) *10)+ "m";
		}}
		}else if(rank == SkillRank.Master){
			for (int y=0;y<3;y++)for (int x=0;x<3;x++)for (int z=0;z<3;z++)if(result.oreExistsArea[y][x][z]) AreaMsgs.add("gui.ProPick.area."+y+x+z);
			if(!AreaMsgs.isEmpty()) AreaMsg.append(TFC_Core.l10n("gui.ProPick.area.at")).append(" ");
			for (String str : AreaMsgs) AreaMsg.append(TFC_Core.l10n(str)).append(" ");
		}
		TFC_Core.sendInfoMessage(player,
				new ChatComponentTranslation("")
				.appendSibling(isDeepSearch?new ChatComponentTranslation("gui.ProPick.deepSearch"):new ChatComponentText(""))
				.appendSibling(new ChatComponentTranslation(quantityMsg))
				.appendSibling(new ChatComponentTranslation(oreName))
				.appendText(" ")
				.appendSibling(new ChatComponentTranslation(LocationMsg))
			    .appendText(" ")
			    .appendSibling(new ChatComponentTranslation(DistanceMsg1))
				.appendSibling(new ChatComponentText(DistanceMsg2))
				.appendSibling(new ChatComponentText(AreaMsg.toString())));
				
		oreName = null;
		result = null;

	}

	@Override
	public boolean canStack()
	{
		return false;
	}

	private class ProspectResult
	{
		public final boolean f=false;
		public final boolean t=true;
		public ItemStack itemStack;
		public int count;
		public boolean[][][] oreExistsArea={{{f,f,f},{f,f,f},{f,f,f}},{{f,f,f},{f,f,f},{f,f,f}},{{f,f,f},{f,f,f},{f,f,f}}};
		public int averageCalculateX;
		public int averageCalculateY;
		public int averageCalculateZ;
		public ProspectResult(ItemStack itemStack, int count, int x,int y,int z)
		{
			this.itemStack = itemStack;
			this.count = count;
			this.averageCalculateX = x;
			this.averageCalculateY = y;
			this.averageCalculateZ = z;
		}
	}

	@Override
	public EnumItemReach getReach(ItemStack is)
	{
		return EnumItemReach.SHORT;
	}

	@Override
	public int getMaxDamage(ItemStack stack)
	{
		return (int) (getMaxDamage()+(getMaxDamage() * AnvilManager.getDurabilityBuff(stack)));
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		float digSpeed = super.getDigSpeed(stack, block, meta);

		if (ForgeHooks.isToolEffective(stack, block, meta))
		{
			return digSpeed + (digSpeed * AnvilManager.getDurabilityBuff(stack));
		}
		return digSpeed;
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List arraylist, boolean flag)
	{
		ItemTerra.addSizeInformation(is, arraylist);
		ItemTerraTool.addSmithingBonusInformation(is, arraylist);
	}
}
