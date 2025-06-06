package cn.mcmod.arsenal.item;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.data.AttachmentRegistry;
import cn.mcmod.arsenal.compat.curios.CuriosCapProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;

public class WeaponFrogItem extends Item {
    public WeaponFrogItem() {
        super(new Properties().stacksTo(1));
    }

    public static void initAttachments(ItemStack stack) {
        ItemStackHandler handler = stack.getData(AttachmentRegistry.ITEM_HANDLER);
        if (ArsenalCore.curiosLoaded) {
            CuriosCapProvider.attachCurio(stack);
        }
    }

    public static ItemStackHandler getInventory(ItemStack stack) {
        return stack.getData(AttachmentRegistry.ITEM_HANDLER);
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);
        initAttachments(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (!stack.hasData(AttachmentRegistry.ITEM_HANDLER)) {
            initAttachments(stack);
        }
    }
}