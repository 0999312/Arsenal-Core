package cn.mcmod.arsenal.net;

import cn.mcmod.arsenal.api.WeaponProgressComponent;
import cn.mcmod.arsenal.item.chinese.XuanyuanjianItem;
import cn.mcmod.arsenal.item.feature.XuanyuanFeature;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class XuanyuanSkillPacket {
    
    public XuanyuanSkillPacket() {
    }
    
    public XuanyuanSkillPacket(FriendlyByteBuf buffer) {
    }
    
    public void toBytes(FriendlyByteBuf buf) {
    }
    
    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack mainHand = player.getItemInHand(InteractionHand.MAIN_HAND);
                if (mainHand.getItem() instanceof XuanyuanjianItem) {
                    WeaponProgressComponent progress = WeaponProgressComponent.fromItemStack(mainHand);

                    if (progress.getLevel() >= 5 && progress.getCooldown() <= 0) {
                        XuanyuanFeature.triggerTianguangSkill(player, mainHand);

                        progress.setCooldown(400);
                        progress.saveToItemStack(mainHand);
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}