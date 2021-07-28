package cn.mcmod.chinese_sword.item;

import java.util.List;

import cn.mcmod.chinese_sword.ChineseSwordConfig;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class XuanyuanjianItem extends AncientSwordItem {

    public XuanyuanjianItem() {
        super(WeaponTiers.MAXIMUM_POWER, new ItemStack(ItemRegistry.XUANYUANJIAN_SHEATH.get()));
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
    public void appendHoverText(ItemStack stackIn, World levelIn, List<ITextComponent> tooltipIn, ITooltipFlag flagIn) {
        super.appendHoverText(stackIn, levelIn, tooltipIn, flagIn);
        tooltipIn.add(new TranslationTextComponent("tooltip.chinese_sword.xuanyuanjian.not_max")
                .withStyle(TextFormatting.DARK_RED));
    }

    @Override
    public boolean isFoil(ItemStack stackIn) {
        // 什么？你认为轩辕剑这种神器需要附魔才可以有光效吗？
        // 但是确实不怎么合适。
        if (ChineseSwordConfig.NORMAL_SWORD_FOIL.get() && ChineseSwordConfig.XUANYUANJIAN_FOIL.get())
            return true;
        return false;
    }
}
