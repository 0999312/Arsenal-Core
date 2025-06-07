package cn.mcmod.arsenal.client;

import cn.mcmod.arsenal.ArsenalCore;

import cn.mcmod.arsenal.compat.curios.client.WeaponFrogRender;
import cn.mcmod.arsenal.item.ItemRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@EventBusSubscriber(modid = ArsenalCore.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        if (ArsenalCore.curiosLoaded) {
            registerCuriosRenderer();
        }
        event.register(KeyDrawSword.KEY);
    }
    private static void registerCuriosRenderer() {
        CuriosRendererRegistry.register(ItemRegistry.WEAPON_FROG.get(), WeaponFrogRender::new);
    }
}