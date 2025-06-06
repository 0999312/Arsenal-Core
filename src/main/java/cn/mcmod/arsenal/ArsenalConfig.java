package cn.mcmod.arsenal;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@Mod.EventBusSubscriber(modid = ArsenalCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArsenalConfig {
    public static double maximum_power_damage;
    public static boolean normal_sword_foil;
    public static boolean xuanyuanjian_foil;

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder()
            .comment("Arsenal Core Config")
            .push("General");

    private static final ModConfigSpec.DoubleValue MAXIMUM_POWER_DAMAGE = BUILDER
            .comment("How high is the damage value of xuan yuan jian's power")
            .defineInRange("maximum_power_damage", 25.0, 0.0, 1000.0);

    private static final ModConfigSpec.BooleanValue NORMAL_SWORD_FOIL = BUILDER
            .comment("Is normal swords foil?")
            .define("foil_normal", true);

    private static final ModConfigSpec.BooleanValue XUANYUANJIAN_FOIL = BUILDER
            .comment("Is the Xuan Yuan Jian foil? Not recommended")
            .define("foil_xuanyuanjian", false);

    public static final ModConfigSpec SPEC = BUILDER.pop().build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        if (event.getConfig().getSpec() == SPEC) {
            maximum_power_damage = MAXIMUM_POWER_DAMAGE.get();
            normal_sword_foil = NORMAL_SWORD_FOIL.get();
            xuanyuanjian_foil = XUANYUANJIAN_FOIL.get();
        }
    }
}