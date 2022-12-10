package me.leansani.phasma.utils;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.IngameGui;
import org.lwjgl.opengl.GL11;

public class RenderUtil {

    public static void drawSmoothRect(MatrixStack m, float left, float top, float right, float bottom, int color) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        IngameGui.fill(m, (int) left, (int) top, (int) right, (int) bottom, color);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        IngameGui.fill(m, (int) (left * 2 - 1), (int) (top * 2), (int) (left * 2), (int) (bottom * 2 - 1), color);
        IngameGui.fill(m, (int) (left * 2), (int) (top * 2 - 1), (int) (right * 2), (int) (top * 2), color);
        IngameGui.fill(m, (int) (right * 2), (int) (top * 2), (int) (right * 2 + 1), (int) (bottom * 2 - 1), color);
        IngameGui.fill(m, (int) (left * 2), (int) (bottom * 2 - 1), (int) (right * 2), (int) (bottom * 2), color);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glScalef(2F, 2F, 2F);
    }

    public static void drawSmoothGradientRect(float left, float top, float right, float bottom, int color1, int color2) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        drawGradientRect(left, top, right, bottom, color1, color2);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawGradientRect(left * 2 - 1, top * 2, left * 2, bottom * 2 - 1, color1, color1);
        drawGradientRect(left * 2, top * 2 - 1, right * 2, top * 2, color1, color2);
        drawGradientRect(right * 2, top * 2, right * 2 + 1, bottom * 2 - 1, color1, color2);
        drawGradientRect(left * 2, bottom * 2 - 1, right * 2, bottom * 2, color1, color2);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glScalef(2F, 2F, 2F);
    }

    public static void drawGradientRect(float left, float top, float right, float bottom, int color1, int color2) {
        float f1 = (float) (color1 >> 24 & 0xFF) / 255.0f;
        float f2 = (float) (color1 >> 16 & 0xFF) / 255.0f;
        float f3 = (float) (color1 >> 8 & 0xFF) / 255.0f;
        float f4 = (float) (color1 & 0xFF) / 255.0f;
        float f5 = (float) (color2 >> 24 & 0xFF) / 255.0f;
        float f6 = (float) (color2 >> 16 & 0xFF) / 255.0f;
        float f7 = (float) (color2 >> 8 & 0xFF) / 255.0f;
        float f8 = (float) (color2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f1);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
}
