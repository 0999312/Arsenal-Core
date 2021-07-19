package cn.mcmod.chinese_sword;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.mcmod.chinese_sword.item.ItemRegistry;

@Mod("chinese_sword")
public class Main {
	public static boolean curiosLoaded = false;
	private static final Logger LOGGER = LogManager.getLogger();
	public static final ItemGroup WEAPON_GROUP = new ItemGroup("chinese_sword") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegistry.IRON_CHINESE_SWORD.get());
		}
	};

	public Main() {
		curiosLoaded = ModList.get().isLoaded("curios");

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);

		ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}


	private void enqueueIMC(final InterModEnqueueEvent event) {
		if (curiosLoaded) {
			InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
					() -> SlotTypePreset.BELT.getMessageBuilder().build());
		}
	}

	public static Logger getLogger() {
		return LOGGER;
	}

}
