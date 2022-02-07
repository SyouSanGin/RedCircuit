package com.syousangin.redcircuit.blocks;

import com.syousangin.redcircuit.RedCircuit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

public class Conductor extends Block {
    public Conductor(){
        super(Properties.of(Material.BARRIER));
        this.registerDefaultState(this.defaultBlockState().setValue(RedCircuit.EMPOWERED,false));
    }
    public static final String CONDUCTOR_ID = "conductor";

    /*可以与红石链接*/
    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, @Nullable Direction direction) {
        return true;
    }




    /*周围环境改变的事件*/
    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        int bestRedSignalOfNeighbors = pLevel.getBestNeighborSignal(pPos);
        /*获取最大的红石信号*/
        boolean bestDigitalSignal=false;
        for (Direction direction : Direction.values()){
            /*遍历所有8个方向的方块，获取最大的信号值*/
            BlockState neighborState = pLevel.getBlockState(pPos.relative(direction));
            bestDigitalSignal =
                    neighborState.is(BlockRegistry.conductor.get())
                    && neighborState.getValue(RedCircuit.EMPOWERED)
                    || bestDigitalSignal;
        }

        /*更新自身的状态*/
        pState.setValue(RedCircuit.EMPOWERED, bestRedSignalOfNeighbors>0 ||bestDigitalSignal);
        pLevel.setBlock(pPos,pState,2);
        /*更新周围状态*/
        for (Direction direction : Direction.values()){
            BlockState ns = pLevel.getBlockState(pPos.relative(direction));
            if (ns.is(BlockRegistry.conductor.get()))
                pLevel.updateNeighborsAt(pPos.relative(direction),BlockRegistry.conductor.get());
        }

    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(RedCircuit.EMPOWERED);
    }
}
