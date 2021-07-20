package cn.mcmod.chinese_sword.client;

import cn.mcmod.chinese_sword.item.AncientSwordItem;
import cn.mcmod.chinese_sword.item.ChineseSwordItem;
import cn.mcmod.chinese_sword.item.ItemRegistry;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {
	@OnlyIn(Dist.CLIENT)
    @SubscribeEvent
	public static void setupClient(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
//			进行批量注册Item Property Override
			ItemRegistry.ITEMS.getEntries().forEach((item) -> {
//				判断物品类型
				if (item.get() instanceof AncientSwordItem || item.get() instanceof ChineseSwordItem)
//					注册原版的blocking Property
					ItemModelsProperties.register(item.get(), new ResourceLocation("blocking"),
							(itemStack, clientWorld, livingEntity) -> {
								return livingEntity != null && livingEntity.isUsingItem()
										&& livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F;
							});
			});
		});
		registerKey(event);
	}
	private static void registerKey(final FMLClientSetupEvent event) {
		 event.enqueueWork(() -> ClientRegistry.registerKeyBinding(KeyDrawSword.KEY));
	}
}
