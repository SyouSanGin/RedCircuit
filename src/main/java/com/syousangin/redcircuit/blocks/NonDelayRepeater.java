package com.syousangin.redcircuit.blocks;



import com.sun.jna.platform.win32.WinNT;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;


import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;


public class NonDelayRepeater extends HorizontalDirectionalBlock {

    public static final String nonDelayRepeaterId = "non_delay_repeater";

    public static final VoxelShape nonDelayRepeaterBase = Block.box(0,0,0,16,2,16);

    public NonDelayRepeater() {
        super(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().sound(SoundType.WOOD).instabreak());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(BlockStateProperties.POWERED, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return nonDelayRepeaterBase;
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return canSupportRigidBlock(pLevel,pPos.below());
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        if (!pLevel.isClientSide) {
            pLevel.setBlock(pPos, pState.setValue(FACING, pPlacer.getDirection().getOpposite()), 2);

            /*更新状态*/
            BlockState sourceState = pLevel.getBlockState(pPos.relative(pPlacer.getDirection().getOpposite()));
            BlockState distState = pLevel.getBlockState(pPos.relative(pPlacer.getDirection()));
            if (sourceState.hasProperty(BlockStateProperties.POWERED)) {
                if (distState.getValue(BlockStateProperties.POWERED))
                    pLevel.setBlock(pPos, pState.setValue(BlockStateProperties.POWERED, true), 2);
                else
                    pLevel.setBlock(pPos, pState.setValue(BlockStateProperties.POWERED, false), 2);

            } else if (sourceState.hasProperty(BlockStateProperties.POWER)) {
                if (distState.getValue(BlockStateProperties.POWER) > 0)
                    pLevel.setBlock(pPos, pState.setValue(BlockStateProperties.POWER, 15), 2);
                else
                    pLevel.setBlock(pPos, pState.setValue(BlockStateProperties.POWER, 0), 2);

            }
        }
    }



    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide && !pFromPos.equals(pPos.relative(pState.getValue(FACING).getOpposite()))) {

            /*更新状态*/
            BlockState sourceState = pLevel.getBlockState(pPos.relative(pState.getValue(FACING)));
            BlockState distState = pLevel.getBlockState(pPos.relative(pState.getValue(FACING).getOpposite()));

            int f1 = sourceState.hasProperty(BlockStateProperties.POWER) ? 1: (sourceState.hasProperty(BlockStateProperties.POWERED)?2:0);
            f1+= distState.hasProperty(BlockStateProperties.POWER) ? 10: (distState.hasProperty(BlockStateProperties.POWERED)?20:0);
            switch (f1){
                case 11:
                    pLevel.setBlock(pPos,pState.setValue(BlockStateProperties.POWERED,sourceState.getValue(BlockStateProperties.POWER)>0),2);
                    pLevel.setBlock(pPos.relative(pState.getValue(FACING).getOpposite()),distState.setValue(BlockStateProperties.POWER,sourceState.getValue(BlockStateProperties.POWER)>0?15:0), 2);
                    break;

                case 12:
                    pLevel.setBlock(pPos,pState.setValue(BlockStateProperties.POWERED,sourceState.getValue(BlockStateProperties.POWERED)),2);
                    pLevel.setBlock(pPos.relative(pState.getValue(FACING).getOpposite()),distState.setValue(BlockStateProperties.POWER,sourceState.getValue(BlockStateProperties.POWERED)?15:0), 2);
                    break;
                case 21:
                    pLevel.setBlock(pPos,pState.setValue(BlockStateProperties.POWERED,sourceState.getValue(BlockStateProperties.POWER)>0),2);
                    pLevel.setBlock(pPos.relative(pState.getValue(FACING).getOpposite()),distState.setValue(BlockStateProperties.POWERED,sourceState.getValue(BlockStateProperties.POWER)>0), 2);
                    break;
                case 22:
                    pLevel.setBlock(pPos,pState.setValue(BlockStateProperties.POWERED,sourceState.getValue(BlockStateProperties.POWERED)),2);
                    pLevel.setBlock(pPos.relative(pState.getValue(FACING).getOpposite()),distState.setValue(BlockStateProperties.POWERED,sourceState.getValue(BlockStateProperties.POWERED)), 2);
                    break;
            }

        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING,BlockStateProperties.POWERED);
    }
}