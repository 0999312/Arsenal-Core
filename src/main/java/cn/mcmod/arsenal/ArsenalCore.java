package cn.mcmod.arsenal;

import cn.mcmod.arsenal.api.tier.WeaponTierRegistry;
import cn.mcmod.arsenal.compat.ultramarine.UltramarineCompat;
import cn.mcmod.arsenal.event.CommonEventHandler;
import cn.mcmod.arsenal.item.ArsenalCreativeModTab;
import cn.mcmod.arsenal.item.ItemRegistry;
import cn.mcmod.arsenal.net.NetPacketHandler;
import cn.mcmod.arsenal.tier.VanillaWeaponTiers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(ArsenalCore.MODID)
public class ArsenalCore {
    public static final String MODID = "arsenal_core";
    public static boolean curiosLoaded = false;
    private static final Logger LOGGER = LogManager.getLogger();

    public ArsenalCore() {
        curiosLoaded = ModList.get().isLoaded("curios");
        UltramarineCompat.registerUltramarineTiers();
        this.registerVanillaTiers();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ArsenalCreativeModTab.CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ArsenalConfig.SPEC);
        MinecraftForge.EVENT_BUS.register(CommonEventHandler.class);
    }

    private void registerVanillaTiers() {
        WeaponTierRegistry.registerAll(
                VanillaWeaponTiers.WOOD.get(),
                VanillaWeaponTiers.STONE.get(),
                VanillaWeaponTiers.IRON.get(),
                VanillaWeaponTiers.GOLD.get(),
                VanillaWeaponTiers.DIAMOND.get(),
                VanillaWeaponTiers.NETHERITE.get(),
                VanillaWeaponTiers.MAXIMUM_POWER.get()
        );
    }

    private void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(NetPacketHandler::registerMessage);
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}