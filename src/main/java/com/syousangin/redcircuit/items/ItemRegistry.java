package com.syousangin.redcircuit.items;

import com.syousangin.redcircuit.Util;
import com.syousangin.redcircuit.blocks.*;
import com.syousangin.redcircuit.tabs.ModTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,Util.MOD_ID);
    public static final RegistryObject<Item> nonDelayRepeater_item = ITEMS.register(
            NonDelayRepeater.nonDelayRepeaterId,
            ()->new BlockItem(BlockRegistry.nonDelayRegister.get(),new Item.Properties().tab(ModTabs.redCircuitTab))
    );
    public static final RegistryObject<Item> andGate_item = ITEMS.register(
            AndGate.andGateID,
            ()->new BlockItem(BlockRegistry.andGate.get(),new Item.Properties().tab(ModTabs.redCircuitTab))
    );

    public static final RegistryObject<Item> orGate_item = ITEMS.register(
            OrGate.orGateId,
            ()->new BlockItem(BlockRegistry.orGate.get(),new Item.Properties().tab(ModTabs.redCircuitTab))
    );
    public static final RegistryObject<Item> xorGate_item = ITEMS.register(
            XorGate.xorGateId,
            ()->new BlockItem(BlockRegistry.xorGate.get(),new Item.Properties().tab(ModTabs.redCircuitTab))
    );
    public static final  RegistryObject<Item> fastTorch_item = ITEMS.register(
            FastTorch.fastTorchId,
            ()-> new StandingAndWallBlockItem(
                    BlockRegistry.fastTorch.get(),
                    BlockRegistry.fastWallTorch.get(),
                    new Item.Properties().tab(ModTabs.redCircuitTab)
            )
    );
}
