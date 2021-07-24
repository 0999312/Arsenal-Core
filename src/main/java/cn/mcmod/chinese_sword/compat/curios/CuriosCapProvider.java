package cn.mcmod.chinese_sword.compat.curios;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosCapProvider implements ICapabilityProvider {
    private final ICurio capInstance;

    public CuriosCapProvider(ICurio capInstance) {
        this.capInstance = capInstance;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CuriosCapability.ITEM)
            return LazyOptional.of(() -> this.capInstance).cast();
        return LazyOptional.empty();
    }
}
