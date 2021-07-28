package cn.mcmod.chinese_sword.item;

import java.util.function.Supplier;

import cn.mcmod.chinese_sword.Constants;
import cn.mcmod.chinese_sword.item.feature.WeaponFeature;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;

public class WeaponTier implements IItemTier {

    private final int harvestLevel;
    private int maxUses;
    private final float efficiency;
    private float baseDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;
    private String name;
    private String modId = Constants.MODID;
    private final WeaponFeature feature;

    public WeaponTier(String unlocName, String modId, int harvestLevel, int maxUses, float efficiency, float baseDamage,
            int enchantability, Supplier<Ingredient> repairMaterial, WeaponFeature feature) {
        this.setUnlocalizedName(unlocName);
        this.setModId(modId);
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.baseDamage = baseDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue<Ingredient>(repairMaterial);
        this.feature = feature;
    }

    public WeaponTier(String unlocName, String modId, int harvestLevel, int maxUses, float efficiency, float baseDamage,
            int enchantability, ResourceLocation tagName, WeaponFeature feature) {
        this(unlocName, modId, harvestLevel, maxUses, efficiency, baseDamage, enchantability,
                () -> Ingredient.of(ItemTags.getAllTags().getTag(tagName)), feature);
    }

    public WeaponTier(String unlocName, int harvestLevel, int maxUses, float efficiency, float baseDamage,
            int enchantability, ResourceLocation tagName, WeaponFeature feature) {
        this(unlocName, Constants.MODID, harvestLevel, maxUses, efficiency, baseDamage, enchantability,
                () -> Ingredient.of(ItemTags.getAllTags().getTag(tagName)), feature);
    }

    public WeaponTier(String unlocName, String modId, IItemTier itemTier, ResourceLocation tagName,
            WeaponFeature feature) {
        this(unlocName, modId, itemTier.getLevel(), itemTier.getUses(), itemTier.getSpeed(),
                itemTier.getAttackDamageBonus(), itemTier.getEnchantmentValue(),
                () -> Ingredient.of(ItemTags.getAllTags().getTag(tagName)), feature);
    }

    public WeaponTier(String unlocName, IItemTier itemTier, ResourceLocation tagName, WeaponFeature feature) {
        this(unlocName, Constants.MODID, itemTier, tagName, feature);
    }

    public WeaponTier(String unlocName, int harvestLevel, int maxUses, float efficiency, float baseDamage,
            int enchantability, Supplier<Ingredient> repairMaterial, WeaponFeature feature) {
        this(unlocName, Constants.MODID, harvestLevel, maxUses, efficiency, baseDamage, enchantability, repairMaterial,
                feature);
    }

    public WeaponTier(String unlocName, String modId, IItemTier itemTier, Supplier<Ingredient> repairMaterial,
            WeaponFeature feature) {
        this(unlocName, modId, itemTier.getLevel(), itemTier.getUses(), itemTier.getSpeed(),
                itemTier.getAttackDamageBonus(), itemTier.getEnchantmentValue(), repairMaterial, feature);
    }

    public WeaponTier(String unlocName, IItemTier itemTier, Supplier<Ingredient> repairMaterial,
            WeaponFeature feature) {
        this(unlocName, Constants.MODID, itemTier, repairMaterial, feature);
    }

    @Override
    public float getAttackDamageBonus() {
        return baseDamage;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public int getUses() {
        return maxUses;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public String getUnlocalizedName() {
        return name;
    }

    public void setUnlocalizedName(String name) {
        this.name = name;
    }

    public WeaponFeature getFeature() {
        return feature;
    }

}
