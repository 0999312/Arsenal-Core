package cn.mcmod.arsenal.event;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.api.WeaponProgressComponent;
import cn.mcmod.arsenal.item.chinese.XuanyuanjianItem;
import cn.mcmod.arsenal.item.feature.XuanyuanFeature;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArsenalCore.MODID)
public class CommonEventHandler {
    
    @SubscribeEvent
    public static void onEntityKilled(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            ItemStack mainHandItem = player.getMainHandItem();

            if (mainHandItem.getItem() instanceof XuanyuanjianItem) {
                handleXuanyuanKill(mainHandItem, player, event.getEntity());
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(event.getSource().getEntity() instanceof LivingEntity)) return;
        ItemStack main = player.getInventory().getSelected();
        XuanyuanFeature.onPlayerHurt(player, main);
    }


    //TODO：替换为翻译键并修改内容
    private static void handleXuanyuanKill(ItemStack stack, Player player, LivingEntity killedEntity) {
        WeaponProgressComponent progress = WeaponProgressComponent.fromItemStack(stack);

        if (!progress.isFirstObtained()) {
            progress.setFirstObtained(true);
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.sendSystemMessage(
                    Component.literal("[轩辕剑] 古老的力量开始觉醒...")
                        .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD)
                );
            }
        }

        int oldLevel = progress.getLevel();
        progress.addKill();

        if (progress.checkLevelUp()) {
            int newLevel = progress.getLevel();
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.sendSystemMessage(
                    Component.literal("[轩辕剑] 等级提升! Lv." + oldLevel + " → Lv." + newLevel + " - " + progress.getLevelDescription())
                        .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD)
                );

                serverPlayer.level().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), 
                    SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0F, 1.0F);
            }

            refreshItemAttributes(player, stack, progress);

        } else {
            progress.saveToItemStack(stack);
        }
    }

    private static void refreshItemAttributes(Player player, ItemStack stack, WeaponProgressComponent progress) {
        progress.saveToItemStack(stack);

        if (player instanceof ServerPlayer serverPlayer) {
            int slot = player.getInventory().selected;
            ItemStack newStack = stack.copy();

            player.getInventory().setItem(slot, newStack);

            serverPlayer.inventoryMenu.broadcastChanges();
        }
    }
}