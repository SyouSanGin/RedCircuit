package com.syousangin.redcircuit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import java.io.*;

public class EXAMPLE_BLK extends Block {
    public EXAMPLE_BLK() {
        super(Properties.of(Material.STONE).strength(50f));
    }
    public static final String EXAMPLE_BLK_ID  = "example_blk";
    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        System.out.println ("Name :" + pBlock.getName() + "\n" + "FromPos: " + pFromPos.toShortString());
    }
}
