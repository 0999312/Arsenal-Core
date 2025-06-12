package cn.mcmod.arsenal.tier;

import cn.mcmod.arsenal.ArsenalConfig;
import cn.mcmod.arsenal.api.toolmaterial.BlankToolMaterial;
import cn.mcmod.arsenal.api.toolmaterial.WeaponToolMaterial;
import cn.mcmod.arsenal.item.feature.XuanyuanFeature;
import java.util.function.Supplier;

import cn.mcmod.arsenal.util.Lazy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.common.Tags;

public final class VanillaWeaponToolMaterials {
    public static final Supplier<WeaponToolMaterial> WOOD = new Lazy<>(() -> new BlankToolMaterial("wooden", ToolMaterial.WOOD, ItemTags.PLANKS.location()));
    public static final Supplier<WeaponToolMaterial> STONE = new Lazy<>(() -> new BlankToolMaterial("stone", ToolMaterial.STONE, ResourceLocation.parse("c:cobblestone")));
    public static final Supplier<WeaponToolMaterial> IRON = new Lazy<>(() -> new BlankToolMaterial("iron", ToolMaterial.IRON, ResourceLocation.parse("c:ingots/iron")));
    public static final Supplier<WeaponToolMaterial> GOLD = new Lazy<>(() -> new BlankToolMaterial("golden", ToolMaterial.GOLD, ResourceLocation.parse("c:ingots/gold")));
    public static final Supplier<WeaponToolMaterial> DIAMOND = new Lazy<>(() -> new BlankToolMaterial("diamond", ToolMaterial.DIAMOND, ResourceLocation.parse("c:gems/diamond")));
    public static final Supplier<WeaponToolMaterial> NETHERITE = new Lazy<>(() -> new BlankToolMaterial("netherite", ToolMaterial.NETHERITE, ResourceLocation.parse("c:ingots/netherite")));

    public static final Supplier<WeaponToolMaterial> COPPER = new Lazy<>(() ->
            new BlankToolMaterial("copper", "arsenal_core",
            200, 5.0F, 1.5F, 8,
            ResourceLocation.parse("c:ingots/copper")));
    public static final Supplier<WeaponToolMaterial> LAPIS = new Lazy<>(() ->
            new BlankToolMaterial("lapis_lazuli", "arsenal_core",
            200, 2.0F, 2.0F, 40,
            Tags.Items.GEMS_LAPIS.location()));

    public static final Supplier<WeaponToolMaterial> MAXIMUM_POWER = new Lazy<>(() ->
            (new WeaponToolMaterial("maximum_power", "arsenal_core",
                    BlockTags.create(ResourceLocation.parse("c:ineffective_tool")),
                    -1, 8.0F, (float) ArsenalConfig.maximum_power_damage, 50,
                    ItemTags.create(ResourceLocation.parse("c:gems/diamond")),
                    new XuanyuanFeature())).setSpecial());
}