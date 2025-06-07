package cn.mcmod.arsenal.util;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.HashMap;
import java.util.Map;

// ？？？。。。。.......艹
public class EnchantmentUtil {
    private static final Map<ResourceKey<Enchantment>, Holder<Enchantment>> CACHE = new HashMap<>();
    private static HolderLookup.Provider registryAccess;

    /**
     * Initializes the utility with the registry access provider.
     * Must be called before using {@link #getHolder(ResourceKey)}.
     *
     * @param access The registry access provider used to look up enchantments.
     */
    public static void init(HolderLookup.Provider access) {
        registryAccess = access;
        CACHE.clear();
    }

    /**
     * Retrieves the {@link Holder} of the specified enchantment key.
     * Uses a cache to avoid repeated registry lookups.
     *
     * @param key The {@link ResourceKey} identifying the enchantment.
     * @return The cached or newly looked-up {@link Holder} of the enchantment.
     * @throws IllegalStateException if {@link #init(HolderLookup.Provider)} was not called prior to usage.
     */
    public static Holder<Enchantment> getHolder(ResourceKey<Enchantment> key) {
        return CACHE.computeIfAbsent(key, k -> {
            if (registryAccess == null) {
                throw new IllegalStateException("EnchantmentUtil.init() must be called before usage.");
            }
            return registryAccess.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(k);
        });
    }

    /**
     * Modifies the damage dealt by an item stack to a target entity, based on applicable enchantments.
     *
     * @param level         The server world context; can be null.
     * @param stack         The item stack used to deal damage.
     * @param target        The entity receiving damage.
     * @param damageSource  The source of the damage.
     * @param baseDamage    The base damage value before enchantment modifications.
     * @return The damage value after applying enchantment modifiers.
     */
    public static float modifyDamage(ServerLevel level, ItemStack stack, Entity target, DamageSource damageSource, float baseDamage) {
        if (level == null) {
            return baseDamage;
        }
        return EnchantmentHelper.modifyDamage(level, stack, target, damageSource, baseDamage);
    }

}
