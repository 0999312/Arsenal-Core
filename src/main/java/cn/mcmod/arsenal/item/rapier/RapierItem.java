package cn.mcmod.arsenal.item.rapier;

import cn.mcmod.arsenal.api.IDrawable;
import cn.mcmod.arsenal.api.WeaponFeature;
import cn.mcmod.arsenal.api.tier.IWeaponTiered;
import cn.mcmod.arsenal.api.tier.WeaponTier;
import java.util.List;
import java.util.function.Consumer;

import cn.mcmod.arsenal.util.EnchantmentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.extensions.IItemStackExtension;

public class RapierItem extends TieredItem implements IDrawable, IWeaponTiered {
    private final WeaponTier tier;
    private ItemStack sheath;
    private final float attackDamage;
    private final ItemAttributeModifiers defaultModifiers;

    public RapierItem(WeaponTier tier, int attackDamageIn, float attackSpeedIn, ItemStack sheathItem, Properties prop) {
        super(tier, prop);
        this.tier = tier;
        this.sheath = sheathItem;
        this.attackDamage = Math.max((float) attackDamageIn + tier.getAttackDamageBonus(), tier.getAttackDamageBonus());

        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

        AttributeModifier damageModifier = new AttributeModifier(BASE_ATTACK_DAMAGE_ID, this.attackDamage, Operation.ADD_VALUE);
        AttributeModifier speedModifier = new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeedIn, Operation.ADD_VALUE);

        builder.add(Attributes.ATTACK_DAMAGE, damageModifier, EquipmentSlotGroup.bySlot(EquipmentSlot.MAINHAND));
        builder.add(Attributes.ATTACK_SPEED, speedModifier, EquipmentSlotGroup.bySlot(EquipmentSlot.MAINHAND));

        this.defaultModifiers = builder.build();
    }

    public RapierItem(WeaponTier tier, ItemStack sheathItem, Properties prop) {
        this(tier, 2, -1.25F, sheathItem, prop);
    }

    public RapierItem(WeaponTier tier, ItemStack sheathItem) {
        this(tier, 2, -1.25F, sheathItem, (new Properties()).stacksTo(1));
    }

    public float getDamage() {
        return this.attackDamage;
    }

    public ItemStack getSheath(ItemStack stack) {
        return this.sheath;
    }

    public WeaponTier getWeaponTier(ItemStack stack) {
        return this.tier;
    }
    public int getMaxDamage(ItemStack stack) {
        return (int)((float)this.getWeaponTier(stack).getUses() * 0.85F);
    }

    public WeaponFeature getFeature(ItemStack stack) {
        return this.getWeaponTier(stack).getFeature();
    }

    // Creative mode allows enchanting Sweeping Edge on an anvil.（But why even do this?）
    @Override
    public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
        if (enchantment.is(Enchantments.SWEEPING_EDGE)) {
            return false;
        }
        return super.isPrimaryItemFor(stack, enchantment);
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantmentHolder) {
        if (enchantmentHolder.is(Enchantments.SWEEPING_EDGE)) {
            return false;
        }
        if (stack.is(Items.ENCHANTED_BOOK)) {
            return true;
        }
        return enchantmentHolder.value().isSupportedItem(stack);
    }





    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand handIn) {
        ItemStack itemStackIn = player.getItemInHand(handIn);
        player.causeFoodExhaustion(0.2F);

        float[] push;
        if (!player.isInWater()) {
            push = calculatePushVector(player, 0.25F);
            player.push(push[0], 0.2F, push[1]);
        } else {
            push = calculatePushVector(player, 0.5F);
            player.push(push[0], 0.1F, push[1]);
        }

        player.getCooldowns().addCooldown(itemStackIn.getItem(), 5);

        if (handIn == InteractionHand.MAIN_HAND) {
            ItemStack offHand = player.getItemInHand(InteractionHand.OFF_HAND);
            if (offHand.getItem().canPerformAction(offHand, ItemAbilities.SHIELD_BLOCK)) {
                player.startUsingItem(InteractionHand.OFF_HAND);
                return InteractionResultHolder.consume(itemStackIn);
            }
        }

        return InteractionResultHolder.pass(itemStackIn);
    }

    private static float[] calculatePushVector(Player player, float baseForce) {
        float radiansY = player.getYRot() / 180.0F * (float) Math.PI;
        float radiansX = player.getXRot() / 180.0F * (float) Math.PI;

        float sinYaw = Mth.sin(radiansY);
        float cosPitch = Mth.cos(radiansX);

        float motionX = sinYaw * cosPitch * baseForce;
        float motionZ = -sinYaw * cosPitch * baseForce;

        return new float[]{motionX, motionZ};
    }

    @Override
    public void appendHoverText(ItemStack stackIn, Item.TooltipContext pContext, List<Component> tooltipIn, TooltipFlag flagIn) {
        super.appendHoverText(stackIn, pContext, tooltipIn, flagIn);
        Component tierText = Component.translatable("tooltip.arsenal.tiers")
                .append(Component.translatable("tier.arsenal." + this.getWeaponTier(stackIn).getUnlocalizedName()));
        tooltipIn.add(tierText);

        if (this.getFeature(stackIn) != null) {
            tooltipIn.add(Component.translatable("tooltip.arsenal.feature." + this.getWeaponTier(stackIn).getFeature().getName())
                    .withStyle(ChatFormatting.GOLD));
        }
    }

    @Override
    public boolean canAttackBlock(BlockState blockstate, Level level, BlockPos blockpos, Player player) {
        return !player.isCreative();
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState blockstate) {
        if (blockstate.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            return blockstate.is(BlockTags.REPLACEABLE) ||
                    blockstate.is(BlockTags.LEAVES) ||
                    blockstate.is(BlockTags.FLOWERS) ||
                    blockstate.is(BlockTags.CROPS) ||
                    blockstate.is(BlockTags.UNDERWATER_BONEMEALS) ? 1.5F : 1.0F;
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
        if (this.getFeature(stack) != null) {
            this.getFeature(stack).inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
        }
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
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.level() instanceof ServerLevel serverLevel) {
            stack.hurtAndBreak(1, serverLevel, attacker, item -> {
                if (attacker instanceof Player player) {
                    player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND);
                }
            });
        } else {
            stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        }
        return true;
    }


    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        boolean result = super.onLeftClickEntity(stack, player, entity);
        if (!(entity instanceof LivingEntity target)) {
            return result;
        } else {
            this.doStingAttack(stack, player, target);
            if (this.getFeature(stack) == null) {
                return result;
            } else {
                return result || this.getFeature(stack).onLeftClickEntity(stack, player, entity);
            }
        }
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState blockstate, BlockPos blockpos, LivingEntity entityLiving) {
        if (blockstate.getDestroySpeed(level, blockpos) != 0.0F) {
            if (level instanceof ServerLevel serverLevel) {
                stack.hurtAndBreak(2, serverLevel, entityLiving, item -> {
                    if (entityLiving instanceof Player player) {
                        player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND);
                    }
                });
            } else {
                stack.hurtAndBreak(2, entityLiving, EquipmentSlot.MAINHAND);
            }
        }
        return true;
    }
    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState blockstate) {
        return blockstate.is(Blocks.COBWEB);
    }


    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return this.defaultModifiers;
    }


    public void doStingAttack(ItemStack stack, LivingEntity attacker, LivingEntity target) {
        if (stack.getItem() instanceof TieredItem rapier) {
            float attackDamageBonus = rapier.getTier().getAttackDamageBonus();

            float enchantDamageBonus = 0.0f;
            if (attacker.level() instanceof ServerLevel serverLevel) {
                DamageSource damageSource = attacker.damageSources().mobAttack(attacker);
                enchantDamageBonus = EnchantmentUtil.modifyDamage(serverLevel, stack, target, damageSource, 0.0f);
            }

            doStingAttack(stack, attackDamageBonus, enchantDamageBonus, attacker, target);
        }
    }


    public static void doStingAttack(ItemStack stack, float baseDamage, float exDamage, LivingEntity attacker, LivingEntity target) {
        if (!target.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
            boolean isPlayer = attacker instanceof Player ;
            float f = baseDamage;
            float f1 = exDamage;
            if (isPlayer) {
                float f2 = ((Player)attacker).getAttackStrengthScale(0.5F);
                f = baseDamage * (0.2F + f2 * f2 * 0.8F);
                f1 = exDamage * f2;
            }

            f += f1;
            DamageSources sources = attacker.level().damageSources();
            DamageSource ds = isPlayer
                    ? sources.playerAttack((Player)attacker)
                    : sources.mobAttack(attacker);

            boolean hurt = target.hurt(ds, f);

            if (hurt) {
                target.invulnerableTime = 0;
                target.level().playSound(null, target, SoundEvents.PLAYER_ATTACK_CRIT, target.getSoundSource(), Math.max(1.0F, f), (target.getRandom().nextFloat() - target.getRandom().nextFloat()) * 0.5F + 1.0F);
            }
        }
    }
}