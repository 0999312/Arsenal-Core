package cn.mcmod.arsenal.compat.curios.client;

import cn.mcmod.arsenal.api.IDrawable;
import cn.mcmod.arsenal.item.WeaponFrogItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

@OnlyIn(Dist.CLIENT)
public class WeaponFrogRender implements ICurioRenderer {

    @Override
    public <S extends LivingEntityRenderState, M extends EntityModel<? super S>> void render (ItemStack stack, SlotContext slotContext, PoseStack poseStack, @NotNull MultiBufferSource renderTypeBuffer, int packedLight, S renderState, RenderLayerParent<S, M> renderLayerParent, EntityRendererProvider.Context context, float yRotation, float xRotation) {
        Level level = slotContext.entity().level();
        ItemStack swordStack = WeaponFrogItem.getInventory(stack, level).getStackInSlot(0);
        if (swordStack.getItem() instanceof IDrawable sword) {
            this.renderItem(sword.getSheath(swordStack), poseStack, renderTypeBuffer, packedLight, slotContext.entity(), renderState);
        }
    }

    public void renderItem (ItemStack item,
                            PoseStack matrixStack,
                            MultiBufferSource renderTypeBuffer,
                            int light,
                            LivingEntity livingEntity,
                            LivingEntityRenderState renderState) {
        matrixStack.pushPose();
        // 如果潜行则偏移/旋转
        ICurioRenderer.translateIfSneaking(matrixStack, livingEntity);
        ICurioRenderer.rotateIfSneaking(matrixStack, livingEntity);

        // 向下偏移
        matrixStack.translate(0.0D, - 0.34375D, 0.0D);

        // 绕 Y 轴旋转 180°（使用 JOML 四元数）
        Quaternionf q = new Quaternionf().rotateAxis((float) Math.toRadians(180.0F),
                0.0F, 1.0F, 0.0F);
        matrixStack.mulPose(q);

        // 缩放
        matrixStack.scale(0.625F, - 0.625F, - 0.625F);

        // 最终渲染
        Minecraft.getInstance()
                .getItemRenderer()
                .renderStatic(
                        livingEntity,
                        item,
                        ItemDisplayContext.HEAD,
                        matrixStack,
                        renderTypeBuffer,
                        livingEntity.level(),
                        light,
                        LivingEntityRenderer.getOverlayCoords(renderState, 10.0F),
                        livingEntity.getId()
                );
        matrixStack.popPose();
    }
}