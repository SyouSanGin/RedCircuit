package com.syousangin.redcircuit.blocks;

import com.syousangin.redcircuit.Util;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Util.MOD_ID);
    public static final RegistryObject<Block> EXAMPLE_BLK_BLK = BLOCKS.register(
            com.syousangin.redcircuit.blocks.EXAMPLE_BLK.EXAMPLE_BLK_ID,
            EXAMPLE_BLK::new
    );
    public static final RegistryObject<Block> nonDelayRegister = BLOCKS.register(
            NonDelayRepeater.nonDelayRepeaterId,
            NonDelayRepeater::new
    );
    public static final RegistryObject<Block> andGate = BLOCKS.register(
            AndGate.andGateID,
            AndGate::new
    );

    public static final RegistryObject<Block> orGate = BLOCKS.register(
            OrGate.orGateId,
            OrGate::new
    );
    public static final RegistryObject<Block> xorGate = BLOCKS.register(
            XorGate.xorGateId,
            XorGate::new
    );
    public  static final  RegistryObject<Block> fastTorch = BLOCKS.register(
            FastTorch.fastTorchId,
            ()->new FastTorch(BlockBehaviour.Properties.of(Material.DECORATION)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WOOD)
            )
    );
    public static final  RegistryObject<Block> fastWallTorch = BLOCKS.register(
            FastWallTorch.fastWallTorchId,
            ()->new FastWallTorch(BlockBehaviour.Properties.of(Material.DECORATION)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WOOD)
                    .dropsLike(BlockRegistry.fastTorch.get()))
    );


}
