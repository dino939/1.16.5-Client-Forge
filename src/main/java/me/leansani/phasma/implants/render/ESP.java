package me.leansani.phasma.implants.render;

import me.leansani.phasma.Module;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static me.leansani.phasma.Brain.mc;

public class ESP extends Module {
    public ESP() {
        super("ESP", 0, Category.RENDER, "Shows players through walls");
    }

    @SubscribeEvent
    public void onRender(RenderPlayerEvent e) {
        if (isToggled()) {
            e.getEntity().setGlowing(true);
        }
    }

    @Override
    public void onDisable() {
        assert mc.level != null;
        for (PlayerEntity p : mc.level.players()) {
            p.setGlowing(false);
        }
    }
}
