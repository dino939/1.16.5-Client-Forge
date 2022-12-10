package me.leansani.phasma.implants.combat;

import me.leansani.phasma.Module;
import me.leansani.phasma.utils.FindItemUtil;
import me.leansani.phasma.utils.TimerUtil;
import net.minecraft.block.Blocks;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static me.leansani.phasma.Brain.mc;

public class AutoCrystal extends Module {
    TimerUtil penis = new TimerUtil();

    public AutoCrystal() {
        super("AutoCrystal", 0, Category.COMBAT, "idi nahuy");
    }

    @SubscribeEvent
    public void onEntityPlace(BlockEvent.EntityPlaceEvent e) {
        if (isToggled()) {
            if (e.getPlacedBlock() == Blocks.OBSIDIAN.defaultBlockState()) {
                penis.reset();
            }
        }
    }

    @SubscribeEvent
    public void onUpdate(GuiScreenEvent.MouseClickedEvent e) {
        if (isToggled()) {
            if (!penis.hasReached(5000)) {
                int crystal = FindItemUtil.findItem36(Items.END_CRYSTAL);
                int obsidian = FindItemUtil.findItem36(Items.OBSIDIAN);

                if (crystal <= 8) {
                    crystal += 36;
                }
                if (obsidian <= 8) {
                    obsidian += 36;
                }
                assert mc.gameMode != null;
                assert mc.player != null;
                mc.gameMode.handleInventoryMouseClick(0, crystal, 0, ClickType.PICKUP, mc.player);
                mc.gameMode.handleInventoryMouseClick(0, obsidian, 0, ClickType.PICKUP, mc.player);
                mc.gameMode.handleInventoryMouseClick(0, crystal, 0, ClickType.PICKUP, mc.player);
                mc.options.keyUse.setDown(true);
                if (mc.hitResult != null) {
                    try {
                        Entity target = ((EntityRayTraceResult) mc.hitResult).getEntity();

                        assert mc.player != null;
                        if (mc.player.getAttackStrengthScale(0) == 1) {
                            new PlayerController(mc, mc.player.connection).attack(mc.player, target);
                            mc.player.swing(Hand.MAIN_HAND);
                            mc.player.resetAttackStrengthTicker();
                        }
                    } catch (Exception ignored) {
                    }
                }
            } else {
                mc.options.keyUse.setDown(e.getButton() == 1);
            }
        }
    }
}