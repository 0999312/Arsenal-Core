package cn.mcmod.chinese_sword.compat.curios;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import top.theillusivec4.curios.api.CuriosCapability;

public class SimpleCapProvider<C> implements ICapabilityProvider {
	private final C capInstance;
	private final LazyOptional<C> capOptional;

	private final Capability<C> capability;

	public SimpleCapProvider(Capability<C> capability, C capInstance) {
		this.capability = capability;
		this.capInstance = capInstance;
		this.capOptional = LazyOptional.of(() -> this.capInstance);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		//避开CuriosCapability.ITEM导致的NULL
	    if (cap == CuriosCapability.ITEM)
	        return LazyOptional.of(() -> this.capInstance).cast(); 
	    //重载资源包的时候可能会导致 capability 为NULL，原因不明。
	    //思考除了CuriosCapability.ITEM，其他的Cap会不会NULL
		return capability.orEmpty(cap, capOptional);
	}

	public static <C> void attach(AttachCapabilitiesEvent<?> event, ResourceLocation key, Capability<C> cap, C capInstance) {
		SimpleCapProvider<C> provider = new SimpleCapProvider<>(cap, capInstance);
		event.addCapability(key, provider);
		event.addListener(provider.capOptional::invalidate);
	}
}
