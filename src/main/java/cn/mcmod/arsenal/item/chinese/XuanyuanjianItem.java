//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.mcmod.arsenal.item.chinese;

import cn.mcmod.arsenal.ArsenalConfig;
import cn.mcmod.arsenal.item.ItemRegistry;
import cn.mcmod.arsenal.tier.VanillaWeaponTiers;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class XuanyuanjianItem extends ChineseSwordItem {
    public XuanyuanjianItem() {
        super(VanillaWeaponTiers.MAXIMUM_POWER.get(), 5, -1.8F, new ItemStack(ItemRegistry.XUANYUANJIAN_SHEATH.get()));
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
    public void appendHoverText(ItemStack stackIn, Level levelIn, List<Component> tooltipIn, TooltipFlag flagIn) {
        super.appendHoverText(stackIn, levelIn, tooltipIn, flagIn);
        tooltipIn.add((Component.translatable("tooltip.arsenal.xuanyuanjian.not_max")).withStyle(ChatFormatting.DARK_RED));
    }

    @Override
    public boolean isFoil(ItemStack stackIn) {
        return ArsenalConfig.normal_sword_foil && ArsenalConfig.xuanyuanjian_foil;
    }
}