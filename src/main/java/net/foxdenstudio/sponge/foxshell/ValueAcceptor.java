package net.foxdenstudio.sponge.foxshell;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;

public class ValueAcceptor implements IValAcceptor {

    private final HashMap<String, IValAcceptor> children;
    private Object data;
    private IValAcceptor parent;

    public ValueAcceptor() {
        children = new HashMap<>();
    }

    @Nullable
    @Override
    public IValAcceptor get(@Nonnull String name) {
        return this.children.get(name);
    }

    @Nullable
    public Object get() {
        return data;
    }

    public void set(Object data) {
        this.data = data;
    }

    public void set(String name, IValAcceptor valueAcceptor) {
        this.children.put(name, valueAcceptor);
    }

    @Override
    public String toString() {
        return "ValueAcceptor{" +
                "children=" + children +
                ", data=" + data +
                ", parent=" + parent +
                '}';
    }
}
