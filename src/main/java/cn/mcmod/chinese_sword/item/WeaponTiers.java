package cn.mcmod.chinese_sword.item;

import cn.mcmod.chinese_sword.ChineseSwordConfig;
import cn.mcmod.chinese_sword.Constants;
import cn.mcmod.chinese_sword.item.feature.XuanyuanFeature;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemTier;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class WeaponTiers {
    public static final WeaponTier WOOD = new WeaponTier("wood", (IItemTier) ItemTier.WOOD, ItemTags.PLANKS.getName(), null);

    public static final WeaponTier STONE = new WeaponTier("stone", (IItemTier) ItemTier.STONE,
            new ResourceLocation("forge:cobblestone"), null);

    public static final WeaponTier IRON = new WeaponTier("iron", (IItemTier) ItemTier.IRON,
            new ResourceLocation("forge:ingots/iron"), null);

    public static final WeaponTier GOLD = new WeaponTier("gold", (IItemTier) ItemTier.GOLD,
            new ResourceLocation("forge:ingots/gold"), null);

    public static final WeaponTier DIAMOND = new WeaponTier("diamond", (IItemTier) ItemTier.DIAMOND,
            new ResourceLocation("forge:gems/diamond"), null);

    public static final WeaponTier NETHERITE = new WeaponTier("netherite", (IItemTier) ItemTier.NETHERITE,
            new ResourceLocation("forge:ingots/netherite"), null);

    public static final WeaponTier COPPER = new WeaponTier("copper", Constants.MODID, 1, 200, 5.0F, 1.5F, 8,
            new ResourceLocation("forge:ingots/copper"), null);

    public static final WeaponTier BRONZE = new WeaponTier("bronze", Constants.MODID, 2, 320, 5.75F, 2.0F, 12,
            new ResourceLocation("forge:ingots/bronze"), null);

    public static final WeaponTier STEEL = new WeaponTier("steel", Constants.MODID, 2, 480, 6.5F, 2.5F, 14,
            new ResourceLocation("forge:ingots/steel"), null);

    public static final WeaponTier SILVER = new WeaponTier("silver", Constants.MODID, 1, 48, 5.0F, 1.5F, 16,
            new ResourceLocation("forge:ingots/silver"), null);

    public static final WeaponTier INVAR = new WeaponTier("invar", Constants.MODID, 2, 440, 6.0F, 2.2F, 12,
            new ResourceLocation("forge:ingots/invar"), null);

    public static final WeaponTier PLATINUM = new WeaponTier("platinum", Constants.MODID, 3, 1024, 4.0F, 3.5F, 18,
            new ResourceLocation("forge:ingots/platinum"), null);

    public static final WeaponTier ELECTRUM = new WeaponTier("electrum", Constants.MODID, 1, 180, 3.5F, 2.0F, 8,
            new ResourceLocation("forge:ingots/electrum"), null);

    public static final WeaponTier NICKEL = new WeaponTier("nickel", Constants.MODID, 1, 200, 4.5F, 2.0F, 6,
            new ResourceLocation("forge:ingots/nickel"), null);

    public static final WeaponTier LEAD = new WeaponTier("lead", Constants.MODID, 1, 240, 4.5F, 2.0F, 5,
            new ResourceLocation("forge:ingots/lead"), null);

    public static final WeaponTier MAXIMUM_POWER = new WeaponTier("maximum_power", Constants.MODID, 5, -1, 8F,
            ChineseSwordConfig.MAXIMUM_POWER_DAMAGE.get().floatValue(), 50, new ResourceLocation("forge:gems/diamond"),
            new XuanyuanFeature());
}
