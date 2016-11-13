package net.foxdenstudio.sponge.foxshell.lexer;

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
            if (this.currentCharacter != '"') {
                this.skipWhitespace();
            }

        }
        return null;
    }
}
