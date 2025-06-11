package cn.mcmod.arsenal.item.feature;

import cn.mcmod.arsenal.api.WeaponFeature;
import cn.mcmod.arsenal.api.WeaponProgressComponent;
import java.util.function.Consumer;

import cn.mcmod.arsenal.item.chinese.XuanyuanjianItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import java.util.List;

public class XuanyuanFeature extends WeaponFeature {
    private static final String NBT_COUNTER_ATTACK_COOLDOWN = "counter_attack_cooldown";

    public XuanyuanFeature() {
        super("maximum_power");
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        WeaponProgressComponent progress = WeaponProgressComponent.fromItemStack(stack);
        if (progress.getCooldown() > 0) {
            progress.setCooldown(progress.getCooldown() - 1);
            progress.saveToItemStack(stack);
        }

        if (stack.hasTag() && stack.getTag().contains(NBT_COUNTER_ATTACK_COOLDOWN)) {
            int cooldown = stack.getTag().getInt(NBT_COUNTER_ATTACK_COOLDOWN);
            if (cooldown > 0) {
                stack.getTag().putInt(NBT_COUNTER_ATTACK_COOLDOWN, cooldown - 1);
            }
        }
    }

    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity instanceof LivingEntity living) {
            WeaponProgressComponent progress = WeaponProgressComponent.fromItemStack(stack);
            int level = progress.getLevel();

            // Lv.1+: 火焰附加
            if (level >= 1) {
                int fire_level = 1 + EnchantmentHelper.getFireAspect(player);
                living.setSecondsOnFire(4 * fire_level);
            }

            // Lv.2+: 亡灵额外伤害（为什么不起作用）
            //TODO: 尝试换到动态添加到原本伤害上
            if (level >= 2 && living.getMobType() == MobType.UNDEAD) {
                float smiteLevel = Math.max(5.0F, 7.5F * (float)EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SMITE, stack));
                DamageSources sources = living.level().damageSources();
                DamageSource magicDs = sources.magic();
                living.hurt(magicDs, smiteLevel);
            }

            // Lv.3+: 范围攻击
            if (level >= 3) {
                performAreaAttack(stack, player, living);
            }

            // Lv.5+: 概率削弱敌人防御
            if (level >= 5 && player.level().random.nextFloat() < 0.3f) {
                living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
            }
        }

        return false;
    }

    // Lv.4+: 受击后触发剑光余波
    public static void onPlayerHurt(Player player, ItemStack stack) {
        if (!(stack.getItem() instanceof XuanyuanjianItem)) {
            return;
        }

        WeaponProgressComponent progress = WeaponProgressComponent.fromItemStack(stack);
        if (progress.getLevel() < 4) {
            return;
        }

        if (!stack.hasTag()) {
            stack.getOrCreateTag();
        }

        int cooldown = stack.getTag().getInt(NBT_COUNTER_ATTACK_COOLDOWN);
        if (cooldown > 0) {
            return;
        }

        triggerSwordWave(player, stack);

        stack.getTag().putInt(NBT_COUNTER_ATTACK_COOLDOWN, 200);
    }

    private static void triggerSwordWave(Player player, ItemStack stack) {
        Level level = player.level();
        if (level instanceof ServerLevel serverLevel) {
            AABB area = new AABB(player.getX() - 4, player.getY() - 1, player.getZ() - 4,
                    player.getX() + 4, player.getY() + 3, player.getZ() + 4);

            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, area);
            for (LivingEntity entity : entities) {
                if (entity != player && !entity.isAlliedTo(player)) {
                    entity.hurt(level.damageSources().playerAttack(player), 7.0f);
                    double dx = entity.getX() - player.getX();
                    double dz = entity.getZ() - player.getZ();
                    entity.knockback(0.5F, dx, dz);
                }
            }

            for (int i = 0; i < 30; i++) {
                double x = player.getX() + (level.random.nextDouble() - 0.5) * 6;
                double y = player.getY() + level.random.nextDouble() * 3;
                double z = player.getZ() + (level.random.nextDouble() - 0.5) * 6;

                if (i % 2 == 0) {
                    serverLevel.sendParticles(ParticleTypes.SWEEP_ATTACK, x, y, z, 1, 0, 0, 0, 0);
                } else {
                    serverLevel.sendParticles(ParticleTypes.FLAME, x, y, z, 1, 0, 0, 0, 0);
                }
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1.0F, 1.2F);
        }
    }

    // TODO: 主动技能 - 天光剑狱 (Lv.5专属)
    // 按下R键触发，对前方扇形范围敌人造成连续斩击
    // 包含震撼音效、天光爆裂粒子、剑刃残影效果
    // 冷却时间20秒
    public static void triggerTianguangSkill(Player player, ItemStack stack) {
        // TODO: 实现天光剑狱技能
        // 1. 检测前方扇形范围内的敌人
        // 2. 对每个敌人造成多段伤害
        // 3. 播放特殊音效和粒子效果
        // 4. 添加剑刃残影视觉效果
    }

    private void performAreaAttack(ItemStack stack, Player player, LivingEntity target) {
        Level level = player.level();
        if (level instanceof ServerLevel serverLevel) {
            // TODO：缩小中心 6×4×6 的 AABB的范围攻击
            AABB area = new AABB(
                    target.getX() - 3, target.getY() - 1, target.getZ() - 3,
                    target.getX() + 3, target.getY() + 3, target.getZ() + 3
            );

            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, area);
            for (LivingEntity entity : entities) {
                if (entity != target && entity != player && !entity.isAlliedTo(player)) {
                    entity.hurt(level.damageSources().playerAttack(player), 3.0f);
                }
            }

            for (int i = 0; i < 40; i++) {
                double x = target.getX() + (level.random.nextDouble() - 0.5) * 6;
                double y = target.getY() + level.random.nextDouble() * 2;
                double z = target.getZ() + (level.random.nextDouble() - 0.5) * 6;

                serverLevel.sendParticles(ParticleTypes.SMOKE, x, y, z, 1, 0, 0.02, 0, 0.1);
                serverLevel.sendParticles(ParticleTypes.FLAME, x, y, z, 1, 0, 0.01, 0, 0.05);
            }
        }
    }


    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return -1;
    }
}