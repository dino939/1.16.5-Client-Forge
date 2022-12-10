package me.leansani.phasma.implants.render;

import me.leansani.phasma.Module;
import me.leansani.phasma.clickgui.settings.ModeSetting;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

import static org.lwjgl.opengl.GL11.glRotatef;

public class SwingAnimation extends Module {
    ModeSetting animation = new ModeSetting("Animation", "Rotate", "Hoiw", "Angle", "Trop");

    public SwingAnimation() {
        super("SwingAnimation", 0, Category.RENDER, "Changes swing animation");

        addSetting(animation);
    }

    @SubscribeEvent
    public void onRenderArms(RenderHandEvent e) {
        if (e.getSwingProgress() > 0) {
            final float angle = (1f - e.getSwingProgress()) * 360f;
            if (Objects.equals(animation.mode, "Rotate")) {
                glRotatef(angle, 0, 1, 0);
            }
            if (Objects.equals(animation.mode, "Hoiw")) {
                glRotatef(angle, 0, 0, 1);
            }
            if (Objects.equals(animation.mode, "Angle")) {
                glRotatef(angle, 1, 0, 0);
            }
            if (Objects.equals(animation.mode, "Trop")) {
                glRotatef(angle, 4, -2, 3);
            }
        }
    }
}
