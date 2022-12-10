package me.leansani.phasma;

import me.leansani.phasma.clickgui.ClickGUIScreen;
import me.leansani.phasma.implants.client.ArrayList;
import me.leansani.phasma.implants.client.ClickGUI;
import me.leansani.phasma.implants.client.Notifications;
import me.leansani.phasma.implants.client.Watermark;
import me.leansani.phasma.implants.combat.*;
import me.leansani.phasma.implants.misc.AutoRespawn;
import me.leansani.phasma.implants.misc.AutoSprint;
import me.leansani.phasma.implants.move.Jesus;
import me.leansani.phasma.implants.render.ESP;
import me.leansani.phasma.implants.render.SwingAnimation;
import me.leansani.phasma.mainmenu.MainMenuScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class Brain {
    public static Minecraft mc = Minecraft.getInstance();
    public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<>();
    ClickGUIScreen clickGuiScreen = new ClickGUIScreen();

    public static void implantsRegister() {
        modules.add(new ESP());
        modules.add(new Watermark());
        modules.add(new Notifications());
        modules.add(new ArrayList());
        modules.add(new AutoSprint());
        modules.add(new ClickGUI());
        modules.add(new SwingAnimation());
        modules.add(new Jesus());
        modules.add(new AutoRespawn());
        modules.add(new AutoShiftTap());
        modules.add(new AttackAura());
        modules.add(new AutoTotem());
        modules.add(new AutoGApple());
        modules.add(new Velocity());
        modules.add(new AutoCrystal());

        modules.add(new ESP());
        modules.add(new Watermark());
        modules.add(new Notifications());
        modules.add(new ArrayList());
        modules.add(new AutoSprint());
        modules.add(new ClickGUI());
        modules.add(new SwingAnimation());
        modules.add(new Jesus());
        modules.add(new AutoRespawn());
        modules.add(new AutoShiftTap());
        modules.add(new AttackAura());
        modules.add(new AutoTotem());
        modules.add(new AutoGApple());
        modules.add(new Velocity());
        modules.add(new AutoCrystal());
        modules.add(new ESP());
        modules.add(new Watermark());
        modules.add(new Notifications());
        modules.add(new ArrayList());
        modules.add(new AutoSprint());
        modules.add(new ClickGUI());
        modules.add(new SwingAnimation());
        modules.add(new Jesus());
        modules.add(new AutoRespawn());
        modules.add(new AutoShiftTap());
        modules.add(new AttackAura());
        modules.add(new AutoTotem());
        modules.add(new AutoGApple());
        modules.add(new Velocity());
        modules.add(new AutoCrystal());
        modules.add(new ESP());
        modules.add(new Watermark());
        modules.add(new Notifications());
        modules.add(new ArrayList());
        modules.add(new AutoSprint());
        modules.add(new ClickGUI());
        modules.add(new SwingAnimation());
        modules.add(new Jesus());
        modules.add(new AutoRespawn());
        modules.add(new AutoShiftTap());
        modules.add(new AttackAura());
        modules.add(new AutoTotem());
        modules.add(new AutoGApple());
        modules.add(new Velocity());
        modules.add(new AutoCrystal());
        modules.add(new ESP());
        modules.add(new Watermark());
        modules.add(new Notifications());
        modules.add(new ArrayList());
        modules.add(new AutoSprint());
        modules.add(new ClickGUI());
        modules.add(new SwingAnimation());
        modules.add(new Jesus());
        modules.add(new AutoRespawn());
        modules.add(new AutoShiftTap());
        modules.add(new AttackAura());
        modules.add(new AutoTotem());
        modules.add(new AutoGApple());
        modules.add(new Velocity());
        modules.add(new AutoCrystal());
    }

    public static boolean moduleIncluded(String name) {
        for (Module m : modules) {
            if (m.isToggled()) {
                if (Objects.equals(m.name, name)) {
                    return true;
                }
            }
        }
        return false;
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent e) {
        if (!(mc.level == null) && !(mc.player == null))
            if (e.getAction() == 1 && mc.screen == null) {
                for (Module m : modules) {
                    if (m.getKeyCode() == e.getKey()) {
                        m.toggle();
                    }
                }
            }
        if (e.getKey() == GLFW.GLFW_KEY_RIGHT_SHIFT && mc.screen == null) {
            mc.setScreen(clickGuiScreen);
        }
    }

    @SubscribeEvent
    public void onInitGui(GuiScreenEvent.InitGuiEvent e) {
        if (e.getGui() instanceof net.minecraft.client.gui.screen.MainMenuScreen) {
            mc.setScreen(new MainMenuScreen());
        }
    }
}
