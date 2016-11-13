package net.foxdenstudio.sponge.foxshell.lexer;

import net.foxdenstudio.sponge.foxshell.lexer.tokens.OperatorToken;
import net.foxdenstudio.sponge.foxshell.lexer.tokens.StringToken;
import net.foxdenstudio.sponge.foxshell.lexer.tokens.SymbolToken;
import net.foxdenstudio.sponge.foxshell.lexer.tokens.Token;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;

public class Lexer {

    private final static Lexer instance = new Lexer();

    private String inputText;
    private Character currentCharacter;
    private Token currentToken;
    private int position;
    private int currentLine;
    private int currentLinePosition;

    private Lexer() {
        reset();
    }

    @Nonnull
    public static Lexer i() {
        return instance;
    }

    public void reset() {
        this.inputText = "";
        this.position = 0;
        this.currentLine = 0;
        this.currentLinePosition = 0;
        this.currentToken = null;
        this.currentCharacter = null;
    }

    public void set(@Nonnull final String inputText) {
        this.inputText = inputText;
        this.currentCharacter = this.inputText.charAt(this.currentLine);
    }

    void syntaxError(@Nullable final String cause) {
        final HashMap<String, Object> data = new HashMap<>();

        data.put("current_line_position", this.currentLinePosition);
        data.put("current_line", this.currentLine);
        data.put("cause", cause);

//        syntaxErrorCallback.fire(data);
    }

    private void advance() {
        Character nextChar;
        if ((nextChar = this.peek()) != null) {
            this.position++;
            this.currentLinePosition++;
            this.currentCharacter = nextChar;
        } else {
            this.currentCharacter = null;
        }
    }

    @Nullable
    private Character peek() {
        if (this.position + 1 > this.inputText.length() - 1) {
            return null;
        } else {
            return this.inputText.charAt(this.position + 1);
        }
    }

    @Nullable
    private Character reversePeek() {
        if (this.position - 1 < 0) {
            return null;
        } else {
            return this.inputText.charAt(this.position - 1);
        }
    }

    private void skipWhitespace() {
        while ((this.currentCharacter != null) && (Character.isWhitespace(this.currentCharacter))) {
            if (this.currentCharacter == '\n') {
                this.currentLine++;
                this.currentLinePosition = 0;
            }
            this.advance();
        }
    }

    @Nullable
    public Token getNextToken() {
        while (this.currentCharacter != null) {

            if (this.currentCharacter == '"') {
                return this.string();
            }

            if (Character.isDigit(this.currentCharacter)) {
                return this.number();
            }

            if (Character.isLetter(this.currentCharacter)) {
                return this.identification();
            }


            switch (this.currentCharacter) {
                case '+':
                    this.advance();
                    return new OperatorToken(Token.Type.PLUS);
                case '-':
                    this.advance();
                    this.skipWhitespace();
                    if (this.currentCharacter == '-') {
                        return new OperatorToken(Token.Type.PLUS);
                    }
                    return new OperatorToken(Token.Type.MINUS);
                case '*':
                    this.advance();
                    return new OperatorToken(Token.Type.MULTIPLY);
                case '/':
                    this.advance();
                    return new OperatorToken(Token.Type.DIVIDE);
                case '^':
                    this.advance();
                    return new OperatorToken(Token.Type.POWER);
                case '%':
                    this.advance();
                    return new OperatorToken(Token.Type.MODULO);
                case '(':
                    this.advance();
                    return new SymbolToken(Token.Type.LPAR);
                case ')':
                    this.advance();
                    return new SymbolToken(Token.Type.RPAR);
                default:
                    this.syntaxError("Invalid operator!");
            }
            this.skipWhitespace();

        }
        return null;
    }

    private Token identification() {
        return null;
    }

    private Token number() {
        return null;
    }

    @Nonnull
    private Token string() {
        this.advance();

        String resultString = "";
        boolean shouldContinue = true;
        while (shouldContinue) {
            resultString += this.currentCharacter;
            this.advance();
            if (this.currentCharacter == '"' && this.reversePeek() != '\\') shouldContinue = false;
        }
        this.advance();
        return new StringToken(resultString);
    }

}
