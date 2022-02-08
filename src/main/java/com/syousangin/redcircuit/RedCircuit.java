package com.syousangin.redcircuit;

import com.syousangin.redcircuit.blocks.BlockRegistry;
import com.syousangin.redcircuit.entities.EntityRegistry;
import com.syousangin.redcircuit.items.ItemRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Util.MOD_ID)
public class RedCircuit {


    public RedCircuit(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockRegistry.BLOCKS.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        EntityRegistry.BLOCKENTITIES.register(modEventBus);
    }

}
