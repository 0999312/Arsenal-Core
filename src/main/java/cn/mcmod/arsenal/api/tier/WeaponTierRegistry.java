package cn.mcmod.arsenal.api.tier;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Set;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.TierSortingRegistry;
import java.util.List;

public class WeaponTierRegistry {
    private static final BiMap<String, WeaponTier> TIER_REG = HashBiMap.create();

    public static void register(String name, WeaponTier tier) {
        if (TIER_REG.containsKey(name)) {
            throw new IllegalArgumentException(String.format("The name %s has been registered twice.", name));
        } else {
            TIER_REG.put(name, tier);
        }
    }

    public static void register(WeaponTier tier) {
        register(tier.getUnlocalizedName(), tier);
    }

    public static void registerAll(WeaponTier... tiers) {
        for(WeaponTier tier : tiers) {
            register(tier.getUnlocalizedName(), tier);
        }

    }

    public static Set<WeaponTier> getWeaponTiers() {
        return TIER_REG.values();
    }

    public static Set<String> getTierName() {
        return TIER_REG.keySet();
    }

    public static WeaponTier getWeaponTier(String name) {
        return TIER_REG.get(name);
    }

    public static String getTierName(WeaponTier tier) {
        return TIER_REG.inverse().get(tier);
    }

    /**
     * 注册武器层级到TierSortingRegistry
     * @param tier 要注册的层级
     * @param name 层级的资源位置名称
     * @param after 此层级应放在哪些层级之后（这些层级将被视为较低层级）
     * @param before 此层级应放在哪些层级之前（这些层级将被视为较高层级）
     */
    public static void registerToSorting(WeaponTier tier, ResourceLocation name, List<Object> after, List<Object> before) {
        TierSortingRegistry.registerTier(tier, name, after, before);
    }

    /**
     * 使用默认设置注册武器层级到TierSortingRegistry
     * @param tier 要注册的层级
     */
    public static void registerToSorting(WeaponTier tier) {
        ResourceLocation name = new ResourceLocation("arsenal_core", tier.getUnlocalizedName());
        TierSortingRegistry.registerTier(tier, name, List.of(), List.of());
    }

    /**
     * 批量注册武器层级到TierSortingRegistry
     * @param tiers 要注册的层级数组
     */
    public static void registerAllToSorting(WeaponTier... tiers) {
        for(WeaponTier tier : tiers) {
            registerToSorting(tier);
        }
    }
}
