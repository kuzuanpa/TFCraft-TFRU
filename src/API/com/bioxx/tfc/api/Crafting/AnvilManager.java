package com.bioxx.tfc.api.Crafting;

import java.util.*;

import com.bioxx.tfc.api.Enums.RuleEnum;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class AnvilManager
{
	private static final AnvilManager INSTANCE = new AnvilManager();
	public static AnvilManager getInstance()
	{
		return INSTANCE;
	}
	public static World world;

	private final List<AnvilRecipe> recipes;
	private final List<AnvilRecipe> recipesWeld;
	private final Map<String, PlanRecipe> plans;

	public Map<String,Integer> planMinimalSteps= new HashMap<>();

	private AnvilManager()
	{
		recipes = new ArrayList<AnvilRecipe>();
		recipesWeld = new ArrayList<AnvilRecipe>();
		plans = new HashMap<String, PlanRecipe>();
	}

		public void addRecipe(AnvilRecipe recipeToAdd)
	{
		recipes.add(recipeToAdd);
	}

	public void addWeldRecipe(AnvilRecipe recipeToAdd)
	{
			recipeToAdd.flux = true;
			recipesWeld.add(recipeToAdd);
	}
	public void clearRecipes()
	{
		recipes.clear();
		recipesWeld.clear();
		plans.clear();
	}

	/**
	 * Adds a name for a plan type to the plans list. If it already exists then it will not be added. All types are not case sensative as it
	 * autoconverts to lowercase when adding to prevent bugs due to case.
	 */
	public void addPlan(String s, PlanRecipe r)
	{
		s = s.toLowerCase();
		if(!plans.containsKey(s)){
			plans.put(s, r);
			planMinimalSteps.put(s,getMinimalSteps(r));
		}
	}

	private static final HashMap<String, Integer> optSet = new HashMap<>();

	private final HashMap<Integer,Integer> OffsetToOptNum = new HashMap<>(); // 存储不同可能的末端操作序列对应的最优操作数

	private final int lowerBound = 0;
	private final int upperBound = 150;

	private final int[] bestOptNum = new int[upperBound - lowerBound + 1];

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
		optSet.put("-1",0); //任意操作
		optSet.put("9",0);  //未知操作
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

	public int getMinimalSteps(PlanRecipe plan){

		//TODO:修改为实际的计算方法
		int craftingValue = 70;

		generatePossibleLastReqOpt(plan.rules);
		Arrays.fill(bestOptNum, Integer.MAX_VALUE);
		bestOptNum[0] = 0;
		for (String[] currPosReqOpt : AllPosLastReqOpt){
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
        return Collections.min(OffsetToOptNum.values());
	}
	public PlanRecipe getPlan(String s)
	{
		return plans.get(s);
	}

	public AnvilRecipe findMatchingRecipe(AnvilRecipe recipe)
	{
		for (int k = 0; k < recipes.size(); k++)
		{
			AnvilRecipe irecipe = recipes.get(k);
			if (irecipe != null && irecipe.matches(recipe))
				return irecipe;
		}

		return null;
	}

	public AnvilRecipe findMatchingWeldRecipe(AnvilRecipe recipe)
	{
		for (int k = 0; k < recipesWeld.size(); k++)
		{
			AnvilRecipe irecipe = recipesWeld.get(k);
			if (irecipe != null && irecipe.matches(recipe))
				return irecipe;
		}

		return null;
	}

	public Object[] findCompleteRecipe(AnvilRecipe recipe, int[] rules)
	{
        for (AnvilRecipe irecipe : recipes) {
            if (irecipe != null && irecipe.isComplete(INSTANCE, recipe, rules))
                return new Object[]{irecipe, irecipe.getCraftingResult(recipe.input1)};
        }

		return null;
	}

	public ItemStack findCompleteWeldRecipe(AnvilRecipe recipe)
	{
		for (int k = 0; k < recipesWeld.size(); k++)
		{
			AnvilRecipe irecipe = recipesWeld.get(k);
			if (irecipe != null && irecipe.matches(recipe))
				return irecipe.getCraftingResult(recipe.input1);
		}

		return null;
	}

	public List<AnvilRecipe> getRecipeList()
	{
		return recipes;
	}

	public List<AnvilRecipe> getWeldRecipeList()
	{
		return recipesWeld;
	}

	public Map<String, PlanRecipe> getPlans()
	{
		return plans;
	}

	public static NBTTagCompound getCraftTag(ItemStack is)
	{
		if(is.hasTagCompound() && is.getTagCompound().hasKey("craftingTag"))
		{
			return (NBTTagCompound) is.getTagCompound().getTag("craftingTag");
		}
		else
			return new NBTTagCompound();
	}

	public static void setCraftTag(ItemStack is, NBTTagCompound nbt)
	{
		if(!is.hasTagCompound())
			is.setTagCompound(new NBTTagCompound());
		is.getTagCompound().setTag("craftingTag", nbt);
	}

	public static float getDurabilityBuff(ItemStack is)
	{
		NBTTagCompound nbt = getCraftTag(is);
		return nbt.getFloat("durabuff");
	}

	public static void setDurabilityBuff(ItemStack is, float value)
	{
		NBTTagCompound nbt = getCraftTag(is);
		nbt.setFloat("durabuff", value);
		setCraftTag(is, nbt);
	}

	public static float getDamageBuff(ItemStack is)
	{
		NBTTagCompound nbt = getCraftTag(is);
		return nbt.getFloat("damagebuff");
	}

	public static void setDamageBuff(ItemStack is, float value)
	{
		NBTTagCompound nbt = getCraftTag(is);
		nbt.setFloat("damagebuff", value);
		setCraftTag(is, nbt);
	}
	public static void setPerfect(ItemStack is)
	{
		NBTTagCompound nbt = getCraftTag(is);
		nbt.setBoolean("perfectCrafted", true);
		setCraftTag(is, nbt);
	}

	public static boolean getPerfect(ItemStack is)
	{
		NBTTagCompound nbt = getCraftTag(is);
		return nbt.getBoolean("perfectCrafted");
	}
}
