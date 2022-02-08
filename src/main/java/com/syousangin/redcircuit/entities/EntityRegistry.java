package com.syousangin.redcircuit.entities;

import com.syousangin.redcircuit.Util;
import com.syousangin.redcircuit.blocks.BlockRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Util.MOD_ID);
    public static final RegistryObject<BlockEntityType<AndGateEntity>> andGateEntity = BLOCKENTITIES.register(
            "and_gate_entity",
            ()-> BlockEntityType.Builder.of(AndGateEntity::new, BlockRegistry.andGate.get(),BlockRegistry.orGate.get(),BlockRegistry.xorGate.get()).build(null)
            );
}
