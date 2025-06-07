package cn.mcmod.arsenal.item.feature;

import cn.mcmod.arsenal.api.WeaponFeature;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class XuanyuanFeature extends WeaponFeature {
    public XuanyuanFeature() {
        super("maximum_power");
    }

    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
    }

    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity instanceof LivingEntity living) {
            int fireLevel = 1 + EnchantmentHelper.getFireAspect(player);
            living.igniteForSeconds(4 * fireLevel);

            if (living.getType().is(EntityTypeTags.UNDEAD)) {
                int smiteLevel = stack.getEnchantmentLevel(Enchantments.SMITE);
                float smiteDamage = Math.max(5.0F, 7.5F * (float) smiteLevel);

                DamageSource magicDs = living.level().damageSources().magic();
                living.hurt(magicDs, smiteDamage);
            }
        }
        return false;
    }

@Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Runnable onBroken) {
        return -1;
    }
}
