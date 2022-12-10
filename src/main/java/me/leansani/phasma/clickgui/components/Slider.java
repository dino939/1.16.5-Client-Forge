package me.leansani.phasma.clickgui.components;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.leansani.phasma.clickgui.Button;
import me.leansani.phasma.clickgui.settings.NumberSetting;
import me.leansani.phasma.clickgui.settings.Setting;
import me.leansani.phasma.utils.RenderUtil;
import net.minecraft.client.gui.IngameGui;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static me.leansani.phasma.Brain.mc;

public class Slider extends Component {
    public boolean sliding;
    public NumberSetting numberSetting = (NumberSetting) setting;


    public Slider(Setting setting, Button parent, int shifting, double defaultvalue) {
        super(setting, parent, shifting);
        this.numberSetting = (NumberSetting) setting;
        numberSetting.setValDouble(defaultvalue);
    }

    @Override
    public void draw(MatrixStack m, int mouseX, int mouseY) {
        double min = numberSetting.getMin();
        double max = numberSetting.getMax();
        int renderWidth = (int) ((parent.parent.width) * (numberSetting.getValue() - min) / (max - min));
        double diff = Math.min(parent.parent.width, Math.max(0, mouseX - parent.parent.x));
        int textShifting = ((parent.parent.height / 2) - mc.font.lineHeight / 2);
        //numberSetting.setValue(numberSetting.getDefaultValue());
        int roundPlaces = 2;

        IngameGui.fill(m, parent.parent.x, parent.parent.y + parent.shifting + shifting, parent.parent.x + parent.parent.width, parent.parent.y + parent.shifting + shifting + parent.parent.height, new Color(0x33000000, true).hashCode());
        IngameGui.fill(m, parent.parent.x, parent.parent.y + parent.shifting + shifting, parent.parent.x + renderWidth, parent.parent.y + parent.shifting + shifting + parent.parent.height, new Color(0xFF00A500).hashCode());
        if (sliding) {
            if (diff == 0) {
                numberSetting.setValDouble(numberSetting.getMin());
            } else {
                double newValue = roundToPlace(((diff / parent.parent.width) * (max - min) + min), 2);
                numberSetting.setValDouble(newValue);
            }
        }


        mc.font.draw(m, numberSetting.getName() + ":" + roundToPlace(numberSetting.getValue(), roundPlaces), parent.parent.x + textShifting, parent.parent.y + parent.shifting + shifting + textShifting, -1);
        super.draw(m, mouseX, mouseY);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            sliding = true;
        }
        if (isHovered(mouseX, mouseY) && button == 0) {
            sliding = true;
        }
        super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > parent.parent.x && mouseX < parent.parent.x + parent.parent.width && mouseY > parent.parent.y + parent.shifting + shifting && mouseY < parent.parent.y + parent.shifting + shifting + parent.parent.height;
    }

    @Override
    public void mouseReleased(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
        sliding = false;
        super.mouseReleased(p_231044_1_, p_231044_3_, p_231044_5_);
    }

    private static double roundToPlace(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
