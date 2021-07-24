package cn.mcmod.chinese_sword.item;

import cn.mcmod.chinese_sword.ChineseSword;
import cn.mcmod.chinese_sword.ChineseSwordConfig;
import cn.mcmod.chinese_sword.compat.curios.CuriosWrapper;
import cn.mcmod.chinese_sword.compat.curios.CuriosCapProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class ChineseSwordItem extends SwordItem implements IDrawable {
    private final WeaponTier tier;
    private final ItemStack sheath;

    public ChineseSwordItem(WeaponTier tier, int attackDamageIn, float attackSpeedIn, ItemStack sheathItem,
            Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
        this.tier = tier;
        this.sheath = sheathItem;
    }

    public ChineseSwordItem(WeaponTier tier, int attackDamageIn, float attackSpeedIn, ItemStack sheathItem) {
        this(tier, attackDamageIn, attackSpeedIn, sheathItem,
                new Item.Properties().stacksTo(1).tab(ChineseSword.WEAPON_GROUP));
    }

    @Override
    public boolean isFoil(ItemStack p_77636_1_) {
        if(ChineseSwordConfig.NORMAL_SWORD_FOIL.get())
            return super.isFoil(p_77636_1_);
        return false;
    }
    
    public ChineseSwordItem(WeaponTier tier, ItemStack sheathItem, Properties builderIn) {
        this(tier, 4, -1.6F, sheathItem, builderIn);
    }

    public ChineseSwordItem(WeaponTier tier, ItemStack sheathItem) {
        this(tier, 4, -1.6F, sheathItem, new Item.Properties().stacksTo(1).tab(ChineseSword.WEAPON_GROUP));
    }

    @Override
    public ItemStack getSheath(ItemStack stack) {
        return this.sheath;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return (int) (getWeaponTier().getUses() * 0.75F);
    }

    @Override
    public UseAction getUseAnimation(ItemStack stackIn) {
        if(ChineseSwordConfig.BLOCKING.get())
            return UseAction.BLOCK;
        return UseAction.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stackIn) {
        return 72000;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        playerIn.startUsingItem(handIn);
        return ActionResult.consume(itemstack);
    }

    public WeaponTier getWeaponTier() {
        return tier;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
        if (ChineseSword.curiosLoaded)
            return new CuriosCapProvider(new CuriosWrapper(sheath));
        return super.initCapabilities(stack, nbt);
    }

}
