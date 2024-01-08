package com.bera.enes.librarysystem;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Assert;

public class LibrarysystemTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Librarysystem librarysystem;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        System.setIn(null);
    }
    
    
}
