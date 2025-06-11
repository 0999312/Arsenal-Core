package cn.mcmod.arsenal.item;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.compat.curios.CuriosCapProvider;
import cn.mcmod.arsenal.compat.curios.ItemHandlerCapProvider;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class WeaponFrogItem extends Item {
    public static final NonNullSupplier<IllegalArgumentException> CAPABILITY_EXCEPTION = () -> new IllegalArgumentException("Capability must not be null!");

    public WeaponFrogItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return ArsenalCore.curiosLoaded ? new CuriosCapProvider(stack, nbt) : new ItemHandlerCapProvider(stack, nbt);
    }

    public static ItemStackHandler getInventory(ItemStack stack) {
        return (ItemStackHandler) stack.getCapability(ForgeCapabilities.ITEM_HANDLER, null).orElseThrow(CAPABILITY_EXCEPTION);
    }
}