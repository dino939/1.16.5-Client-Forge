package me.leansani.phasma.utils;

public class HoverUtil {

    public static boolean hovered(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX > x && mouseY > y && mouseX < width && mouseY < height;
    }
}

