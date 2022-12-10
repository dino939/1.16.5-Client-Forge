package me.leansani.phasma.notifications;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.leansani.phasma.utils.RenderUtil;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.awt.*;

import static me.leansani.phasma.Brain.mc;

public class Notification {
    private final NotificationType type;
    private final String title;
    private final String messsage;
    private long start;
    private final long fadedIn;
    private final long fadeOut;
    private final long end;


    public Notification(NotificationType type, String title, String messsage, int length) {
        this.type = type;
        this.title = title;
        this.messsage = messsage;

        fadedIn = 200L * length;
        fadeOut = fadedIn + 500L * length;
        end = fadeOut + fadedIn;
    }

    public void show() {start = System.currentTimeMillis();}

    public boolean isShown() {return getTime() <= end;}

    private long getTime() {return System.currentTimeMillis() - start;}

    public void render(MatrixStack m, RenderGameOverlayEvent e) {
        double offset;
        int width = 120;
        int height = 30;
        long time = getTime();

        if (time < fadedIn) {
            offset = Math.tanh(time / (double) (fadedIn) * 3.0) * width;
        } else if (time > fadeOut) {
            offset = (Math.tanh(3.0 - (time - fadeOut) / (double) (end - fadeOut) * 3.0) * width);
        } else {
            offset = width;
        }

        Color color = new Color(0, 0, 0, 160);
        Color color1;

        if (type == NotificationType.INFO)
            color1 = new Color(0, 255, 0);
        else if (type == NotificationType.WARNING)
            color1 = new Color(255, 255, 0);
        else {
            color1 = new Color(255, 0, 18);
            int i = Math.max(0, Math.min(255, (int) (Math.sin(time / 100.0) * 255.0 / 2 + 127.5)));
            color = new Color(i, 0, 0, 220);
        }

        int sw = e.getWindow().getGuiScaledWidth();

        RenderUtil.drawSmoothRect(m, (float) (sw - offset + 5), e.getWindow().getGuiScaledHeight() - 5 - height, sw + 10, e.getWindow().getGuiScaledHeight() - 5, color.getRGB());
        RenderUtil.drawSmoothRect(m, (float) (sw - offset), (float) (e.getWindow().getGuiScaledHeight() - 5 - height), (float) (sw - offset + 4), e.getWindow().getGuiScaledHeight() - 5, color1.getRGB());

        mc.font.draw(m, title, (int) (e.getWindow().getGuiScaledWidth() - offset + 8), e.getWindow().getGuiScaledHeight() - 2 - height, -1);
        mc.font.draw(m, messsage, (int) (sw - offset + 8), e.getWindow().getGuiScaledHeight() - 15, -1);
    }
}
