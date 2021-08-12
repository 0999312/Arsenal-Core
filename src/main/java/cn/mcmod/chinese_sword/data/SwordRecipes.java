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
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeRecipeProvider;

public class SwordRecipes extends ForgeRecipeProvider {

    public SwordRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ItemRegistry.XUANYUANJIAN.get()).pattern("IDI").pattern("SLS").pattern("ISI")
                .define('I', Ingredient.of(Tags.Items.STORAGE_BLOCKS_DIAMOND))
                .define('L', Ingredient.of(ItemRegistry.NETHERITE_ANCIENT_SWORD.get()))
                .define('D', Ingredient.of(Items.DRAGON_EGG)).define('S', Ingredient.of(Tags.Items.NETHER_STARS))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.DRAGON_EGG)).save(consumer);
        registerSwordRecipes(consumer, ItemRegistry.WOODEN_CHINESE_SWORD.get(), ItemRegistry.WOODEN_ANCIENT_SWORD.get(),
                ItemRegistry.WOODEN_CHINESE_SWORD_SHEATH.get(), ItemRegistry.WOODEN_ANCIENT_SWORD_SHEATH.get(),
                Ingredient.of(ItemTags.PLANKS), Ingredient.of(Items.WOODEN_SWORD));
        registerSwordRecipes(consumer, ItemRegistry.STONE_CHINESE_SWORD.get(), ItemRegistry.STONE_ANCIENT_SWORD.get(),
                ItemRegistry.STONE_CHINESE_SWORD_SHEATH.get(), ItemRegistry.STONE_ANCIENT_SWORD_SHEATH.get(),
                Ingredient.of(Tags.Items.COBBLESTONE), Ingredient.of(Items.STONE_SWORD));
        registerSwordRecipes(consumer, ItemRegistry.IRON_CHINESE_SWORD.get(), ItemRegistry.IRON_ANCIENT_SWORD.get(),
                ItemRegistry.IRON_CHINESE_SWORD_SHEATH.get(), ItemRegistry.IRON_ANCIENT_SWORD_SHEATH.get(),
                Ingredient.of(Tags.Items.INGOTS_IRON), Ingredient.of(Items.IRON_SWORD));
        registerSwordRecipes(consumer, ItemRegistry.GOLDEN_CHINESE_SWORD.get(), ItemRegistry.GOLDEN_ANCIENT_SWORD.get(),
                ItemRegistry.GOLDEN_CHINESE_SWORD_SHEATH.get(), ItemRegistry.GOLDEN_ANCIENT_SWORD_SHEATH.get(),
                Ingredient.of(Tags.Items.INGOTS_GOLD), Ingredient.of(Items.GOLDEN_SWORD));
        registerSwordRecipes(consumer, ItemRegistry.DIAMOND_CHINESE_SWORD.get(), ItemRegistry.DIAMOND_ANCIENT_SWORD.get(),
                ItemRegistry.DIAMOND_CHINESE_SWORD_SHEATH.get(), ItemRegistry.DIAMOND_ANCIENT_SWORD_SHEATH.get(),
                Ingredient.of(Tags.Items.GEMS_DIAMOND), Ingredient.of(Items.DIAMOND_SWORD));
        registerSwordRecipes(consumer, ItemRegistry.NETHERITE_CHINESE_SWORD.get(), ItemRegistry.NETHERITE_ANCIENT_SWORD.get(),
                ItemRegistry.NETHERITE_CHINESE_SWORD_SHEATH.get(), ItemRegistry.NETHERITE_ANCIENT_SWORD_SHEATH.get(),
                Ingredient.of(Tags.Items.INGOTS_NETHERITE), Ingredient.of(Items.NETHERITE_SWORD));
    }

    private void registerSwordRecipes(Consumer<IFinishedRecipe> consumer, IItemProvider out_chinese,
            IItemProvider out_ancient, IItemProvider sheath_chinese, IItemProvider sheath_ancient, Ingredient material, Ingredient sword) {
        ShapedRecipeBuilder.shaped(sheath_ancient)
                .pattern("  I")
                .pattern(" L ")
                .pattern("PS ")
                .define('I', material)
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(sheath_chinese)
                .pattern(" IL")
                .pattern(" L ")
                .pattern("PS ")
                .define('I', material)
                .define('L', Ingredient.of(ItemTags.LOGS)).define('P', Ingredient.of(ItemTags.PLANKS))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(out_ancient)
                .pattern(" S")
                .pattern("B ")
                .define('B', sword)
                .define('S', sheath_ancient)
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(out_chinese)
                .pattern(" S")
                .pattern("B ")
                .define('B', sword)
                .define('S', sheath_chinese)
                .unlockedBy("ingredient", InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                .save(consumer);
    }
}
