package me.leansani.phasma.clickgui.settings;

public class BooleanSetting extends Setting {
    private boolean enabled;

    public BooleanSetting(String name, boolean defaultVal) {
        super(name);
        this.enabled = defaultVal;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled() {
        this.enabled = enabled;
    }
}
