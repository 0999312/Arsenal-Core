package cn.mcmod.arsenal.item;

import net.minecraft.world.item.Item;

public class SwordSheathItem extends Item {
    private boolean isHidden = false;

    public SwordSheathItem(Item.Properties properties) {
        super(properties.stacksTo(1).fireResistant());
    }

    public SwordSheathItem(boolean isHidden, Item.Properties properties) {
        super(properties.stacksTo(1));
        this.isHidden = isHidden;
    }

    public boolean isHidden() {
        return this.isHidden;
    }

    public SwordSheathItem setHidden(boolean isHidden) {
        this.isHidden = isHidden;
        return this;
    }
}