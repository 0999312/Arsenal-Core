package cn.mcmod.arsenal.compat.curios;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.data.ComponentRegistry;
import cn.mcmod.arsenal.item.ItemRegistry;
import cn.mcmod.arsenal.item.WeaponFrogItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import top.theillusivec4.curios.api.CuriosCapability;

public class CuriosCapProvider {

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        if (ArsenalCore.curiosLoaded) {
            event.registerItem(CuriosCapability.ITEM, (stack, context) -> new CuriosWrapper(stack), ItemRegistry.WEAPON_FROG.get());
        }
    }

    public static void attachCurio(ItemStack stack, Level level) {
        if (!stack.has(ComponentRegistry.ITEM_HANDLER_COMPONENT.get())) {
            WeaponFrogItem.initAttachments(stack, level);
        }
    }
}