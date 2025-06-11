package cn.mcmod.arsenal.net;

import cn.mcmod.arsenal.util.SheathItemAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SheathSyncPacket {
    private final int entityId;
    private final CompoundTag sheathNbt;
    public SheathSyncPacket(int entityId, ItemStack sheath) {
        this.entityId = entityId;
        this.sheathNbt = sheath.save(new CompoundTag());
    }
    public static void encode(SheathSyncPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
        buf.writeNbt(msg.sheathNbt);
    }
    public static SheathSyncPacket decode(FriendlyByteBuf buf) {
        return new SheathSyncPacket(buf.readInt(), ItemStack.of(buf.readNbt()));
    }
    public static void handle(SheathSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientLevel world = Minecraft.getInstance().level;
            if (world != null) {
                Entity entity = world.getEntity(msg.entityId);
                if (entity instanceof SheathItemAccessor accessor) {
                    accessor.arsenal_core_reborn$setSheathItem(ItemStack.of(msg.sheathNbt));
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}