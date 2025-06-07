package cn.mcmod.arsenal.item.rapier;

import cn.mcmod.arsenal.api.tier.WeaponToolMaterial;
import cn.mcmod.arsenal.util.EnchantmentUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class SmallswordItem extends RapierItem {
    public SmallswordItem(WeaponToolMaterial tier, ItemStack sheathItem, Properties properties) {
        super(tier, 0, -0.75F, sheathItem, properties);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return (int)((float)this.getWeaponToolMaterial(stack).getDurability() * 0.7F);
    }

    @Override
    public void doStingAttack(ItemStack stack, LivingEntity attacker, LivingEntity target) {
        if (stack.getItem() instanceof SmallswordItem rapier) {
            float baseDamage = rapier.getWeaponToolMaterial(stack).getAttackDamageBonus() * 1.25F;
            float enchantDamageBonus = 0.0f;
            if (attacker.level() instanceof ServerLevel serverLevel) {
                DamageSource damageSource = attacker.damageSources().mobAttack(attacker);
                enchantDamageBonus = EnchantmentUtil.modifyDamage(serverLevel, stack, target, damageSource, 0.0f) * 1.1F;
            }
            doStingAttack(stack, baseDamage, enchantDamageBonus, attacker, target);
        }
    }
}