package cn.mcmod.chinese_sword.item;

import cn.mcmod.chinese_sword.ChineseSword;
import net.minecraft.item.Item;

public class SwordSheathItem extends Item {
//  TODO:鞘的更多用处。
    public SwordSheathItem() {
        super(new Item.Properties().stacksTo(1).tab(ChineseSword.WEAPON_GROUP));
    }

}
