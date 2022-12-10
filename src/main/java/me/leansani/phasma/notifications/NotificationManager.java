package me.leansani.phasma.notifications;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.concurrent.LinkedBlockingQueue;

public class NotificationManager {
    private static final LinkedBlockingQueue<Notification> pendingNotifications = new LinkedBlockingQueue<>();
    private static Notification currentNotification = null;

    public static void show(Notification notification) {pendingNotifications.add(notification);}

    public static void update() {
        if (currentNotification != null && !currentNotification.isShown()) {
            currentNotification = null;
        }

        if (currentNotification == null && !pendingNotifications.isEmpty()) {
            currentNotification = pendingNotifications.poll();
            currentNotification.show();
        }

    }

    public static void render(MatrixStack matrixStack, RenderGameOverlayEvent event) {
        update();

        if (currentNotification != null)
            currentNotification.render(matrixStack,event);
    }
}
