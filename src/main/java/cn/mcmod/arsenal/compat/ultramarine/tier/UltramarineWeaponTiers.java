package cn.mcmod.arsenal.compat.ultramarine.tier;

import cn.mcmod.arsenal.api.tier.BlankTier;
import cn.mcmod.arsenal.api.tier.WeaponTier;
import cn.mcmod.arsenal.util.Lazy;
import com.voxelutopia.ultramarine.data.ModTiers;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class UltramarineWeaponTiers {
    public static final Supplier<WeaponTier> BLUE_AND_WHITE_PORCELAIN = new Lazy<>(() -> new BlankTier("blue_and_white_porcelain", ModTiers.BLUE_AND_WHITE_PORCELAIN, new ResourceLocation("forge:blues_and_white_porcelain")));
}
