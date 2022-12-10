package me.leansani.phasma.implants.move;

import me.leansani.phasma.Module;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static me.leansani.phasma.Brain.mc;

public class Jesus extends Module {
    public Jesus() {
        super("Jesus", 0, Category.MOVE, "Shows players through walls");
    }

    @SubscribeEvent
    public void onPlayerUpdate(TickEvent.PlayerTickEvent event){
        if (isToggled()) {
            assert mc.player != null;
            if (mc.player.isInWater() && !mc.player.isCrouching()) {
                double velY = 0.1;
                if (mc.player.getVehicle() != null && !(mc.player.getVehicle() instanceof BoatEntity)) {
                    velY = 0.3;
                }
                Vector3d vel = mc.player.getDeltaMovement();
                mc.player.setDeltaMovement(vel.x(), velY, vel.z());
            }
        }
    }
}
