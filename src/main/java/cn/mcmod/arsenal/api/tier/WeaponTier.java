//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.mcmod.arsenal.api.tier;

import cn.mcmod.arsenal.api.WeaponFeature;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;

public class WeaponTier implements Tier {
    private String modId;
    private final int harvestLevel;
    private int maxUses;
    private final float efficiency;
    private float baseDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;
    private final String name;
    private boolean isSpecial;
    private final WeaponFeature feature;
    private TagKey<Block> miningTag;

    public WeaponTier(String unlocName, String modId, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability, Supplier<Ingredient> repairMaterial, WeaponFeature feature) {
        this.modId = "arsenal_core";
        this.isSpecial = false;
        this.setModId(modId);
        this.name = unlocName;
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.baseDamage = baseDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
        this.feature = feature;
        this.miningTag = null;
    }

    public WeaponTier(String unlocName, String modId, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability, ResourceLocation tagName, WeaponFeature feature) {
        this(unlocName, modId, harvestLevel, maxUses, efficiency, baseDamage, enchantability, (() -> Ingredient.of(ItemTags.create(tagName))), feature);
    }

    public WeaponTier(String unlocName, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability, ResourceLocation tagName, WeaponFeature feature) {
        this(unlocName, "arsenal_core", harvestLevel, maxUses, efficiency, baseDamage, enchantability, (() -> Ingredient.of(ItemTags.create(tagName))), feature);
    }

    public WeaponTier(String unlocName, String modId, Tier itemTier, ResourceLocation tagName, WeaponFeature feature) {
        this(unlocName, modId, itemTier.getLevel(), itemTier.getUses(), itemTier.getSpeed(), itemTier.getAttackDamageBonus(), itemTier.getEnchantmentValue(), (() -> Ingredient.of(ItemTags.create(tagName))), feature);
    }

    public WeaponTier(String unlocName, Tier itemTier, ResourceLocation tagName, WeaponFeature feature) {
        this(unlocName, "arsenal_core", itemTier, tagName, feature);
    }

    public WeaponTier(String unlocName, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability, Supplier<Ingredient> repairMaterial, WeaponFeature feature) {
        this(unlocName, "arsenal_core", harvestLevel, maxUses, efficiency, baseDamage, enchantability, repairMaterial, feature);
    }

    public WeaponTier(String unlocName, String modId, Tier itemTier, Supplier<Ingredient> repairMaterial, WeaponFeature feature) {
        this(unlocName, modId, itemTier.getLevel(), itemTier.getUses(), itemTier.getSpeed(), itemTier.getAttackDamageBonus(), itemTier.getEnchantmentValue(), repairMaterial, feature);
    }

    public WeaponTier(String unlocName, Tier itemTier, Supplier<Ingredient> repairMaterial, WeaponFeature feature) {
        this(unlocName, "arsenal_core", itemTier, repairMaterial, feature);
    }

    @Override
    public int hashCode() {
        return this.getUnlocalizedName().hashCode();
    }

    @Override
    public float getAttackDamageBonus() {
        return this.baseDamage;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    @Deprecated
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    @Nullable
    public TagKey<Block> getTag() {
        return this.miningTag;
    }

    public WeaponTier setMiningTag(TagKey<Block> tag) {
        this.miningTag = tag;
        return this;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    public String getModId() {
        return this.modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public String getUnlocalizedName() {
        return this.name;
    }

    public WeaponFeature getFeature() {
        return this.feature;
    }

    public boolean isSpecial() {
        return this.isSpecial;
    }

    public WeaponTier setSpecial() {
        this.isSpecial = true;
        return this;
    }
}