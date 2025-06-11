package cn.mcmod.arsenal.item.chinese;

import cn.mcmod.arsenal.api.tier.WeaponTier;
import net.minecraft.world.item.ItemStack;

public class AncientSwordItem extends ChineseSwordItem {
    public AncientSwordItem(WeaponTier tier, int attackDamageIn, float attackSpeedIn, ItemStack sheathItem) {
        super(tier, attackDamageIn, attackSpeedIn, sheathItem);
    }

    public AncientSwordItem(WeaponTier tier, ItemStack sheathItem) {
        super(tier, 5, -2.0F, sheathItem);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return (int)((float)this.getWeaponTier(stack).getUses() * 0.9F);
    }
}