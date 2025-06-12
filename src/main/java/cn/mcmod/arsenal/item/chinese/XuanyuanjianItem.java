package cn.mcmod.arsenal.item.chinese;

import cn.mcmod.arsenal.ArsenalConfig;
import cn.mcmod.arsenal.item.ItemRegistry;
import cn.mcmod.arsenal.tier.VanillaWeaponToolMaterials;
import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

public class XuanyuanjianItem extends ChineseSwordItem {
    public XuanyuanjianItem(Item.Properties properties) {
        super(VanillaWeaponToolMaterials.MAXIMUM_POWER.get(), 5, -1.8F,
                new ItemStack(ItemRegistry.XUANYUANJIAN_SHEATH.get()),
                properties.stacksTo(1).component(DataComponents.UNBREAKABLE, Unit.INSTANCE));
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isCombineRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public void appendHoverText (ItemStack stack, TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, ctx, display, tooltip, flag);
        tooltip.accept((Component.translatable("tooltip.arsenal.xuanyuanjian.not_max")).withStyle(ChatFormatting.DARK_RED));
    }


    @Override
    public boolean isFoil(ItemStack stackIn) {
        return ArsenalConfig.normal_sword_foil && ArsenalConfig.xuanyuanjian_foil;
    }
}