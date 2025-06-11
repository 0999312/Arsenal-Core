package cn.mcmod.arsenal.item;

import net.minecraft.world.item.Item;

public class SwordSheathItem extends Item {
    private boolean isHidden = false;

    public SwordSheathItem() {
        super(new Properties().stacksTo(1));
    }

    public SwordSheathItem(boolean isHidden) {
        super(new Properties().stacksTo(1));
        this.isHidden = isHidden;
    }

    public boolean isHidden() {
        return this.isHidden;
    }


    public SwordSheathItem setHidden(boolean isHidden) {
        this.isHidden = isHidden;
        return this;
    }

    @Override
    public boolean isFireResistant() {
        return true;
    }

}