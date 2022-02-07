package com.syousangin.redcircuit.blocks;

import com.syousangin.redcircuit.Util;
import net.minecraft.world.level.block.Block;
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

}
