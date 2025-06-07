package cn.mcmod.arsenal.api.tier;

import cn.mcmod.arsenal.api.WeaponFeature;
import net.minecraft.world.item.ItemStack;

public interface IWeaponToolMaterial {
    WeaponToolMaterial getWeaponToolMaterial(ItemStack var1);

    WeaponFeature getFeature(ItemStack var1);
}
