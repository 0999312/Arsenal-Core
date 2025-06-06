//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.mcmod.arsenal.client;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.compat.curios.client.WeaponFrogRender;
import cn.mcmod.arsenal.item.ItemRegistry;
import cn.mcmod.arsenal.item.chinese.AncientSwordItem;
import cn.mcmod.arsenal.item.chinese.ChineseSwordItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(modid = ArsenalCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemRegistry.ITEMS.getEntries().forEach(ClientEventHandler::registerBlockingProperties));
        if (ArsenalCore.curiosLoaded) {
            registerCuriosRenderer();
        }
    }

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(KeyDrawSword.KEY);
    }

    private static void registerCuriosRenderer() {
        CuriosRendererRegistry.register(ItemRegistry.WEAPON_FROG.get(), WeaponFrogRender::new);
    }

    private static void registerBlockingProperties(DeferredHolder<Item,? extends Item> item) {
        if (item.get() instanceof AncientSwordItem || item.get() instanceof ChineseSwordItem) {
            ItemProperties.register(item.get(), new ResourceLocation("blocking"), (stack, level, entity, seed) ->
                    entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
        }
    }
}