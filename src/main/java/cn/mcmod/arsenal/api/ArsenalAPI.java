package cn.mcmod.arsenal.api;

import cn.mcmod.arsenal.api.tier.WeaponTier;
import cn.mcmod.arsenal.api.tier.WeaponTierRegistry;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class ArsenalAPI {
    /**
     * 为所有非特殊武器层级注册物品
     * @param valueMapper 值映射函数
     * @return 武器层级到值的映射
     */
    public static <V> Map<WeaponTier, V> registerWeaponsAllTier(Function<WeaponTier, V> valueMapper) {
        return registerWeaponsAllTier(tier -> !tier.isSpecial(), valueMapper);
    }

    /**
     * 为符合条件的武器层级注册物品
     * @param keyPredicate 层级过滤条件
     * @param valueMapper 值映射函数
     * @return 武器层级到值的映射
     */
    public static <V> Map<WeaponTier, V> registerWeaponsAllTier(Predicate<WeaponTier> keyPredicate, Function<WeaponTier, V> valueMapper) {
        return WeaponTierRegistry.getWeaponTiers().stream()
                .filter(keyPredicate)
                .collect(Collectors.toMap(
                        Function.identity(),
                        valueMapper,
                        (v, v2) -> v,
                        Maps::newHashMap
                ));
    }
}
