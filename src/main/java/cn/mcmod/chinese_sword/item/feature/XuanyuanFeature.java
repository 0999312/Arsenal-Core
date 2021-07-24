package cn.mcmod.chinese_sword.item.feature;

import java.util.function.Consumer;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class XuanyuanFeature extends WeaponFeature {

    public XuanyuanFeature() {
        super("maximum_power");
    }

    @Override
    public void use(World worldIn, PlayerEntity playerIn, Hand handIn) {
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
    }

    @Override
    public void onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if(entity instanceof LivingEntity) {
            int fire_level = 1 + EnchantmentHelper.getFireAspect(player);
            LivingEntity living = (LivingEntity)entity;
            living.setSecondsOnFire(4 * fire_level);
            if(living.getMobType() == CreatureAttribute.UNDEAD) {
                int smite_level = 1 + EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SMITE,stack);
                living.hurt(DamageSource.MAGIC, 5 * smite_level);
            }
        }
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return -1;
    }

}
