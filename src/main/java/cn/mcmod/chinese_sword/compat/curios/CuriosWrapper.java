package cn.mcmod.chinese_sword.compat.curios;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosWrapper implements ICurio {
    private final ItemStack sheath;

    public CuriosWrapper(ItemStack sheath) {
        this.sheath = sheath;
    }

    @Override
    public boolean canRightClickEquip() {
        return false;
    }

    @Override
    public boolean canRender(String identifier, int index, LivingEntity entity) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer,
            int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks,
            float ageInTicks, float netHeadYaw, float headPitch) {
        if (!this.sheath.isEmpty()) {
            matrixStack.pushPose();
            ICurio.RenderHelper.translateIfSneaking(matrixStack, livingEntity);
            ICurio.RenderHelper.rotateIfSneaking(matrixStack, livingEntity);

            matrixStack.translate(-0.15D, -0.825D, 0D);
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
            Minecraft.getInstance().getItemRenderer().renderStatic(livingEntity, sheath,
                    ItemCameraTransforms.TransformType.HEAD, false, matrixStack, renderTypeBuffer, livingEntity.level,
                    light, OverlayTexture.NO_OVERLAY);
            matrixStack.popPose();
        }
    }
}
