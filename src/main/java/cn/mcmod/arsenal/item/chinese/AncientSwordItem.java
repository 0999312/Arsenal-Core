package cn.mcmod.arsenal.item.chinese;

import cn.mcmod.arsenal.api.toolmaterial.WeaponToolMaterial;
import net.minecraft.world.item.ItemStack;

public class AncientSwordItem extends ChineseSwordItem {
    public AncientSwordItem(WeaponToolMaterial toolMaterial, int attackDamageIn, float attackSpeedIn, ItemStack sheathItem) {
        super(toolMaterial, attackDamageIn, attackSpeedIn, sheathItem);
    }

    public AncientSwordItem(WeaponToolMaterial toolMaterial, ItemStack sheathItem) {
        super(toolMaterial, 5, -2.0F, sheathItem);
    }

    public AncientSwordItem(WeaponToolMaterial toolMaterial, ItemStack sheathItem, Properties properties) {
        super(toolMaterial, 5, -2.0F, sheathItem, properties);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return (int)((float)this.getWeaponToolMaterial(stack).getDurability() * 0.9F);
    }
}