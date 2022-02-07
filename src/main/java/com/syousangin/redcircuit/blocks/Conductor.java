package com.syousangin.redcircuit.blocks;

import com.google.common.collect.Sets;
import com.syousangin.redcircuit.RedCircuit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

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


    @Override
    public void setPlacedBy(Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @Nullable LivingEntity pPlacer, @NotNull ItemStack pStack) {
        if (!pLevel.isClientSide){
            boolean nowState = this.calculatePower(pLevel,pPos);
            if(pState.getValue(RedCircuit.EMPOWERED) != nowState) {
                pLevel.setBlock(pPos, pState.setValue(RedCircuit.EMPOWERED, nowState), 2);

                Set<BlockPos> set = Sets.newHashSet();
                set.add(pPos);
                for (Direction direction : Direction.values()) {
                    set.add(pPos.relative(direction));
                }
                for (BlockPos blockPos : set) {
                    pLevel.updateNeighborsAt(blockPos, this);
                }
            }
        }
    }

    /*周围环境改变的事件*/
    @Override
    public void neighborChanged(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Block pBlock, @NotNull BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide){
            boolean nowState = this.calculatePower(pLevel,pPos);
            if(pState.getValue(RedCircuit.EMPOWERED) != nowState) {
                pLevel.setBlock(pPos, pState.setValue(RedCircuit.EMPOWERED, nowState), 2);

                Set<BlockPos> set = Sets.newHashSet();
                set.add(pPos);
                for (Direction direction : Direction.values()) {
                    set.add(pPos.relative(direction));
                }
                for (BlockPos blockPos : set) {
                    pLevel.updateNeighborsAt(blockPos, this);
                }
            }
        }
    }
    private boolean calculatePower(Level level,BlockPos thisPos){
        boolean neighborRedstonePower = level.getBestNeighborSignal(thisPos) > 0;
        boolean neighborDigitalPower = false;
        for (Direction direction: Direction.values()){
            if(level.getBlockState(thisPos.relative(direction)).is(BlockRegistry.conductor.get())){
                neighborDigitalPower =
                        level.getBlockState(thisPos.relative(direction)).getValue(RedCircuit.EMPOWERED)
                        || neighborDigitalPower;
            }
        }
        return neighborDigitalPower || neighborRedstonePower;
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(RedCircuit.EMPOWERED);
    }
}
