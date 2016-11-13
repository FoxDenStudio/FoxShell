package net.foxdenstudio.sponge.foxshell.lexer;

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
        lexer.set("\"This is a test\"");
        System.out.println(lexer.getNextToken());
    }

}