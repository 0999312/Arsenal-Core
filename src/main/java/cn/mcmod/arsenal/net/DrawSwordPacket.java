package cn.mcmod.arsenal.net;

import cn.mcmod.arsenal.ArsenalCore;

import java.util.Random;

import cn.mcmod.arsenal.api.IDrawable;
import cn.mcmod.arsenal.item.WeaponFrogItem;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

public record DrawSwordPacket(String message) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<DrawSwordPacket> TYPE =
            new CustomPacketPayload.Type<>(new ResourceLocation(ArsenalCore.MODID, "draw_sword"));

    public static final StreamCodec<FriendlyByteBuf, DrawSwordPacket> STREAM_CODEC =
            StreamCodec.composite(
                    net.minecraft.network.codec.ByteBufCodecs.STRING_UTF8,
                    DrawSwordPacket::message,
                    DrawSwordPacket::new
            );

    @Override
    public CustomPacketPayload.Type<DrawSwordPacket> type() {
        return TYPE;
    }

    private static final Random RANDOM = new Random();


    public static void handleDrawSword(DrawSwordPacket packet, IPayloadContext ctx) {
        Player player = ctx.player();
        if (!(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        if (ArsenalCore.curiosLoaded) {
            CuriosApi.getCuriosInventory(serverPlayer).flatMap(inv -> inv.getStacksHandler("belt")).ifPresent(stacksHandler -> {
                IDynamicStackHandler handler = stacksHandler.getStacks();
                for (int i = 0; i < handler.getSlots(); i++) {
                    ItemStack stack = handler.getStackInSlot(i);
                    if (stack.getItem() instanceof WeaponFrogItem) {
                        Level level = serverPlayer.level();
                        ItemStackHandler itemHandler = WeaponFrogItem.getInventory(stack, level);
                        if (itemHandler != null) {
                            if (itemHandler.getStackInSlot(0).isEmpty()) {
                                sheathSword(serverPlayer, itemHandler, stack, level);
                            } else {
                                drawSword(serverPlayer, itemHandler.getStackInSlot(0));
                                itemHandler.extractItem(0, 1, false);
                                WeaponFrogItem.saveInventory(stack, itemHandler, level);
                            }
                        }
                        break;
                    }
                }
            });
        }
    }

    private static void sheathSword(ServerPlayer player, IItemHandler handler, ItemStack weaponFrogStack, Level level) {
        ItemStack mainHandItem = player.getMainHandItem();
        if (mainHandItem.getItem() instanceof IDrawable) {
            handler.insertItem(0, mainHandItem.copy(), false);
            mainHandItem.shrink(1);
            if (handler instanceof ItemStackHandler itemStackHandler) {
                WeaponFrogItem.saveInventory(weaponFrogStack, itemStackHandler, level);
            }
            SoundEvent soundEvent = SoundEvents.ITEM_BREAK;
            Holder<SoundEvent> soundHolder = BuiltInRegistries.SOUND_EVENT.wrapAsHolder(soundEvent);

            player.connection.send(new ClientboundSoundPacket(
                    soundHolder,
                    SoundSource.PLAYERS,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    1.0F,
                    1.0F,
                    RANDOM.nextLong()
            ));
        }
    }

    private static void drawSword(ServerPlayer player, ItemStack blade) {
        if (blade.getItem() instanceof IDrawable sword) {
            ItemStack mainHandItem = player.getMainHandItem().copy();
            player.setItemInHand(InteractionHand.MAIN_HAND, blade.copy());
            if (!mainHandItem.isEmpty() && !player.addItem(mainHandItem)) {
                player.drop(mainHandItem, false);
            }

            if (sword.drawSwordAttack(player, mainHandItem)) {
                SoundEvent soundEvent = SoundEvents.PLAYER_ATTACK_SWEEP;
                Holder<SoundEvent> soundHolder = BuiltInRegistries.SOUND_EVENT.wrapAsHolder(soundEvent);

                player.connection.send(new ClientboundSoundPacket(
                        soundHolder,
                        SoundSource.PLAYERS,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        1.0F,
                        1.0F,
                        RANDOM.nextLong()
                ));
            }
        }
    }
}