package cn.mcmod.arsenal.data;

import cn.mcmod.arsenal.ArsenalCore;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class AttachmentRegistry {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, ArsenalCore.MODID);

    public static final Supplier<AttachmentType<ItemStackHandler>> ITEM_HANDLER =
            ATTACHMENT_TYPES.register("item_handler",
                    () -> AttachmentType.serializable(() -> new ItemStackHandler(1)).build());

    public static void register(IEventBus modBus) {
        ATTACHMENT_TYPES.register(modBus);
    }
}