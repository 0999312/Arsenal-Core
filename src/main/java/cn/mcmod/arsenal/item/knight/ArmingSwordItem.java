package cn.mcmod.arsenal.item.knight;

import cn.mcmod.arsenal.ArsenalConfig;
import cn.mcmod.arsenal.api.IDrawable;
import cn.mcmod.arsenal.api.WeaponFeature;
import cn.mcmod.arsenal.api.toolmaterial.IWeaponToolMaterial;
import cn.mcmod.arsenal.api.toolmaterial.WeaponToolMaterial;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ArmingSwordItem extends Item implements IDrawable, IWeaponToolMaterial {
    private final WeaponToolMaterial toolMaterial;
    private final ItemStack sheath;
    private final int attackDamage;
    private final float attackSpeed;

    public ArmingSwordItem(WeaponToolMaterial toolMaterial, int attackDamageIn, float attackSpeedIn, ItemStack sheathItem, Properties builderIn) {
        super(createProperties(toolMaterial, attackDamageIn, attackSpeedIn, builderIn));
        this.toolMaterial = toolMaterial;
        this.sheath = sheathItem;
        this.attackDamage = attackDamageIn;
        this.attackSpeed = attackSpeedIn;
    }

    private static Item.Properties createProperties(WeaponToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Properties props) {
        props.component(DataComponents.WEAPON, new Weapon(
                3,
                5f
        ));

        ItemAttributeModifiers.Builder attributeBuilder = ItemAttributeModifiers.builder();
        attributeBuilder.add(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                        BASE_ATTACK_DAMAGE_ID,
                        attackDamage + toolMaterial.getToolMaterial().attackDamageBonus(),
                        AttributeModifier.Operation.ADD_VALUE
                ),
                EquipmentSlotGroup.MAINHAND
        );
        attributeBuilder.add(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(
                        BASE_ATTACK_SPEED_ID,
                        attackSpeed,
                        AttributeModifier.Operation.ADD_VALUE
                ),
                EquipmentSlotGroup.MAINHAND
        );

        props.attributes(attributeBuilder.build());

        props.durability((int)((float)toolMaterial.getDurability() * 1.25F));

        return props;
    }

    public ArmingSwordItem(WeaponToolMaterial toolMaterial, float attackDamage, float attackSpeed, ItemStack sheathItem, Properties properties) {
        this(toolMaterial, (int)attackDamage, attackSpeed, sheathItem, properties);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return (int)((float)this.getWeaponToolMaterial(stack).getDurability() * 1.25F);
    }

    @Override
    public void appendHoverText(ItemStack stackIn, Item.TooltipContext pContext, TooltipDisplay display, Consumer<Component> tooltipIn, TooltipFlag flagIn) {
        super.appendHoverText(stackIn, pContext, display, tooltipIn, flagIn);
        MutableComponent tierText = Component.translatable("tooltip.arsenal.tiers")
                .append(Component.translatable("tier.arsenal." + this.getWeaponToolMaterial(stackIn).getUnlocalizedName()));
        tooltipIn.accept(tierText);

        if (this.getFeature(stackIn) != null) {
            tooltipIn.accept(Component.translatable("tooltip.arsenal.feature." + this.getWeaponToolMaterial(stackIn).getFeature().getName())
                    .withStyle(ChatFormatting.GOLD));
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return ArsenalConfig.normal_sword_foil && super.isFoil(stack);
    }

    @Override
    public ItemStack getSheath(ItemStack stack) {
        return this.sheath;
    }

    @Override
    public WeaponToolMaterial getWeaponToolMaterial(ItemStack stack) {
        return this.toolMaterial;
    }

    @Override
    public WeaponFeature getFeature(ItemStack stack) {
        return this.getWeaponToolMaterial(stack).getFeature();
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        boolean result = super.onLeftClickEntity(stack, player, entity);
        if (this.getFeature(stack) == null) {
            return result;
        } else {
            return result || this.getFeature(stack).onLeftClickEntity(stack, player, entity);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel worldIn, Entity entityIn, EquipmentSlot itemSlot) {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot);
        if (this.getFeature(stack) != null) {
            this.getFeature(stack).inventoryTick(stack, worldIn, entityIn, itemSlot);
        }
    }


    @Override
    public boolean canDestroyBlock(ItemStack stack, BlockState state, Level level, BlockPos pos, LivingEntity entity) {
        return ! (entity instanceof Player player) || ! player.getAbilities().instabuild;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<Item> onBroken) {
        if (this.getFeature(stack) != null) {
            int feature_damage = this.getFeature(stack).damageItem(stack, amount, entity, onBroken);
            return super.damageItem(stack, amount, entity, onBroken) + feature_damage;
        } else {
            return super.damageItem(stack, amount, entity, onBroken);
        }
    }
}