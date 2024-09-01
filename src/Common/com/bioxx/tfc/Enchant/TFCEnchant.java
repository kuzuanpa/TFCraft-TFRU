package com.bioxx.tfc.Enchant;

import com.bioxx.tfc.Enchant.javelin.EnchantFidelity;
import com.bioxx.tfc.Enchant.javelin.EnchantPiercing;
import net.minecraft.enchantment.Enchantment;

public class TFCEnchant {
    public static Enchantment piercing, fidelity;
    public static void registerEnchantments(){
        piercing=new EnchantPiercing(65,4);
        fidelity=new EnchantFidelity(67,1);
    }
}
