package cn.mcmod.arsenal.net;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetPacketHandler {
    public static SimpleChannel INSTANCE;
    public static final String PROTOCOL_VERSION = "1.0";
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation("arsenal_core", "network"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        INSTANCE.messageBuilder(DrawSwordPacket.class, nextID(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(DrawSwordPacket::toBytes)
                .decoder(DrawSwordPacket::new)
                .consumerMainThread(DrawSwordPacket::handler)
                .add();

        INSTANCE.messageBuilder(XuanyuanSkillPacket.class, nextID(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(XuanyuanSkillPacket::toBytes)
                .decoder(XuanyuanSkillPacket::new)
                .consumerMainThread(XuanyuanSkillPacket::handler)
                .add();

        INSTANCE.registerMessage(
                nextID(),
                SheathSyncPacket.class,
                SheathSyncPacket::encode,
                SheathSyncPacket::decode,
                SheathSyncPacket::handle
        );
    }
}