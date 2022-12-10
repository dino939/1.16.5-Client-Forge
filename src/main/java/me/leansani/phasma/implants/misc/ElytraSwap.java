package me.leansani.phasma.implants.misc;

import me.leansani.phasma.Module;
import me.leansani.phasma.utils.FindItemUtil;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.Items;

import static me.leansani.phasma.Brain.mc;

public class ElytraSwap extends Module {
    public boolean state;
    public int save;

    public ElytraSwap() {
        super("ElytraSwap", 0, Category.MISC, "Automatically changes chest slot to elytra");
    }

    @Override
    public void onEnable() {
        int elytra = FindItemUtil.findItem36(Items.ELYTRA);
        state = !state;

        assert mc.player != null;
        assert mc.gameMode != null;
        if (state) {
            if (elytra <= 8) {
                elytra += 36;
            }
            if (elytra >= 0) {
                mc.gameMode.handleInventoryMouseClick(0, elytra, 0, ClickType.PICKUP, mc.player);
                mc.gameMode.handleInventoryMouseClick(0, 6, 0, ClickType.PICKUP, mc.player);
                mc.gameMode.handleInventoryMouseClick(0, elytra, 0, ClickType.PICKUP, mc.player);
                //mc.gameMode.(findItem(Items.FIREWORK_ROCKET));
            }
            save = elytra;
        } else {
            if (save >= 0) {
                mc.gameMode.handleInventoryMouseClick(0, save, 0, ClickType.PICKUP, mc.player);
                mc.gameMode.handleInventoryMouseClick(0, 6, 0, ClickType.PICKUP, mc.player);
                mc.gameMode.handleInventoryMouseClick(0, save, 0, ClickType.PICKUP, mc.player);
            }
            save = -1;
        }
        toggle();
    }
}
