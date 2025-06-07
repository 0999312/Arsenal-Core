package cn.mcmod.arsenal.item.knight;

import cn.mcmod.arsenal.ArsenalConfig;
import cn.mcmod.arsenal.api.IDrawable;
import cn.mcmod.arsenal.api.WeaponFeature;
import cn.mcmod.arsenal.api.tier.IWeaponTiered;
import cn.mcmod.arsenal.api.tier.WeaponTier;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ArmingSwordItem extends SwordItem implements IDrawable, IWeaponTiered {
    private final WeaponTier tier;
    private final ItemStack sheath;
    private final int attackDamage;
    private final float attackSpeed;

    public ArmingSwordItem(WeaponTier tier, int attackDamageIn, float attackSpeedIn, ItemStack sheathItem, Properties builderIn) {
        super(tier, builderIn.component(DataComponents.TOOL, createToolProperties()).attributes(createAttributes(tier, attackDamageIn, attackSpeedIn)));
        this.tier = tier;
        this.sheath = sheathItem;
        this.attackDamage = attackDamageIn;
        this.attackSpeed = attackSpeedIn;
    }

    public ArmingSwordItem(WeaponTier tier, int attackDamageIn, float attackSpeedIn, ItemStack sheathItem) {
        this(tier, attackDamageIn, attackSpeedIn, sheathItem, (new Properties()).stacksTo(1));
    }

    public ArmingSwordItem(WeaponTier tier, ItemStack sheathItem, Properties builderIn) {
        this(tier, 4, -2.4F, sheathItem, builderIn);
    }

    public ArmingSwordItem(WeaponTier tier, ItemStack sheathItem) {
        this(tier, 4, -2.4F, sheathItem, (new Properties()).stacksTo(1));
    }


    @Override
    public int getMaxDamage(ItemStack stack) {
        return (int)((float)super.getMaxDamage(stack) * 1.25F);
    }

    @Override
    public void appendHoverText(ItemStack stackIn, Item.TooltipContext pContext, List<Component> tooltipIn, TooltipFlag flagIn) {
        super.appendHoverText(stackIn, pContext, tooltipIn, flagIn);
        MutableComponent tierText = Component.translatable("tooltip.arsenal.tiers")
                .append(Component.translatable("tier.arsenal." + this.getWeaponTier(stackIn).getUnlocalizedName()));
        tooltipIn.add(tierText);

        if (this.getFeature(stackIn) != null) {
            tooltipIn.add(Component.translatable("tooltip.arsenal.feature." + this.getWeaponTier(stackIn).getFeature().getName())
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
    public WeaponTier getWeaponTier(ItemStack stack) {
        return this.tier;
    }

    @Override
    public WeaponFeature getFeature(ItemStack stack) {
        return this.getWeaponTier(stack).getFeature();
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
}