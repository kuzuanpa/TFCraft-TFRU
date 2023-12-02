package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Blocks.BlockTerra;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        updated=false;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
    {
        if (!world.isRemote)
        {
            BlockCollapsible.tryToFall(world, x, y, z, this);
            world.scheduleBlockUpdate(x, y, z, this, tickRate(world));
        }
        int i=x;
        int j=y;
        int k=z;
        Random random = new Random();
        if (!world.isRemote && world.doChunksNearChunkExist(i,j,k, 1) && !BlockCollapsible.isNearSupport(world, i, j, k, 4, 0))
        {
            if (world.getBlock(x,y,z)instanceof Block) world.getBlock(x,y,z).onNeighborBlockChange(world,x,y,z,this);
            int meta = world.getBlockMetadata(i, j, k);

            boolean canFallOneBelow = BlockCollapsible.canFallBelow(world, i, j-1, k);
            byte count = 0;
            List<Integer> sides = new ArrayList<Integer>();

            if(world.isAirBlock(i+1, j, k))
            {
                count++;
                if(BlockCollapsible.canFallBelow(world, i+1, j-1, k))
                    sides.add(0);
            }
            if(world.isAirBlock(i, j, k+1))
            {
                count++;
                if(BlockCollapsible.canFallBelow(world, i, j-1, k+1))
                    sides.add(1);
            }
            if(world.isAirBlock(i-1, j, k))
            {
                count++;
                if(BlockCollapsible.canFallBelow(world, i-1, j-1, k))
                    sides.add(2);
            }
            if(world.isAirBlock(i, j, k-1))
            {
                count++;
                if(BlockCollapsible.canFallBelow(world, i, j-1, k-1))
                    sides.add(3);
            }

            if (!canFallOneBelow && count > 2 && !sides.isEmpty())
            {
                spreadDistance=0;
                switch (sides.get(random.nextInt(sides.size())))
                {
                    case 0:
                    {
                        world.setBlockToAir(i, j, k);
                        world.setBlock(i+1, j, k, this, meta, 0x2);
                        BlockCollapsible.tryToFall(world, i + 1, j, k, this);
                        break;
                    }
                    case 1:
                    {
                        world.setBlockToAir(i, j, k);
                        world.setBlock(i, j, k+1, this, meta, 0x2);
                        BlockCollapsible.tryToFall(world, i, j, k + 1, this);
                        break;
                    }
                    case 2:
                    {
                        world.setBlockToAir(i, j, k);
                        world.setBlock(i-1, j, k, this, meta, 0x2);
                        BlockCollapsible.tryToFall(world, i - 1, j, k, this);
                        break;
                    }
                    case 3:
                    {
                        world.setBlockToAir(i, j, k);
                        world.setBlock(i, j, k-1, this, meta, 0x2);
                        BlockCollapsible.tryToFall(world, i, j, k - 1, this);
                        break;
                    }
                }
            }
            else if(canFallOneBelow)
            {
                BlockCollapsible.tryToFall(world, i, j, k, this);
            }
            spreadCollapse(world,x+1,y,z,this,spreadDistance);
            spreadCollapse(world,x-1,y,z,this,spreadDistance);
            spreadCollapse(world,x,y+1,z,this,spreadDistance);
            spreadCollapse(world,x,y-1,z,this,spreadDistance);
            spreadCollapse(world,x,y,z+1,this,spreadDistance);
            spreadCollapse(world,x,y,z-1,this,spreadDistance);
        }
    }
    public void spreadCollapse(World world,int x,int y,int z,Block block,int spreadDistance){
        if (updated||spreadDistance>5||!(world.getBlock(x,y,z) instanceof BlockSandLike))return;
        this.spreadDistance=spreadDistance;
        world.getBlock(x,y,z).onNeighborBlockChange(world,x,y,z,block);
    }
}
