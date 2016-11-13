package net.foxdenstudio.sponge.foxshell.lexer;

import net.foxdenstudio.sponge.foxshell.lexer.tokens.Token;

public class Lexer {

    private static Lexer instance;
    private String inputText;
    private Character currentCharacter;
    private Token currentToken;
    private int position;
    private int currentLine;
    private int currentLinePosition;

    private Lexer() {
        reset();
    }

    public static Lexer i() {
        return instance;
    }

    public void reset() {
        this.inputText = "";
        this.position = 0;
        this.currentLine = 0;
        this.currentLinePosition = 0;
        this.currentToken = null;
        this.currentCharacter = '\0';
    }



}
