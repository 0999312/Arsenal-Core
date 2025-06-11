package cn.mcmod.arsenal.item.chinese;

import cn.mcmod.arsenal.ArsenalConfig;
import cn.mcmod.arsenal.api.WeaponProgressComponent;
import cn.mcmod.arsenal.item.ItemRegistry;
import cn.mcmod.arsenal.item.feature.XuanyuanFeature;
import cn.mcmod.arsenal.tier.VanillaWeaponTiers;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.world.entity.EquipmentSlot;

public class XuanyuanjianItem extends ChineseSwordItem {
    public XuanyuanjianItem() {
        super(VanillaWeaponTiers.MAXIMUM_POWER.get(), 0, 0.0F, new ItemStack(ItemRegistry.XUANYUANJIAN_SHEATH.get()));
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return ImmutableMultimap.of();
        }
        return super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) {
            WeaponProgressComponent progress = WeaponProgressComponent.fromItemStack(stack);
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

            builder.put(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier",
                            progress.getAttackDamage(), AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED,
                    new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier",
                            progress.getAttackSpeed(), AttributeModifier.Operation.ADDITION));

            return builder.build();
        }
        return super.getAttributeModifiers(slot, stack);
    }

    @Override
    public void appendHoverText(ItemStack stackIn, Level levelIn, List<Component> tooltipIn, TooltipFlag flagIn) {
        super.appendHoverText(stackIn, levelIn, tooltipIn, flagIn);

        WeaponProgressComponent progress = WeaponProgressComponent.fromItemStack(stackIn);

        tooltipIn.add(Component.empty());

        tooltipIn.add(Component.translatable("tooltip.arsenal.xuanyuan.level",
                        progress.getLevel(), Component.translatable("tooltip.arsenal.xuanyuan.level." + progress.getLevel()))
                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));

        tooltipIn.add(Component.translatable("tooltip.arsenal.xuanyuan.kills", progress.getKills())
                .withStyle(ChatFormatting.GREEN));

        int nextLevelKills = getNextLevelKills(progress.getLevel());
        if (nextLevelKills > 0) {
            int remaining = nextLevelKills - progress.getKills();
            tooltipIn.add(Component.translatable("tooltip.arsenal.xuanyuan.next_level", remaining)
                    .withStyle(ChatFormatting.GRAY));
        } else {
            tooltipIn.add(Component.translatable("tooltip.arsenal.xuanyuan.max_level")
                    .withStyle(ChatFormatting.LIGHT_PURPLE));
        }

        if (progress.getLevel() >= 5) {
            tooltipIn.add(Component.empty());
            tooltipIn.add(Component.translatable("tooltip.arsenal.xuanyuan.skill_hint")
                    .withStyle(ChatFormatting.YELLOW));
        }

        if (progress.getLevel() < 5) {
            tooltipIn.add(Component.translatable("tooltip.arsenal.xuanyuan.not_max")
                    .withStyle(ChatFormatting.DARK_RED));
        }
    }

    private int getNextLevelKills(int currentLevel) {
        return switch (currentLevel) {
            case 0 -> 20;
            case 1 -> 60;
            case 2 -> 120;
            case 3 -> 240;
            case 4 -> 480;
            default -> 0;
        };
    }


    @Override
    public boolean isFoil(ItemStack stackIn) {
        WeaponProgressComponent progress = WeaponProgressComponent.fromItemStack(stackIn);
        return ArsenalConfig.normal_sword_foil && ArsenalConfig.xuanyuanjian_foil && progress.getLevel() >= 3;
    }
}