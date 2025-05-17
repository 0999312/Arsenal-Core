//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.mcmod.arsenal.item.feature;

import cn.mcmod.arsenal.api.WeaponFeature;
import java.util.function.Consumer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
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
        if (entity instanceof LivingEntity) {
            int fire_level = 1 + EnchantmentHelper.getFireAspect(player);
            LivingEntity living = (LivingEntity)entity;
            living.setSecondsOnFire(4 * fire_level);
            if (living.getMobType() == MobType.UNDEAD) {
                float smiteLevel = Math.max(5.0F, 7.5F * (float)EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SMITE, stack));
                DamageSources sources = living.level().damageSources();
                DamageSource magicDs = sources.magic();
                living.hurt(magicDs, smiteLevel);
            }
        }

        return false;
    }

    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return -1;
    }
}
