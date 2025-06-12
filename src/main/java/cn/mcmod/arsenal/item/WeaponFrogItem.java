package cn.mcmod.arsenal.item;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.data.ComponentRegistry;
import cn.mcmod.arsenal.compat.curios.CuriosCapProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class WeaponFrogItem extends Item {
    public WeaponFrogItem(Properties properties) {
        super(properties);
    }

    public static void initAttachments(ItemStack stack, Level level) {
        if (!stack.has(ComponentRegistry.ITEM_HANDLER_COMPONENT.get())) {
            ItemStackHandler handler = new ItemStackHandler(1);
            if (level != null) {
                HolderLookup.Provider registries = level.registryAccess();
                CompoundTag nbt = handler.serializeNBT(registries);
                stack.set(ComponentRegistry.ITEM_HANDLER_COMPONENT.get(), new ComponentRegistry.ItemHandlerData(nbt));
            } else {
                stack.set(ComponentRegistry.ITEM_HANDLER_COMPONENT.get(), new ComponentRegistry.ItemHandlerData(new CompoundTag()));
            }
        }
        if (ArsenalCore.curiosLoaded) {
            CuriosCapProvider.attachCurio(stack, level);
        }
    }

    public static ItemStackHandler getInventory(ItemStack stack, Level level) {
        ComponentRegistry.ItemHandlerData data = stack.get(ComponentRegistry.ITEM_HANDLER_COMPONENT.get());
        ItemStackHandler handler = new ItemStackHandler(1);

        if (data != null && level != null) {
            try {
                HolderLookup.Provider registries = level.registryAccess();
                handler.deserializeNBT(registries, data.data());
            } catch (Exception e) {
                handler = new ItemStackHandler(1);
            }
        }

        return handler;
    }

    public static void saveInventory(ItemStack stack, ItemStackHandler handler, Level level) {
        if (level != null) {
            try {
                HolderLookup.Provider registries = level.registryAccess();
                CompoundTag nbt = handler.serializeNBT(registries);
                stack.set(ComponentRegistry.ITEM_HANDLER_COMPONENT.get(), new ComponentRegistry.ItemHandlerData(nbt));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCraftedBy(ItemStack stack, Player player) {
        super.onCraftedBy(stack, player);
        initAttachments(stack, player.level());
    }


    @Override
    public void inventoryTick (ItemStack stack, ServerLevel serverLevel, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, serverLevel, entity, slot);
        if (!stack.has(ComponentRegistry.ITEM_HANDLER_COMPONENT.get())) {
            initAttachments(stack, serverLevel);
        }
    }
}