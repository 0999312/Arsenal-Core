package cn.mcmod.chinese_sword.data;

import java.util.function.Consumer;

import cn.mcmod.chinese_sword.item.ItemRegistry;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeRecipeProvider;

public class SheathRecipes extends ForgeRecipeProvider {

    public SheathRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ItemRegistry.XUANYUANJIAN.get())
        .pattern("IDI")
        .pattern("SLS")
        .pattern("ISI")
        .define('I', Ingredient.of(Tags.Items.STORAGE_BLOCKS_DIAMOND))
        .define('L', Ingredient.of(ItemRegistry.NETHERITE_ANCIENT_SWORD.get()))
        .define('D', Ingredient.of(Items.DRAGON_EGG))
        .define('S', Ingredient.of(Tags.Items.NETHER_STARS))
        .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.DRAGON_EGG)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.NETHERITE_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ").define('I', Ingredient.of(Tags.Items.INGOTS_NETHERITE))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.DIAMOND)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.NETHERITE_CHINESE_SWORD_SHEATH.get()).pattern(" IL").pattern(" L ")
                .pattern("PS ").define('I', Ingredient.of(Tags.Items.INGOTS_NETHERITE))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.DIAMOND)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.DIAMOND_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ").define('I', Ingredient.of(Tags.Items.GEMS_DIAMOND))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.DIAMOND)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.DIAMOND_CHINESE_SWORD_SHEATH.get()).pattern(" IL").pattern(" L ")
                .pattern("PS ").define('I', Ingredient.of(Tags.Items.GEMS_DIAMOND))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.DIAMOND)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.GOLDEN_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ").define('I', Ingredient.of(Tags.Items.INGOTS_GOLD))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.GOLD_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.GOLDEN_CHINESE_SWORD_SHEATH.get()).pattern(" IL").pattern(" L ")
                .pattern("PS ").define('I', Ingredient.of(Tags.Items.INGOTS_GOLD))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.GOLD_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.IRON_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ").define('I', Ingredient.of(Tags.Items.INGOTS_IRON))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.IRON_CHINESE_SWORD_SHEATH.get()).pattern(" IL").pattern(" L ")
                .pattern("PS ").define('I', Ingredient.of(Tags.Items.INGOTS_IRON))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.STONE_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ").define('I', Ingredient.of(Tags.Items.COBBLESTONE))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.COBBLESTONE)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.STONE_CHINESE_SWORD_SHEATH.get()).pattern(" IL").pattern(" L ")
                .pattern("PS ").define('I', Ingredient.of(Tags.Items.COBBLESTONE))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.COBBLESTONE)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.WOODEN_ANCIENT_SWORD_SHEATH.get()).pattern("  P").pattern(" L ")
                .pattern("PS ").define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.STICK)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.WOODEN_CHINESE_SWORD_SHEATH.get()).pattern(" PL").pattern(" L ")
                .pattern("PS ").define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.STICK)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.STEEL_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/steel"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.STEEL_CHINESE_SWORD_SHEATH.get()).pattern(" IL").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/steel"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.ELECTRUM_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/electrum"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.ELECTRUM_CHINESE_SWORD_SHEATH.get()).pattern(" IL").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/electrum"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.INVAR_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/invar"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.INVAR_CHINESE_SWORD_SHEATH.get()).pattern(" IL").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/invar"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.SILVER_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/silver"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.SILVER_CHINESE_SWORD_SHEATH.get()).pattern(" IL").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/silver"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.PLATINUM_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/platinum"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.PLATINUM_CHINESE_SWORD_SHEATH.get()).pattern(" IL").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/platinum"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.LEAD_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/lead"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.LEAD_CHINESE_SWORD_SHEATH.get()).pattern(" IL").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/lead"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.NICKEL_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/nickel"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.COPPER_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/copper"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.BRONZE_ANCIENT_SWORD_SHEATH.get()).pattern("  I").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/bronze"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(ItemRegistry.NICKEL_CHINESE_SWORD_SHEATH.get()).pattern(" IL").pattern(" L ")
                .pattern("PS ")
                .define('I', Ingredient.of(ItemTags.createOptional(new ResourceLocation("forge", "ingots/nickel"))))
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(consumer);
    }
}
