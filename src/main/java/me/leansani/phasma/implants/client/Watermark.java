package me.leansani.phasma.implants.client;

import javafx.scene.effect.GaussianBlur;
import me.leansani.phasma.Module;
import me.leansani.phasma.utils.RenderUtil;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static me.leansani.phasma.Brain.mc;

public class Watermark extends Module {
    int x = 10;
    int y = 10;

    public Watermark() {
        super("Watermark", 0, Category.CLIENT, "Shows the watermark of the cheat");
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent e) {
        if (isToggled()) {
            if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
                assert mc.player != null;
                try {
                    RenderUtil.drawSmoothRect(e.getMatrixStack(), x - 1, y - 3, x + mc.font.width("Phasma | FPS: " + mc.fpsString.split("fps")[0] + "| Name: " + mc.player.getGameProfile().getName() + " | Server: " + Objects.requireNonNull(mc.getCurrentServer()).ip + " | Ping: " + Objects.requireNonNull(mc.getCurrentServer()).ping + " | Time: " + new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis()))) + 2, y + 10, new Color(0x99000000, true).hashCode());
                    RenderUtil.drawSmoothRect(e.getMatrixStack(), x, y, x + mc.font.width("Phasma | FPS: " + mc.fpsString.split("fps")[0] + "| Name: " + mc.player.getGameProfile().getName() + " | Server: " + Objects.requireNonNull(mc.getCurrentServer()).ip + " | Ping: " + Objects.requireNonNull(mc.getCurrentServer()).ping + " | Time: " + new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis()))) + 1, y - 2, -1);
                    mc.font.drawShadow(e.getMatrixStack(), "Phasma | FPS: " + mc.fpsString.split("fps")[0] + "| Name: " + mc.player.getGameProfile().getName() + " | Server: " + Objects.requireNonNull(mc.getCurrentServer()).ip + " | Ping: " + Objects.requireNonNull(mc.getCurrentServer()).ping + " | Time: " + new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis())), x + 1, y + 1, -1);
                    if (mc.screen instanceof ChatScreen) {
                        RenderUtil.drawSmoothRect(e.getMatrixStack(), x - 1, y - 3, x + mc.font.width("Phasma | FPS: " + mc.fpsString.split("fps")[0] + "| Name: " + mc.player.getGameProfile().getName() + " | Server: " + Objects.requireNonNull(mc.getCurrentServer()).ip + " | Ping: " + Objects.requireNonNull(mc.getCurrentServer()).ping + " | Time: " + new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis()))) + 2, y + 10, new Color(0x4DFFFFFF, true).hashCode());
                    }
                } catch (Exception exception) {
                    RenderUtil.drawSmoothRect(e.getMatrixStack(), x - 1, y - 3, x + mc.font.width("Phasma | FPS: " + mc.fpsString.split("fps")[0] + "| Name: " + mc.player.getGameProfile().getName() + " | Server: null | Ping: null | Time: " + new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis()))) + 2, y + 10, new Color(0x99000000, true).hashCode());
                    RenderUtil.drawSmoothRect(e.getMatrixStack(), x, y, x + mc.font.width("Phasma | FPS: " + mc.fpsString.split("fps")[0] + "| Name: " + mc.player.getGameProfile().getName() + " | Server: null | Ping: null | Time: " + new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis()))) + 1, y - 2, -1);
                    mc.font.drawShadow(e.getMatrixStack(), "Phasma | FPS: " + mc.fpsString.split("fps")[0] + "| Name: " + mc.player.getGameProfile().getName() + " | Server: null | Ping: null | Time: " + new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis())), x + 1, y + 1, -1);
                    if (mc.screen instanceof ChatScreen) {
                        RenderUtil.drawSmoothRect(e.getMatrixStack(), x - 1, y - 3, x + mc.font.width("Phasma | FPS: " + mc.fpsString.split("fps")[0] + "| Name: " + mc.player.getGameProfile().getName() + " | Server: null | Ping: null | Time: " + new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis()))) + 2, y + 10, new Color(0x4DFFFFFF, true).hashCode());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onMouseDrag(GuiScreenEvent.MouseDragEvent e) {
        if (mc.screen instanceof ChatScreen) {
            assert mc.player != null;
            try {
                if (e.getMouseX() > x - 1 && e.getMouseX() < x + mc.font.width("Phasma | FPS: " + mc.fpsString.split("fps")[0] + "| Name: " + mc.player.getGameProfile().getName() + " | Server: " + Objects.requireNonNull(mc.getCurrentServer()).ip + " | Ping: " + Objects.requireNonNull(mc.getCurrentServer()).ping + " | Time: " + new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis()))) + 2 && e.getMouseY() > y - 3 - 20 && e.getMouseY() < y + 15 + 20) {
                    x = (int) e.getMouseX() - mc.font.width("Phasma | FPS: " + mc.fpsString.split("fps")[0] + "| Name: " + mc.player.getGameProfile().getName() + " | Server: " + Objects.requireNonNull(mc.getCurrentServer()).ip + " | Ping: " + Objects.requireNonNull(mc.getCurrentServer()).ping + " | Time: " + new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis()))) / 2 + 1;
                    y = (int) e.getMouseY() - 5;
                }
            } catch (Exception exception) {
                if (e.getMouseX() > x  - 1 && e.getMouseX() < x + mc.font.width("Phasma | FPS: " + mc.fpsString.split("fps")[0] + "| Name: " + mc.player.getGameProfile().getName() + " | Server: null | Ping: null | Time: " + new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis()))) + 2 && e.getMouseY() > y - 3 - 20 && e.getMouseY() < y + 15 + 20) {
                    x = (int) e.getMouseX() - mc.font.width("Phasma | FPS: " + mc.fpsString.split("fps")[0] + "| Name: " + mc.player.getGameProfile().getName() + " | Server: null | Ping: null | Time: " + new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis()))) / 2 + 1;
                    y = (int) e.getMouseY() - 5;
                }
            }
        }
    }
}
