package cn.mcmod.arsenal.item.feature;

import cn.mcmod.arsenal.api.WeaponFeature;
import cn.mcmod.arsenal.util.EnchantmentUtil;
import net.minecraft.core.Holder;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class XuanyuanFeature extends WeaponFeature {
    public XuanyuanFeature() {
        super("maximum_power");
    }

    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
    }

    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (!(entity instanceof LivingEntity living)) return false;

        Holder<Enchantment> fireAspect = EnchantmentUtil.getHolder(Enchantments.FIRE_ASPECT);
        int fireLevel = 1 + player.getMainHandItem().getEnchantmentLevel(fireAspect);
        living.igniteForSeconds(4 * fireLevel);

        if (living.getType().is(EntityTypeTags.UNDEAD)) {
            Holder<Enchantment> smite = EnchantmentUtil.getHolder(Enchantments.SMITE);
            int smiteLevel = stack.getEnchantmentLevel(smite);
            float smiteDamage = Math.max(5.0F, 7.5F * smiteLevel);

            DamageSource magic = living.level().damageSources().magic();
            living.hurt(magic, smiteDamage);
        }

        return false;
    }



@Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<Item> onBroken) {
        return -1;
    }
}
