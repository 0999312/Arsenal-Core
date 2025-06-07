package cn.mcmod.arsenal;

import cn.mcmod.arsenal.api.tier.WeaponTierRegistry;
import cn.mcmod.arsenal.compat.curios.CuriosCapProvider;
import cn.mcmod.arsenal.data.ComponentRegistry;
import cn.mcmod.arsenal.item.ArsenalCreativeModTab;
import cn.mcmod.arsenal.item.ItemRegistry;
import cn.mcmod.arsenal.tier.VanillaWeaponToolMaterials;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(ArsenalCore.MODID)
public class ArsenalCore {
    public static final String MODID = "arsenal_core";
    public static boolean curiosLoaded = false;
    private static final Logger LOGGER = LogManager.getLogger();

    public ArsenalCore(IEventBus modEventBus) {
        curiosLoaded = ModList.get().isLoaded("curios");
        this.registerVanillaTiers();
        ItemRegistry.ITEMS.register(modEventBus);
        ComponentRegistry.register(modEventBus);
        modEventBus.addListener(CuriosCapProvider::registerCapabilities);
        ArsenalCreativeModTab.CREATIVE_MODE_TABS.register(modEventBus);

        ModList.get().getModContainerById(MODID).ifPresent(container -> {
            container.registerConfig(ModConfig.Type.COMMON, ArsenalConfig.SPEC);
        });
    }


    private void registerVanillaTiers() {
        WeaponTierRegistry.registerAll(
                VanillaWeaponToolMaterials.WOOD.get(),
                VanillaWeaponToolMaterials.STONE.get(),
                VanillaWeaponToolMaterials.IRON.get(),
                VanillaWeaponToolMaterials.GOLD.get(),
                VanillaWeaponToolMaterials.DIAMOND.get(),
                VanillaWeaponToolMaterials.NETHERITE.get(),
                VanillaWeaponToolMaterials.MAXIMUM_POWER.get());
    }


    public static Logger getLogger() {
        return LOGGER;
    }
}