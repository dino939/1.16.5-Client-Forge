package me.leansani.phasma;

import me.leansani.phasma.clickgui.settings.Setting;
import me.leansani.phasma.notifications.Notification;
import me.leansani.phasma.notifications.NotificationManager;
import me.leansani.phasma.notifications.NotificationType;
import net.minecraft.util.SoundEvents;

import java.util.ArrayList;
import java.util.List;

import static me.leansani.phasma.Brain.mc;
import static me.leansani.phasma.Phasma.EVENT_BUS;

public class Module {
    public String name;
    public boolean toggled;
    public int keyCode;
    public String description;
    public Category category;
    public List<Setting> settingList = new ArrayList<>();

    public void addSetting(Setting c) {
        settingList.add(c);
    }

    public Module(String name, int key, Category category, String description) {
        this.name = name;
        this.keyCode = key;
        this.category = category;
        this.description = description;
    }

    public void onEnable() {
        EVENT_BUS.register(this);
    }

    public void onDisable() {
        EVENT_BUS.unregister(this);
    }

    public String getName() {
        return name;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void toggle() {
        toggled = !toggled;
        if (toggled) {
            onEnable();
            if (Brain.moduleIncluded("ClientSounds")) {
                assert mc.player != null;
                mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, 1.5F, 1.5F);
            }
            NotificationManager.show(new Notification(NotificationType.INFO, "Info", getName() + " was ON!", 1));
        } else {
            onDisable();
            if (Brain.moduleIncluded("ClientSounds")) {
                assert mc.player != null;
                mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, 1.5F, 1.5F);
            }
            NotificationManager.show(new Notification(NotificationType.INFO, "Info", getName() + " was OFF!", 1));
        }
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public enum Category {
        CLIENT,
        COMBAT,
        EXPLOIT,
        MISC,
        MOVE,
        PLAYER,
        RENDER
    }
}
