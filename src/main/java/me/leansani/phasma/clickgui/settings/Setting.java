package me.leansani.phasma.clickgui.settings;

public class Setting {
    public String name;
    public boolean isVisible = true;

    public Setting(String name) {this.name = name;}

    public boolean isVisible() {return isVisible;}

    public String getName() {return name;}
}
