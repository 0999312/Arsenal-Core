package cn.mcmod.arsenal.net;

import cn.mcmod.arsenal.ArsenalCore;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = ArsenalCore.MODID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkHandler {

    @SubscribeEvent
    public static void onPayloadRegister(RegisterPayloadHandlersEvent event) {

        PayloadRegistrar registrar = event.registrar("1.0");

        registrar.playToServer(DrawSwordPacket.TYPE, DrawSwordPacket.STREAM_CODEC, DrawSwordPacket::handleDrawSword);
    }
}