package cn.mcmod.chinese_sword.item.feature;

import java.util.function.Consumer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class WeaponFeature {
    private final String name;

    public WeaponFeature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot,
            boolean isSelected);

    public abstract boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity);

    public abstract <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity,
            Consumer<T> onBroken);
}
