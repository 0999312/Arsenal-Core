package cn.mcmod.arsenal.item;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.tier.VanillaWeaponTiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ArsenalCreativeModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ArsenalCore.MODID);

    public static final Supplier<CreativeModeTab> WEAPON_GROUP = CREATIVE_MODE_TABS.register(
            "arsenal_core_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.arsenal_core"))
                    .icon(() -> new ItemStack(ItemRegistry.ANCIENT_SWORD.get(VanillaWeaponTiers.IRON.get()).get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ItemRegistry.WEAPON_FROG.get());

//                        // 添加轩辕剑及其剑鞘
//                        output.accept(ItemRegistry.XUANYUANJIAN.get());
//                        output.accept(ItemRegistry.XUANYUANJIAN_SHEATH.get());

                        // 添加所有中国剑及其剑鞘
                        ItemRegistry.CHINESE_SWORD.forEach((tier, registryObject) -> {
                            output.accept(registryObject.get());
                            output.accept(ItemRegistry.CHINESE_SWORD_SHEATH.get(tier).get());
                        });

                        // 添加所有古代剑及其剑鞘
                        ItemRegistry.ANCIENT_SWORD.forEach((tier, registryObject) -> {
                            output.accept(registryObject.get());
                            output.accept(ItemRegistry.ANCIENT_SWORD_SHEATH.get(tier).get());
                        });

                        // 添加所有刺剑及其剑鞘
                        ItemRegistry.RAPIER.forEach((tier, registryObject) -> {
                            output.accept(registryObject.get());
                            output.accept(ItemRegistry.RAPIER_SCABBARD.get(tier).get());
                        });

                        // 添加所有小剑及其剑鞘
                        ItemRegistry.SMALLSWORD.forEach((tier, registryObject) -> {
                            output.accept(registryObject.get());
                            output.accept(ItemRegistry.SMALLSWORD_SCABBARD.get(tier).get());
                        });

                        // 添加所有骑士剑及其剑鞘
                        ItemRegistry.ARMING_SWORD.forEach((tier, registryObject) -> {
                            output.accept(registryObject.get());
                            output.accept(ItemRegistry.ARMING_SWORD_SCABBARD.get(tier).get());
                        });

                        // 添加所有长剑及其剑鞘
                        ItemRegistry.LONGSWORD.forEach((tier, registryObject) -> {
                            output.accept(registryObject.get());
                            output.accept(ItemRegistry.LONGSWORD_SCABBARD.get(tier).get());
                        });
                    })
                    .build());
}
