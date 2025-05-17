package cn.mcmod.arsenal.util;

import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {
    private final Supplier<T> factory;
    private T value;
    public Lazy(Supplier<T> factory) { this.factory = factory; }
    @Override
    public T get() {
        if (value == null) value = factory.get();
        return value;
    }
}