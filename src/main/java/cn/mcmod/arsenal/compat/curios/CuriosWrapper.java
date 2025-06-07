package cn.mcmod.arsenal.compat.curios;

import cn.mcmod.arsenal.item.WeaponFrogItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosWrapper implements ICurio {
    private final ItemStack sheath;

    public CuriosWrapper(ItemStack sheath) {
        this.sheath = sheath;
    }

    @Override
    public ICurio.SoundInfo getEquipSound(SlotContext slotContext) {
        return new ICurio.SoundInfo(SoundEvents.ARMOR_EQUIP_GENERIC.value(), 1.0F, 1.0F);
    }

    @Override
    public boolean canSync(SlotContext slotContext) {
        return true;
    }

    @Override
    public void readSyncData(SlotContext slotContext, CompoundTag nbt) {
        try {
            Level level = slotContext.entity().level();
            HolderLookup.Provider registries = level.registryAccess();
            ItemStackHandler handler = new ItemStackHandler(1);
            handler.deserializeNBT(registries, nbt);
            WeaponFrogItem.saveInventory(this.sheath, handler, level);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CompoundTag writeSyncData(SlotContext slotContext) {
        try {
            Level level = slotContext.entity().level();
            HolderLookup.Provider registries = level.registryAccess();
            ItemStackHandler handler = WeaponFrogItem.getInventory(this.sheath, level);
            return handler.serializeNBT(registries);
        } catch (Exception e) {
            e.printStackTrace();
            return new CompoundTag();
        }
    }

    @Override
    public ItemStack getStack() {
        return this.sheath;
    }
}