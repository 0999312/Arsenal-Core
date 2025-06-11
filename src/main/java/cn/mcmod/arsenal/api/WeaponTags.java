package cn.mcmod.arsenal.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class WeaponTags {
    public static final TagKey<Item> ANCIENT_SWORD = ItemTags.create(new ResourceLocation("arsenal_core", "ancient_sword"));
    public static final TagKey<Item> CHINESE_SWORD = ItemTags.create(new ResourceLocation("arsenal_core", "chinese_sword"));
    public static final TagKey<Item> SMALLSWORD = ItemTags.create(new ResourceLocation("arsenal_core", "smallsword"));
    public static final TagKey<Item> RAPIER = ItemTags.create(new ResourceLocation("arsenal_core", "rapier"));
    public static final TagKey<Item> LONGSWORD = ItemTags.create(new ResourceLocation("arsenal_core", "longsword"));
    public static final TagKey<Item> ARMING_SWORD = ItemTags.create(new ResourceLocation("arsenal_core", "arming_sword"));
}
