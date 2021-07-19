package cn.mcmod.chinese_sword.item;

import cn.mcmod.chinese_sword.Main;
import cn.mcmod.chinese_sword.compat.curios.CuriosWrapper;
import cn.mcmod.chinese_sword.compat.curios.SimpleCapProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.CuriosCapability;

public class ChineseSwordItem extends SwordItem {
	private final WeaponTier tier;
	private final ItemStack sheath;
	
	protected ChineseSwordItem(WeaponTier tier, int attackDamageIn, float attackSpeedIn,ItemStack sheathItem, Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
		this.tier = tier;
		this.sheath = sheathItem;
	}
	
	public ChineseSwordItem(WeaponTier tier,ItemStack sheathItem, Properties builderIn) {
		this(tier, 4, -1.6F, sheathItem, builderIn);
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return (int) (getWeaponTier().getUses() * 0.75F);
	}
	
	@Override
	public UseAction getUseAnimation(ItemStack stackIn) {
		return UseAction.BLOCK;
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
		if(Main.curiosLoaded)
			return new SimpleCapProvider<>(CuriosCapability.ITEM, new CuriosWrapper(sheath));
		return super.initCapabilities(stack, nbt);
	}
	
}
