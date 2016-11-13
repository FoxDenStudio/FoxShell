package net.foxdenstudio.sponge.foxshell.lexer.tokens;

import javax.annotation.Nonnull;

public class NumberToken extends Token {
    private final Number numberData;

    public NumberToken(@Nonnull final Number numberData) {
        super(Token.Type.FLOAT);
        this.numberData = numberData;
    }

    @Nonnull
    public Number getNumberData() {
        return this.numberData;
    }

    @Nonnull
    @Override
    public String toString() {
        return "{\"NumberToken\":{" +
                "\"numberData\":" + this.numberData +
                "}}";
    }
}