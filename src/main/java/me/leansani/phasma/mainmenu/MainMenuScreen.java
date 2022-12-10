package me.leansani.phasma.mainmenu;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.leansani.phasma.managers.GLSLSandboxManager;
import me.leansani.phasma.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.*;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.screen.ModListScreen;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static me.leansani.phasma.Brain.mc;

public class MainMenuScreen extends Screen {
    private GLSLSandboxManager shader;

    public MainMenuScreen() {
        super(new StringTextComponent("MainMenu"));
    }

    @Override
    public void init(Minecraft p_231158_1_, int p_231158_2_, int p_231158_3_) {
        if (shader == null) {
            shader = new GLSLSandboxManager("glslsandboxshader1.fsh");
        }
        super.init(p_231158_1_, p_231158_2_, p_231158_3_);
    }

    @Override
    public void render(MatrixStack m, int mouseX, int mouseY, float partialTicks) {
        shader.bind(mouseX, mouseY, mc.getWindow().getScreenWidth(), mc.getWindow().getScreenHeight(), 2);
        IngameGui.fill(m, 0, 0, mc.getWindow().getScreenWidth(), mc.getWindow().getScreenHeight(), -1);
        shader.unbind();
        RenderUtil.drawSmoothRect(m, mc.getWindow().getGuiScaledWidth() / 2 - 120, mc.getWindow().getGuiScaledHeight() / 2 + 110, mc.getWindow().getGuiScaledWidth() / 2 + 120, mc.getWindow().getGuiScaledHeight() / 2 - 110, new Color(0, 0, 0, 165).hashCode());
        RenderUtil.drawSmoothRect(m, mc.getWindow().getGuiScaledWidth() / 2 - 65, mc.getWindow().getGuiScaledHeight() / 2 - 40, mc.getWindow().getGuiScaledWidth() / 2 - 2, mc.getWindow().getGuiScaledHeight() / 2 - 20, new Color(0, 0, 0, 165).hashCode());
        mc.font.drawShadow(m, "Multiplayer", mc.getWindow().getGuiScaledWidth() / 2 - mc.font.width("MultiPl") / 2 - 45, mc.getWindow().getGuiScaledHeight() / 2 - 35, -1);
        RenderUtil.drawSmoothRect(m, mc.getWindow().getGuiScaledWidth() / 2 + 2, mc.getWindow().getGuiScaledHeight() / 2 - 40, mc.getWindow().getGuiScaledWidth() / 2 + 65, mc.getWindow().getGuiScaledHeight() / 2 - 20, new Color(0, 0, 0, 165).hashCode());
        mc.font.drawShadow(m, "Singleplayer", mc.getWindow().getGuiScaledWidth() / 2 - mc.font.width("SinglePl") / 2 + 22, mc.getWindow().getGuiScaledHeight() / 2 - 35, -1);
        RenderUtil.drawSmoothRect(m, mc.getWindow().getGuiScaledWidth() / 2 - 65, mc.getWindow().getGuiScaledHeight() / 2 - 15, mc.getWindow().getGuiScaledWidth() / 2 + 65, mc.getWindow().getGuiScaledHeight() / 2 + 5, new Color(0, 0, 0, 165).hashCode());
        mc.font.drawShadow(m, "Accounts", mc.getWindow().getGuiScaledWidth() / 2 - mc.font.width("Accounts") / 2, mc.getWindow().getGuiScaledHeight() / 2 - 10, -1);
        RenderUtil.drawSmoothRect(m, mc.getWindow().getGuiScaledWidth() / 2 - 65, mc.getWindow().getGuiScaledHeight() / 2 + 10, mc.getWindow().getGuiScaledWidth() / 2 + 65, mc.getWindow().getGuiScaledHeight() / 2 + 30, new Color(0, 0, 0, 165).hashCode());
        mc.font.drawShadow(m, "GameFolder", mc.getWindow().getGuiScaledWidth() / 2 - mc.font.width("GameFolder") / 2, mc.getWindow().getGuiScaledHeight() / 2 + 15, -1);
        RenderUtil.drawSmoothRect(m, mc.getWindow().getGuiScaledWidth() / 2 - 65, mc.getWindow().getGuiScaledHeight() / 2 + 35, mc.getWindow().getGuiScaledWidth() / 2 - 2, mc.getWindow().getGuiScaledHeight() / 2 + 55, new Color(0, 0, 0, 165).hashCode());
        mc.font.drawShadow(m, "Settings", mc.getWindow().getGuiScaledWidth() / 2 - mc.font.width("Settings") / 2 - 33, mc.getWindow().getGuiScaledHeight() / 2 + 40, -1);
        RenderUtil.drawSmoothRect(m, mc.getWindow().getGuiScaledWidth() / 2 + 2, mc.getWindow().getGuiScaledHeight() / 2 + 35, mc.getWindow().getGuiScaledWidth() / 2 + 65, mc.getWindow().getGuiScaledHeight() / 2 + 55, new Color(0, 0, 0, 165).hashCode());
        mc.font.drawShadow(m, "Mods", mc.getWindow().getGuiScaledWidth() / 2 - mc.font.width("Mods") / 2 + 34, mc.getWindow().getGuiScaledHeight() / 2 + 40, -1);
        RenderUtil.drawSmoothRect(m, mc.getWindow().getGuiScaledWidth() / 2 - 65, mc.getWindow().getGuiScaledHeight() / 2 + 60, mc.getWindow().getGuiScaledWidth() / 2 + 65, mc.getWindow().getGuiScaledHeight() / 2 + 80, new Color(0, 0, 0, 165).hashCode());
        mc.font.drawShadow(m, "Exit", mc.getWindow().getGuiScaledWidth() / 2 - mc.font.width("Exit") / 2, mc.getWindow().getGuiScaledHeight() / 2 + 65, -1);
        GL11.glScaled(5, 5, 5);
        mc.font.drawShadow(m, "PHASMA", mc.getWindow().getGuiScaledWidth() / 10 - mc.font.width("PHASMA") * 5 / 10, mc.getWindow().getGuiScaledHeight() / 10 - 19, -1);
        super.render(m, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            if (mouseX > mc.getWindow().getGuiScaledWidth() / 2 - 65 && mouseY > mc.getWindow().getGuiScaledHeight() / 2 - 40 && mouseX < mc.getWindow().getGuiScaledWidth() / 2 - 2 && mouseY < mc.getWindow().getGuiScaledHeight() / 2 - 20) {mc.setScreen(new MultiplayerScreen(this));}
            if (mouseX > mc.getWindow().getGuiScaledWidth() / 2 + 2 && mouseY > mc.getWindow().getGuiScaledHeight() / 2 - 40 && mouseX < mc.getWindow().getGuiScaledWidth() / 2 + 65 && mouseY < mc.getWindow().getGuiScaledHeight() / 2 - 20) {mc.setScreen(new WorldSelectionScreen(this));}
            if (mouseX > mc.getWindow().getGuiScaledWidth() / 2 - 65 && mouseY > mc.getWindow().getGuiScaledHeight() / 2 - 15 && mouseX < mc.getWindow().getGuiScaledWidth() / 2 + 65 && mouseY < mc.getWindow().getGuiScaledHeight() / 2 + 5) {mc.setScreen(new AccountsScreen());}
            if (mouseX > mc.getWindow().getGuiScaledWidth() / 2 - 65 && mouseY > mc.getWindow().getGuiScaledHeight() / 2 + 10 && mouseX < mc.getWindow().getGuiScaledWidth() / 2 + 65 && mouseY < mc.getWindow().getGuiScaledHeight() / 2 + 30) {

            }
            if (mouseX > mc.getWindow().getGuiScaledWidth() / 2 - 65 && mouseY > mc.getWindow().getGuiScaledHeight() / 2 + 35 && mouseX < mc.getWindow().getGuiScaledWidth() / 2 - 2 && mouseY < mc.getWindow().getGuiScaledHeight() / 2 + 55) {mc.setScreen(new OptionsScreen(this, mc.options));}
            if (mouseX > mc.getWindow().getGuiScaledWidth() / 2 + 2 && mouseY > mc.getWindow().getGuiScaledHeight() / 2 + 35 && mouseX < mc.getWindow().getGuiScaledWidth() / 2 + 65 && mouseY < mc.getWindow().getGuiScaledHeight() / 2 + 55) {mc.setScreen(new ModListScreen(this));}
            if (mouseX > mc.getWindow().getGuiScaledWidth() / 2 - 65 && mouseY > mc.getWindow().getGuiScaledHeight() / 2 + 60 && mouseX < mc.getWindow().getGuiScaledWidth() / 2 + 65 && mouseY < mc.getWindow().getGuiScaledHeight() / 2 + 80) {mc.stop();}
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
