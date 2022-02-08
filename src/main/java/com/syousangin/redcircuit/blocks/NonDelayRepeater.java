package com.syousangin.redcircuit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
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
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
    }

    protected boolean isAlternateInput(@NotNull BlockState pState) {
        return isDiode(pState);
    }

    @Override
    protected int getDelay(@NotNull BlockState pState) {
        return 0;
    }

    @Override
    public boolean canSurvive(@NotNull BlockState pState, @NotNull LevelReader pLevel, BlockPos pPos) {
        return canSupportRigidBlock(pLevel,pPos.below());
    }
    @Override
    protected int getInputSignal(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState) {
        int a = super.getInputSignal(pLevel,pPos,pState);
        return a>0 ? 15:0;
    }


    @Override
    protected int getOutputSignal(BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState) {
        return pLevel.getBlockState(pPos).getValue(POWERED) ? 15:0;
    }



    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING,POWERED);
    }
}