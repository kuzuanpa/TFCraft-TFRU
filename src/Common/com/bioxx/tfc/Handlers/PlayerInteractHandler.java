package com.bioxx.tfc.Handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.bioxx.tfc.Items.*;
import com.bioxx.tfc.Items.ItemBlocks.ItemTerraBlock;
import com.bioxx.tfc.api.*;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.api.Util.Helper;
import org.apache.logging.log4j.Level;

public class PlayerInteractHandler
{
	private Map<UUID, Long> lastDrink = new HashMap<UUID, Long>();

	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if (event.entityPlayer.worldObj.isRemote)
			return;

		ItemStack itemInHand = event.entityPlayer.getCurrentEquippedItem();

		boolean validAction = event.action == Action.RIGHT_CLICK_BLOCK || event.action == Action.RIGHT_CLICK_AIR;

		if(validAction && event.getResult() != Result.DENY && itemInHand == null)
		{
			handleDrinkingWater( event.entityPlayer );
		}
	}

	private void handleDrinkingWater(EntityPlayer entityPlayer)
	{
		Long lastCheck = lastDrink.get(entityPlayer.getUniqueID());
		if(lastCheck != null && lastCheck + 20 > TFC_Time.getTotalTicks())
			return;
		lastDrink.put(entityPlayer.getUniqueID(), TFC_Time.getTotalTicks());
		World world = entityPlayer.worldObj;
		MovingObjectPosition mop = Helper.getMovingObjectPositionFromPlayer(world, entityPlayer, true);
		FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(entityPlayer);
		Boolean canDrink = fs.getMaxWater(entityPlayer) - 500 > fs.waterLevel;

		// if we found a block, and we can drink proceed
		if(mop != null && canDrink && mop.typeOfHit == MovingObjectType.BLOCK)
		{
			if( TFC_Core.isFreshWater(world.getBlock(mop.blockX, mop.blockY, mop.blockZ)) )
			{
				// recover a portion of players water, this is intentionally slow,
				fs.restoreWater(entityPlayer, 2000);

				// drinking sound..
				world.playSoundAtEntity(entityPlayer,"random.drink", 0.2F, world.rand.nextFloat() * 0.1F + 0.9F);
			}
		}
	}
	@SubscribeEvent
	public void onToolTip(ItemTooltipEvent event) {
		ItemStack object = event.itemStack;
		if (!(object.getItem() instanceof ItemTerra) && !(object.getItem() instanceof ItemTerraBlock) && !(object.getItem() instanceof ItemTFCArmor) && object.hasTagCompound() && TFC_ItemHeat.hasTemp(object)) {
			float temp = TFC_ItemHeat.getTemp(object);
			float meltTemp = -1.0F;
			HeatIndex hi = HeatRegistry.getInstance().findMatchingIndex(object);
			if (hi != null) {
				meltTemp = hi.meltTemp;
			}

			if (meltTemp != -1.0F) {
				event.toolTip.add(TFC_ItemHeat.getHeatColor(temp, meltTemp));
			}
		}

		if (!(object.getItem() instanceof ItemIngot) && !(object.getItem() instanceof ItemMetalSheet) && !(object.getItem() instanceof ItemUnfinishedArmor) && !(object.getItem() instanceof ItemBloom) && object.getItem() != TFCItems.wroughtIronKnifeHead && object.hasTagCompound() && TFC_ItemHeat.hasTemp(object)) {
			String s = "";
			if (HeatRegistry.getInstance().isTemperatureDanger(object)) {
				s = s + EnumChatFormatting.WHITE + TFC_Core.translate("gui.ingot.danger") + " | ";
			}

			if (HeatRegistry.getInstance().isTemperatureWeldable(object)) {
				s = s + EnumChatFormatting.WHITE + TFC_Core.translate("gui.ingot.weldable") + " | ";
			}

			if (HeatRegistry.getInstance().isTemperatureWorkable(object)) {
				s = s + EnumChatFormatting.WHITE + TFC_Core.translate("gui.ingot.workable");
			}

			if (!"".equals(s)) {
				event.toolTip.add(s);
			}
		}

		if (!(object.getItem() instanceof ItemTerra) && object.hasTagCompound() && (object.getTagCompound().hasKey("itemCraftingValue") || object.getTagCompound().hasKey("itemCraftingRule1"))) {
			event.toolTip.add(TFC_Core.translate("gui.ItemWorked"));
		}

	}
	/**
	 * When a player picks up a vanilla item it will get replaced with
	 * the appropriate TFC item. This will solve problems like the boat
	 * crash dropping vanilla sticks and planks.
	 */
	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event)
	{
		EntityItem item = event.item;
		ItemStack is = item.getEntityItem();
		EntityPlayer player = event.entityPlayer;

		if (is.getItem() == Items.stick)
		{
			int count = is.stackSize;
			item.delayBeforeCanPickup = 100;
			item.setDead();
			item.setInvisible(true);
			Random rand = player.worldObj.rand;
			player.worldObj.playSoundAtEntity(player, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			ItemStack tfcSticks = new ItemStack(TFCItems.stick, count);
			player.inventory.addItemStackToInventory(tfcSticks);
		}
		else if (is.getItem() == Item.getItemFromBlock(Blocks.planks) && is.getItemDamage() == 0) // Only Oak
		{
			int count = is.stackSize;
			item.delayBeforeCanPickup = 100;
			item.setDead();
			item.setInvisible(true);
			Random rand = player.worldObj.rand;
			player.worldObj.playSoundAtEntity(player, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			ItemStack tfcPlanks = new ItemStack(TFCBlocks.planks, count);
			player.inventory.addItemStackToInventory(tfcPlanks);
		}
		else if (is.getItem() == Item.getItemFromBlock(Blocks.lit_pumpkin))
		{
			int count = is.stackSize;
			item.delayBeforeCanPickup = 100;
			item.setDead();
			item.setInvisible(true);
			Random rand = player.worldObj.rand;
			player.worldObj.playSoundAtEntity(player, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			ItemStack jackOLanternTFC = new ItemStack(TFCBlocks.litPumpkin, count);
			player.inventory.addItemStackToInventory(jackOLanternTFC);
		}
	}
}
