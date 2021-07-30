package cn.mcmod.chinese_sword.data;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        // 合成表Data Generator, 类型为合成台的有序合成。
        event.getGenerator().addProvider(new SwordRecipes(event.getGenerator()));
        // 生成中式剑模型的Data Generator, 提供了 public 的两种剑的Model Builder.
        // 物品模型大量生成的典型案例。
        event.getGenerator().addProvider(new SwordModelProvider(event.getGenerator(), event.getExistingFileHelper()));
        // 生成中式剑Tag的Data Generator, 提供了 public 的两种剑的Tag Builder.
        // 生成非 Forge 和 Minecraft 命名域的Tag的典型案例。
        event.getGenerator().addProvider(new SwordTagsProvider(event.getGenerator(), event.getExistingFileHelper()));
    }
}
