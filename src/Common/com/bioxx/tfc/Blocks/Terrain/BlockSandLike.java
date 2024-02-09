package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BlockSandLike extends BlockTerra {
    public BlockSandLike(Material ground) {
        super(ground);
    }
    public int spreadDistance=0;
    public boolean updated=false;
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack is)
    {
        onNeighborBlockChange(world,x, y, z, this);
    }
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
    {
        if(world.isRemote)return;
        BlockCollapsible.tryToFall(world, x, y, z, this);
        world.scheduleBlockUpdate(x, y, z, this, tickRate(world));
        Random random = new Random();
        if (world.doChunksNearChunkExist(x, y, z, 1) && !BlockCollapsible.isNearSupport(world, x, y, z, 4, 0))
        {
            int meta = world.getBlockMetadata(x, y, z);

            boolean canFallOneBelow = BlockCollapsible.canFallBelow(world, x, y -1, z);
            byte count = 0;
            List<Integer> sides = new ArrayList<Integer>();

            if(world.isAirBlock(x +1, y, z))
            {
                count++;
                if(BlockCollapsible.canFallBelow(world, x +1, y -1, z))
                    sides.add(0);
            }
            if(world.isAirBlock(x, y, z +1))
            {
                count++;
                if(BlockCollapsible.canFallBelow(world, x, y -1, z +1))
                    sides.add(1);
            }
            if(world.isAirBlock(x -1, y, z))
            {
                count++;
                if(BlockCollapsible.canFallBelow(world, x -1, y -1, z))
                    sides.add(2);
            }
            if(world.isAirBlock(x, y, z -1))
            {
                count++;
                if(BlockCollapsible.canFallBelow(world, x, y -1, z -1))
                    sides.add(3);
            }

            if (!canFallOneBelow && count > 2 && !sides.isEmpty())
            {
                switch (sides.get(random.nextInt(sides.size())))
                {
                    case 0:
                    {
                        world.setBlockToAir(x, y, z);
                        world.setBlock(x +1, y, z, this, meta, 0x2);
                        BlockCollapsible.tryToFall(world, x + 1, y, z, this);
                        break;
                    }
                    case 1:
                    {
                        world.setBlockToAir(x, y, z);
                        world.setBlock(x, y, z +1, this, meta, 0x2);
                        BlockCollapsible.tryToFall(world, x, y, z + 1, this);
                        break;
                    }
                    case 2:
                    {
                        world.setBlockToAir(x, y, z);
                        world.setBlock(x -1, y, z, this, meta, 0x2);
                        BlockCollapsible.tryToFall(world, x - 1, y, z, this);
                        break;
                    }
                    case 3:
                    {
                        world.setBlockToAir(x, y, z);
                        world.setBlock(x, y, z -1, this, meta, 0x2);
                        BlockCollapsible.tryToFall(world, x, y, z - 1, this);
                        break;
                    }
                }
            }
            else if(canFallOneBelow)
            {
                BlockCollapsible.tryToFall(world, x, y, z, this);
            }
        }
    }
}
