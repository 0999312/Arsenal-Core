package cn.mcmod.arsenal.compat.curios;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosCapProvider extends ItemHandlerCapProvider {
    private final ICurio capInstance;

    public CuriosCapProvider(ItemStack stack, CompoundTag nbt) {
        super(stack, nbt);
        this.capInstance = new CuriosWrapper(stack);
    }

    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == CuriosCapability.ITEM ? LazyOptional.of(this::getCuriosInstance).cast() : super.getCapability(cap, side);
    }

    public ICurio getCuriosInstance() {
        return this.capInstance;
    }
}
