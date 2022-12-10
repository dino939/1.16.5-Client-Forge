package me.leansani.phasma.clickgui.components;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.leansani.phasma.clickgui.Button;
import me.leansani.phasma.clickgui.settings.ModeSetting;
import me.leansani.phasma.clickgui.settings.Setting;
import me.leansani.phasma.utils.RenderUtil;
import net.minecraft.client.gui.IngameGui;

import java.awt.*;

import static me.leansani.phasma.Brain.mc;

public class ModeBox extends Component {
    private final ModeSetting modeSetting;

    public ModeBox(Setting setting, Button parent, int shifting) {
        super(setting, parent, shifting);
        this.modeSetting = (ModeSetting) setting;
    }

    @Override
    public void draw(MatrixStack m, int mouseX, int mouseY) {
        int textShifting = ((parent.parent.height / 2) - mc.font.lineHeight / 2);

        IngameGui.fill(m, parent.parent.x, parent.parent.y + parent.shifting + shifting, parent.parent.x + parent.parent.width, parent.parent.y + parent.shifting + shifting + parent.parent.height, new Color(0x33000000, true).hashCode());
        mc.font.draw(m, modeSetting.getName() + ":" + modeSetting.getMode(), parent.parent.x + textShifting, parent.parent.y + parent.shifting + shifting + textShifting + 2, -1);
        super.draw(m, mouseX, mouseY);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isHovered(mouseX, mouseY)) {
            if (mouseButton == 0) {
                modeSetting.cycle();
            }
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
