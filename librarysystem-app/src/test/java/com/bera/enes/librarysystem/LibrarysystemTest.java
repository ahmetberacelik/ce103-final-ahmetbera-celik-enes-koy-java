package com.bera.librarysystem;
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
    
    @Test
    public void testMainMenuInvalid() throws IOException, InterruptedException {
    	ByteArrayInputStream inContent = new ByteArrayInputStream("abc\n\n48\n\n5\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.mainMenu();
        
        Assert.assertTrue(result);
        String expectedOutput = "1. Catalog Search\n" +
                "2. Reservation And Renewal\n" +
                "3. Event And Workshop Schedule\n" +
                "4. Library Information\n" +
                "5. Exit\n" +
                "Enter your choice (1-5):" +
                "Invalid choice. Please enter a number.\n" +
                "1. Catalog Search\n" +
                "2. Reservation And Renewal\n" +
                "3. Event And Workshop Schedule\n" +
                "4. Library Information\n" +
                "5. Exit\n" +
                "Enter your choice (1-5):" +
                "Invalid choice. Please try again.\n" +
                "1. Catalog Search\n" +
                "2. Reservation And Renewal\n" +
                "3. Event And Workshop Schedule\n" +
                "4. Library Information\n" +
                "5. Exit\n" +
                "Enter your choice (1-5):";
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void testMainMenuValid() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("1\n4\n2\nEnes Koy\n\n4\n3\n3\n4\n\n5\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.mainMenu();
        Assert.assertTrue(result);
        String expectedOutput = "1. Catalog Search\n" +
                "2. Reservation And Renewal\n" +
                "3. Event And Workshop Schedule\n" +
                "4. Library Information\n" +
                "5. Exit\n" +
                "Enter your choice (1-5):" +
                "1. Search Books\n" +
                "2. Search Movies\n" +
                "3. Search Music\n" +
                "4. Exit\n" +
                "Enter your choice (1-4):" +
                "1. Catalog Search\n" +
                "2. Reservation And Renewal\n" +
                "3. Event And Workshop Schedule\n" +
                "4. Library Information\n" +
                "5. Exit\n" +
                "Enter your choice (1-5):" +
                "Please register with your user name.\n" +
        	    "Write your user name:\n" +
        	    "Welcome Enes Koy\n" +
        	    "1. Reserve Items\n" +
    	        "2. Restore Items\n" +
    	        "3. View Reservation\n" +
    	        "4. Exit\n" +
    	        "Enter your choice (1-4):" +
    	        "1. Catalog Search\n" +
                "2. Reservation And Renewal\n" +
                "3. Event And Workshop Schedule\n" +
                "4. Library Information\n" +
                "5. Exit\n" +
                "Enter your choice (1-5):" +
    	        "1. View Events\n" +
    	        "2. Register for Events\n" +
    	        "3. Exit\n" +
    	        "Enter your choice (1-3):" +
    	        "1. Catalog Search\n" +
                "2. Reservation And Renewal\n" +
                "3. Event And Workshop Schedule\n" +
                "4. Library Information\n" +
                "5. Exit\n" +
                "Enter your choice (1-5):"+
    	        "Library Location and Hours Informations\n" +
        		"Public Library (In city center) --> Available for Monday to Saturday. Weekdays --> 8.00 to 22.00\n" +
        		"Private Library (Next to the public cultural center )--> Avaliable for 7/24 hours\n" +
                "Public Library (Inside main campus) --> Available for Monday to Saturday. Weekdays --> 8.00 to 22.00\n" +
                "1. Catalog Search\n" +
                "2. Reservation And Renewal\n" +
                "3. Event And Workshop Schedule\n" +
                "4. Library Information\n" +
                "5. Exit\n" +
                "Enter your choice (1-5):";
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void testCatalogSearchMenuInvalid() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("abc\n\n48\n\n4\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.catalogSearch();
        
        Assert.assertTrue(result);
        String expectedOutput = "1. Search Books\n" +
                "2. Search Movies\n" +
                "3. Search Music\n" +
                "4. Exit\n" +
                "Enter your choice (1-4):" +
                "Invalid choice. Please enter a number.\n" +
                "1. Search Books\n" +
                "2. Search Movies\n" +
                "3. Search Music\n" +
                "4. Exit\n" +
                "Enter your choice (1-4):" +
                "Invalid choice. Please try again.\n" +
                "1. Search Books\n" +
                "2. Search Movies\n" +
                "3. Search Music\n" +
                "4. Exit\n" +
                "Enter your choice (1-4):";
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void testCatalogSearchMenuValid() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("1\ninvalidinput\n\n2\ninvalidinput\n\n3\ninvalidinput\n\n4\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.catalogSearch();
        Assert.assertTrue(result);
        String expectedOutput = "1. Search Books\n" +
                "2. Search Movies\n" +
                "3. Search Music\n" +
                "4. Exit\n" +
                "Enter your choice (1-4):" +
                "Please write book name you want to search, please pay attention to upper and lower case letters.\n" +
        	    "(A correct example: Crime and Punishment):\n" +
        	    "Sorry... The book you are looking for is not available.\n" +
                "1. Search Books\n" +
                "2. Search Movies\n" +
                "3. Search Music\n" +
                "4. Exit\n" +
                "Enter your choice (1-4):" +
                "Please write movie name you want to search, please pay attention to upper and lower case letters.\n" +
        	    "(A correct example: Into the Wild):\n" +
        	    "Sorry... The movie you are looking for is not available.\n" +
                "1. Search Books\n" +
                "2. Search Movies\n" +
                "3. Search Music\n" +
                "4. Exit\n" +
                "Enter your choice (1-4):" +
                "Please write music name you want to search, please pay attention to upper and lower case letters.\n" +
        	    "(A correct example: Castle of Glass):\n" +
        	    "Sorry... The music you are looking for is not available.\n" +
                "1. Search Books\n" +
                "2. Search Movies\n" +
                "3. Search Music\n" +
                "4. Exit\n" +
                "Enter your choice (1-4):";
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void SearchBooksValid() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("\nUncle Vanya\n\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.searchBooks();
        Assert.assertTrue(result);
        String expectedOutput = "Please write book name you want to search, please pay attention to upper and lower case letters.\n" +
        	    "(A correct example: Crime and Punishment):\n" +
        	    "The book Uncle Vanya is available.\n";   
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void SearchMoviesValid() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("\nThe Prestige\n\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.searchMovies();
        Assert.assertTrue(result);
        String expectedOutput = "Please write movie name you want to search, please pay attention to upper and lower case letters.\n" +
        	    "(A correct example: Into the Wild):\n" +
        	    "The movie The Prestige is available.\n";   
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void SearchMusicValid() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("\nOhne Dich\n\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.searchMusic();
        Assert.assertTrue(result);
        String expectedOutput = "Please write music name you want to search, please pay attention to upper and lower case letters.\n" +
        	    "(A correct example: Castle of Glass):\n" +
        	    "The music Ohne Dich is available.\n";   
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void testReservationAndRenewalInvalid() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("abc\n\n48\n\n4\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.reservationAndRenewal();
        Assert.assertTrue(result);
        String expectedOutput = "1. Reserve Items\n" +
        		"2. Restore Items\n" +
        		"3. View Reservation\n" +
        		"4. Exit\n" +
        		"Enter your choice (1-4):" +
                "Invalid choice. Please enter a number.\n" +
                "1. Reserve Items\n" +
        		"2. Restore Items\n" +
        		"3. View Reservation\n" +
        		"4. Exit\n" +
        		"Enter your choice (1-4):" +
                "Invalid choice. Please try again.\n" +
                "1. Reserve Items\n" +
        		"2. Restore Items\n" +
        		"3. View Reservation\n" +
        		"4. Exit\n" +
        		"Enter your choice (1-4):";
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void testReservationAndRenewalValid() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("1\n4\n2\n\n3\n\n4\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.reservationAndRenewal();
        Assert.assertTrue(result);
        String expectedOutput = "1. Reserve Items\n" +
        		"2. Restore Items\n" +
        		"3. View Reservation\n" +
        		"4. Exit\n" +
        		"Enter your choice (1-4):" +
        		"1. Reserve Books\n" +
    	        "2. Reserve Movies\n" +
    	        "3. Reserve Music\n" +
    	        "4. Exit\n" +
    	        "Enter your choice (1-4):" +
                "1. Reserve Items\n" +
        		"2. Restore Items\n" +
        		"3. View Reservation\n" +
        		"4. Exit\n" +
        		"Enter your choice (1-4):" +
        		"You have no borrowed material.\n" +
                "1. Reserve Items\n" +
        		"2. Restore Items\n" +
        		"3. View Reservation\n" +
        		"4. Exit\n" +
        		"Enter your choice (1-4):" +
        		"You have no borrowed material.\n" +
        		"1. Reserve Items\n" +
        		"2. Restore Items\n" +
        		"3. View Reservation\n" +
        		"4. Exit\n" +
        		"Enter your choice (1-4):";
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void reservationScreenLoginInvalid() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("Invalid User\n\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.reservationScreenLogin();
        Assert.assertFalse(result);
        String expectedOutput = "Please register with your user name.\n" +
        		"Write your user name:\n" + 
        		"The username you entered is not registered. Please check your entry.\n";
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void reserveItemsTest() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("1\n\n\n2\n\n\n3\n\n\n4\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.reserveItems();
        Assert.assertTrue(result);
        String expectedOutput = "1. Reserve Books\n" +
    	        "2. Reserve Movies\n" +
    	        "3. Reserve Music\n" +
    	        "4. Exit\n" +
    	        "Enter your choice (1-4):" +
    	        "Please write book name you want to reserve, please pay attention to upper and lower case letters.\n" +
        	    "(A correct example: Crime and Punishment):\n" +
        	    "Sorry, the book is not available.\n" +
        	    "1. Reserve Books\n" +
    	        "2. Reserve Movies\n" +
    	        "3. Reserve Music\n" +
    	        "4. Exit\n" +
    	        "Enter your choice (1-4):" +
        	    "Please write movie name you want to reserve, please pay attention to upper and lower case letters.\n" +
        	    "(A correct example: Into the Wild):\n" + 
        	    "Sorry, the movie is not available.\n" +
        	    "1. Reserve Books\n" +
    	        "2. Reserve Movies\n" +
    	        "3. Reserve Music\n" +
    	        "4. Exit\n" +
    	        "Enter your choice (1-4):" +
        	    "Please write music name you want to reserve, please pay attention to upper and lower case letters.\n" +
        	    "(A correct example: Castle of Glass):\n" +
        	    "Sorry, the music is not available.\n" +
        	    "1. Reserve Books\n" +
    	        "2. Reserve Movies\n" +
    	        "3. Reserve Music\n" +
    	        "4. Exit\n" +
    	        "Enter your choice (1-4):";
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void reserveItemsInvalid() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("abc\n\n48\n\n4\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.reserveItems();
        Assert.assertTrue(result);
        String expectedOutput = "1. Reserve Books\n" +
    	        "2. Reserve Movies\n" +
    	        "3. Reserve Music\n" +
    	        "4. Exit\n" +
    	        "Enter your choice (1-4):" +
                "Invalid choice. Please enter a number.\n" +
                "1. Reserve Books\n" +
    	        "2. Reserve Movies\n" +
    	        "3. Reserve Music\n" +
    	        "4. Exit\n" +
    	        "Enter your choice (1-4):" +
                "Invalid choice. Please try again.\n" +
                "1. Reserve Books\n" +
    	        "2. Reserve Movies\n" +
    	        "3. Reserve Music\n" +
    	        "4. Exit\n" +
    	        "Enter your choice (1-4):";
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void reserveBooksValid() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("\nUncle Vanya\n\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.reserveBook();
        Assert.assertTrue(result);
        String expectedOutput = "Please write book name you want to reserve, please pay attention to upper and lower case letters.\n" +
        	    "(A correct example: Crime and Punishment):\n" +
        	    "The book Uncle Vanya is available.\n" +
        	    "Uncle Vanya is reserved by Example User\n";
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void reserveMovieValid() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("\nInto the Wild\n\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.reserveMovie();
        Assert.assertTrue(result);
        String expectedOutput = "Please write movie name you want to reserve, please pay attention to upper and lower case letters.\n" +
        	    "(A correct example: Into the Wild):\n" +
        	    "The movie Into the Wild is available.\n" +
        	    "Into the Wild is reserved by Example User\n";
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
    
    @Test
    public void reserveMusicValid() throws IOException, InterruptedException{
    	ByteArrayInputStream inContent = new ByteArrayInputStream("\nMockingbird\n\n".getBytes());
        System.setIn(inContent);

        librarysystem = new Librarysystem(System.in, System.out);
        boolean result = librarysystem.reserveMusic();
        Assert.assertTrue(result);
        String expectedOutput = "Please write music name you want to reserve, please pay attention to upper and lower case letters.\n" +
        	    "(A correct example: Castle of Glass):\n" +
        	    "The music Mockingbird is available.\n" +
        	    "Mockingbird is reserved by Example User\n";
        assertEquals(expectedOutput, outContent.toString());
        System.setIn(null);
        System.setOut(null);
    }
}