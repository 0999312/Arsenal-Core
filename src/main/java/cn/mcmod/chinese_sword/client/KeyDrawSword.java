package cn.mcmod.chinese_sword.client;

import org.lwjgl.glfw.GLFW;

import cn.mcmod.chinese_sword.ChineseSword;
import cn.mcmod.chinese_sword.net.DrawSwordPacket;
import cn.mcmod.chinese_sword.net.NetPacketHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyDrawSword {
    public static final KeyBinding KEY = new KeyBinding("key.draw_sword", KeyConflictContext.IN_GAME,
            KeyModifier.CONTROL, InputMappings.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_2, "key.category.chinese_sword");

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if (KEY.isDown()) {
            // 实际值无意义，测试网络包的彩蛋
            // 烈火，拔刀！——火炎剑 烈火
            if (ChineseSword.curiosLoaded)
                NetPacketHandler.INSTANCE.sendToServer(new DrawSwordPacket("Reika Battou"));
        }
    }
}
