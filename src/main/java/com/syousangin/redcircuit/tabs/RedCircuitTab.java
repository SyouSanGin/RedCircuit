package com.syousangin.redcircuit.tabs;

import com.syousangin.redcircuit.RedCircuit;
import com.syousangin.redcircuit.items.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class RedCircuitTab extends CreativeModeTab {
    public RedCircuitTab() {
        super ("redcircuit_tab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemRegistry.EXAMPLE_BLK_ITEM.get());
    }
}
