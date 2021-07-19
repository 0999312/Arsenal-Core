package cn.mcmod.chinese_sword.item;

import cn.mcmod.chinese_sword.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "chinese_sword");

	public static final RegistryObject<Item> WOODEN_CHINESE_SWORD_SHEATH = ITEMS.register("wooden_chinese_sword_sheath",
			SwordSheathItem::new);
	public static final RegistryObject<Item> WOODEN_ANCIENT_SWORD_SHEATH = ITEMS.register("wooden_ancient_sword_sheath",
			SwordSheathItem::new);

	public static final RegistryObject<Item> STONE_CHINESE_SWORD_SHEATH = ITEMS.register("stone_chinese_sword_sheath",
			SwordSheathItem::new);
	public static final RegistryObject<Item> STONE_ANCIENT_SWORD_SHEATH = ITEMS.register("stone_ancient_sword_sheath",
			SwordSheathItem::new);

	public static final RegistryObject<Item> IRON_CHINESE_SWORD_SHEATH = ITEMS.register("iron_chinese_sword_sheath",
			SwordSheathItem::new);
	public static final RegistryObject<Item> IRON_ANCIENT_SWORD_SHEATH = ITEMS.register("iron_ancient_sword_sheath",
			SwordSheathItem::new);

	public static final RegistryObject<Item> GOLDEN_CHINESE_SWORD_SHEATH = ITEMS.register("golden_chinese_sword_sheath",
			SwordSheathItem::new);
	public static final RegistryObject<Item> GOLDEN_ANCIENT_SWORD_SHEATH = ITEMS.register("golden_ancient_sword_sheath",
			SwordSheathItem::new);

	public static final RegistryObject<Item> DIAMOND_CHINESE_SWORD_SHEATH = ITEMS.register("diamond_chinese_sword_sheath",
			SwordSheathItem::new);
	public static final RegistryObject<Item> DIAMOND_ANCIENT_SWORD_SHEATH = ITEMS.register("diamond_ancient_sword_sheath",
			SwordSheathItem::new);

	public static final RegistryObject<Item> NETHERITE_CHINESE_SWORD_SHEATH = ITEMS.register("netherite_chinese_sword_sheath",
			SwordSheathItem::new);
	public static final RegistryObject<Item> NETHERITE_ANCIENT_SWORD_SHEATH = ITEMS.register("netherite_ancient_sword_sheath",
			SwordSheathItem::new);
	
	public static final RegistryObject<Item> WOODEN_CHINESE_SWORD = ITEMS.register("wooden_chinese_sword",
			() -> new ChineseSwordItem(WeaponTiers.WOOD, new ItemStack(WOODEN_CHINESE_SWORD_SHEATH.get()), new Item.Properties().stacksTo(1).tab(Main.WEAPON_GROUP)));
	public static final RegistryObject<Item> WOODEN_ANCIENT_SWORD = ITEMS.register("wooden_ancient_sword",
			() -> new AncientSwordItem(WeaponTiers.WOOD, new ItemStack(WOODEN_ANCIENT_SWORD_SHEATH.get()), new Item.Properties().stacksTo(1).tab(Main.WEAPON_GROUP)));

	public static final RegistryObject<Item> STONE_CHINESE_SWORD = ITEMS.register("stone_chinese_sword",
			() -> new ChineseSwordItem(WeaponTiers.STONE, new ItemStack(STONE_CHINESE_SWORD_SHEATH.get()),  new Item.Properties().stacksTo(1).tab(Main.WEAPON_GROUP)));
	public static final RegistryObject<Item> STONE_ANCIENT_SWORD = ITEMS.register("stone_ancient_sword",
			() -> new AncientSwordItem(WeaponTiers.STONE, new ItemStack(STONE_ANCIENT_SWORD_SHEATH.get()),  new Item.Properties().stacksTo(1).tab(Main.WEAPON_GROUP)));

	public static final RegistryObject<Item> IRON_CHINESE_SWORD = ITEMS.register("iron_chinese_sword",
			() -> new ChineseSwordItem(WeaponTiers.IRON, new ItemStack(IRON_CHINESE_SWORD_SHEATH.get()),  new Item.Properties().stacksTo(1).tab(Main.WEAPON_GROUP)));
	public static final RegistryObject<Item> IRON_ANCIENT_SWORD = ITEMS.register("iron_ancient_sword",
			() -> new AncientSwordItem(WeaponTiers.IRON, new ItemStack(IRON_ANCIENT_SWORD_SHEATH.get()),  new Item.Properties().stacksTo(1).tab(Main.WEAPON_GROUP)));

	public static final RegistryObject<Item> GOLDEN_CHINESE_SWORD = ITEMS.register("golden_chinese_sword",
			() -> new ChineseSwordItem(WeaponTiers.GOLD, new ItemStack(GOLDEN_CHINESE_SWORD_SHEATH.get()),  new Item.Properties().stacksTo(1).tab(Main.WEAPON_GROUP)));
	public static final RegistryObject<Item> GOLDEN_ANCIENT_SWORD = ITEMS.register("golden_ancient_sword",
			() -> new AncientSwordItem(WeaponTiers.GOLD, new ItemStack(GOLDEN_ANCIENT_SWORD_SHEATH.get()),  new Item.Properties().stacksTo(1).tab(Main.WEAPON_GROUP)));

	public static final RegistryObject<Item> DIAMOND_CHINESE_SWORD = ITEMS.register("diamond_chinese_sword",
			() -> new ChineseSwordItem(WeaponTiers.DIAMOND, new ItemStack(DIAMOND_CHINESE_SWORD_SHEATH.get()),  new Item.Properties().stacksTo(1).tab(Main.WEAPON_GROUP)));
	public static final RegistryObject<Item> DIAMOND_ANCIENT_SWORD = ITEMS.register("diamond_ancient_sword",
			() -> new AncientSwordItem(WeaponTiers.DIAMOND, new ItemStack(DIAMOND_ANCIENT_SWORD_SHEATH.get()),  new Item.Properties().stacksTo(1).tab(Main.WEAPON_GROUP)));

	public static final RegistryObject<Item> NETHERITE_CHINESE_SWORD = ITEMS.register("netherite_chinese_sword",
			() -> new ChineseSwordItem(WeaponTiers.NETHERITE, new ItemStack(NETHERITE_CHINESE_SWORD_SHEATH.get()),  new Item.Properties().stacksTo(1).tab(Main.WEAPON_GROUP)));
	public static final RegistryObject<Item> NETHERITE_ANCIENT_SWORD = ITEMS.register("netherite_ancient_sword",
			() -> new AncientSwordItem(WeaponTiers.NETHERITE, new ItemStack(NETHERITE_ANCIENT_SWORD_SHEATH.get()), new Item.Properties().stacksTo(1).tab(Main.WEAPON_GROUP)));


}
