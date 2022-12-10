package me.leansani.phasma.clickgui.settings;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ModeSetting extends Setting {
    private List<String> modes;
    private int index;
    public String mode;

    public List<String> getModes() {
        return modes;
    }

    public void setModes(List<String> modes) {
        this.modes = modes;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        this.mode = modes.get(index);
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
        this.index = modes.indexOf(mode);
    }

    public ModeSetting(String name, String... modes) {
        super(name);
        this.modes = Arrays.asList(modes);
        this.mode = this.modes.get(0);
        this.index = this.modes.indexOf(modes);
    }

    public void cycle() {
        if (index < modes.size() - 1) {
            index++;
            mode = modes.get(index);
        } else if (index >= modes.size() - 1) {
            index = 0;
            mode = modes.get(0);
        }
    }

    public boolean isMode(String mode) {
        return Objects.equals(this.mode, mode);
    }
}
