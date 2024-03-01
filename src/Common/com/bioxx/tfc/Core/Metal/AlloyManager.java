package com.bioxx.tfc.Core.Metal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bioxx.tfc.api.Metal;

public class AlloyManager 
{
	public static final AlloyManager INSTANCE = new AlloyManager();
	
	public List<Alloy> alloys;
	
	
	public AlloyManager()
	{
		alloys = new ArrayList<Alloy>();
	}
	
	public void addAlloy(Alloy a)
	{
		alloys.add(a);
	}
	
	public boolean matches(List<AlloyMetal> ingred)
	{
		Iterator<Alloy> iter = alloys.iterator();
		Alloy match = null;
		while(iter.hasNext() && match == null)
		{
			match = iter.next();
			match = match.matches(ingred);
		}
		return match != null;
	}
	
	public Metal matchesAlloy(List<AlloyMetal> ingred, Alloy.EnumTier furnaceTier)
	{
		Alloy match = null;
		Metal finalMetal = ingred.get(0).metalType;
		if(ingred.size()==1||ingred.stream().allMatch(metal->metal.metalType==finalMetal))return ingred.get(0).metalType;
		for (Alloy alloy : alloys) {
			match = alloy;
			if (furnaceTier.tier >= match.furnaceTier.tier) match = match.matches(ingred);
			else match = null;
			if (match != null) return match.outputType;
		}
		//If we can't find any alloy, try to remove this alloy and search again
		for (Alloy alloy : alloys) {
			match = alloy;
			if(ingred.stream().anyMatch(alloy1-> alloy1.metalType==alloy.outputType)){ingred=reCalculateMetalPercent(ingred,alloy.outputType);}
			if (furnaceTier.tier >= match.furnaceTier.tier) match = match.matches(ingred);
			else match = null;
			if (match != null&&match.alloyIngred.stream().noneMatch(alloy1->alloy1.metalType==alloy.outputType))return match.outputType;
		}
		return null;
	}

	public List<AlloyMetal> reCalculateMetalPercent(List<AlloyMetal> list,Metal alloyToRemove){
		list.removeIf(alloyMetal -> alloyMetal.metalType==alloyToRemove);
		double totalAmount = list.stream().mapToDouble(alloy->alloy.metal).sum();
		list.forEach(alloyMetal -> alloyMetal.metal = (float) (100*alloyMetal.metal/totalAmount));
		return list;
	}
}
