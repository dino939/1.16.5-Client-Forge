package me.leansani.phasma.implants.combat;

import me.leansani.phasma.Module;
import me.leansani.phasma.utils.FindItemUtil;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static me.leansani.phasma.Brain.mc;

public class AutoTotem extends Module {
    public AutoTotem() {super("AutoTotem", 0, Category.COMBAT, "Automatically picks up a totem in dangerous situations");}

    /*@SubscribeEvent
    public void onTick(LivingDeathEvent e) {
        if (isToggled()) {
            int totem = FindItemUtil.findItem36(Items.TOTEM_OF_UNDYING);

            if (totem <= 8) {
                totem += 36;
            }
            assert mc.player != null;
            mc.player.connection.send(new CHeldItemChangePacket(totem));

            mc.player.setHealth(1.0F);
            e.setCanceled(true);
        }
    }*/

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent e) {
        Entity entity = e.getEntity();
        World world = entity.getCommandSenderWorld();
        if (world.isClientSide) {
            return;
        }

        if (entity instanceof PlayerEntity == false) {
            return;
        }

        PlayerEntity player = (PlayerEntity) entity;
        if (player.getMainHandItem().getItem().equals(Items.TOTEM_OF_UNDYING) || player.getOffhandItem().getItem().equals(Items.TOTEM_OF_UNDYING)) {
            return;
        }

        if (mc.player.inventory == null) {
            return;
        }

        ItemStack totemstack = null;
        for(int i = 0; i < mc.player.inventory.getContainerSize(); i++) {
            ItemStack stack = mc.player.inventory.getItem(i);
            if (stack.getItem().equals(Items.TOTEM_OF_UNDYING)) {
                totemstack = stack;
                break;
            }
        }

        if (totemstack == null) {
            return;
        }

        e.setCanceled(true);
        if (player instanceof ServerPlayerEntity) {
            ServerPlayerEntity entityplayermp = (ServerPlayerEntity) player;
            entityplayermp.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING));
            CriteriaTriggers.USED_TOTEM.trigger(entityplayermp, totemstack);
        }

        player.setHealth(1.0F);
        player.removeAllEffects();
        player.addEffect(new EffectInstance(Effects.REGENERATION, 900, 1));
        player.addEffect(new EffectInstance(Effects.ABSORPTION, 100, 1));
        world.broadcastEntityEvent(player, (byte)35);
        totemstack.shrink(1);
    }
}