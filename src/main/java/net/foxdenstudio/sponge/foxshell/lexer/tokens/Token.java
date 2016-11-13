package net.foxdenstudio.sponge.foxshell.lexer.tokens;

import javax.annotation.Nonnull;

public abstract class Token {

    private final Token.Type tokenType;

    public Token(@Nonnull final Token.Type tokenType) {
        this.tokenType = tokenType;
    }

    @Nonnull
    @Override
    public String toString() {
        return "{\"Token\":{}}";
    }

    @Nonnull
    public Type getTokenType() {
        return this.tokenType;
    }

    public enum Type {
        EOF(Devsi.CONTROL),
        START(Devsi.CONTROL),
        FINISH(Devsi.CONTROL),
        ASSIGN(Devsi.CONTROL),

        VARIABLE(Devsi.CONTROL),
        IDENTIFICATION(Devsi.CONTROL),
        LPAR(Devsi.OTHER),
        RPAR(Devsi.OTHER),
        COLON(Devsi.OTHER),
        COMMA(Devsi.OTHER),

        FLOAT(Devsi.DATA),
        STRING(Devsi.DATA),

        PLUS(Devsi.SECONDARY_OP),
        MINUS(Devsi.SECONDARY_OP),

        MULTIPLY(Devsi.PRIMARY_OP),
        DIVIDE(Devsi.PRIMARY_OP),
        MODULO(Devsi.PRIMARY_OP),
        POWER(Devsi.PRIMARY_OP),
        SQRT(Devsi.PRIMARY_OP);

        public final Devsi devsi;

        Type(@Nonnull final Devsi devsi) {
            this.devsi = devsi;
        }

        public boolean isPrimaryMathOperator() {
            return this.devsi == Devsi.PRIMARY_OP;
        }

        public boolean isSecondaryMathOperator() {
            return this.devsi == Devsi.SECONDARY_OP;
        }

        @Nonnull
        @Override
        public String toString() {
            return "{\"Symbols\":{" +
                    "\"name\":\"" + this.name() + "\"" +
                    ",\"devsi\":\"" + this.devsi +
                    "\"}}";
        }

        public boolean isVariableType() {
            return this.devsi == Devsi.DATA;
        }

        public enum Devsi {
            OTHER, DATA, CONTROL, PRIMARY_OP, SECONDARY_OP;
        }
    }
}
