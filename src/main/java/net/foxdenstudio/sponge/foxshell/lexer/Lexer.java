package net.foxdenstudio.sponge.foxshell.lexer;

import net.foxdenstudio.sponge.foxshell.lexer.tokens.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Lexer {

    private final static Lexer instance = new Lexer();

    private String inputText;
    private Character currentCharacter;
    private Token currentToken;
    private int position;
    private int currentLine;
    private int currentLinePosition;
    private boolean single;

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
        final String stringBuilder = "\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n" +
                this.inputText + "\n" +
                "\n" +
                "Error at position: " + this.currentLinePosition + " on line: " + this.currentLine + "\n" +
                "Cause: " + (cause == null ? "Unknown!" : cause) + "\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n";
        System.out.flush();
        System.err.flush();
        System.err.println(stringBuilder);
        System.err.flush();
        System.exit(1);
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
            if (this.single) {
                if (Character.isWhitespace(this.currentCharacter)) {
                    this.advance();
                    this.single = false;
                    return new SymbolToken(Token.Type.FINISH);
                }
            }

            this.skipWhitespace();

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
                case '$':
                    this.advance();
                    if (this.currentCharacter == '{') {
                        this.advance();
                        return new SymbolToken(Token.Type.START);
                    } else {
                        this.single = true;
                        return new SymbolToken(Token.Type.START_SINGLE);
                    }
                case '}':
                    this.advance();
                    return new SymbolToken(Token.Type.FINISH);
                case '.':
                    this.advance();
                    return new SymbolToken(Token.Type.PERIOD);
                default:
                    this.syntaxError("Invalid operator!");
            }
            this.skipWhitespace();

        }
        if (this.single) {
            this.single = false;
            return new SymbolToken(Token.Type.FINISH);
        }
        return SymbolToken.EOF;
    }

    private Token identification() {
        String resultString = "";
        while (this.currentCharacter != null && Character.isLetterOrDigit(this.currentCharacter)) {
            resultString += this.currentCharacter;
            this.advance();
        }

        //TODO This should reference the map...
        return new IdentificationToken(resultString);
//        return RESERVERED_KEYWORDS.getOrDefault(resultString, new IdentificationToken(resultString));
    }

    private Token number() {
        String resultString = "";
        while (this.currentCharacter != null && Character.isDigit(this.currentCharacter)) {
            resultString += this.currentCharacter;
            this.advance();
        }

        if (this.currentCharacter != null && this.currentCharacter == '.') {
            resultString += this.currentCharacter;
            this.advance();

            while (this.currentCharacter != null && Character.isDigit(this.currentCharacter)) {
                resultString += this.currentCharacter;
                this.advance();
            }

            return new NumberToken(new Float(resultString));
        } else {
            return new NumberToken(new Integer(resultString));
        }
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
