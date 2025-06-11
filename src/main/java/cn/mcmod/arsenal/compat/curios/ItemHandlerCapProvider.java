package cn.mcmod.arsenal.compat.curios;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

public class ItemHandlerCapProvider implements ICapabilitySerializable<CompoundTag> {
    public ItemStack stack;
    public final ItemStackHandler handler;

    public ItemHandlerCapProvider(ItemStack stack, CompoundTag nbt) {
        this.stack = stack;
        this.handler = new ItemStackHandler();
    }

    public ItemStackHandler getInventory() {
        return this.handler;
    }

    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == ForgeCapabilities.ITEM_HANDLER ? LazyOptional.of(() -> this.handler).cast() : LazyOptional.empty();
    }

    public CompoundTag serializeNBT() {
        return this.handler.serializeNBT();
    }

    public void deserializeNBT(CompoundTag nbt) {
        this.handler.deserializeNBT(nbt);
    }
}