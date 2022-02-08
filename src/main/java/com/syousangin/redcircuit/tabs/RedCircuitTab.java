package com.syousangin.redcircuit.tabs;

import com.syousangin.redcircuit.items.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class RedCircuitTab extends CreativeModeTab {
    public RedCircuitTab() {
        super ("redcircuit_tab");
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(ItemRegistry.nonDelayRepeater_item.get());
    }
}
