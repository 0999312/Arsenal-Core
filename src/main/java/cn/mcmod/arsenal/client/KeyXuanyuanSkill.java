package cn.mcmod.arsenal.client;

import cn.mcmod.arsenal.net.NetPacketHandler;
import cn.mcmod.arsenal.net.XuanyuanSkillPacket;
import com.mojang.blaze3d.platform.InputConstants.Type;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber({Dist.CLIENT})
public class KeyXuanyuanSkill {
    public static final KeyMapping XUANYUAN_SKILL_KEY = new KeyMapping("key.xuanyuan_skill", KeyConflictContext.IN_GAME, KeyModifier.NONE, Type.KEYSYM, 82, "key.category.arsenal"); // R键

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.Key event) {
        if (XUANYUAN_SKILL_KEY.consumeClick()) {
            NetPacketHandler.INSTANCE.sendToServer(new XuanyuanSkillPacket());
        }
    }
}