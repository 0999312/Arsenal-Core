package cn.mcmod.arsenal.api.tier;

import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class BlankTier extends WeaponTier {
    public BlankTier(String unlocName, String modId, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        super(unlocName, modId, harvestLevel, maxUses, efficiency, baseDamage, enchantability, repairMaterial, null);
    }

    public BlankTier(String unlocName, String modId, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability, ResourceLocation tagName) {
        this(unlocName, modId, harvestLevel, maxUses, efficiency, baseDamage, enchantability, (() -> Ingredient.of(ItemTags.create(tagName))));
    }

    public BlankTier(String unlocName, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability, ResourceLocation tagName) {
        this(unlocName, "arsenal_core", harvestLevel, maxUses, efficiency, baseDamage, enchantability, (() -> Ingredient.of(ItemTags.create(tagName))));
    }

    public BlankTier(String unlocName, String modId, Tier itemTier, ResourceLocation tagName) {
        this(unlocName, modId, itemTier.getLevel(), itemTier.getUses(), itemTier.getSpeed(), itemTier.getAttackDamageBonus(), itemTier.getEnchantmentValue(), (() -> Ingredient.of(ItemTags.create(tagName))));
    }

    public BlankTier(String unlocName, Tier itemTier, ResourceLocation tagName) {
        this(unlocName, "arsenal_core", itemTier, tagName);
    }

    public BlankTier(String unlocName, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this(unlocName, "arsenal_core", harvestLevel, maxUses, efficiency, baseDamage, enchantability, repairMaterial);
    }

    public BlankTier(String unlocName, String modId, Tier itemTier, Supplier<Ingredient> repairMaterial) {
        this(unlocName, modId, itemTier.getLevel(), itemTier.getUses(), itemTier.getSpeed(), itemTier.getAttackDamageBonus(), itemTier.getEnchantmentValue(), repairMaterial);
    }

    public BlankTier(String unlocName, Tier itemTier, Supplier<Ingredient> repairMaterial) {
        this(unlocName, "arsenal_core", itemTier, repairMaterial);
    }
}