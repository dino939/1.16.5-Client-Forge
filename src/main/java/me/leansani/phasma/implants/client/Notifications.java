package me.leansani.phasma.implants.client;

import me.leansani.phasma.Module;
import me.leansani.phasma.notifications.NotificationManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Notifications extends Module {
    public Notifications() {
        super("Notifications", 0, Category.CLIENT, "Shows the watermark of the cheat");
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event){
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            NotificationManager.render(event.getMatrixStack(), event);
        }
    }
}
