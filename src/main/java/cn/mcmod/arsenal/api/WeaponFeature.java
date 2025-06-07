package cn.mcmod.arsenal.api;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public abstract class WeaponFeature {
    private final String name;

    public WeaponFeature(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract void inventoryTick(ItemStack var1, Level var2, Entity var3, int var4, boolean var5);

    public abstract boolean onLeftClickEntity(ItemStack var1, Player var2, Entity var3);

    public abstract <T extends LivingEntity> int damageItem(ItemStack var1, int var2, T var3, Consumer<Item> var4);
}
