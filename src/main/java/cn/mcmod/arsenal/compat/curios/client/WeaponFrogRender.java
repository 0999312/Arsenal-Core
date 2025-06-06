//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.mcmod.arsenal.compat.curios.client;

import cn.mcmod.arsenal.api.IDrawable;
import cn.mcmod.arsenal.item.WeaponFrogItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Quaternionf;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

@OnlyIn(Dist.CLIENT)
public class WeaponFrogRender implements ICurioRenderer {


    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack swordStack = WeaponFrogItem.getInventory(stack).getStackInSlot(0);
        if (swordStack.getItem() instanceof IDrawable sword) {
            this.renderItem(sword.getSheath(swordStack), matrixStack, renderTypeBuffer, light, slotContext.entity());
        }
    }

    public void renderItem(ItemStack item,
                           PoseStack matrixStack,
                           MultiBufferSource renderTypeBuffer,
                           int light,
                           LivingEntity livingEntity) {
        matrixStack.pushPose();
        // 如果潜行则偏移/旋转
        ICurioRenderer.translateIfSneaking(matrixStack, livingEntity);
        ICurioRenderer.rotateIfSneaking(matrixStack, livingEntity);

        // 向下偏移
        matrixStack.translate(0.0D, -0.34375D, 0.0D);

        // 绕 Y 轴旋转 180°（使用 JOML 四元数）
        Quaternionf q = new Quaternionf().rotateAxis((float) Math.toRadians(180.0F),
                0.0F, 1.0F, 0.0F);
        matrixStack.mulPose(q);

        // 缩放
        matrixStack.scale(0.625F, -0.625F, -0.625F);

        // 最终渲染
        Minecraft.getInstance()
                .getItemRenderer()
                .renderStatic(livingEntity,
                        item,
                        ItemDisplayContext.HEAD,
                        false,
                        matrixStack,
                        renderTypeBuffer,
                        livingEntity.level(),
                        light,
                        LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0F),
                        livingEntity.getId());
        matrixStack.popPose();
    }
}