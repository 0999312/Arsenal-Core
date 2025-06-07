package cn.mcmod.arsenal.tier;

import cn.mcmod.arsenal.ArsenalConfig;
import cn.mcmod.arsenal.api.tier.BlankTier;
import cn.mcmod.arsenal.api.tier.WeaponTier;
import cn.mcmod.arsenal.item.feature.XuanyuanFeature;
import java.util.function.Supplier;

import cn.mcmod.arsenal.util.Lazy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

public final class VanillaWeaponTiers {
    public static final Supplier<WeaponTier> WOOD = new Lazy<>(() -> new BlankTier("wooden", Tiers.WOOD, ItemTags.PLANKS.location()));
    public static final Supplier<WeaponTier> STONE = new Lazy<>(() -> new BlankTier("stone", Tiers.STONE, ResourceLocation.parse("c:cobblestone")));
    public static final Supplier<WeaponTier> IRON = new Lazy<>(() -> new BlankTier("iron", Tiers.IRON, ResourceLocation.parse("c:ingots/iron")));
    public static final Supplier<WeaponTier> GOLD = new Lazy<>(() -> new BlankTier("golden", Tiers.GOLD, ResourceLocation.parse("c:ingots/gold")));
    public static final Supplier<WeaponTier> DIAMOND = new Lazy<>(() -> new BlankTier("diamond", Tiers.DIAMOND, ResourceLocation.parse("c:gems/diamond")));
    public static final Supplier<WeaponTier> NETHERITE = new Lazy<>(() -> new BlankTier("netherite", Tiers.NETHERITE, ResourceLocation.parse("c:ingots/netherite")));
    public static final Supplier<WeaponTier> COPPER = new Lazy<>(() -> new BlankTier("copper", "arsenal_core", 200, 5.0F, 1.5F, 8, ResourceLocation.parse("c:ingots/copper")));
    public static final Supplier<WeaponTier> LAPIS = new Lazy<>(() -> new BlankTier("lapis_lazuli", "arsenal_core", 200, 2.0F, 2.0F, 40, Tags.Items.GEMS_LAPIS.location()));
    public static final Supplier<WeaponTier> MAXIMUM_POWER = new Lazy<>(() -> (new WeaponTier("maximum_power", "arsenal_core", -1, 8.0F, (float) ArsenalConfig.maximum_power_damage, 50, (() -> Ingredient.of(ItemTags.create(ResourceLocation.parse("c:gems/diamond")))), new XuanyuanFeature())).setSpecial());

}