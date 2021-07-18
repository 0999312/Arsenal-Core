package cn.mcmod.chinese_sword.item;

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
	private final ResourceLocation tagName;
	private String name;
	private String modId = "chinese_sword";

	public WeaponTier(String unlocName, String modId, int harvestLevel, int maxUses, float efficiency, float baseDamage,
			int enchantability, ResourceLocation tagName) {
		this.setUnlocalizedName(unlocName);
		this.setModId(modId);
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.baseDamage = baseDamage;
		this.enchantability = enchantability;
		this.tagName = tagName;
		this.repairMaterial = new LazyValue<Ingredient>(() -> Ingredient.of(ItemTags.getAllTags().getTag(tagName)));
	}

	public WeaponTier(String unlocName, int harvestLevel, int maxUses, float efficiency, float baseDamage,
			int enchantability, ResourceLocation tagName) {
		this(unlocName, "chinese_sword", harvestLevel, maxUses, efficiency, baseDamage, enchantability, tagName);
	}

	public WeaponTier(String unlocName, String modId, IItemTier itemTier, ResourceLocation tagName) {
		this(unlocName, modId, itemTier.getLevel(), itemTier.getUses(), itemTier.getSpeed(),
				itemTier.getAttackDamageBonus(), itemTier.getEnchantmentValue(), tagName);
	}

	public WeaponTier(String unlocName, IItemTier itemTier, ResourceLocation tagName) {
		this(unlocName, "chinese_sword", itemTier, tagName);
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

	public ResourceLocation getTagName() {
		return this.tagName;
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
}
