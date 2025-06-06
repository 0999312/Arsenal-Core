package cn.mcmod.arsenal.compat.curios;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.data.AttachmentRegistry;
import cn.mcmod.arsenal.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.ItemStackHandler;
import top.theillusivec4.curios.api.CuriosCapability;

public class CuriosCapProvider {

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        if (ArsenalCore.curiosLoaded) {
            event.registerItem(CuriosCapability.ITEM, (stack, context) -> new CuriosWrapper(stack), ItemRegistry.WEAPON_FROG.get());
        }
    }

    public static void attachCurio(ItemStack stack) {
        if (!stack.hasData(AttachmentRegistry.ITEM_HANDLER)) {
            stack.setData(AttachmentRegistry.ITEM_HANDLER, new ItemStackHandler(1));
        }
    }
}