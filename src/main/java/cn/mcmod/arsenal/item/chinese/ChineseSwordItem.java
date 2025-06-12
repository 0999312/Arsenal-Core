package cn.mcmod.arsenal.item.chinese;

import cn.mcmod.arsenal.ArsenalConfig;
import cn.mcmod.arsenal.api.IDrawable;
import cn.mcmod.arsenal.api.WeaponFeature;
import cn.mcmod.arsenal.api.toolmaterial.IWeaponToolMaterial;
import cn.mcmod.arsenal.api.toolmaterial.WeaponToolMaterial;
import java.util.List;
import java.util.function.Consumer;
import java.util.Optional;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import org.jetbrains.annotations.Nullable;

public class ChineseSwordItem extends Item implements IDrawable, IWeaponToolMaterial {
    private final WeaponToolMaterial toolMaterial;
    private final ItemStack sheath;
    private final int attackDamage;
    private final float attackSpeed;

    public ChineseSwordItem(WeaponToolMaterial toolMaterial, int attackDamageIn, float attackSpeedIn, ItemStack sheathItem, Item.Properties props) {
        super(createProperties(toolMaterial, attackDamageIn, attackSpeedIn, props));
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

        props.component(DataComponents.BLOCKS_ATTACKS, new BlocksAttacks(
                0.5f,
                1.5f,
                List.of(new BlocksAttacks.DamageReduction(
                        175f,
                        Optional.empty(),
                        2f,
                        2f
                )),
                new BlocksAttacks.ItemDamageFunction(
                        1.2f, 0.4f, 0.43f
                ),
                Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                Optional.of(SoundEvents.SHIELD_BLOCK),
                Optional.of(SoundEvents.SHIELD_BREAK)
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

        props.durability(toolMaterial.getDurability());

        return props;
    }

    public ChineseSwordItem(WeaponToolMaterial toolMaterial, int attackDamageIn, float attackSpeedIn, ItemStack sheathItem) {
        this(toolMaterial, attackDamageIn, attackSpeedIn, sheathItem, new Item.Properties().stacksTo(1));
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        WeaponToolMaterial tier = getWeaponToolMaterial(stack);
        tooltip.accept(Component.translatable("tooltip.arsenal.tiers")
                .append(Component.translatable("tier.arsenal." + tier.getUnlocalizedName())));

        WeaponFeature feature = getFeature(stack);
        if (feature != null) {
            tooltip.accept(Component.translatable("tooltip.arsenal.feature." + feature.getName()).withStyle(ChatFormatting.GOLD));
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
    public WeaponToolMaterial getWeaponToolMaterial(ItemStack var1) {
        return this.toolMaterial;
    }

    @Override
    public WeaponFeature getFeature(ItemStack stack) {
        return this.getWeaponToolMaterial(stack).getFeature();
    }

    @Override
    public void inventoryTick (ItemStack stack, ServerLevel worldIn, Entity entityIn, @Nullable EquipmentSlot itemSlot) {
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
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResult.SUCCESS;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack p_43105_) {
        return ItemUseAnimation.BLOCK;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }
}