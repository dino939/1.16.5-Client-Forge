package me.leansani.phasma.mainmenu;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.leansani.phasma.managers.GLSLSandboxManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

import static me.leansani.phasma.Brain.mc;

public class AccountsScreen extends Screen {
    private GLSLSandboxManager shader;

    public AccountsScreen() {
        super(new StringTextComponent("Accounts"));
    }

    @Override
    public void init(Minecraft p_231158_1_, int p_231158_2_, int p_231158_3_) {
        if (shader == null) {
            shader = new GLSLSandboxManager("glslsandboxshader2.fsh");
        }
        super.init(p_231158_1_, p_231158_2_, p_231158_3_);
    }

    @Override
    public void render(MatrixStack m, int mouseX, int mouseY, float partialTicks) {
        shader.bind(mouseX, mouseY, mc.getWindow().getScreenWidth(), mc.getWindow().getScreenHeight(), 2);
        IngameGui.fill(m, 0, 0, mc.getWindow().getScreenWidth(), mc.getWindow().getScreenHeight(), -1);
        shader.unbind();

        super.render(m, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
