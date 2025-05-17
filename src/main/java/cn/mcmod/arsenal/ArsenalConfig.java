package cn.mcmod.arsenal;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = "arsenal_core", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArsenalConfig {
    public static double maximum_power_damage;
    public static boolean normal_sword_foil;
    public static boolean xuanyuanjian_foil;

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder()
            .comment("Arsenal Core Config")
            .push("General");

    private static final ForgeConfigSpec.DoubleValue MAXIMUM_POWER_DAMAGE = BUILDER
            .comment("How high is the damage value of xuan yuan jian's power")
            .defineInRange("maximum_power_damage", 25.0, 0.0, 1000.0);

    private static final ForgeConfigSpec.BooleanValue NORMAL_SWORD_FOIL = BUILDER
            .comment("Is normal swords foil?")
            .define("foil_normal", true);

    private static final ForgeConfigSpec.BooleanValue XUANYUANJIAN_FOIL = BUILDER
            .comment("Is the Xuan Yuan Jian foil? Not recommended")
            .define("foil_xuanyuanjian", false);

    public static final ForgeConfigSpec SPEC = BUILDER.pop().build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        if (event.getConfig().getSpec() == SPEC) {
            maximum_power_damage = MAXIMUM_POWER_DAMAGE.get();
            normal_sword_foil = NORMAL_SWORD_FOIL.get();
            xuanyuanjian_foil = XUANYUANJIAN_FOIL.get();
        }
    }
}