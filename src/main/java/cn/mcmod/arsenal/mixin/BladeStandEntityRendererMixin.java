package cn.mcmod.arsenal.mixin;

import cn.mcmod.arsenal.item.chinese.AncientSwordItem;
import cn.mcmod.arsenal.item.chinese.ChineseSwordItem;
import cn.mcmod.arsenal.item.knight.ArmingSwordItem;
import cn.mcmod.arsenal.item.rapier.RapierItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mods.flammpfeil.slashblade.client.renderer.entity.BladeStandEntityRenderer;
import mods.flammpfeil.slashblade.entity.BladeStandEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BladeStandEntityRenderer.class)
public class BladeStandEntityRendererMixin {

    @Shadow(remap = false)
    @Final
    private ItemRenderer itemRenderer;

    @Shadow(remap = false)
    private void renderItem(BladeStandEntity entity, ItemStack itemstack, PoseStack matrixStackIn,
                            MultiBufferSource bufferIn, int packedLightIn) {
        if (!itemstack.isEmpty()) {
            BakedModel ibakedmodel = this.itemRenderer.getModel(itemstack, entity.level(), null, 0);
            this.itemRenderer.render(itemstack, ItemDisplayContext.FIXED, false, matrixStackIn, bufferIn, packedLightIn,
                    OverlayTexture.NO_OVERLAY, ibakedmodel);
        }
    }


    @Inject(method = "doRender", at = @At(value = "INVOKE", target =
            "Lmods/flammpfeil/slashblade/client/renderer/entity/BladeStandEntityRenderer;renderItem(Lmods/flammpfeil/slashblade/entity/BladeStandEntity;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            ordinal = 1), remap = false)
    private void renderSheath(BladeStandEntity entity, float entityYaw, float partialTicks, PoseStack matrixStackIn,
                              MultiBufferSource bufferIn, int packedLightIn, CallbackInfo ci) {

        ItemStack sword = entity.getItem();
        Item swordItem = sword.getItem();

        if (swordItem instanceof RapierItem) {
            matrixStackIn.translate(0.0, 0, -0.07);
            matrixStackIn.scale(0.7f, 0.7f, 0.7f);

            matrixStackIn.mulPose(Axis.ZP.rotationDegrees(90f));
            matrixStackIn.mulPose(Axis.YP.rotationDegrees(180f));

            this.renderItem(entity, entity.getItem(), matrixStackIn, bufferIn, packedLightIn);
        }else if (swordItem.getClass() == ChineseSwordItem.class) {
            matrixStackIn.translate(0.0, 0, -0.025);
            matrixStackIn.scale(0.7f, 0.7f, 0.7f);

            matrixStackIn.mulPose(Axis.ZP.rotationDegrees(90f));
            matrixStackIn.mulPose(Axis.YP.rotationDegrees(180f));

            this.renderItem(entity, entity.getItem(), matrixStackIn, bufferIn, packedLightIn);

        } else if (swordItem instanceof ArmingSwordItem || swordItem instanceof AncientSwordItem) {

            matrixStackIn.translate(0.0, 0, -0.05);
            matrixStackIn.scale(0.7f, 0.7f, 0.7f);

            matrixStackIn.mulPose(Axis.ZP.rotationDegrees(90f));
            matrixStackIn.mulPose(Axis.YP.rotationDegrees(180f));

            this.renderItem(entity, entity.getItem(), matrixStackIn, bufferIn, packedLightIn);

        }

//        if (entity instanceof SheathItemAccessor accessor) {
//            ItemStack sheathItem = accessor.arsenal_core_reborn$getSheathItem();
//            if (!sheathItem.isEmpty()) {
//                matrixStackIn.pushPose();
//                matrixStackIn.translate(-0.7, 0.4, 0.1);
//                matrixStackIn.scale(0.7f, 0.7f, 0.7f);
//                this.renderItem(entity, sheathItem, matrixStackIn, bufferIn, packedLightIn);
//                matrixStackIn.popPose();
//            }
//        }
    }
}