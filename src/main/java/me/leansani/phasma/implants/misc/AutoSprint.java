package me.leansani.phasma.implants.misc;

import me.leansani.phasma.Module;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static me.leansani.phasma.Brain.mc;

public class AutoSprint extends Module {
    public AutoSprint() {super("AutoSprint", 0, Category.MISC, "Automatically starts running when you walk");}

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (isToggled()) {
            mc.options.keySprint.setDown(true);
        }
    }
}
