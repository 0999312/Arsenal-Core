package cn.mcmod.chinese_sword.data;

import cn.mcmod.chinese_sword.Constants;
import cn.mcmod.chinese_sword.item.WeaponTiers;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SwordModelProvider extends ItemModelProvider {

    public SwordModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    public SwordModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ancientSword("wooden");
        chineseSword("wooden");
        ancientSword(WeaponTiers.STONE.getUnlocalizedName());
        chineseSword(WeaponTiers.STONE.getUnlocalizedName());
        ancientSword(WeaponTiers.IRON.getUnlocalizedName());
        chineseSword(WeaponTiers.IRON.getUnlocalizedName());
        ancientSword("golden");
        chineseSword("golden");
        ancientSword(WeaponTiers.DIAMOND.getUnlocalizedName());
        chineseSword(WeaponTiers.DIAMOND.getUnlocalizedName());
        ancientSword(WeaponTiers.NETHERITE.getUnlocalizedName());
        chineseSword(WeaponTiers.NETHERITE.getUnlocalizedName());
        ancientSword(WeaponTiers.STEEL.getUnlocalizedName());
        chineseSword(WeaponTiers.STEEL.getUnlocalizedName());
        ancientSword(WeaponTiers.INVAR.getUnlocalizedName());
        chineseSword(WeaponTiers.INVAR.getUnlocalizedName());
        ancientSword(WeaponTiers.SILVER.getUnlocalizedName());
        chineseSword(WeaponTiers.SILVER.getUnlocalizedName());
        ancientSword(WeaponTiers.PLATINUM.getUnlocalizedName());
        chineseSword(WeaponTiers.PLATINUM.getUnlocalizedName());
        ancientSword(WeaponTiers.ELECTRUM.getUnlocalizedName());
        chineseSword(WeaponTiers.ELECTRUM.getUnlocalizedName());
        ancientSword(WeaponTiers.LEAD.getUnlocalizedName());
        chineseSword(WeaponTiers.LEAD.getUnlocalizedName());
        ancientSword(WeaponTiers.NICKEL.getUnlocalizedName());
        chineseSword(WeaponTiers.NICKEL.getUnlocalizedName());
        ancientSword(WeaponTiers.INVAR.getUnlocalizedName());
        chineseSword(WeaponTiers.INVAR.getUnlocalizedName());
    }

    public void ancientSword(String name) {
        withExistingParent(name + "_ancient_sword", new ResourceLocation(Constants.MODID, "item/ancient_sword"))
                .texture("0", "item/" + name + "_sword").override().predicate(new ResourceLocation("blocking"), 1)
                .model(getExistingFile(
                        new ResourceLocation(Constants.MODID, "item/" + name + "_ancient_sword_blocking")));
        withExistingParent(name + "_ancient_sword_blocking",
                new ResourceLocation(Constants.MODID, "item/ancient_sword_blocking")).texture("0",
                        "item/" + name + "_sword");
        withExistingParent(name + "_ancient_sword_sheath",
                new ResourceLocation(Constants.MODID, "item/ancient_sword_sheath"))
                        .texture("0", "item/" + name + "_sword").texture("1", "minecraft:block/dark_oak_log");
    }

    public void chineseSword(String name) {
        withExistingParent(name + "_chinese_sword", new ResourceLocation(Constants.MODID, "item/chinese_sword"))
                .texture("0", "item/" + name + "_sword").override().predicate(new ResourceLocation("blocking"), 1)
                .model(getExistingFile(
                        new ResourceLocation(Constants.MODID, "item/" + name + "_chinese_sword_blocking")));
        withExistingParent(name + "_chinese_sword_blocking",
                new ResourceLocation(Constants.MODID, "item/chinese_sword_blocking")).texture("0",
                        "item/" + name + "_sword");
        withExistingParent(name + "_chinese_sword_sheath",
                new ResourceLocation(Constants.MODID, "item/chinese_sword_sheath"))
                        .texture("0", "item/" + name + "_sword").texture("1", "minecraft:block/dark_oak_log");
    }
}
