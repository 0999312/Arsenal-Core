package cn.mcmod.chinese_sword.net;

import cn.mcmod.chinese_sword.Constants;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetPacketHandler {
    public static SimpleChannel INSTANCE;
    public static final String PROTOCOL_VERSION = "1.0";
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Constants.MODID, "network"),
                () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

        INSTANCE.messageBuilder(DrawSwordPacket.class, nextID(),NetworkDirection.PLAY_TO_SERVER).encoder(DrawSwordPacket::toBytes)
                .decoder(DrawSwordPacket::new).consumer(DrawSwordPacket::handler).add();
    }
}
