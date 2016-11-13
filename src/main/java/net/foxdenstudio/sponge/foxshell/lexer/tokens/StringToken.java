package net.foxdenstudio.sponge.foxshell.lexer.tokens;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class StringToken extends Token {
    private final String stringData;

    public StringToken(@Nullable final String stringData) {
        super(Type.STRING);
        this.stringData = stringData;
    }

    @Nullable
    public String getStringData() {
        return this.stringData;
    }

    @Nonnull
    @Override
    public String toString() {
        return "{\"StringToken\":{" +
                "\"stringData\":\"" + this.stringData + '\"' +
                "}}";
    }
}
