package cn.mcmod.arsenal.mixin;

import cn.mcmod.arsenal.item.SwordSheathItem;
import cn.mcmod.arsenal.item.chinese.ChineseSwordItem;
import cn.mcmod.arsenal.item.knight.ArmingSwordItem;
import cn.mcmod.arsenal.item.rapier.RapierItem;
import cn.mcmod.arsenal.net.NetPacketHandler;
import cn.mcmod.arsenal.net.SheathSyncPacket;
import cn.mcmod.arsenal.util.SheathItemAccessor;
import mods.flammpfeil.slashblade.entity.BladeStandEntity;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BladeStandEntity.class)
public  class BladeStandEntityMixin implements SheathItemAccessor {

    @Unique
    private static final EntityDataAccessor<ItemStack> SHEATH_ITEM =
            SynchedEntityData.defineId(BladeStandEntity.class, EntityDataSerializers.ITEM_STACK);


    @Unique
    private ItemStack arsenal_core_reborn$sheathItem = ItemStack.EMPTY;



    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void onInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        BladeStandEntity self = (BladeStandEntity) (Object) this;
        ItemFrame frame = (ItemFrame)(Object)this;
        Entity entity = (Entity)(Object)this;
        
        if (!self.level().isClientSide() && hand == InteractionHand.MAIN_HAND) {
            ItemStack itemstack = player.getItemInHand(hand);
            
            if (player.isShiftKeyDown() && !frame.getItem().isEmpty()) {
                return;
            }

            boolean isSupportedSword = itemstack.getItem() instanceof ItemSlashBlade ||
                                     itemstack.getItem() instanceof ChineseSwordItem ||
                                     itemstack.getItem() instanceof ArmingSwordItem ||
                                     itemstack.getItem() instanceof RapierItem;

            boolean isSheath = itemstack.getItem() instanceof SwordSheathItem;

            if (!itemstack.isEmpty() && (isSupportedSword || isSheath)) {
                if (isSheath) {
                    if (!frame.getItem().isEmpty() && this.arsenal_core_reborn$getSheathItem().isEmpty()) {
                        this.arsenal_core_reborn$setSheathItem(itemstack.copy());
                        if (!player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                        entity.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
                        cir.setReturnValue(InteractionResult.SUCCESS);
                    }
                } else if (isSupportedSword) {
                    if (frame.getItem().isEmpty()) {
                        if (!entity.isRemoved()) {
                            frame.setItem(itemstack);
                            if (!player.getAbilities().instabuild) {
                                itemstack.shrink(1);
                            }
                            entity.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
                            cir.setReturnValue(InteractionResult.SUCCESS);
                        }
                    } else {
                        ItemStack displayed = frame.getItem().copy();
                        frame.setItem(itemstack);
                        player.setItemInHand(hand, displayed);
                        entity.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1.0F, 1.0F);
                        cir.setReturnValue(InteractionResult.SUCCESS);
                    }
                }
            } else if (itemstack.isEmpty() && (!frame.getItem().isEmpty() || !this.arsenal_core_reborn$sheathItem.isEmpty())) {
                if (!this.arsenal_core_reborn$sheathItem.isEmpty()) {
                    player.setItemInHand(hand, this.arsenal_core_reborn$sheathItem.copy());
                    this.arsenal_core_reborn$sheathItem = ItemStack.EMPTY;
                    entity.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1.0F, 1.0F);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                } else if (!frame.getItem().isEmpty()) {
                    player.setItemInHand(hand, frame.getItem().copy());
                    frame.setItem(ItemStack.EMPTY);
                    entity.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1.0F, 1.0F);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                }
            }
        }
    }


    public void arsenal_core_reborn$setSheathItem(ItemStack stack) {
        this.arsenal_core_reborn$sheathItem = stack;
        if (!((Entity)(Object)this).level().isClientSide()) {
            SheathSyncPacket pkt = new SheathSyncPacket(((Entity)(Object)this).getId(), stack);
            NetPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> (Entity)(Object)this), pkt);
        }
    }

    @Override
    public ItemStack arsenal_core_reborn$getSheathItem() {
        return arsenal_core_reborn$sheathItem;
    }


    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void onSave(CompoundTag compound, CallbackInfo ci) {
        ItemStack sheathItem = this.arsenal_core_reborn$getSheathItem();
        if (!sheathItem.isEmpty()) {
            CompoundTag sheathTag = new CompoundTag();
            sheathItem.save(sheathTag);
            compound.put("SheathItem", sheathTag);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void onLoad(CompoundTag compound, CallbackInfo ci) {
        if (compound.contains("SheathItem")) {
            CompoundTag sheathTag = compound.getCompound("SheathItem");
            this.arsenal_core_reborn$setSheathItem(ItemStack.of(sheathTag));
        } else {
            this.arsenal_core_reborn$setSheathItem(ItemStack.EMPTY);
        }
    }
}