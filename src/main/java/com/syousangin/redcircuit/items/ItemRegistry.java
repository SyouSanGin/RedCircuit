package com.syousangin.redcircuit.items;

import com.syousangin.redcircuit.Util;
import com.syousangin.redcircuit.blocks.BlockRegistry;
import com.syousangin.redcircuit.blocks.Conductor;
import com.syousangin.redcircuit.blocks.EXAMPLE_BLK;
import com.syousangin.redcircuit.tabs.ModTabs;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,Util.MOD_ID);
    public static final RegistryObject<Item> EXAMPLE_BLK_ITEM = ITEMS.register(
            EXAMPLE_BLK.EXAMPLE_BLK_ID,
            ()->new BlockItem(BlockRegistry.EXAMPLE_BLK_BLK.get(), new Item.Properties().tab(ModTabs.redCircuitTab))
    );
    public static final RegistryObject<Item> conductorItem = ITEMS.register(
            Conductor.CONDUCTOR_ID,
            ()->new BlockItem(BlockRegistry.conductor.get(), new Item.Properties().tab(ModTabs.redCircuitTab))
    );
}
