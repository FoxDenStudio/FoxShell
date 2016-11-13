package net.foxdenstudio.sponge.foxshell;

import javax.annotation.Nonnull;

public class Bridge {

    private final ValueAcceptor valueAcceptor;

    public Bridge(@Nonnull final ValueAcceptor valueAcceptor) {
        this.valueAcceptor = valueAcceptor;
    }


}
