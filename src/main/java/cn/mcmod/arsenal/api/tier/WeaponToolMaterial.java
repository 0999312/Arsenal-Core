package cn.mcmod.arsenal.api.tier;

import cn.mcmod.arsenal.api.WeaponFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;

public class WeaponToolMaterial {
    private final ToolMaterial toolMaterial;
    private final String modId;
    private final String name;
    private boolean isSpecial;
    private final WeaponFeature feature;
    private TagKey<Block> miningTag;

    public WeaponToolMaterial (String unlocName, String modId, TagKey<Block> incorrectBlocksForDrops, int durability, float speed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems, WeaponFeature feature) {
        this.modId = modId;
        this.name = unlocName;
        this.isSpecial = false;
        this.feature = feature;
        this.miningTag = null;
        this.toolMaterial = new ToolMaterial(incorrectBlocksForDrops, durability, speed, attackDamageBonus, enchantmentValue, repairItems);
    }

    public WeaponToolMaterial (String unlocName, String modId, TagKey<Block> incorrectBlocksForDrops, int durability, float speed, float attackDamageBonus, int enchantmentValue, ResourceLocation repairItemTag, WeaponFeature feature) {
        this(unlocName, modId, incorrectBlocksForDrops, durability, speed, attackDamageBonus, enchantmentValue, ItemTags.create(repairItemTag), feature);
    }

    public WeaponToolMaterial (String unlocName, String modId, ToolMaterial baseMaterial, WeaponFeature feature) {
        this.modId = modId;
        this.name = unlocName;
        this.isSpecial = false;
        this.feature = feature;
        this.miningTag = null;
        this.toolMaterial = baseMaterial;
    }

    @Override
    public int hashCode() {
        return this.getUnlocalizedName().hashCode();
    }

    public ToolMaterial getToolMaterial() {
        return this.toolMaterial;
    }

    public float getAttackDamageBonus() {
        return this.toolMaterial.attackDamageBonus();
    }

    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.toolMaterial.incorrectBlocksForDrops();
    }

    public int getEnchantmentValue() {
        return this.toolMaterial.enchantmentValue();
    }

    public TagKey<Block> getMiningTag() {
        return this.miningTag;
    }

    public WeaponToolMaterial setMiningTag(TagKey<Block> tag) {
        this.miningTag = tag;
        return this;
    }

    public TagKey<Item> getRepairItems() {
        return this.toolMaterial.repairItems();
    }

    public float getSpeed() {
        return this.toolMaterial.speed();
    }

    public int getDurability() {
        return this.toolMaterial.durability();
    }

    public String getModId() {
        return this.modId;
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

    public WeaponToolMaterial setSpecial() {
        this.isSpecial = true;
        return this;
    }

    /**
     * 应用剑属性到物品属性
     */
    public Item.Properties applySwordProperties(Item.Properties properties, float attackDamage, float attackSpeed) {
        return this.toolMaterial.applySwordProperties(properties, attackDamage, attackSpeed);
    }

    /**
     * 应用工具属性到物品属性
     */
    public Item.Properties applyToolProperties(Item.Properties properties, TagKey<Block> mineableBlocks, float attackDamage, float attackSpeed) {
        return this.toolMaterial.applyToolProperties(properties, mineableBlocks, attackDamage, attackSpeed);
    }
}