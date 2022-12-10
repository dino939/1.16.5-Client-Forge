package me.leansani.phasma.implants.combat;

import me.leansani.phasma.Module;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class Velocity extends Module {
    public Velocity() {
        super("Velocity", 0, Category.COMBAT, "Zalupa");
    }

    /*@SubscribeEvent
    public void onReceivePacket(PacketEvent.Incoming e) {
        if (e.getPacket() instanceof SEntityVelocityPacket || e.getPacket() instanceof SExplosionPacket) {
            if (((SEntityVelocityPacket) e.getPacket()).getId() == mc.player.getId()) {
                e.setCanceled(true);
            }
        }
    }*/
}