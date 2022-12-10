package me.leansani.phasma.implants.combat;

import me.leansani.phasma.Module;
import net.minecraft.item.Items;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static me.leansani.phasma.Brain.mc;

public class AutoGApple extends Module {
    public AutoGApple() {super("AutoGApple", 0, Category.COMBAT, "If you are low on health, you will automatically eat a golden apple");}

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (isToggled()) {
            assert mc.player != null;
            mc.options.keyUse.setDown(mc.player.getHealth() < 16 && mc.player.getOffhandItem().getItem() == Items.GOLDEN_APPLE);
        }
    }
}
