package cn.mcmod.arsenal.api;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class WeaponProgressComponent {
    private static final String NBT_KILLS = "weapon_kills";
    private static final String NBT_LEVEL = "weapon_level";
    private static final String NBT_COOLDOWN = "weapon_cooldown";
    private static final String NBT_LAST_USE_TIME = "weapon_last_use_time";
    private static final String NBT_FIRST_OBTAINED = "weapon_first_obtained";
    
    private int kills;
    private int level;
    private int cooldown;
    private long lastUseTime;
    private boolean firstObtained;
    
    public WeaponProgressComponent() {
        this.kills = 0;
        this.level = 0;
        this.cooldown = 0;
        this.lastUseTime = 0;
        this.firstObtained = false;
    }
    
    public static WeaponProgressComponent fromItemStack(ItemStack stack) {
        WeaponProgressComponent component = new WeaponProgressComponent();
        if (stack.hasTag()) {
            CompoundTag tag = stack.getTag();
            component.kills = tag.getInt(NBT_KILLS);
            component.level = tag.getInt(NBT_LEVEL);
            component.cooldown = tag.getInt(NBT_COOLDOWN);
            component.lastUseTime = tag.getLong(NBT_LAST_USE_TIME);
            component.firstObtained = tag.getBoolean(NBT_FIRST_OBTAINED);
        }
        return component;
    }
    
    public void saveToItemStack(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt(NBT_KILLS, kills);
        tag.putInt(NBT_LEVEL, level);
        tag.putInt(NBT_COOLDOWN, cooldown);
        tag.putLong(NBT_LAST_USE_TIME, lastUseTime);
        tag.putBoolean(NBT_FIRST_OBTAINED, firstObtained);
    }
    
    // Getters and Setters
    public int getKills() { return kills; }
    public void setKills(int kills) { this.kills = kills; }
    public void addKill() { this.kills++; }
    
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    
    public int getCooldown() { return cooldown; }
    public void setCooldown(int cooldown) { this.cooldown = cooldown; }
    
    public long getLastUseTime() { return lastUseTime; }
    public void setLastUseTime(long lastUseTime) { this.lastUseTime = lastUseTime; }
    
    public boolean isFirstObtained() { return firstObtained; }
    public void setFirstObtained(boolean firstObtained) { this.firstObtained = firstObtained; }
    
    // 根据击杀数计算等级
    public int calculateLevel() {
        if (kills >= 480) return 5;
        if (kills >= 240) return 4;
        if (kills >= 120) return 3;
        if (kills >= 60) return 2;
        if (kills >= 20) return 1;
        return 0;
    }
    
    // 检查是否升级
    public boolean checkLevelUp() {
        int newLevel = calculateLevel();
        if (newLevel > level) {
            level = newLevel;
            return true;
        }
        return false;
    }
    
    // 获取当前等级的属性
    public float getAttackDamage() {
        return switch (level) {
            case 1 -> 6.0f;
            case 2 -> 7.0f;
            case 3 -> 8.0f;
            case 4 -> 10.0f;
            case 5 -> 12.0f;
            default -> 5.0f;
        };
    }
    
    public float getAttackSpeed() {
        return switch (level) {
            case 1 -> - 1.7f;
            case 2 -> - 1.6f;
            case 3 -> - 1.5f;
            case 4 -> - 1.4f;
            case 5 -> - 1.2f;
            default -> - 1.8f;
        };
    }
    
    public String getLevelDescription() {
        return switch (level) {
            case 0 -> "初始觉醒状态";
            case 1 -> "剑气微启";
            case 2 -> "力破山河";
            case 3 -> "神剑觉醒";
            case 4 -> "无双圣剑";
            case 5 -> "轩辕真解";
            default -> "未知状态";
        };
    }
}