package net.foxdenstudio.sponge.foxshell;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IValAcceptor {
    @Nullable
    IValAcceptor get(@Nonnull final String name);

    public Object get();

}
