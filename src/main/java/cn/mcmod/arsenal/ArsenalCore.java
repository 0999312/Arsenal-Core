//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.mcmod.arsenal;

import cn.mcmod.arsenal.api.tier.WeaponTier;
import cn.mcmod.arsenal.api.tier.WeaponTierRegistry;
import cn.mcmod.arsenal.item.ArsenalCreativeModTab;
import cn.mcmod.arsenal.item.ItemRegistry;
import cn.mcmod.arsenal.net.NetPacketHandler;
import cn.mcmod.arsenal.tier.VanillaWeaponTiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

@Mod(ArsenalCore.MODID)
public class ArsenalCore {
    public static final String MODID = "arsenal_core";
    public static boolean curiosLoaded = false;
    private static final Logger LOGGER = LogManager.getLogger();

    public ArsenalCore() {
        curiosLoaded = ModList.get().isLoaded("curios");
        this.registerVanillaTiers();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ArsenalCreativeModTab.CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ArsenalConfig.SPEC);
    }

    private void registerVanillaTiers() {
        WeaponTierRegistry.registerAll(
                VanillaWeaponTiers.WOOD.get(),
                VanillaWeaponTiers.STONE.get(),
                VanillaWeaponTiers.IRON.get(),
                VanillaWeaponTiers.GOLD.get(),
                VanillaWeaponTiers.DIAMOND.get(),
                VanillaWeaponTiers.NETHERITE.get(),
                VanillaWeaponTiers.MAXIMUM_POWER.get());
    }

    private void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(NetPacketHandler::registerMessage);
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}