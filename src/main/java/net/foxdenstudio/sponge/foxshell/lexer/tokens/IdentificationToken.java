package net.foxdenstudio.sponge.foxshell.lexer.tokens;

import javax.annotation.Nonnull;

public class IdentificationToken extends Token {
    private final String name;

    public IdentificationToken(final String name) {
        super(Token.Type.IDENTIFICATION);
        this.name = name;
    }

    public IdentificationToken(final Token.Type symbol, final String name) {
        super(symbol);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Nonnull
    @Override
    public String toString() {
        return "{\"IdentificationToken\":{" +
                "\"name\":\"" + this.name + '\"' +
                "}}";
    }

}
