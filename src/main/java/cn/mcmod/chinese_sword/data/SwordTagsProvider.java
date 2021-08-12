package cn.mcmod.chinese_sword.data;

import cn.mcmod.chinese_sword.Constants;
import cn.mcmod.chinese_sword.item.AncientSwordItem;
import cn.mcmod.chinese_sword.item.ChineseSwordItem;
import cn.mcmod.chinese_sword.item.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.common.data.ForgeItemTagsProvider;
import net.minecraftforge.fml.RegistryObject;

public class SwordTagsProvider extends ForgeItemTagsProvider {

    public SwordTagsProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        // 即便你不生成Block Tag, 你也得必须提供一个非空的BlockTagsProvider.
        super(gen, new ForgeBlockTagsProvider(gen, existingFileHelper), existingFileHelper);
    }

    @Override
    public String getName() {
        return "Chinese Style Sword Tags";
    }
    
    @Override
    public void addTags() {
        ItemRegistry.ITEMS.getEntries().forEach(this::tagSword);
    }

    public void tagSword(RegistryObject<Item> item) {
        if (item.get() instanceof AncientSwordItem)
            tag(ItemTags.createOptional(new ResourceLocation(Constants.MODID, "ancient_sword"))).add(item.get());
        else if (item.get() instanceof ChineseSwordItem)
            tag(ItemTags.createOptional(new ResourceLocation(Constants.MODID, "chinese_sword"))).add(item.get());
    }
}
