package me.leansani.phasma.clickgui;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.leansani.phasma.Brain;
import me.leansani.phasma.Module;
import me.leansani.phasma.managers.GLSLSandboxManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

import static me.leansani.phasma.Brain.mc;

public class ClickGUIScreen extends Screen {
    public static List<Frame> categoryFrames = new ArrayList<>();
    static GLSLSandboxManager shader;

    public ClickGUIScreen() {
        super(new StringTextComponent("ClickGUI"));

        categoryFrames.add(new Frame(20, 15, 100, 16, Module.Category.CLIENT));
        categoryFrames.add(new Frame(130, 15, 100, 16, Module.Category.COMBAT));
        categoryFrames.add(new Frame(240, 15, 100, 16, Module.Category.EXPLOIT));
        categoryFrames.add(new Frame(350, 15, 100, 16, Module.Category.MISC));
        categoryFrames.add(new Frame(460, 15, 100, 16, Module.Category.MOVE));
        categoryFrames.add(new Frame(570, 15, 100, 16, Module.Category.PLAYER));
        categoryFrames.add(new Frame(680, 15, 100, 16, Module.Category.RENDER));
    }

    @Override
    public void init(Minecraft p_231158_1_, int p_231158_2_, int p_231158_3_) {
        if (shader == null) {
            shader = new GLSLSandboxManager("glslsandboxshader3.fsh");
        }
        super.init(p_231158_1_, p_231158_2_, p_231158_3_);
    }

    @Override
    public void render(MatrixStack m, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(m);
        if (mc.gameRenderer.currentEffect() == null) {
            //mc.gameRenderer.loadEffect(new ResourceLocation("shaders/blur.json"));
        }
        for (Frame f : categoryFrames) {
            f.render(m, mouseX, mouseY, partialTicks);
        }
        super.render(m, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseScrolled(double p_231043_1_, double p_231043_3_, double p_231043_5_) {
        for (Frame f : categoryFrames) {
            f.mouseScrolled(p_231043_1_, p_231043_3_, p_231043_5_);
        }

        return super.mouseScrolled(p_231043_1_, p_231043_3_, p_231043_5_);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        for (Frame f : categoryFrames) {
            f.mouseClicked(mouseX, mouseY, mouseButton);
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean mouseReleased(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
        for (Frame f : categoryFrames) {
            f.mouseReleased(p_231044_1_, p_231044_3_, p_231044_5_);
        }
        return super.mouseReleased(p_231044_1_, p_231044_3_, p_231044_5_);
    }

    @Override
    public boolean keyPressed(int p_231046_1_, int p_231046_2_, int p_231046_3_) {
        for (Frame f : categoryFrames) {
            f.keyPressed(p_231046_1_);
        }
        return super.keyPressed(p_231046_1_, p_231046_2_, p_231046_3_);
    }
}
