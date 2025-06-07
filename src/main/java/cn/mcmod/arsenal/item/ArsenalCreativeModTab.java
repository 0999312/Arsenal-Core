package cn.mcmod.arsenal.item;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.tier.VanillaWeaponToolMaterials;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ArsenalCreativeModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ArsenalCore.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WEAPON_GROUP = CREATIVE_MODE_TABS.register(
            "arsenal_core_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.arsenal_core"))
                    .icon(() -> new ItemStack(ItemRegistry.ANCIENT_SWORD.get(VanillaWeaponToolMaterials.IRON.get()).get()))
                    .displayItems((parameters, output) -> {
//                        output.accept(ItemRegistry.XUANYUANJIAN.get());
//                        output.accept(ItemRegistry.XUANYUANJIAN_SHEATH.get());

                        output.accept(ItemRegistry.WEAPON_FROG.get());
                        ItemRegistry.CHINESE_SWORD.forEach((tier, registryObject) -> {
                            output.accept(registryObject.get());
                            output.accept(ItemRegistry.CHINESE_SWORD_SHEATH.get(tier).get());
                        });
                        ItemRegistry.ANCIENT_SWORD.forEach((tier, registryObject) -> {
                            output.accept(registryObject.get());
                            output.accept(ItemRegistry.ANCIENT_SWORD_SHEATH.get(tier).get());
                        });
                        ItemRegistry.RAPIER.forEach((tier, registryObject) -> {
                            output.accept(registryObject.get());
                            output.accept(ItemRegistry.RAPIER_SCABBARD.get(tier).get());
                        });
                        ItemRegistry.SMALLSWORD.forEach((tier, registryObject) -> {
                            output.accept(registryObject.get());
                            output.accept(ItemRegistry.SMALLSWORD_SCABBARD.get(tier).get());
                        });
                        ItemRegistry.ARMING_SWORD.forEach((tier, registryObject) -> {
                            output.accept(registryObject.get());
                            output.accept(ItemRegistry.ARMING_SWORD_SCABBARD.get(tier).get());
                        });
                        ItemRegistry.LONGSWORD.forEach((tier, registryObject) -> {
                            output.accept(registryObject.get());
                            output.accept(ItemRegistry.LONGSWORD_SCABBARD.get(tier).get());
                        });
                    })
                    .build());
}
