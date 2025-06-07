package cn.mcmod.arsenal.api;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IDrawable {
    default boolean isDrawable(ItemStack stack) {
        return true;
    }

    ItemStack getSheath(ItemStack var1);

    default boolean drawSwordAttack(Player player, ItemStack sword) {
        return true;
    }
}
