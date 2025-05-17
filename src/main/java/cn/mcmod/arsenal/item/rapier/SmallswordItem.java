//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.mcmod.arsenal.item.rapier;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.api.tier.WeaponTier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class SmallswordItem extends RapierItem {
    public SmallswordItem(WeaponTier tier, ItemStack sheathItem, Properties prop) {
        super(tier, 0, -0.75F, sheathItem, prop);
    }

    public SmallswordItem(WeaponTier tier, ItemStack sheathItem) {
        super(tier, 0, -0.75F, sheathItem, (new Properties()).stacksTo(1));
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return (int)((float)this.getWeaponTier(stack).getUses() * 0.7F);
    }

    @Override
    public void DoStingAttack(ItemStack stack, LivingEntity attacker, LivingEntity target) {
        if (stack.getItem() instanceof TieredItem rapier) {
            DoStingAttack(stack, rapier.getTier().getAttackDamageBonus() * 1.25F, EnchantmentHelper.getDamageBonus(stack, target.getMobType()) * 1.1F, attacker, target);
        }
    }
}