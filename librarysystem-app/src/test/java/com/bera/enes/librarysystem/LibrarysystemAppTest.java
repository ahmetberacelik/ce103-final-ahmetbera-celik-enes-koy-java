package com.bera.enes.librarysystem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class LibrarysystemAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("5".getBytes());
    LibrarysystemApp librarysystemApp = new LibrarysystemApp();
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    @Test
    public void testLibrarysystemAppMain() throws IOException, InterruptedException{
        String[] args = null;
        LibrarysystemApp.main(args);

        String expectedOutputStartsWith = "1. Catalog Search";
        String actualOutput = outContent.toString();
        
        assertEquals(true, actualOutput.startsWith(expectedOutputStartsWith));
    }
}