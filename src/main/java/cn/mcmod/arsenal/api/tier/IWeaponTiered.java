package cn.mcmod.arsenal.api.tier;

import cn.mcmod.arsenal.api.WeaponFeature;
import net.minecraft.world.item.ItemStack;

public interface IWeaponTiered {
    WeaponTier getWeaponTier(ItemStack var1);

    WeaponFeature getFeature(ItemStack var1);
}
