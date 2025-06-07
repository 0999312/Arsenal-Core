package cn.mcmod.arsenal.api.tier;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.api.WeaponFeature;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class WeaponTier implements Tier {
    private String modId;
    private int maxUses;
    private final float efficiency;
    private float baseDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;
    private final String name;
    private boolean isSpecial;
    private final WeaponFeature feature;
    private TagKey<Block> miningTag;

    public WeaponTier(String unlocName, String modId, int maxUses, float efficiency, float baseDamage, int enchantability, Supplier<Ingredient> repairMaterial, WeaponFeature feature) {
        this.modId = ArsenalCore.MODID;
        this.isSpecial = false;
        this.setModId(modId);
        this.name = unlocName;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.baseDamage = baseDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
        this.feature = feature;
        this.miningTag = null;
    }

    public WeaponTier(String unlocName, String modId, Tier itemTier, ResourceLocation tagName, WeaponFeature feature) {
        this(unlocName, modId, itemTier.getUses(), itemTier.getSpeed(), itemTier.getAttackDamageBonus(), itemTier.getEnchantmentValue(), (() -> Ingredient.of(ItemTags.create(tagName))), feature);
    }

    public WeaponTier(String unlocName, String modId, Tier itemTier, Supplier<Ingredient> repairMaterial, WeaponFeature feature) {
        this(unlocName, modId, itemTier.getUses(), itemTier.getSpeed(), itemTier.getAttackDamageBonus(), itemTier.getEnchantmentValue(), repairMaterial, feature);
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
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return BlockTags.create(ResourceLocation.parse("c:ineffective_tool"));
    }
    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    public TagKey<Block> getMiningTag () {
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