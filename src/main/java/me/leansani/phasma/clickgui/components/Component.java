package me.leansani.phasma.clickgui.components;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.leansani.phasma.Module;
import me.leansani.phasma.clickgui.Button;
import me.leansani.phasma.clickgui.settings.Setting;

public class Component {
    public double x;
    public double y;
    public Button parent;
    public Module module;
    public Setting setting;
    public int shifting;

    public Component(Setting setting, Button parent, int shifting) {
        this.shifting = shifting;
        this.setting = setting;
        this.parent = parent;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
    }

    public void mouseReleased(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
    }

    public void draw(MatrixStack m, int mouseX, int mouseY) {
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > parent.parent.x && mouseX < parent.parent.x + parent.parent.width && mouseY > parent.parent.y + parent.shifting + shifting && mouseY < parent.parent.y + parent.shifting + shifting + parent.parent.height;
    }
}
