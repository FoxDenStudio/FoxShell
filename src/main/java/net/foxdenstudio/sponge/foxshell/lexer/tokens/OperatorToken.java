package net.foxdenstudio.sponge.foxshell.lexer.tokens;

public class OperatorToken extends Token {

    private final Token.Type operator;

    public OperatorToken(final Token.Type operator) {
        super(operator);
        this.operator = operator;
    }

    public Token.Type getOperator() {
        return this.operator;
    }

    @Override
    public String toString() {
        return "{\"OperatorToken\":{" +
                "\"operator\":" + this.operator +
                "}}";
    }
}
