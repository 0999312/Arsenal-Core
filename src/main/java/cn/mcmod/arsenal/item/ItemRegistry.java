package cn.mcmod.arsenal.item;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.api.ArsenalAPI;
import cn.mcmod.arsenal.api.toolmaterial.WeaponToolMaterial;
import cn.mcmod.arsenal.item.chinese.AncientSwordItem;
import cn.mcmod.arsenal.item.chinese.ChineseSwordItem;
import cn.mcmod.arsenal.item.chinese.XuanyuanjianItem;
import cn.mcmod.arsenal.item.knight.ArmingSwordItem;
import cn.mcmod.arsenal.item.knight.LongswordItem;
import cn.mcmod.arsenal.item.rapier.RapierItem;
import cn.mcmod.arsenal.item.rapier.SmallswordItem;
import java.util.Map;
import java.util.function.BiFunction;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.commons.lang3.function.TriFunction;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ArsenalCore.MODID);

    private static Map<WeaponToolMaterial, DeferredHolder<Item, Item>> registerSheaths(String suffix) {
        return ArsenalAPI.registerWeaponsAllToolMaterial(tier ->
                registerItem(tier.getUnlocalizedName() + suffix,
                        (props, id) -> new SwordSheathItem(props.stacksTo(1).fireResistant())));
    }

    private static Map<WeaponToolMaterial, DeferredHolder<Item, Item>> registerWeapon(
            String suffix,
            int attackDamage,
            float attackSpeed,
            Map<WeaponToolMaterial, DeferredHolder<Item, Item>> sheathMap,
            TriFunction<WeaponToolMaterial, ItemStack, Item.Properties, ? extends Item> weaponFactory) {

        return ArsenalAPI.registerWeaponsAllToolMaterial(tier -> registerItem(tier.getUnlocalizedName() + suffix,
                (props, id) -> {
                    ItemStack sheathStack = new ItemStack(sheathMap.get(tier).get());
                    return weaponFactory.apply(tier, sheathStack, props);
                }));
    }


    public static final DeferredHolder<Item, WeaponFrogItem> WEAPON_FROG =
            registerItem("weapon_frog", (props, id) -> new WeaponFrogItem(props.stacksTo(1)));

    public static final Map<WeaponToolMaterial, DeferredHolder<Item, Item>> CHINESE_SWORD_SHEATH = registerSheaths("_chinese_sword_sheath");
    public static final Map<WeaponToolMaterial, DeferredHolder<Item, Item>> CHINESE_SWORD = registerWeapon(
            "_chinese_sword", 4, -1.8F, CHINESE_SWORD_SHEATH,
            (material, sheath, properties) -> new ChineseSwordItem(material, 4, -1.8F, sheath, properties.stacksTo(1))
    );

    public static final Map<WeaponToolMaterial, DeferredHolder<Item, Item>> ANCIENT_SWORD_SHEATH = registerSheaths("_ancient_sword_sheath");
    public static final Map<WeaponToolMaterial, DeferredHolder<Item, Item>> ANCIENT_SWORD = registerWeapon(
            "_ancient_sword", 0, 0, ANCIENT_SWORD_SHEATH,
            AncientSwordItem::new
    );

    public static final Map<WeaponToolMaterial, DeferredHolder<Item, Item>> RAPIER_SCABBARD = registerSheaths("_rapier_scabbard");
    public static final Map<WeaponToolMaterial, DeferredHolder<Item, Item>> RAPIER = registerWeapon(
            "_rapier", 0, 0, RAPIER_SCABBARD,
            RapierItem::new
    );

    public static final Map<WeaponToolMaterial, DeferredHolder<Item, Item>> SMALLSWORD_SCABBARD = registerSheaths("_smallsword_scabbard");
    public static final Map<WeaponToolMaterial, DeferredHolder<Item, Item>> SMALLSWORD = registerWeapon(
            "_smallsword", 0, 0, SMALLSWORD_SCABBARD,
            SmallswordItem::new
    );

    public static final Map<WeaponToolMaterial, DeferredHolder<Item, Item>> ARMING_SWORD_SCABBARD = registerSheaths("_arming_sword_scabbard");
    public static final Map<WeaponToolMaterial, DeferredHolder<Item, Item>> ARMING_SWORD = registerWeapon(
            "_arming_sword", 4, -2.4F, ARMING_SWORD_SCABBARD,
            (material, sheath, properties) -> new ArmingSwordItem(material, 4, -2.4F, sheath, properties.stacksTo(1))
    );

    public static final Map<WeaponToolMaterial, DeferredHolder<Item, Item>> LONGSWORD_SCABBARD = registerSheaths("_longsword_scabbard");
    public static final Map<WeaponToolMaterial, DeferredHolder<Item, Item>> LONGSWORD = registerWeapon(
            "_longsword", 0, 0, LONGSWORD_SCABBARD,
            LongswordItem::new
    );

    public static final DeferredHolder<Item, Item> XUANYUANJIAN_SHEATH =
            registerItem("xuanyuanjian_sheath",
                    (props, id) -> new SwordSheathItem(true, props));
    public static final DeferredHolder<Item, ChineseSwordItem> XUANYUANJIAN =
            registerItem("xuanyuanjian",
                    (props, id) -> new XuanyuanjianItem(props));

    public static <T extends Item> DeferredHolder<Item, T> registerItem(String name, BiFunction<Item.Properties, ResourceLocation, T> factory) {
        return ITEMS.register(name, key -> {
            Item.Properties props = new Item.Properties();
            props.setId(ResourceKey.create(Registries.ITEM, key));
            return factory.apply(props, key);
        });
    }
}

