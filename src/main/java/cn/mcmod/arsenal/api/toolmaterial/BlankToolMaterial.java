package cn.mcmod.arsenal.api.toolmaterial;

import cn.mcmod.arsenal.ArsenalCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;

public class BlankToolMaterial extends WeaponToolMaterial {
    public BlankToolMaterial(String unlocName, String modId, TagKey<Block> incorrectBlocksForDrops, int durability, float speed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems) {
        super(unlocName, modId, incorrectBlocksForDrops, durability, speed, attackDamageBonus, enchantmentValue, repairItems, null);
    }

    public BlankToolMaterial(String unlocName, String modId, int durability, float speed, float attackDamageBonus, int enchantmentValue, ResourceLocation repairItemTag) {
        super(unlocName, modId, BlockTags.create(ResourceLocation.parse("c:ineffective_tool")), durability, speed, attackDamageBonus, enchantmentValue, repairItemTag, null);
    }

    public BlankToolMaterial(String unlocName, int durability, float speed, float attackDamageBonus, int enchantmentValue, ResourceLocation repairItemTag) {
        this(unlocName, ArsenalCore.MODID, durability, speed, attackDamageBonus, enchantmentValue, repairItemTag);
    }

    public BlankToolMaterial(String unlocName, String modId, ToolMaterial baseMaterial) {
        super(unlocName, modId, baseMaterial, null);
    }

    public BlankToolMaterial(String unlocName, ToolMaterial baseMaterial) {
        this(unlocName, ArsenalCore.MODID, baseMaterial);
    }

    public BlankToolMaterial(String unlocName, String modId, ToolMaterial baseMaterial, ResourceLocation repairItemTag) {
        super(unlocName, modId,
                new ToolMaterial(
                        baseMaterial.incorrectBlocksForDrops(),
                        baseMaterial.durability(),
                        baseMaterial.speed(),
                        baseMaterial.attackDamageBonus(),
                        baseMaterial.enchantmentValue(),
                        ItemTags.create(repairItemTag)
                ), null);
    }

    public BlankToolMaterial(String unlocName, ToolMaterial baseMaterial, ResourceLocation repairItemTag) {
        this(unlocName, ArsenalCore.MODID, baseMaterial, repairItemTag);
    }
}