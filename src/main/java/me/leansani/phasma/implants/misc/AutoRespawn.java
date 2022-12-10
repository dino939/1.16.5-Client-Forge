package me.leansani.phasma.implants.misc;

import me.leansani.phasma.Module;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static me.leansani.phasma.Brain.mc;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", 0, Category.MISC, "Automatically starts running when you walk");
    }

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (isToggled()) {
            assert mc.player != null;
            if (mc.player.isDeadOrDying()) {
                mc.player.respawn();
            }
        }
    }
}