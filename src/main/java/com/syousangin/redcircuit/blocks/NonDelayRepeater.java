package com.syousangin.redcircuit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;


public class NonDelayRepeater extends DiodeBlock {

    public static final String nonDelayRepeaterId = "non_delay_repeater";

    public NonDelayRepeater() {
        super(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().sound(SoundType.WOOD));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, Boolean.valueOf(false)));
    }

    protected boolean isAlternateInput(BlockState pState) {
        return isDiode(pState);
    }

    @Override
    protected int getDelay(BlockState pState) {
        return 0;
    }



    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING,POWERED);
    }
}