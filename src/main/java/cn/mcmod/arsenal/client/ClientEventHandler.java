package cn.mcmod.arsenal.client;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.compat.curios.client.WeaponFrogRender;
import cn.mcmod.arsenal.item.ItemRegistry;
import cn.mcmod.arsenal.item.chinese.AncientSwordItem;
import cn.mcmod.arsenal.item.chinese.ChineseSwordItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@EventBusSubscriber(
        bus = Bus.MOD,
        value = {Dist.CLIENT}
)
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
        event.register(KeyDrawSword.DRAW_SWORD_KEY);
        event.register(KeyXuanyuanSkill.XUANYUAN_SKILL_KEY);
    }

    private static void registerCuriosRenderer() {
        CuriosRendererRegistry.register(ItemRegistry.WEAPON_FROG.get(), WeaponFrogRender::new);
    }

    private static void registerBlockingProperties(RegistryObject<Item> item) {
        if (item.get() instanceof AncientSwordItem || item.get() instanceof ChineseSwordItem) {
            ItemProperties.register(item.get(), new ResourceLocation("blocking"), (stack, level, entity, seed) ->
                    entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
        }
    }
}