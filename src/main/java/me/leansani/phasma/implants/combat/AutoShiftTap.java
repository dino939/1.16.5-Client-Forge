package me.leansani.phasma.implants.combat;

import me.leansani.phasma.Module;
import net.minecraft.network.play.client.CEntityActionPacket;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static me.leansani.phasma.Brain.mc;

public class AutoShiftTap extends Module {
    public AutoShiftTap() {
        super("AutoShiftTap", 0, Category.COMBAT, "Shift is automatically pressed when hitting an entity");
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent e) {
        if (isToggled()) {
            assert mc.player != null;
            mc.player.input.shiftKeyDown = true;
            //mc.player.connection.send(new CEntityActionPacket(mc.player, CEntityActionPacket.Action.RELEASE_SHIFT_KEY));
            //mc.player.connection.send(new CEntityActionPacket(mc.player, CEntityActionPacket.Action.PRESS_SHIFT_KEY));
        }
    }
}
