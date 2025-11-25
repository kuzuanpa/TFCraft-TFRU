package com.bioxx.tfc.Core;

import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Crafting.AnvilRecipe;
import com.bioxx.tfc.api.Crafting.AnvilReq;
import com.bioxx.tfc.api.Crafting.AnvilWeldRecipe;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import eu.usrv.yamcore.auxiliary.ItemDescriptor;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictMaterial;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GTRecipes {

    public static void addGTRecipes(AnvilManager manager){
        if (MultiTileEntityRegistry.getRegistry("ktfru.multitileentity") != null) manager.addRecipe(new AnvilRecipe(MultiTileEntityRegistry.getRegistry("ktfru.multitileentity").getItem(32762, 1), OP.plate.mat(MT.Bronze, 1), "casing", AnvilReq.COPPER, OP.casingMachine.mat(MT.Bronze, 1)));
        if(new ItemDescriptor("forestry","sturdyMachine").getItemStack(1)!=null)manager.addRecipe(new AnvilRecipe(OP.plateTriple.mat(MT.Bronze,1),OP.plateTriple.mat(MT.Bronze,1),"sturdyMachine",AnvilReq.COPPER,new ItemDescriptor("forestry","sturdyMachine").getItemStack(1)));

        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet2x), new ItemStack(TFCItems.wroughtIronSheet2x), "hopper", AnvilReq.WROUGHTIRON, new ItemStack(TFCBlocks.hopper, 1, 0)));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet), new ItemStack(TFCItems.wroughtIronSheet), "trapdoor", AnvilReq.BRONZE, new ItemStack(Items.iron_door, 1)));
        manager.addRecipe(new AnvilRecipe(OP.plateQuadruple.mat(MT.Fe, 1), OP.plateTriple.mat(MT.Fe, 1), "bucket", AnvilReq.BRONZE, new ItemStack(Items.cauldron, 1)));
        manager.addRecipe(new AnvilRecipe(OP.plateQuadruple.mat(MT.WroughtIron, 1), OP.plateTriple.mat(MT.WroughtIron, 1), "bucket", AnvilReq.BRONZE, new ItemStack(Items.cauldron, 1)));
        manager.addRecipe(new AnvilRecipe(OP.plateQuadruple.mat(MT.Fe, 1), OP.plateTriple.mat(MT.WroughtIron, 1), "bucket", AnvilReq.BRONZE, new ItemStack(Items.cauldron, 1)));
        manager.addRecipe(new AnvilRecipe(OP.plateQuadruple.mat(MT.WroughtIron, 1), OP.plateTriple.mat(MT.Fe, 1), "bucket", AnvilReq.BRONZE, new ItemStack(Items.cauldron, 1)));

        //GT Items
        for (OreDictMaterial mat : new OreDictMaterial[]{MT.Pb, MT.Steel, MT.Cu, MT.WroughtIron, MT.Bronze, MT.Bi, MT.BlackBronze, MT.BismuthBronze, MT.Cupronickel, MT.Ni, MT.Sn, MT.Au, MT.Brass, MT.Electrum, MT.Ag, MT.RoseGold, MT.SterlingSilver, MT.Invar, MT.Ge, MT.Co, MT.AluminiumBrass, MT.Al, MT.TinAlloy, MT.Fe, MT.BlackSteel, MT.RedSteel, MT.BlueSteel, MT.Efrine, MT.StainlessSteel, MT.ArsenicBronze, MT.ArsenicCopper, MT.Manasteel}) {
            AnvilReq req = getAnvilReqFromMaterial(mat);
            manager.addRecipe(new AnvilRecipe(OP.plateQuadruple     .mat(mat, 1), OP.plateQuadruple     .mat(mat, 1), "casing"          , req, OP.casingMachine         .mat(mat, 1)));
            manager.addRecipe(new AnvilRecipe(OP.casingMachine      .mat(mat, 1), OP.casingMachine      .mat(mat, 1), "robustcasing"    , req, OP.casingMachineDouble   .mat(mat, 1)).setMinStepBonusItem(OP.nugget.mat(mat,9)));
            manager.addRecipe(new AnvilRecipe(OP.casingMachineDouble.mat(mat, 1), OP.casingMachineDouble.mat(mat, 1), "reinforcedcasing", req, OP.casingMachineQuadruple.mat(mat, 1)).setMinStepBonusItem(OP.nugget.mat(mat,9)));
            manager.addRecipe(new AnvilRecipe(OP.plate              .mat(mat, 1), OP.stick              .mat(mat, 1), "gear"            , req, OP.gearGtSmall           .mat(mat, 1)).setMinStepBonusItem(OP.nugget.mat(mat,2)));
            manager.addRecipe(new AnvilRecipe(OP.plateQuadruple     .mat(mat, 1), OP.stickLong          .mat(mat, 1), "gear"            , req, OP.gearGt                .mat(mat, 1)).setMinStepBonusItem(OP.nugget.mat(mat,6)));
            manager.addRecipe(new AnvilRecipe(OP.stick              .mat(mat, 1), OP.stick              .mat(mat, 1), "chain"           , req, OP.chain                 .mat(mat, 1)));

            manager.addRecipe(new AnvilRecipe(OP.ingotDouble        .mat(mat, 1), null, "sheet"      , req, OP.plate      .mat(mat, 1)).setMinStepBonusItem(OP.nugget.mat(mat,3)));
            manager.addRecipe(new AnvilRecipe(OP.stick              .mat(mat, 1), null, "ring"       , req, OP.ring       .mat(mat, 1)));
            manager.addRecipe(new AnvilRecipe(OP.plate              .mat(mat, 1), null, "casingsmall", req, OP.casingSmall.mat(mat, 1)));
            manager.addRecipe(new AnvilRecipe(OP.plate              .mat(mat, 1), null, "foil"       , req, OP.foil       .mat(mat, 2)).setMinStepBonusItem(OP.nugget.mat(mat,3)));
            manager.addRecipe(new AnvilRecipe(OP.stickLong          .mat(mat, 1), null, "spring"     , req, OP.spring     .mat(mat, 1)));
            manager.addRecipe(new AnvilRecipe(OP.stick              .mat(mat, 1), null, "springsmall", req, OP.springSmall.mat(mat, 2)));
            manager.addRecipe(new AnvilRecipe(OP.plate              .mat(mat, 1), null, "platecurved", req, OP.plateCurved.mat(mat, 1)));

            manager.addWeldRecipe(new AnvilWeldRecipe(OP.plate.mat(mat, 1), OP.plate.mat(mat, 1), req, OP.plateDouble.mat(mat, 1)));
            manager.addWeldRecipe(new AnvilWeldRecipe(OP.plateDouble.mat(mat, 1), OP.plate.mat(mat, 1), req, OP.plateTriple.mat(mat, 1)));
            manager.addWeldRecipe(new AnvilWeldRecipe(OP.plateTriple.mat(mat, 1), OP.plate.mat(mat, 1), req, OP.plateQuadruple.mat(mat, 1)));
            manager.addWeldRecipe(new AnvilWeldRecipe(OP.plateQuadruple.mat(mat, 1), OP.plate.mat(mat, 1), req, OP.plateQuintuple.mat(mat, 1)));
            manager.addWeldRecipe(new AnvilWeldRecipe(OP.plateDouble.mat(mat, 1), OP.plateDouble.mat(mat, 1), req, OP.plateQuadruple.mat(mat, 1)));
            manager.addWeldRecipe(new AnvilWeldRecipe(OP.plateTriple.mat(mat, 1), OP.plateDouble.mat(mat, 1), req, OP.plateQuintuple.mat(mat, 1)));

            manager.addWeldRecipe(new AnvilWeldRecipe(OP.ingot.mat(mat, 1), OP.ingot.mat(mat, 1), req, OP.ingotDouble.mat(mat, 1)));
            manager.addWeldRecipe(new AnvilWeldRecipe(OP.ingotDouble.mat(mat, 1), OP.ingot.mat(mat, 1), req, OP.ingotTriple.mat(mat, 1)));
            manager.addWeldRecipe(new AnvilWeldRecipe(OP.ingotTriple.mat(mat, 1), OP.ingot.mat(mat, 1), req, OP.ingotQuadruple.mat(mat, 1)));
            manager.addWeldRecipe(new AnvilWeldRecipe(OP.ingotQuadruple.mat(mat, 1), OP.ingot.mat(mat, 1), req, OP.ingotQuintuple.mat(mat, 1)));
            manager.addWeldRecipe(new AnvilWeldRecipe(OP.ingotDouble.mat(mat, 1), OP.ingotDouble.mat(mat, 1), req, OP.ingotQuadruple.mat(mat, 1)));
            manager.addWeldRecipe(new AnvilWeldRecipe(OP.ingotTriple.mat(mat, 1), OP.ingotDouble.mat(mat, 1), req, OP.ingotQuintuple.mat(mat, 1)));

            manager.addWeldRecipe(new AnvilWeldRecipe(OP.stick.mat(mat, 1), OP.stick.mat(mat, 1), req, OP.stickLong.mat(mat, 1)));
        }
    }

    public static AnvilReq getAnvilReqFromMaterial(OreDictMaterial mat){
        if (mat==MT.Cu)return AnvilReq.COPPER;
        if (mat==MT.Pb||mat==MT.Bronze||mat==MT.Bi||mat==MT.Ni||mat==MT.Sn||mat==MT.Au||mat==MT.Ge||mat==MT.Al)return AnvilReq.COPPER;
        if (mat==MT.BlackBronze||mat==MT.WroughtIron||mat==MT.Fe||mat==MT.BismuthBronze||mat==MT.Cupronickel||mat==MT.Brass||mat==MT.Electrum||mat==MT.Ag||mat==MT.RoseGold||mat==MT.SterlingSilver||mat==MT.ArsenicBronze||mat== MT.ArsenicCopper) return AnvilReq.BRONZE;
        if (mat==MT.Steel||mat==MT.BlackSteel||mat==MT.Invar||mat==MT.Co||mat==MT.AluminiumBrass||mat==MT.TinAlloy)return AnvilReq.WROUGHTIRON;
        if (mat==MT.RedSteel||mat==MT.BlueSteel)return AnvilReq.REDSTEEL;
        return AnvilReq.STEEL;
    }
}
