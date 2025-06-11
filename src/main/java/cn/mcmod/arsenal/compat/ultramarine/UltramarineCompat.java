package cn.mcmod.arsenal.compat.ultramarine;

import cn.mcmod.arsenal.api.tier.WeaponTierRegistry;
import cn.mcmod.arsenal.compat.ultramarine.tier.UltramarineWeaponTiers;
import net.minecraftforge.fml.ModList;

public class UltramarineCompat {

    public static boolean ultramarineLoaded = ModList.get().isLoaded("ultramarine");
    public static void registerUltramarineTiers() {
        if (ultramarineLoaded) {
            WeaponTierRegistry.registerAll(
                    UltramarineWeaponTiers.BLUE_AND_WHITE_PORCELAIN.get()
            );
        }
    }
}
