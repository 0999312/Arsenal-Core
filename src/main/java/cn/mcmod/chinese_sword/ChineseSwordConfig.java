package cn.mcmod.chinese_sword;

import net.minecraftforge.common.ForgeConfigSpec;

public class ChineseSwordConfig {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.BooleanValue BLOCKING;
    public static ForgeConfigSpec CLIENT_CONFIG;
    public static ForgeConfigSpec.BooleanValue NORMAL_SWORD_FOIL;
    public static ForgeConfigSpec.BooleanValue XUANYUANJIAN_FOIL;
    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("General settings").push("general");
        BLOCKING = COMMON_BUILDER.comment("Is the sword blockable?").comment("Recommended for environments where right-click behavior is likely to be modified").define("blocking", true);
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
    static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        CLIENT_BUILDER.comment("Client settings").push("client");
        NORMAL_SWORD_FOIL = CLIENT_BUILDER.comment("Is normal swords foil?").define("foil_normal", true);
        XUANYUANJIAN_FOIL = CLIENT_BUILDER.comment("Is the Xuan Yuan Jian foil?").comment("Not recommended").define("foil_xuanyuanjian", false);
        CLIENT_BUILDER.pop();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }
}
