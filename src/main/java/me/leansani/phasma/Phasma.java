package me.leansani.phasma;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("optifine")
public class Phasma {
    public static EventBus EVENT_BUS = (EventBus) MinecraftForge.EVENT_BUS;

    public Phasma() {
        //HWIDManager.hwidCheck();
        Brain.implantsRegister();

        EVENT_BUS.register(new Brain());
    }
}
