package com.syousangin.redcircuit.blocks;

import com.syousangin.redcircuit.entities.AndGateEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class XorGate extends DiodeBlock implements EntityBlock {
    public static final String xorGateId = "xor_gate";
    public XorGate(){
        super(Properties.of(Material.DECORATION).instabreak().sound(SoundType.WOOD));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
    }
    @Override
    protected int getDelay(@NotNull BlockState pState) {
        return 0;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new AndGateEntity(pPos,pState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED,FACING);
    }


    @Override
    public boolean canSurvive(@NotNull BlockState pState, @NotNull LevelReader pLevel, BlockPos pPos) {
        return canSupportRigidBlock(pLevel,pPos.below());
    }

    @Override
    protected int getInputSignal(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState) {
        int a = super.getInputSignal(pLevel,pPos,pState);
        int b = super.getAlternateSignal(pLevel,pPos,pState);
        BlockEntity entity = pLevel.getBlockEntity(pPos);
        if (entity instanceof AndGateEntity){
            ((AndGateEntity) entity).setOutputSignal((a>0&&b==0)|| (a==0&&b>0) ? 15:0);
            entity.setChanged();
        }
        return (a>0&&b==0)|| (a==0&&b>0) ? 15:0;
    }

    @Override
    protected int getOutputSignal(BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState) {
        BlockEntity entity = pLevel.getBlockEntity(pPos);
        if (entity instanceof AndGateEntity){
            return ((AndGateEntity) entity).getOutputSignal();
        }
        else return  0;
    }
}
