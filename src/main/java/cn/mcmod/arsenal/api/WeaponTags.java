package cn.mcmod.arsenal.api;

import cn.mcmod.arsenal.ArsenalCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class WeaponTags {
    public static final TagKey<Item> ANCIENT_SWORD = ItemTags.create(ResourceLocation.fromNamespaceAndPath(ArsenalCore.MODID, "ancient_sword"));
    public static final TagKey<Item> CHINESE_SWORD = ItemTags.create(ResourceLocation.fromNamespaceAndPath(ArsenalCore.MODID, "chinese_sword"));
    public static final TagKey<Item> SMALLSWORD = ItemTags.create(ResourceLocation.fromNamespaceAndPath(ArsenalCore.MODID, "smallsword"));
    public static final TagKey<Item> RAPIER = ItemTags.create(ResourceLocation.fromNamespaceAndPath(ArsenalCore.MODID, "rapier"));
    public static final TagKey<Item> LONGSWORD = ItemTags.create(ResourceLocation.fromNamespaceAndPath(ArsenalCore.MODID, "longsword"));
    public static final TagKey<Item> ARMING_SWORD = ItemTags.create(ResourceLocation.fromNamespaceAndPath(ArsenalCore.MODID, "arming_sword"));
}
