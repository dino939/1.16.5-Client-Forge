package me.leansani.phasma.utils;

import net.minecraft.item.Item;

import static me.leansani.phasma.Brain.mc;

public class FindItemUtil {

    public static int findItem36(Item item) {
        for (int i = 0; i < 36; ++i) {
            assert mc.player != null;
            if (mc.player.inventory.getItem(i).getItem() == item) {
                return i;
            }
        }
        return -1;
    }

    public static int findItem9(Item item) {
        for (int i = 0; i < 9; ++i) {
            assert mc.player != null;
            if (mc.player.inventory.getItem(i).getItem() == item) {
                return i;
            }
        }
        return -1;
    }
}
