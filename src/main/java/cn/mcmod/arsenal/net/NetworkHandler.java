package cn.mcmod.arsenal.net;


import cn.mcmod.arsenal.ArsenalCore;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

@Mod.EventBusSubscriber(modid = ArsenalCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkHandler {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlerEvent event) {
        IPayloadRegistrar registrar = event.registrar(ArsenalCore.MODID).versioned("1.0");

        registrar.play(DrawSwordPacket.ID, DrawSwordPacket::new,
                handler -> handler.server(DrawSwordPacket::handleDrawSword));
    }
}