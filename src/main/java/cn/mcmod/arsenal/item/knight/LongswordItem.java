package cn.mcmod.arsenal.item.knight;

import cn.mcmod.arsenal.api.toolmaterial.WeaponToolMaterial;
import net.minecraft.world.item.ItemStack;

public class LongswordItem extends ArmingSwordItem {
    public LongswordItem(WeaponToolMaterial toolMaterial, ItemStack sheathItem, Properties properties) {
        super(toolMaterial, 6, -2.6F, sheathItem, properties);
    }
}