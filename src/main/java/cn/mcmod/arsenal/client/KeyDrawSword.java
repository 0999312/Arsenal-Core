//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.mcmod.arsenal.client;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.net.DrawSwordPacket;
import cn.mcmod.arsenal.net.NetPacketHandler;
import com.mojang.blaze3d.platform.InputConstants.Type;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber({Dist.CLIENT})
public class KeyDrawSword {
    public static final KeyMapping KEY = new KeyMapping("key.draw_sword", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, Type.MOUSE, 1, "key.category.arsenal");

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.Key event) {
        if (KEY.isDown() && ArsenalCore.curiosLoaded) {
            NetPacketHandler.INSTANCE.sendToServer(new DrawSwordPacket("Reika Battou"));
        }
    }
}
