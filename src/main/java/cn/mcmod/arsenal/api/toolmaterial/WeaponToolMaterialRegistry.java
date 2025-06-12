package cn.mcmod.arsenal.api.toolmaterial;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Set;


public class WeaponToolMaterialRegistry {
    private static final BiMap<String, WeaponToolMaterial> TIER_REG = HashBiMap.create();

    public static void register(String name, WeaponToolMaterial tier) {
        if (TIER_REG.containsKey(name)) {
            throw new IllegalArgumentException(String.format("The name %s has been registered twice.", name));
        } else {
            TIER_REG.put(name, tier);
        }
    }

    public static void register(WeaponToolMaterial tier) {
        register(tier.getUnlocalizedName(), tier);
    }

    public static void registerAll(WeaponToolMaterial... tiers) {
        for(WeaponToolMaterial tier : tiers) {
            register(tier.getUnlocalizedName(), tier);
        }

    }

    public static Set<WeaponToolMaterial> getWeaponTiers() {
        return TIER_REG.values();
    }

    public static Set<String> getTierName() {
        return TIER_REG.keySet();
    }

    public static WeaponToolMaterial getWeaponTier(String name) {
        return TIER_REG.get(name);
    }

    public static String getTierName(WeaponToolMaterial tier) {
        return TIER_REG.inverse().get(tier);
    }
}
