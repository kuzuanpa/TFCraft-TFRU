package com.bioxx.tfc.api.Crafting;

import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public enum AnvilReq
{
    STONE("Stone", 0),
    COPPER("Copper", 1),
    BRONZE("Bronze", 2),
    WROUGHTIRON("Wrought Iron", 3),
    STEEL("Steel", 5),
    BLACKSTEEL("Black Steel", 5),
    REDSTEEL("Red Steel", 7),
    BLUESTEEL("Blue Steel", 7),
	BISMUTHBRONZE("Bismuth Bronze", 2),
    BLACKBRONZE("Black Bronze", 2),
	ROSEGOLD("Rose Gold", 2),
	MANASTEEL("Mana Steel", 6);

	 public static final AnvilReq[] RULES = new AnvilReq[]{STONE, COPPER, BRONZE, WROUGHTIRON, STEEL, BLACKSTEEL,MANASTEEL, REDSTEEL, BLUESTEEL, BISMUTHBRONZE, BLACKBRONZE, ROSEGOLD, MANASTEEL};
    public final int Tier;

    public final String Name;
    
    AnvilReq(String n, int tier)
    {
        Name = n;
        Tier = tier;
    }

    public boolean matches(int tier)
    {
		return tier >= Tier;
    }
    public boolean matches(AnvilReq req)
    {
		return req.Tier >= Tier;
    }
    public static boolean matches(int recipeLevel, int anvilLevel)
    {
		return anvilLevel >= recipeLevel;
	}
    
    public static AnvilReq getReqFromInt(int i)
    {
    	switch(i)
    	{
    	case 1:
    		return COPPER;
    	case 2:
    		return BRONZE;
    	case 3:
    		return WROUGHTIRON;
    	case 4:
    		return STEEL;
    	case 5:
    		return BLACKSTEEL;
    	case 6:
    		return REDSTEEL;
    	case 7:
    		return BLUESTEEL;
    	default:
    		return STONE;
    	}
    }
    public static AnvilReq getReqFromInt2(int i)
    {
    	switch(i)
    	{
    	case 0:
    		return BISMUTHBRONZE;
    	case 1:
    		return BLACKBRONZE;
    	case 2:
    		return ROSEGOLD;
    	default:
    		return BISMUTHBRONZE;
    	}
    }
	public static AnvilReq getReqFromInt3(int i){
		return MANASTEEL;
	}

}
