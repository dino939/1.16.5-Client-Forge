package me.leansani.phasma.clickgui;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.leansani.phasma.Brain;
import me.leansani.phasma.Module;
import me.leansani.phasma.clickgui.components.Component;
import me.leansani.phasma.utils.HoverUtil;
import me.leansani.phasma.utils.RenderUtil;
import me.leansani.phasma.utils.TimerUtil;
import net.minecraft.client.gui.IngameGui;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static me.leansani.phasma.Brain.mc;

public class Frame {
    public List<Button> buttonList = new ArrayList<>();
    public int x, y, width, height;
    public boolean extended, dragging;
    public Module.Category category;
    static int scrollHeight = 160;
    int scrollShifting;
    TimerUtil scrollDelay = new TimerUtil();

    public Frame(int x, int y, int width, int height, Module.Category category) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.category = category;
        int y1 = y + height;
        int shifting = height;

        for (Module m : Brain.modules) {
            if (m.getCategory() == category) {
                buttonList.add(new Button(m, x, y1, width + 2, height, this, shifting));
                y1 += height;
                shifting += height;
            }
        }
    }

    public void render(MatrixStack m, int mouseX, int mouseY, float partialTicks) {
        ClickGUIScreen.shader.bind(mouseX, mouseY, mc.getWindow().getScreenWidth(), mc.getWindow().getScreenHeight(), 1);
        IngameGui.fill(m, x, y, x + width, extended ? y + height + scrollHeight : y + height, -1);
        ClickGUIScreen.shader.unbind();
        IngameGui.fill(m, x - 1, y, x, extended ? y + height + scrollHeight : y + height, new Color(0x00A500).hashCode());
        IngameGui.fill(m, x + width, y, x + width + 1, extended ? y + height + scrollHeight : y + height, new Color(0x00A500).hashCode());
        IngameGui.fill(m, x - 1, y - 1, x + width + 1, y, new Color(0x00A500).hashCode());
        IngameGui.fill(m, x - 1, extended ? y + height + scrollHeight : y + height, x + width + 1, extended ? y + height + scrollHeight + 1 : y + height + 1, new Color(0x00A500).hashCode());
        RenderUtil.drawSmoothRect(m, x + width / 2 - mc.font.width(category.name()) / 2, y + 13, x + width / 2 + mc.font.width(category.name()) / 2, y + 14, -1);
        mc.font.drawShadow(m, category.name(), x + width / 2 - mc.font.width(category.name()) / 2, y + 3, -1);
        if (extended) {
            int y1 = y + height;

            for (Button b : buttonList) {
                b.x = x;
                b.y = y1;
                y1 += height;
                if (b.shifting > 0 && b.shifting < scrollHeight + 16) {
                    b.render(m, mouseX, mouseY, partialTicks);
                }
            }
        }
        if (dragging) {
            x = mouseX - width / 2;
            y = mouseY - height / 2;
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseX > x - 1 && mouseY > y + height + scrollHeight && mouseX < x + width + 1 && mouseY < y + height + scrollHeight + 5) {
            if (mouseButton == 0 && scrollHeight < 240) {
                scrollHeight += 16;
            } else if (mouseButton == 1 && scrollHeight > 96) {
                scrollHeight -= 16;
            }
        }
        if (mouseX > x && mouseY > y && mouseX < x + width && mouseY < y + height && mouseButton == 0) {
            dragging = true;
        }
        if (HoverUtil.hovered((int) mouseX, (int) mouseY, x, y, x + width, y + height)) {
            if (mouseButton == 1) {
                extended = !extended;
            }
        }
        if (extended) {
            for (Button b : buttonList) {
                if (b.shifting > 0 && b.shifting < scrollHeight + 16) {
                    b.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
    }

    public void keyPressed(int keyCode) {
        if (keyCode == GLFW.GLFW_KEY_RIGHT) {
            x += 10;
        }
        if (keyCode == GLFW.GLFW_KEY_LEFT) {
            x -= 10;
        }
        if (keyCode == GLFW.GLFW_KEY_DOWN) {
            y += 10;
        }
        if (keyCode == GLFW.GLFW_KEY_UP) {
            y -= 10;
        }
        for (Button b : buttonList) {
            b.keyPressed(keyCode);
        }
    }

    public void mouseReleased(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
        dragging = false;
        for (Button b : buttonList) {
            b.mouseReleased(p_231044_1_, p_231044_3_, p_231044_5_);
        }
    }

    public void mouseScrolled(double p_231043_1_, double p_231043_3_, double p_231043_5_) {
        for (Button b : buttonList) {
            if (scrollDelay.hasReached(1) && HoverUtil.hovered((int) p_231043_1_, (int) p_231043_3_, x, y, x + width, y + height + scrollHeight)) {
                if (p_231043_5_ == 1) {
                    if (b.shifting > scrollHeight - 16) {
                        scrollShifting -= 16;
                        updateButtons();
                        scrollDelay.reset();
                    }
                } else if (b.shifting < 32) {
                    scrollShifting += 16;
                    updateButtons();
                    scrollDelay.reset();
                }
            }
        }
    }

    public void updateButtons() {
        int shifting = height;

        for (Button b : buttonList) {
            b.shifting = shifting + scrollShifting;
            shifting += height;
            if (b.extended) {
                for (Component c : b.components) {
                    if (c.setting.isVisible()) {
                        shifting += height;
                    }
                }
            }
        }
    }
}
