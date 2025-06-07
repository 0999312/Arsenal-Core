package cn.mcmod.arsenal.event;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.util.EnchantmentUtil;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.LevelEvent;

@EventBusSubscriber(modid = ArsenalCore.MODID)
public class CommonEventHandler {

    @SubscribeEvent
    public static void onLevelLoad(LevelEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            EnchantmentUtil.init(serverLevel.registryAccess());
        }
    }

}
