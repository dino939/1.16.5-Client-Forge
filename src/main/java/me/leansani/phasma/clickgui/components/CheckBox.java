package me.leansani.phasma.clickgui.components;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.leansani.phasma.clickgui.Button;
import me.leansani.phasma.clickgui.settings.BooleanSetting;
import me.leansani.phasma.clickgui.settings.Setting;
import me.leansani.phasma.utils.RenderUtil;
import net.minecraft.client.gui.IngameGui;

import java.awt.*;

import static me.leansani.phasma.Brain.mc;

public class CheckBox extends Component {
    String symb;
    private final BooleanSetting booleanSetting;

    public CheckBox(Setting setting, Button parent, int shifting) {
        super(setting, parent, shifting);
        this.booleanSetting = (BooleanSetting) setting;
    }

    @Override
    public void draw(MatrixStack m, int mouseX, int mouseY) {
        int textShifting = parent.parent.height / 2 - mc.font.lineHeight;

        if (booleanSetting.isEnabled()) {
            symb = "+";
        } else {
            symb = "-";
        }
        IngameGui.fill(m, parent.parent.x, parent.parent.y + parent.shifting + shifting, parent.parent.x + parent.parent.width, parent.parent.y + parent.shifting + shifting + parent.parent.height, new Color(0x33000000, true).hashCode());
        mc.font.draw(m, booleanSetting.getName() + ":" + symb, parent.parent.x + textShifting + 4, parent.parent.y + parent.shifting + shifting + textShifting + 5, -1);
        super.draw(m, mouseX, mouseY);
    }

    @Override
    public void mouseClicked(int mx, int my, int mb) {
        if (isHovered(mx, my)) {
            booleanSetting.toggle();
        }
        super.mouseClicked(mx, my, mb);
    }
}
