package net.foxdenstudio.sponge.foxshell.lexer;

import net.foxdenstudio.sponge.foxshell.lexer.tokens.SymbolToken;
import net.foxdenstudio.sponge.foxshell.lexer.tokens.Token;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LexerTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getNextToken() throws Exception {
        final Lexer lexer = Lexer.i();
        lexer.set("5+5 \"This is a test\"");
        Token token;
        while ((token = lexer.getNextToken()) != SymbolToken.EOF) {
            System.out.println(token);
        }
    }

}