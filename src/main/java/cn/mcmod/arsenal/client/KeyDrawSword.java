//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.mcmod.arsenal.client;

import cn.mcmod.arsenal.ArsenalCore;
import cn.mcmod.arsenal.net.DrawSwordPacket;
import com.mojang.blaze3d.platform.InputConstants.Type;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(Dist.CLIENT)
public class KeyDrawSword {
    public static final KeyMapping KEY = new KeyMapping("key.draw_sword", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, Type.MOUSE, 1, "key.category.arsenal");

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.Key event) {
        if (KEY.isDown() && ArsenalCore.curiosLoaded) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                PacketDistributor.sendToServer(new DrawSwordPacket("Reika Battou"));
            }
        }
    }
}
