package com.bioxx.tfc.api.Crafting;

import java.util.*;

import com.bioxx.tfc.api.Enums.RuleEnum;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Constant.Global;
import org.apache.logging.log4j.Level;

public class AnvilRecipe {
	public ItemStack result;
	public String planName = "";
	public ItemStack input1;
	public ItemStack input2;
	public int craftingValue;
	public int anvilreq;
	public boolean inheritsDamage;
	public int craftingXP = 1;
	public List<String> skillsList = new ArrayList<>();
	public static int craftingBoundDefault = 50;
	private final long seed;
	public int minStep = -1;

	public AnvilRecipe(ItemStack in, ItemStack in2, String p, AnvilReq req, ItemStack result)
	{
		this(in, in2, p.toLowerCase(), req.Tier, result);
	}

	public AnvilRecipe(ItemStack in, ItemStack in2, String p, int req, ItemStack result)
	{
		input1 = in;
		input2 = in2;
		anvilreq = req;
		this.result = result;
		this.seed=TFC_Core.getSuperSeed(AnvilManager.world)+(input1 != null ? Item.getIdFromItem(input1.getItem()) : 0) + (result != null ? Item.getIdFromItem(result.getItem()) : 0);
		this.craftingValue = 70 + new Random(seed).nextInt(craftingBoundDefault);
		this.planName = p;
		inheritsDamage = false;
		skillsList.add(Global.SKILL_GENERAL_SMITHING);
		if(AnvilManager.enableMinStepBonus) minStep = getMinimalSteps(AnvilManager.getInstance().getPlan(planName), craftingValue);
	}
	@Deprecated
	public AnvilRecipe(ItemStack in, ItemStack in2, String planName,boolean isWeld, AnvilReq req, ItemStack result)
	{
		this(in, in2, planName.toLowerCase(), req.Tier, result);
		if(isWeld)FMLLog.log(Level.FATAL,"Deprecated AnvilRecipe init! Use AnvilWeldRecipe instead. /in:"+in.getDisplayName()+"/ in2:"+in2.getDisplayName()+"/ result:"+result.getDisplayName());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AnvilRecipe)) return false;
		AnvilRecipe that = (AnvilRecipe) o;
		return isStackEqual(result, that.result) && Objects.equals(planName, that.planName) && isStackEqual(input1, that.input1) && isStackEqual(input2, that.input2);
	}

	@Override
	public int hashCode() {
		return Objects.hash(result, planName, input1, input2);
	}

	public AnvilRecipe setCraftingBound(int max)
	{
		craftingValue = 70 + new Random(seed).nextInt(max);
		if(AnvilManager.enableMinStepBonus) this.minStep = getMinimalSteps(AnvilManager.getInstance().getPlan(planName), craftingValue);
		return this;
	}

	public AnvilRecipe clearRecipeSkills()
	{
		skillsList.clear();
		return this;
	}

	public AnvilRecipe setCraftingXP(int xp)
	{
		this.craftingXP = xp;
		return this;
	}

	public AnvilRecipe setInheritsDamage()
	{
		inheritsDamage = true;
		return this;
	}

	public AnvilRecipe addRecipeSkill(String s)
	{
		this.skillsList.add(s);
		return this;
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */    
	public boolean matches(ItemStack in1, ItemStack in2, String thePlan, int req)
	{
        return isStackEqual(input1, in1) &&
                isStackEqual(input2, in2) &&
                planName.equals(thePlan) &&
                AnvilReq.matches(anvilreq, req);
    }

	public boolean isComplete(AnvilManager am, ItemStack in1, ItemStack in2, String thePlan, int req, int craftingValue, int[] rules)
	{
		PlanRecipe pr = am.getPlan(thePlan);
        return isStackEqual(input1, in1) &&
                isStackEqual(input2, in2) &&
                planName.equals(thePlan) &&
                pr.rules[0].matches(rules, 0) && pr.rules[1].matches(rules, 1) && pr.rules[2].matches(rules, 2) &&
                this.craftingValue == craftingValue &&
                AnvilReq.matches(anvilreq, req);
    }

	public static boolean isStackEqual(ItemStack is1, ItemStack is2)
	{
		// XOR, if both are null return true
		if (is1 != null && is2 != null)
		{
			if (is1.getItem() != is2.getItem())
				return false;
			return is1.getItemDamage() == 32767 || is1.getItemDamage() == is2.getItemDamage();
		}
		else return is1 == null && is2 == null;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	public ItemStack getCraftingResult()
	{
		return result;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	public ItemStack getCraftingResult(ItemStack input)
	{
		if (result==null) FMLLog.log(Level.FATAL,"Null Item at Anvil Recipe: "+input1.toString()+input2.toString());
		ItemStack is = result.copy();
		if(this.inheritsDamage)
			is.setItemDamage(input.getItemDamage());
		return is;
	}



	public int getCraftingValue()
	{
		return craftingValue;
	}

	public float getSkillMult(EntityPlayer p)
	{
		float skill = 0;
		float total = 0;
		for (String s : skillsList)
		{
			total++;
			skill+=TFC_Core.getSkillStats(p).getSkillMultiplier(s);
		}
		if(total > 0)
			return skill/total;
		return 0;
	}

	public String getPlanName()
	{
		return planName;
	}

	public ItemStack getInput1()
	{
		return input1;
	}

	public ItemStack getInput2()
	{
		return input2;
	}

	public int getAnvilreq()
	{
		return anvilreq;
	}

	public boolean isInheritsDamage()
	{
		return inheritsDamage;
	}

	public int getCraftingXP()
	{
		return craftingXP;
	}

	public List<String> getSkillsList()
	{
		return skillsList;
	}

	private static final boolean outputAllResult=true;
	public int getMinimalSteps(PlanRecipe plan,int craftingValue){
		try {
			long timeStarted=System.nanoTime();
			generatePossibleLastReqOpt(plan.rules);
			Arrays.fill(bestOptNum, 65536);
			bestOptNum[0] = 0;
			for (String[] currPosReqOpt : AllPosLastReqOpt) {
				int offset = 0;
				int EffLenth = 0;
				for (String opt : currPosReqOpt) { //获取当前操作序列的偏移量，任意匹配的操作实际上不算必须操作
					offset += optSet.get(opt);
					if (!Objects.equals(opt, "9") && !Objects.equals(opt, "-1")) EffLenth++;
				}
				OffsetToOptNum.put(offset,
						TraceFromStart(0, craftingValue - offset) + EffLenth);
			}
			 //取OffsetToOptNum中最小的值
			 if(outputAllResult||Collections.min(OffsetToOptNum.values())>20||Collections.min(OffsetToOptNum.values())<0){
			 FMLLog.log(Level.FATAL,"DEBUG: Successfully getMinimalSteps "+Collections.min(OffsetToOptNum.values())+"for: "+planName+", CraftValue: "+craftingValue);
			 FMLLog.log(Level.FATAL,"DEBUG: Took: "+(System.nanoTime()-timeStarted)+"ns");}
			return Collections.min(OffsetToOptNum.values());
		}catch (Throwable t){

			FMLLog.log(Level.FATAL,t,"Exception when getMinimalSteps: "+planName+", CraftValue: "+craftingValue);
			t.printStackTrace();
			return 1;
		}
	}

	private static final HashMap<String, Integer> optSet = new HashMap<>();

	private final HashMap<Integer,Integer> OffsetToOptNum = new HashMap<>(); // 存储不同可能的末端操作序列对应的最优操作数

	private static final int lowerBound = 0;
	private static final int upperBound = 150;

	private final int[] bestOptNum = new int[upperBound - lowerBound + 50];

	private ArrayList<String[]> AllPosLastReqOpt = new ArrayList<>();

	static {
		optSet.put("0_1", -3);
		optSet.put("0_2", -6);
		optSet.put("0_3", -9);
		optSet.put("1", -15);
		optSet.put("3", 2);
		optSet.put("4", 7);
		optSet.put("5", 13);
		optSet.put("6", 16);
		optSet.put("-1",0); //Any
		optSet.put("9",0);  //Unknown
	}

	public boolean isHaveMatchedOpt(RuleEnum currOpt){
		// 去除不符合条件的操作序列
		ArrayList<String[]> PosLastReqOpt = new ArrayList<>();

		for (String[] currPosReqOpt : AllPosLastReqOpt) {
			//对中间操作序列中的精确匹配
			boolean accurateMatch = false;
			for (int i = 0; i < currPosReqOpt.length; i++)
				if (currOpt.matches(
						Integer.parseInt(currPosReqOpt[i].split("_")[0]),
						i)) {
					//当前序列中的操作符合条件，保存到temp
					PosLastReqOpt.add(currPosReqOpt);
					accurateMatch = true;
					break;
				}
			if (accurateMatch) continue;
//            if(!PosLastReqOpt.isEmpty()) continue; //如果已经有精确匹配，跳过模糊匹配
			//无精确匹配，对中间操作序列中模糊匹配位点替换为当前操作值
			for (int i = 0; i < currPosReqOpt.length; i++)
				if (currPosReqOpt[i].equals("9")){
//                    String[] temp = currPosReqOpt.clone();
//                    temp[i] = String.valueOf(currOpt.Action);
//                    PosLastReqOpt.add(temp);
					if(currOpt.Action == 0) {
						PosLastReqOpt.add(CopyAndReplace(currPosReqOpt, i, "0_1"));
						PosLastReqOpt.add(CopyAndReplace(currPosReqOpt, i, "0_2"));
						PosLastReqOpt.add(CopyAndReplace(currPosReqOpt, i, "0_3"));
					}
					else PosLastReqOpt.add(CopyAndReplace(currPosReqOpt, i, String.valueOf(currOpt.Action)));
					break;
				}
		}
		AllPosLastReqOpt = PosLastReqOpt;
		return !AllPosLastReqOpt.isEmpty();

	}

	public void createNewOptSeq(String opt, int pos){
		// 生成新的操作序列
		String[] temp = new String[3];
		Arrays.fill(temp, "9"); //初始化9代表未知的操作
		temp[pos] = opt;
		AllPosLastReqOpt.add(temp);
	}

	public String[] CopyAndReplace(String[] currPosReqOpt, int pos, String opt){
		String[] temp = currPosReqOpt.clone();
		temp[pos] = opt;
		return temp;
	}

	public void createNewOptSeq(int opt, int pos){
		int act = opt;
		//如果act为0，则加入0_1,0_2,0_3
		if (act == 0) {
			createNewOptSeq("0_1", pos);
			createNewOptSeq("0_2", pos);
			createNewOptSeq("0_3", pos);
		}
		else createNewOptSeq(String.valueOf(act), pos);
	}

	public void generatePossibleLastReqOpt(RuleEnum[] LastReqOpt){
		// 遍历LastReqOpt，生成所有可能的最后若干操作
		for(RuleEnum opt : LastReqOpt)
			if (!isHaveMatchedOpt(opt))
				for (int lpos = opt.Min; lpos <= opt.Max; lpos++)
					createNewOptSeq(opt.Action, lpos);
	}

	public int TraceFromStart (int Start, int Dest){
		//从Start开始，依BFS更新bestOptNum，直到到达Dest
		//初始化队列
		Queue<Integer> q = new ArrayDeque<>();
		q.add(Start);

		while (!q.isEmpty()){
			int CurrDest = q.poll();
			for (String opt : optSet.keySet()){
				int NextDest = CurrDest - optSet.get(opt);
				if (NextDest < lowerBound) continue;
				if (NextDest > upperBound) continue;
				if (bestOptNum[NextDest] > bestOptNum[CurrDest] + 1){
					bestOptNum[NextDest] = bestOptNum[CurrDest] + 1;
					q.add(NextDest);
				}
			}

			if (CurrDest == Dest) return bestOptNum[Dest];
		}

		return bestOptNum[Dest];
	}
}


