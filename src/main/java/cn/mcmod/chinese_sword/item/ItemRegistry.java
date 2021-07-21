package cn.mcmod.chinese_sword.item;

import cn.mcmod.chinese_sword.Constants;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MODID);

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

    public static final RegistryObject<Item> DIAMOND_CHINESE_SWORD_SHEATH = ITEMS
            .register("diamond_chinese_sword_sheath", SwordSheathItem::new);

    public static final RegistryObject<Item> DIAMOND_ANCIENT_SWORD_SHEATH = ITEMS
            .register("diamond_ancient_sword_sheath", SwordSheathItem::new);

    public static final RegistryObject<Item> NETHERITE_CHINESE_SWORD_SHEATH = ITEMS
            .register("netherite_chinese_sword_sheath", SwordSheathItem::new);

    public static final RegistryObject<Item> NETHERITE_ANCIENT_SWORD_SHEATH = ITEMS
            .register("netherite_ancient_sword_sheath", SwordSheathItem::new);

    public static final RegistryObject<Item> STEEL_CHINESE_SWORD_SHEATH = ITEMS.register("steel_chinese_sword_sheath",
            SwordSheathItem::new);

    public static final RegistryObject<Item> STEEL_ANCIENT_SWORD_SHEATH = ITEMS.register("steel_ancient_sword_sheath",
            SwordSheathItem::new);

    public static final RegistryObject<Item> SILVER_CHINESE_SWORD_SHEATH = ITEMS.register("silver_chinese_sword_sheath",
            SwordSheathItem::new);

    public static final RegistryObject<Item> SILVER_ANCIENT_SWORD_SHEATH = ITEMS.register("silver_ancient_sword_sheath",
            SwordSheathItem::new);

    public static final RegistryObject<Item> PLATINUM_CHINESE_SWORD_SHEATH = ITEMS
            .register("platinum_chinese_sword_sheath", SwordSheathItem::new);

    public static final RegistryObject<Item> PLATINUM_ANCIENT_SWORD_SHEATH = ITEMS
            .register("platinum_ancient_sword_sheath", SwordSheathItem::new);

    public static final RegistryObject<Item> ELECTRUM_CHINESE_SWORD_SHEATH = ITEMS
            .register("electrum_chinese_sword_sheath", SwordSheathItem::new);

    public static final RegistryObject<Item> ELECTRUM_ANCIENT_SWORD_SHEATH = ITEMS
            .register("electrum_ancient_sword_sheath", SwordSheathItem::new);

    public static final RegistryObject<Item> INVAR_CHINESE_SWORD_SHEATH = ITEMS.register("invar_chinese_sword_sheath",
            SwordSheathItem::new);

    public static final RegistryObject<Item> INVAR_ANCIENT_SWORD_SHEATH = ITEMS.register("invar_ancient_sword_sheath",
            SwordSheathItem::new);

    public static final RegistryObject<Item> NICKEL_CHINESE_SWORD_SHEATH = ITEMS.register("nickel_chinese_sword_sheath",
            SwordSheathItem::new);

    public static final RegistryObject<Item> NICKEL_ANCIENT_SWORD_SHEATH = ITEMS.register("nickel_ancient_sword_sheath",
            SwordSheathItem::new);

    public static final RegistryObject<Item> LEAD_CHINESE_SWORD_SHEATH = ITEMS.register("lead_chinese_sword_sheath",
            SwordSheathItem::new);

    public static final RegistryObject<Item> LEAD_ANCIENT_SWORD_SHEATH = ITEMS.register("lead_ancient_sword_sheath",
            SwordSheathItem::new);

    public static final RegistryObject<Item> WOODEN_CHINESE_SWORD = ITEMS.register("wooden_chinese_sword",
            () -> new ChineseSwordItem(WeaponTiers.WOOD, new ItemStack(WOODEN_CHINESE_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> WOODEN_ANCIENT_SWORD = ITEMS.register("wooden_ancient_sword",
            () -> new AncientSwordItem(WeaponTiers.WOOD, new ItemStack(WOODEN_ANCIENT_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> STONE_CHINESE_SWORD = ITEMS.register("stone_chinese_sword",
            () -> new ChineseSwordItem(WeaponTiers.STONE, new ItemStack(STONE_CHINESE_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> STONE_ANCIENT_SWORD = ITEMS.register("stone_ancient_sword",
            () -> new AncientSwordItem(WeaponTiers.STONE, new ItemStack(STONE_ANCIENT_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> IRON_CHINESE_SWORD = ITEMS.register("iron_chinese_sword",
            () -> new ChineseSwordItem(WeaponTiers.IRON, new ItemStack(IRON_CHINESE_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> IRON_ANCIENT_SWORD = ITEMS.register("iron_ancient_sword",
            () -> new AncientSwordItem(WeaponTiers.IRON, new ItemStack(IRON_ANCIENT_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> GOLDEN_CHINESE_SWORD = ITEMS.register("golden_chinese_sword",
            () -> new ChineseSwordItem(WeaponTiers.GOLD, new ItemStack(GOLDEN_CHINESE_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> GOLDEN_ANCIENT_SWORD = ITEMS.register("golden_ancient_sword",
            () -> new AncientSwordItem(WeaponTiers.GOLD, new ItemStack(GOLDEN_ANCIENT_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> DIAMOND_CHINESE_SWORD = ITEMS.register("diamond_chinese_sword",
            () -> new ChineseSwordItem(WeaponTiers.DIAMOND, new ItemStack(DIAMOND_CHINESE_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> DIAMOND_ANCIENT_SWORD = ITEMS.register("diamond_ancient_sword",
            () -> new AncientSwordItem(WeaponTiers.DIAMOND, new ItemStack(DIAMOND_ANCIENT_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> NETHERITE_CHINESE_SWORD = ITEMS.register("netherite_chinese_sword",
            () -> new ChineseSwordItem(WeaponTiers.NETHERITE, new ItemStack(NETHERITE_CHINESE_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> NETHERITE_ANCIENT_SWORD = ITEMS.register("netherite_ancient_sword",
            () -> new AncientSwordItem(WeaponTiers.NETHERITE, new ItemStack(NETHERITE_ANCIENT_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> STEEL_CHINESE_SWORD = ITEMS.register("steel_chinese_sword",
            () -> new ChineseSwordItem(WeaponTiers.STEEL, new ItemStack(STEEL_CHINESE_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> STEEL_ANCIENT_SWORD = ITEMS.register("steel_ancient_sword",
            () -> new AncientSwordItem(WeaponTiers.STEEL, new ItemStack(STEEL_ANCIENT_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> SILVER_CHINESE_SWORD = ITEMS.register("silver_chinese_sword",
            () -> new ChineseSwordItem(WeaponTiers.SILVER, new ItemStack(SILVER_CHINESE_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> SILVER_ANCIENT_SWORD = ITEMS.register("silver_ancient_sword",
            () -> new AncientSwordItem(WeaponTiers.SILVER, new ItemStack(SILVER_ANCIENT_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> PLATINUM_CHINESE_SWORD = ITEMS.register("platinum_chinese_sword",
            () -> new ChineseSwordItem(WeaponTiers.PLATINUM, new ItemStack(PLATINUM_CHINESE_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> PLATINUM_ANCIENT_SWORD = ITEMS.register("platinum_ancient_sword",
            () -> new AncientSwordItem(WeaponTiers.PLATINUM, new ItemStack(PLATINUM_ANCIENT_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> ELECTRUM_CHINESE_SWORD = ITEMS.register("electrum_chinese_sword",
            () -> new ChineseSwordItem(WeaponTiers.ELECTRUM, new ItemStack(ELECTRUM_CHINESE_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> ELECTRUM_ANCIENT_SWORD = ITEMS.register("electrum_ancient_sword",
            () -> new AncientSwordItem(WeaponTiers.ELECTRUM, new ItemStack(ELECTRUM_ANCIENT_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> INVAR_CHINESE_SWORD = ITEMS.register("invar_chinese_sword",
            () -> new ChineseSwordItem(WeaponTiers.INVAR, new ItemStack(INVAR_CHINESE_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> INVAR_ANCIENT_SWORD = ITEMS.register("invar_ancient_sword",
            () -> new AncientSwordItem(WeaponTiers.INVAR, new ItemStack(INVAR_ANCIENT_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> NICKEL_CHINESE_SWORD = ITEMS.register("nickel_chinese_sword",
            () -> new ChineseSwordItem(WeaponTiers.NICKEL, new ItemStack(NICKEL_CHINESE_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> NICKEL_ANCIENT_SWORD = ITEMS.register("nickel_ancient_sword",
            () -> new AncientSwordItem(WeaponTiers.NICKEL, new ItemStack(NICKEL_ANCIENT_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> LEAD_CHINESE_SWORD = ITEMS.register("lead_chinese_sword",
            () -> new ChineseSwordItem(WeaponTiers.LEAD, new ItemStack(LEAD_CHINESE_SWORD_SHEATH.get())));

    public static final RegistryObject<Item> LEAD_ANCIENT_SWORD = ITEMS.register("lead_ancient_sword",
            () -> new AncientSwordItem(WeaponTiers.LEAD, new ItemStack(LEAD_ANCIENT_SWORD_SHEATH.get())));

}
