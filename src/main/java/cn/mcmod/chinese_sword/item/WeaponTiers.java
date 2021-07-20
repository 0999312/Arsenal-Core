package cn.mcmod.chinese_sword.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemTier;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class WeaponTiers {
    public static final WeaponTier WOOD = new WeaponTier("wood", (IItemTier) ItemTier.WOOD, ItemTags.PLANKS.getName());

    public static final WeaponTier STONE = new WeaponTier("stone", (IItemTier) ItemTier.STONE,
            new ResourceLocation("forge:cobblestone"));

    public static final WeaponTier IRON = new WeaponTier("iron", (IItemTier) ItemTier.IRON,
            new ResourceLocation("forge:ingots/iron"));

    public static final WeaponTier GOLD = new WeaponTier("gold", (IItemTier) ItemTier.GOLD,
            new ResourceLocation("forge:ingots/gold"));

    public static final WeaponTier DIAMOND = new WeaponTier("diamond", (IItemTier) ItemTier.DIAMOND,
            new ResourceLocation("forge:gems/diamond"));

    public static final WeaponTier NETHERITE = new WeaponTier("netherite", (IItemTier) ItemTier.NETHERITE,
            new ResourceLocation("forge:ingots/netherite"));

    public static final WeaponTier COPPER = new WeaponTier("copper", "chinese_sword", 1, 200, 5.0F, 1.5F, 8,
            new ResourceLocation("forge:ingots/copper"));

    public static final WeaponTier BRONZE = new WeaponTier("bronze", "chinese_sword", 2, 320, 5.75F, 2.0F, 12,
            new ResourceLocation("forge:ingots/bronze"));

    public static final WeaponTier STEEL = new WeaponTier("steel", "chinese_sword", 2, 480, 6.5F, 2.5F, 14,
            new ResourceLocation("forge:ingots/steel"));

}
