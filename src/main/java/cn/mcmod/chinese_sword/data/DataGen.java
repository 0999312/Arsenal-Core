package cn.mcmod.chinese_sword.data;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        // 鞘的合成表Data Generator, 类型为合成台的有序合成。
        event.getGenerator().addProvider(new SheathRecipes(event.getGenerator()));
        // 锻造台合成表Data Generator, 类型为锻造台的合成，合成只有左右两格。
        // 比铁砧更加优雅！
        event.getGenerator().addProvider(new SmithRecipes(event.getGenerator()));
    }
}
