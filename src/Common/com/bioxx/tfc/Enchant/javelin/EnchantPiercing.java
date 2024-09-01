package com.bioxx.tfc.Enchant.javelin;

import com.bioxx.tfc.Items.Tools.ItemJavelin;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class EnchantPiercing extends Enchantment {
    public EnchantPiercing(int p_i1926_1_, int p_i1926_2_) {
        super(p_i1926_1_, p_i1926_2_, EnumEnchantmentType.bow);
        this.setName("piercing");
    }


    public int getMinEnchantability(int p_77321_1_)
    {
        return 1 + (p_77321_1_ - 1) * 10;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int p_77317_1_)
    {
        return this.getMinEnchantability(p_77317_1_) + 15;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel()
    {
        return 5;
    }
    public boolean canApply(ItemStack stack)
    {
        return stack.getItem() instanceof ItemJavelin;
    }
}
