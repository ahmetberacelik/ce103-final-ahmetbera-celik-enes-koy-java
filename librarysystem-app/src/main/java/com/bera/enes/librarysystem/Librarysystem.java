/**
 * @file Librarysystem.java
 * @brief Contains the implementation of the Librarysystem class for managing a library system.
 * 
 * This file defines the Librarysystem class, which provides functionalities for catalog search,
 * reservation and renewal, event and workshop schedules, library information, and user interaction
 * through a main menu.
 *
 * @package com.bera.librarysystem
 * @brief The com.bera.librarysystem package contains all the classes and files related to the Library System App.
 */
package com.bera.enes.librarysystem;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
/**
 * @brief Librarysystem class to manage a library system.
 *
 * This class includes functionalities for catalog search, reservation and renewal, event and workshop schedule,
 * library information, and user interaction through the main menu.
 */
public class Librarysystem {
	private Scanner scanner; /**<Scanner for user input in the Library System. */
    private PrintStream out; /**<PrintStream for output in the Library System. */
    /**
     * @brief Constructor for the Librarysystem class.
     *
     * This constructor initializes a Librarysystem object with the specified input stream for user input
     * and output stream for printing messages.
     *
     * @param in The input stream for user input.
     * @param out The output stream for printing messages.
     */
    public Librarysystem(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }
    
    String[] reservedItems = new String[15];
	int reservedItemCount = 0;
    
    String[] books = {"Crime and Punishment", "Martin Eden", "Ruh Adam", "Uncle Vanya", "Kinyas ve Kayra"};
    String[] movies = {"Seven", "Into the Wild", "Donnie Darko", "The Prestige", "Batman Begins"};
    String[] musics = {"Castle of Glass", "Mockingbird", "Turn the Page", "Ohne Dich", "Nothing Else Matters"};
    
    String[] originalBooks = {"Crime and Punishment", "Martin Eden", "Ruh Adam", "Uncle Vanya", "Kinyas ve Kayra"};
    String[] originalMovies = {"Seven", "Into the Wild", "Donnie Darko", "The Prestige", "Batman Begins"};
    String[] originalMusics = {"Castle of Glass", "Mockingbird", "Turn the Page", "Ohne Dich", "Nothing Else Matters"};
    
    String[] registered_user_name = {"Ahmet Bera Celik","Enes Koy","Ugur Coruh","Yakup Eroglu","Hasan Basri Taskin"};
    
    String active_user = "Example User";
    final String events = "Upcoming Library Events\n" +
    		"1 - Reading incentive program for children (Two days later, 5 p.m)\n" +
    		"2 - Book chat with the author (Five days later, 10 a.m)\n";
    
    final String lib_infos = "Library Location and Hours Informations\n" +
    		"Public Library (In city center) --> Available for Monday to Saturday. Weekdays --> 8.00 to 22.00\n" +
    		"Private Library (Next to the public cultural center )--> Avaliable for 7/24 hours\n" +
            "Public Library (Inside main campus) --> Available for Monday to Saturday. Weekdays --> 8.00 to 22.00\n";
    /**
     * @brief Waits for the user to press Enter.
     *
     * This method is used to pause execution and wait for the user to press Enter.
     * @throws IOException If an I/O error occurs.
     */
    private void take_enter_input() throws IOException {
        System.in.read();
    }
    /**
     * @brief Clears the console screen.
     *
     * This method detects the operating system to determine the appropriate command for clearing the console screen.
     * On Windows, it uses the "cmd /c cls" command, while on other operating systems, it uses ANSI escape codes to clear the screen.
     *
     * @throws InterruptedException If the thread is interrupted while waiting for the process to complete.
     * @throws IOException If an I/O error occurs during the execution of the process.
     */
    public void clearScreen() throws InterruptedException, IOException {  
        String operatingSystem = System.getProperty("os.name");
        if (operatingSystem.contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            out.print("\033[H\033[2J");
            out.flush();
        }
   }
    /**
     * @brief Displays the main menu and handles user choices.
     *
     * This method continuously displays the main menu options and executes the corresponding functionality
     * based on the user's input until the user chooses to exit. It provides options for catalog search,
     * reservation and renewal, event and workshop schedules, and library information.
     *
     * @return True if the program should continue running, false if the user chooses to exit.
     * @throws IOException If an I/O error occurs during user input.
     * @throws InterruptedException If the thread is interrupted.
     */
	public boolean mainMenu() throws IOException, InterruptedException {
        boolean isRunning = true;
        while (isRunning) {
            clearScreen();
            out.print("1. Catalog Search\n");
            out.print("2. Reservation And Renewal\n");
            out.print("3. Event And Workshop Schedule\n");
            out.print("4. Library Information\n");
            out.print("5. Exit\n");
            out.print("Enter your choice (1-5):");
            if (!scanner.hasNextInt()) {
                out.print("Invalid choice. Please enter a number.\n");
                take_enter_input();
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    catalogSearch();
                    break;
                case 2:
                    if(!reservationScreenLogin()) break;
                    reservationAndRenewal();
                    break;
                case 3:
                    EventAndWorkshopSchedule();
                    break;
                case 4:
                    libraryInformations();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    out.print("Invalid choice. Please try again.\n");
                    take_enter_input();
                    break;
            }
        }
        return true;
    }
	/**
	 * @brief Manages catalog search functionality.
	 *
	 * This method allows the user to perform searches within the catalog, providing options to search for books,
	 * movies, and music. It displays a sub-menu for the user to make choices and calls corresponding search methods.
	 *
	 * @return True if the program should continue running, false if the user chooses to exit.
	 * @throws IOException If an I/O error occurs during user input.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public boolean catalogSearch() throws IOException, InterruptedException {
        boolean isRunning = true;
        while (isRunning) {
            clearScreen();
            out.print("1. Search Books\n");
            out.print("2. Search Movies\n");
            out.print("3. Search Music\n");
            out.print("4. Exit\n");
            out.print("Enter your choice (1-4):");

            if (!scanner.hasNextInt()) {
                out.print("Invalid choice. Please enter a number.\n");
                take_enter_input();
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    searchBooks();
                    break;
                case 2:
                    searchMovies();
                    break;
                case 3:
                    searchMusic();
                    break;
                case 4:
                    isRunning = false;
                    break;
                default:
                    out.print("Invalid choice. Please try again.\n");
                    take_enter_input();
                    break;
            }
        }
        return true;
    }
	/**
	 * @brief Searches for a book in the catalog.
	 *
	 * This method prompts the user to enter the name of the book they want to search for,
	 * taking into account upper and lower case letters. It then searches for the book in the catalog
	 * and provides the corresponding availability status.
	 *
	 * @return True if the book is found and available, false otherwise.
	 * @throws IOException If an I/O error occurs during user input.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public boolean searchBooks() throws IOException, InterruptedException{
		clearScreen();
		out.print("Please write book name you want to search, please pay attention to upper and lower case letters.\n");
	    out.print("(A correct example: Crime and Punishment):\n");
	    scanner.nextLine();
	    String book_query = scanner.nextLine();

	    for (int i = 0; i < books.length; i++) {
            if (books[i].equals(book_query)) {
                out.print("The book " + books[i] + " is available.\n");
                take_enter_input();
                return true;
            }
        }
	    out.print("Sorry... The book you are looking for is not available.\n");
	    take_enter_input();
        return false;
	    }
	/**
	 * @brief Searches for a movie in the catalog.
	 *
	 * This method prompts the user to enter the name of the movie they want to search for,
	 * taking into account upper and lower case letters. It then searches for the movie in the catalog
	 * and provides the corresponding availability status.
	 *
	 * @return True if the movie is found and available, false otherwise.
	 * @throws IOException If an I/O error occurs during user input.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public boolean searchMovies() throws IOException, InterruptedException{
		clearScreen();
		out.print("Please write movie name you want to search, please pay attention to upper and lower case letters.\n");
	    out.print("(A correct example: Into the Wild):\n");
	    scanner.nextLine();
	    String movie_query = scanner.nextLine();

	    for (int i = 0; i < movies.length; i++) {
            if (movies[i].equals(movie_query)) {
                out.print("The movie " + movies[i] + " is available.\n");
                take_enter_input();
                return true;
            }
        }
	    out.print("Sorry... The movie you are looking for is not available.\n");
	    take_enter_input();
        return false;
	    }
	/**
	 * @brief Searches for a music in the catalog.
	 *
	 * This method prompts the user to enter the name of the music they want to search for,
	 * taking into account upper and lower case letters. It then searches for the music in the catalog
	 * and provides the corresponding availability status.
	 *
	 * @return True if the music is found and available, false otherwise.
	 * @throws IOException If an I/O error occurs during user input.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public boolean searchMusic() throws IOException, InterruptedException{
		clearScreen();
		out.print("Please write music name you want to search, please pay attention to upper and lower case letters.\n");
	    out.print("(A correct example: Castle of Glass):\n");
	    scanner.nextLine();
	    String music_query = scanner.nextLine();

	    for (int i = 0; i < musics.length; i++) {
            if (musics[i].equals(music_query)) {
                out.print("The music " + musics[i] + " is available.\n");
                take_enter_input();
                return true;
            }
        }
	    out.print("Sorry... The music you are looking for is not available.\n");
	    take_enter_input();
        return false;
	    }
}
