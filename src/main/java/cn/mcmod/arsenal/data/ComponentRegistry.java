package cn.mcmod.arsenal.data;

import cn.mcmod.arsenal.ArsenalCore;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Objects;
import java.util.function.Supplier;

public class ComponentRegistry {
    private static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, ArsenalCore.MODID);

    public record ItemHandlerData(CompoundTag data) {
            public ItemHandlerData (CompoundTag data) {
                this.data = data.copy();
            }

            @Override
            public CompoundTag data () {
                return this.data.copy();
            }

            @Override
            public boolean equals (Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                ItemHandlerData that = (ItemHandlerData) obj;
                return Objects.equals(data, that.data);
            }

    }

    public static final Codec<ItemHandlerData> ITEM_HANDLER_DATA_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    CompoundTag.CODEC.fieldOf("data").forGetter(ItemHandlerData::data)
            ).apply(instance, ItemHandlerData::new)
    );

    public static final StreamCodec<FriendlyByteBuf, ItemHandlerData> ITEM_HANDLER_DATA_STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.COMPOUND_TAG, ItemHandlerData::data,
                    ItemHandlerData::new
            );

    public static final Supplier<DataComponentType<ItemHandlerData>> ITEM_HANDLER_COMPONENT =
            DATA_COMPONENT_TYPES.register("item_handler",
                    () -> DataComponentType.<ItemHandlerData>builder()
                            .persistent(ITEM_HANDLER_DATA_CODEC)
                            .networkSynchronized(ITEM_HANDLER_DATA_STREAM_CODEC)
                            .build());

    public static void register(IEventBus modBus) {
        DATA_COMPONENT_TYPES.register(modBus);
    }
}