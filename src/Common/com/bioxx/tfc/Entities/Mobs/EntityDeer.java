package com.bioxx.tfc.Entities.Mobs;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.bioxx.tfc.TFCItems;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Sounds;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.AI.EntityAIAvoidEntityTFC;
import com.bioxx.tfc.Entities.AI.EntityAIMateTFC;
import com.bioxx.tfc.Entities.AI.EntityAIPanicTFC;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.Items.ItemCustomNameTag;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Entities.IAnimal;
import com.bioxx.tfc.api.Entities.IAnimal.InteractionEnum;
import com.bioxx.tfc.api.Util.Helper;

public class EntityDeer extends EntityAnimal implements IAnimal
{
	/** The eat grass AI task for this mob. */
	private final EntityAIEatGrass aiEatGrass = new EntityAIEatGrass(this);

	private boolean running;
	protected long animalID;
	protected int sex = 0;
	protected int hunger;
	protected int age;
	protected boolean pregnant;
	protected int pregnancyRequiredTime;
	protected long timeOfConception;
	protected float mateSizeMod;
	public float size_mod;			//How large the animal is
	public float strength_mod;		//how strong the animal is
	public float aggression_mod = 1;//How aggressive / obstinate the animal is
	public float obedience_mod = 1;	//How well the animal responds to commands.
	public float colour_mod = 1;	//what the animal looks like
	public float climate_mod = 1;	//climate adaptability
	public float hard_mod = 1;		//hardiness
	public boolean inLove;
	public Vec3 attackedVec = null;
	public Entity fearSource = null;
	
	private boolean wasRoped = false;

	int degreeOfDiversion = 1;
	
	private int familiarity = 0;
	private long lastFamiliarityUpdate = 0;
	private boolean familiarizedToday = false;
	
	protected float avgAdultWeight = 95;			//The average weight of adult males in kg
	protected float dimorphism = 0.1728f;		//1 - dimorphism = the average relative size of females : males. This is calculated by cube-square law from
											//the square root of the ratio of female mass : male mass

	public EntityDeer(World par1World)
	{
		super(par1World);
		animalID = TFC_Time.getTotalTicks() + getEntityId();
		hunger = 168000;
		pregnant = false;
		pregnancyRequiredTime = 7 * TFC_Time.daysInMonth;
		timeOfConception = 0;
		mateSizeMod = 0;
		sex = rand.nextInt(2);
		size_mod =(float)Math.sqrt((((rand.nextInt (rand.nextInt((degreeOfDiversion + 1)*10)+1) * (rand.nextBoolean() ? 1 : -1)) * 0.01f) + 1F) * (1.0F - dimorphism * sex));
		strength_mod = (float)Math.sqrt((((rand.nextInt (rand.nextInt(degreeOfDiversion*10)+1) * (rand.nextBoolean() ? 1 : -1)) * 0.01f) + size_mod));
		aggression_mod = (float)Math.sqrt((((rand.nextInt (rand.nextInt(degreeOfDiversion*10)+1) * (rand.nextBoolean() ? 1 : -1)) * 0.01f) + 1));
		obedience_mod = (float)Math.sqrt((((rand.nextInt (rand.nextInt(degreeOfDiversion*10)+1) * (rand.nextBoolean() ? 1 : -1)) * 0.01f) + (1f/aggression_mod)));
		colour_mod = (float)Math.sqrt((((rand.nextInt (rand.nextInt((degreeOfDiversion+2)*10)+1) * (rand.nextBoolean() ? 1 : -1)) * 0.01f) + 1));
		hard_mod = (float)Math.sqrt((((rand.nextInt (rand.nextInt(degreeOfDiversion*10)+1) * (rand.nextBoolean() ? 1 : -1)) * 0.01f) + size_mod));
		climate_mod = (float)Math.sqrt((((rand.nextInt (rand.nextInt(degreeOfDiversion*10)+1) * (rand.nextBoolean() ? 1 : -1)) * 0.01f) + hard_mod));
		running = false;

		this.setSize(0.9F, 1.3F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanicTFC(this, 0.68F,true));
		this.tasks.addTask(2, new EntityAIMateTFC(this,worldObj, 1.0f));
		this.tasks.addTask(3, new EntityAIAvoidEntityTFC(this, EntityPlayer.class, 12.0F, 0.5F, 0.7F));
		this.tasks.addTask(3, new EntityAIAvoidEntityTFC(this, EntityWolfTFC.class, 8f, 0.5F, 0.7F));
		this.tasks.addTask(3, new EntityAIAvoidEntityTFC(this, EntityBear.class, 16f, 0.25F, 0.3F));
		this.tasks.addTask(3, new EntityAITempt(this, 0.25F, Items.wheat, false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 0.25F));
		this.tasks.addTask(5, this.aiEatGrass);
		this.tasks.addTask(1, new EntityAIWander(this, 0.5));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));

		//	We hijack the growingAge to hold the day of birth rather
		//	than number of ticks to next growth event. We want spawned
		//	animals to be adults, so we set their birthdays far enough back
		//	in time such that they reach adulthood now.
		//
		this.setAge((int) TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
	}

	public EntityDeer(World par1World, IAnimal mother,  ArrayList<Float> data)
	{
		this(par1World);
		float father_size = 1;
		float father_str = 1;
		float father_aggro = 1;
		float father_obed = 1;
		float father_col = 1;
		float father_clim = 1;
		float father_hard = 1;
		for(int i = 0; i < data.size(); i++){
			switch(i){
			case 0:father_size = data.get(i);break;
			case 1:father_str = data.get(i);break;
			case 2:father_aggro = data.get(i);break;
			case 3:father_obed = data.get(i);break;
			case 4:father_col = data.get(i);break;
			case 5:father_clim = data.get(i);break;
			case 6:father_hard = data.get(i);break;
			default:break;
			}
		}
		this.posX = ((EntityLivingBase)mother).posX;
		this.posY = ((EntityLivingBase)mother).posY;
		this.posZ = ((EntityLivingBase)mother).posZ;
		float invSizeRatio = 1f / (2 - dimorphism);
		size_mod = (float)Math.sqrt(size_mod * size_mod * (float)Math.sqrt((mother.getSize() + father_size) * invSizeRatio));
		strength_mod = (float)Math.sqrt(strength_mod * strength_mod * (float)Math.sqrt((mother.getStrength() + father_str) * 0.5F));
		aggression_mod = (float)Math.sqrt(aggression_mod * aggression_mod * (float)Math.sqrt((mother.getAggression() + father_aggro) * 0.5F));
		obedience_mod = (float)Math.sqrt(obedience_mod * obedience_mod * (float)Math.sqrt((mother.getObedience() + father_obed) * 0.5F));
		colour_mod = (float)Math.sqrt(colour_mod * colour_mod * (float)Math.sqrt((mother.getColour() + father_col) * 0.5F));
		hard_mod = (float)Math.sqrt(hard_mod * hard_mod * (float)Math.sqrt((mother.getHardiness() + father_hard) * 0.5F));
		climate_mod = (float)Math.sqrt(climate_mod * climate_mod * (float)Math.sqrt((mother.getClimateAdaptation() + father_clim) * 0.5F));

		this.familiarity = (int) (mother.getFamiliarity()<90?mother.getFamiliarity()/2:mother.getFamiliarity()*0.9f);
		
		//	We hijack the growingAge to hold the day of birth rather
		//	than number of ticks to next growth event.
		//
		this.setAge((int) TFC_Time.getTotalDays());
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected boolean canDespawn()
	{
		return this.ticksExisted > 10000 && !wasRoped;
	}

	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();
	}

	public boolean getRunning()
	{
		return running;
	}

	public void syncData()
	{
		if(dataWatcher!= null)
		{
			if(!this.worldObj.isRemote)
			{
				this.dataWatcher.updateObject(13, Integer.valueOf(sex));
				this.dataWatcher.updateObject(14, Float.valueOf(size_mod));

				this.dataWatcher.updateObject(24, Float.valueOf(strength_mod));
				this.dataWatcher.updateObject(25, Float.valueOf(aggression_mod));
				this.dataWatcher.updateObject(26, Float.valueOf(obedience_mod));
				this.dataWatcher.updateObject(27, Float.valueOf(colour_mod));
				this.dataWatcher.updateObject(28, Float.valueOf(climate_mod));
				this.dataWatcher.updateObject(29, Float.valueOf(hard_mod));
			}
			else
			{
				sex = this.dataWatcher.getWatchableObjectInt(13);
				size_mod = this.dataWatcher.getWatchableObjectFloat(14);

				strength_mod = this.dataWatcher.getWatchableObjectFloat(24);
				aggression_mod = this.dataWatcher.getWatchableObjectFloat(25);
				obedience_mod = this.dataWatcher.getWatchableObjectFloat(26);
				colour_mod = this.dataWatcher.getWatchableObjectFloat(27);
				climate_mod = this.dataWatcher.getWatchableObjectFloat(28);
				hard_mod = this.dataWatcher.getWatchableObjectFloat(29);
			}
		}
	}

	public void setRunning(boolean r)
	{
		running = r;
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate()
	{
		/*if(pregnant){
			if(TFC_Time.getTotalTicks() >= conception + pregnancyTime * TFC_Settings.dayLength){
				EntityDeer baby = new EntityDeer(worldObj, this,mateSizeMod);
				giveBirth(baby);
				pregnant = false;
			}
		}*/

		if(super.isInLove())
		{
			super.resetInLove();
			setInLove(true);
		}

		if(this.getLeashed()&&!wasRoped)wasRoped = true;
		
		syncData();
		if(isAdult())
		{
			setGrowingAge(0);
		}
		else
		{
			setGrowingAge(-1);
		}
		
		this.handleFamiliarityUpdate();

		if(pregnant)
		{
			if(TFC_Time.getTotalTicks() >= timeOfConception + pregnancyRequiredTime)
			{
				EntityDeer baby = (EntityDeer) createChildTFC(this);
				baby.setLocationAndAngles(posX + (rand.nextFloat() - 0.5F) * 2F, posY, posZ + (rand.nextFloat() - 0.5F) * 2F, 0.0F, 0.0F);
				baby.rotationYawHead = baby.rotationYaw;
				baby.renderYawOffset = baby.rotationYaw;
				worldObj.spawnEntityInWorld(baby);
				baby.setAge((int)TFC_Time.getTotalDays());
				pregnant = false;
			}
		}

		if(attackedVec != null)
		{
			//System.out.println(this.entityId+", Vec: "+attackedVec.xCoord+", "+attackedVec.yCoord+", "+attackedVec.zCoord);
			Vec3 positionVec = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			if(this.getFearSource() != null && this.getDistanceSqToEntity(this.getFearSource()) > Global.SEALEVEL)
			{
				this.setFearSource(null);
			}
			else if(positionVec.distanceTo(getAttackedVec()) > 16)
			{
				this.setAttackedVec(null);
			}
		}

		if (hunger > 144000 && rand.nextInt (100) == 0 && getHealth() < TFC_Core.getEntityMaxHealth(this) && !isDead)
		{
			this.heal(1);
		}

		/**
		 * This Cancels out the changes made to growingAge by EntityAgeable
		 * */
		TFC_Core.PreventEntityDataUpdate = true;
		super.onLivingUpdate();
		TFC_Core.PreventEntityDataUpdate = false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		Entity entity = par1DamageSource.getEntity();
		if(entity != null)
		{
			setAttackedVec(Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ));
			setFearSource(entity);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400);//MaxHealth
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte)0));
		this.dataWatcher.addObject(13, new Integer(0));
		this.dataWatcher.addObject(14, new Float(1));
		this.dataWatcher.addObject(15, Integer.valueOf(0));

		this.dataWatcher.addObject(24, new Float(1));
		this.dataWatcher.addObject(25, new Float(1));
		this.dataWatcher.addObject(26, new Float(1));
		this.dataWatcher.addObject(27, new Float(1));
		this.dataWatcher.addObject(28, new Float(1));
		this.dataWatcher.addObject(29, new Float(1));
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		float ageMod = TFC_Core.getPercentGrown(this);	
		this.entityDropItem(new ItemStack(TFCItems.Hide, 1, Math.max(0, Math.min(2, (int)(ageMod * size_mod * 1.84)))), 0);
		this.dropItem(Items.bone, (int)((rand.nextInt(4) + 2) * ageMod));
		float foodWeight = ageMod * (this.size_mod * 2400);//528 oz (33lbs) is the average yield of lamb after slaughter and processing

		TFC_Core.animalDropMeat(this, TFCItems.venisonRaw, foodWeight);
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	@Override
	protected Item getDropItem()
	{
		return Item.getItemFromBlock(Blocks.wool);
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	@Override
	public boolean interact(EntityPlayer player)
	{
		if(!worldObj.isRemote)
		{
			if(player.isSneaking()){
				this.familiarize(player);
				return true;
			}
			//par1EntityPlayer.addChatMessage(new ChatComponentText(getGender()==GenderEnum.FEMALE?"Female":"Male"));
			if(getGender()==GenderEnum.FEMALE && pregnant)
			{
				player.addChatMessage(new ChatComponentText("Pregnant"));
			}
			//par1EntityPlayer.addChatMessage("12: "+dataWatcher.getWatchableObjectInt(12)+", 15: "+dataWatcher.getWatchableObjectInt(15));
		}
		ItemStack itemstack = player.getHeldItem();
		if(itemstack != null && itemstack.getItem() instanceof ItemCustomNameTag && itemstack.hasTagCompound() && itemstack.stackTagCompound.hasKey("ItemName")){
			if(this.trySetName(itemstack.stackTagCompound.getString("ItemName"), player)){
				itemstack.stackSize--;
			}
			return true;
		}
		return super.interact(player);
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger ("Sex", sex);
		nbt.setLong ("Animal ID", animalID);
		nbt.setFloat ("Size Modifier", size_mod);

		nbt.setFloat ("Strength Modifier", strength_mod);
		nbt.setFloat ("Aggression Modifier", aggression_mod);
		nbt.setFloat ("Obedience Modifier", obedience_mod);
		nbt.setFloat ("Colour Modifier", colour_mod);
		nbt.setFloat ("Climate Adaptation Modifier", climate_mod);
		nbt.setFloat ("Hardiness Modifier", hard_mod);
		
		nbt.setBoolean("wasRoped", wasRoped);

		nbt.setInteger ("Hunger", hunger);
		nbt.setBoolean("Pregnant", pregnant);
		nbt.setFloat("MateSize", mateSizeMod);
		nbt.setLong("ConceptionTime", timeOfConception);
		nbt.setInteger("Age", getBirthDay());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		animalID = nbt.getLong ("Animal ID");
		sex = nbt.getInteger ("Sex");
		size_mod = nbt.getFloat ("Size Modifier");

		strength_mod = nbt.getFloat ("Strength Modifier");
		aggression_mod = nbt.getFloat ("Aggression Modifier");
		obedience_mod = nbt.getFloat ("Obedience Modifier");
		colour_mod = nbt.getFloat ("Colour Modifier");
		climate_mod = nbt.getFloat ("Climate Adaptation Modifier");
		hard_mod = nbt.getFloat ("Hardiness Modifier");

		wasRoped = nbt.getBoolean("wasRoped");
		
		hunger = nbt.getInteger ("Hunger");
		pregnant = nbt.getBoolean("Pregnant");
		mateSizeMod = nbt.getFloat("MateSize");
		timeOfConception = nbt.getLong("ConceptionTime");
		this.dataWatcher.updateObject(15, nbt.getInteger ("Age"));
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound()
	{
		if(getGender() == GenderEnum.MALE && isAdult() && worldObj.rand.nextInt(100) < 5)
			return TFC_Sounds.DEERCRY;
		return TFC_Sounds.DEERSAY;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound()
	{
		return TFC_Sounds.DEERHURT;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound()
	{
		return TFC_Sounds.DEERDEATH;
	}

	@Override
	public void setGrowingAge(int par1)
	{
		if(!TFC_Core.PreventEntityDataUpdate)
		{
			this.dataWatcher.updateObject(12, Integer.valueOf(par1));
		}
	}

	@Override
	public boolean isChild()
	{
		return !isAdult();
	}

	/*
	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) 
	{
		return new EntityDeer(worldObj, this, entityageable.getEntityData().getInteger("Size Modifier"));
	}*/

	//Commented out old method, the third variable should be the size modifier of the father, not the mother
	@Override
	public EntityAgeable createChild(EntityAgeable entityageable)
	{
		ArrayList<Float> data = new ArrayList<Float>();
		data.add(mateSizeMod);
		return new EntityDeer(worldObj, this, data);
	}

	@Override
	public int getBirthDay()
	{
		return this.dataWatcher.getWatchableObjectInt(15);
	}

	@Override
	public int getNumberOfDaysToAdult()
	{
		return TFC_Time.daysInMonth * 24;
	}

	@Override
	public boolean isAdult()
	{
		return getBirthDay()+getNumberOfDaysToAdult() <= TFC_Time.getTotalDays();
	}

	@Override
	public float getSize()
	{
		return size_mod;
	}

	@Override
	public boolean isPregnant()
	{
		return pregnant;
	}

	@Override
	public EntityLiving getEntity()
	{
		return this;
	}

	@Override
	public boolean canMateWith(IAnimal animal)
	{
		if(animal.getGender() != this.getGender() && animal.isAdult() && animal instanceof EntityDeer)
			return true;
		else
			return false;
	}

	@Override
	public void mate(IAnimal otherAnimal) 
	{
		if (sex == 0)
		{
			otherAnimal.mate(this);
			return;
		}
		timeOfConception = TFC_Time.getTotalTicks();
		pregnant = true;
		//targetMate.setGrowingAge (TFC_Settings.dayLength);
		resetInLove();
		otherAnimal.setInLove(false);
		mateSizeMod = otherAnimal.getSize();
	}

	@Override
	public boolean getInLove()
	{
		return inLove;
	}

	@Override
	public void setInLove(boolean b)
	{
		this.inLove = b;
	}

	@Override
	public int getAnimalTypeID()
	{
		return Helper.stringToInt("deer");
	}

	@Override
	public void eatGrassBonus()
	{
		hunger += 24000;
	}

	@Override
	public int getHunger()
	{
		return hunger;
	}

	@Override
	public void setHunger(int h)
	{
		hunger = h;
	}

	@Override
	public GenderEnum getGender()
	{
		return GenderEnum.genders[getSex()];
	}

	@Override
	public int getSex()
	{
		return dataWatcher.getWatchableObjectInt(13);
	}

	@Override
	public EntityAgeable createChildTFC(EntityAgeable entityageable)
	{
		ArrayList<Float> data = new ArrayList<Float>();
		data.add(entityageable.getEntityData().getFloat("MateSize"));
		return new EntityDeer(worldObj, this, data);
	}

	@Override
	public void setAge(int par1)
	{
		this.dataWatcher.updateObject(15, Integer.valueOf(par1));
	}

	@Override
	public float getStrength()
	{
		return strength_mod;
	}

	@Override
	public float getAggression()
	{
		return aggression_mod;
	}

	@Override
	public float getObedience()
	{
		return obedience_mod;
	}

	@Override
	public float getColour()
	{
		return colour_mod;
	}

	@Override
	public float getClimateAdaptation()
	{
		return climate_mod;
	}

	@Override
	public float getHardiness()
	{
		return hard_mod;
	}

	@Override
	public Vec3 getAttackedVec()
	{
		return attackedVec;
	}

	@Override
	public void setAttackedVec(Vec3 attackedVec)
	{
		this.attackedVec = attackedVec;
	}

	@Override
	public Entity getFearSource()
	{
		return this.fearSource;
	}

	@Override
	public void setFearSource(Entity fearSource)
	{
		this.fearSource = fearSource;
	}

	@Override
	public int getFamiliarity() {
		// TODO Auto-generated method stub
		return familiarity;
	}

	@Override
	public void handleFamiliarityUpdate() {
		if(lastFamiliarityUpdate < TFC_Time.getTotalDays()){
			if(familiarizedToday && familiarity < 100){
				lastFamiliarityUpdate = TFC_Time.getTotalDays();
				familiarizedToday = false;
				float familiarityChange = (6 * obedience_mod / aggression_mod);
				if(this.isAdult() && (familiarity > 30 && familiarity < 80)){
					//Nothing
				}
				else if(this.isAdult()){
					familiarity += familiarityChange;
				}
				else if(!this.isAdult()){
					float ageMod = 2f/(1f + TFC_Core.getPercentGrown(this));
					familiarity += ageMod * familiarityChange;
					if(familiarity > 70){
						obedience_mod *= 1.01f;
					}
				}
			}
			else if(familiarity < 30){
				familiarity -= 2*(TFC_Time.getTotalDays() - lastFamiliarityUpdate);
			}
		}
		if(familiarity > 100)familiarity = 100;
		if(familiarity < 0)familiarity = 0;
	}

	@Override
	public void familiarize(EntityPlayer ep) {
		ItemStack stack = ep.getHeldItem();
		if(stack != null && stack.getItem()!= null && stack.getItem().equals(TFCItems.Powder) && stack.getItemDamage() == 9){
			if (!ep.capabilities.isCreativeMode)
			{
				stack.stackSize--;
				ep.inventory.setInventorySlotContents(ep.inventory.currentItem,stack);
			}
			worldObj.playSoundAtEntity(this, "random.burp", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			
			familiarizedToday = true;
			this.getLookHelper().setLookPositionWithEntity(ep, 0, 0);
			this.playLivingSound();
		}

	}
	
	@Override
	public boolean trySetName(String name, EntityPlayer player) {
		if(this.checkFamiliarity(InteractionEnum.NAME, player) && !this.hasCustomNameTag()){
			this.setCustomNameTag(name);
			return true;
		}
		this.playSound(TFC_Sounds.DEERCRY,  6, (rand.nextFloat()/2F)+(isChild()?1.25F:0.75F));
		return false;
	}
	
	@Override
	public boolean checkFamiliarity(InteractionEnum interaction, EntityPlayer player) {
		boolean flag = false;
		switch(interaction){
		case MOUNT: flag = familiarity > 15;break;
		case BREED: flag = familiarity > 20;break;
		case SHEAR: flag = familiarity > 10;break;
		case MILK: flag = familiarity > 10;break;
		case NAME: flag = familiarity > 60;break;
		default: break;
		}
		if(!flag && !player.worldObj.isRemote){
			player.addChatMessage(new ChatComponentText("The animal won't let you do that."));
		}
		return flag;
	}
}
