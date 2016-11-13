package net.foxdenstudio.sponge.foxshell.lexer;

import net.foxdenstudio.sponge.foxshell.Bridge;
import net.foxdenstudio.sponge.foxshell.IValAcceptor;
import net.foxdenstudio.sponge.foxshell.ValueAcceptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BridgeTest {
    final ValueAcceptor root = new ValueAcceptor();
    private Bridge bridge;

    @Before
    public void setUp() throws Exception {
        final ValueAcceptor rt = new ValueAcceptor();
        rt.set("block", new IValAcceptor() {
            @Nullable
            @Override
            public IValAcceptor get(@Nonnull String name) {
                return null;
            }

            @Override
            public Object get() {
                return "Test";
            }
        });
        root.set("rt", rt);
    }

    @Test
    public void bridgeTest() throws Exception {
        bridge = new Bridge(root);
    }

    @After
    public void tearDown() throws Exception {

    }

}