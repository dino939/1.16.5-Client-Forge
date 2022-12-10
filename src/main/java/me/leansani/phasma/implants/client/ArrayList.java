package me.leansani.phasma.implants.client;

import me.leansani.phasma.Brain;
import me.leansani.phasma.Module;
import me.leansani.phasma.managers.GLSLSandboxManager;
import me.leansani.phasma.utils.RenderUtil;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;

import static me.leansani.phasma.Brain.mc;

public class ArrayList extends Module {

    public ArrayList() {
        super("ArrayList", 0, Category.CLIENT, "Shows enabled modules");}

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent e) {
        if (isToggled()) {
            int x = 10;
            int y = 25;
            int distance = 10;

            if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
                java.util.ArrayList<Module> enabledMods = new java.util.ArrayList<>();

                for (Module m : Brain.modules) {
                    if (m.toggled) {
                        enabledMods.add(m);
                    }
                }

                enabledMods.sort((module1, module2) -> mc.font.width(module2.getName()) - mc.font.width(module1.getName()));

                for (Module m : enabledMods) {
                    RenderUtil.drawSmoothRect(e.getMatrixStack(), x, y, x + mc.font.width(m.name) + 6, y + 10, new Color(0x99000000, true).hashCode());
                    RenderUtil.drawSmoothRect(e.getMatrixStack(), x, y, x + 2, y + 10, -1);
                    mc.font.drawShadow(e.getMatrixStack(), m.name, x + 5, y + 1, -1);

                    y += distance;
                }
            }
        }
    }

    /*@SubscribeEvent
    public void onMouseDrag(GuiScreenEvent.MouseDragEvent e) {
        for (Module m : Brain.modules) {
            if (mc.screen instanceof ChatScreen && e.getMouseX() > x && e.getMouseY() > y && e.getMouseX() < x + mc.font.width(m.name) + 6 && e.getMouseX() < y + 10) {
                x = (int) e.getMouseX() + mc.font.width(m.name) + 6 / 2;
                y = (int) e.getMouseY() + 5;
            }
        }
    }*/
}
