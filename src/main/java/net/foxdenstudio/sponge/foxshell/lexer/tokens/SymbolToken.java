package net.foxdenstudio.sponge.foxshell.lexer.tokens;

import javax.annotation.Nonnull;

public class SymbolToken extends IdentificationToken {

    public static final SymbolToken EOF = new SymbolToken(Token.Type.EOF);
    private final Token.Type symbol;

    public SymbolToken(final Token.Type symbol) {
        super(symbol, symbol.name());
        this.symbol = symbol;
    }

    public Token.Type getSymbol() {
        return this.symbol;
    }

    @Nonnull
    @Override
    public String toString() {
        return "{\"SymbolToken\":{" +
                "\"symbol\":" + this.symbol +
                "}}";
    }
}
