package me.leansani.phasma.implants.combat;

import me.leansani.phasma.Brain;
import me.leansani.phasma.Module;
import me.leansani.phasma.clickgui.components.CheckBox;
import me.leansani.phasma.clickgui.settings.BooleanSetting;
import me.leansani.phasma.clickgui.settings.ModeSetting;
import me.leansani.phasma.clickgui.settings.NumberSetting;
import me.leansani.phasma.utils.TimerUtil;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static me.leansani.phasma.Brain.mc;

public class AttackAura extends Module {
    ModeSetting attackMode = new ModeSetting("AttackMode", "1.9", "1.8.9");
    NumberSetting range = new NumberSetting("Range", 1, 10, 3.5, 1);
    BooleanSetting criticals = new BooleanSetting("Criticals",true);
    NumberSetting criticalsFallDistance = new NumberSetting("CriticalsFallDistance", 0.1, 0.4, 0.2, 1);
    TimerUtil attackDelay = new TimerUtil();

    public AttackAura() {
        super("AttackAura", 0, Category.COMBAT, "Automatically starts running when you walk");

        addSetting(attackMode);
        addSetting(range);
        addSetting(criticals);
        addSetting(criticalsFallDistance);
    }

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (isToggled()) {
            assert mc.level != null;
            for (PlayerEntity target : mc.level.players()) {
                if (target != mc.player) {
                    assert mc.player != null;
                    if (mc.player.distanceTo(target) <= range.getValue()) {
                        if (mc.player.fallDistance >= criticalsFallDistance.getValue() || mc.player.isInWater() || mc.player.isInLava() || attackMode.isMode("1.8.9") || !criticals.isEnabled()) {
                            if (mc.player.getAttackStrengthScale(0.85F) == 1 || attackMode.isMode("1.8.9") && attackDelay.hasReached(50)) {
                                assert mc.gameMode != null;
                                mc.gameMode.attack(mc.player, target);
                                mc.player.swing(Hand.MAIN_HAND);
                                attackDelay.reset();
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickBlock e) {e.setCanceled(true);}
}
