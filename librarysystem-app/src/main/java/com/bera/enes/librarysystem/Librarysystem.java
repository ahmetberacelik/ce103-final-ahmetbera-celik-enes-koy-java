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
		/**
	 * @brief Manages the reservation and renewal operations in the library system.
	 *
	 * This method presents a menu to the user, allowing them to perform various reservation and renewal operations,
	 * such as reserving items, restoring items, viewing reservations, or exiting the menu.
	 *
	 * @return True if the reservation and renewal operations are successfully completed, false otherwise.
	 * @throws IOException If an I/O error occurs during user input.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public boolean reservationAndRenewal() throws IOException, InterruptedException{
	    boolean isRunning = true;
	    while (isRunning) {
	        clearScreen();
	        out.print("1. Reserve Items\n");
	        out.print("2. Restore Items\n");
	        out.print("3. View Reservation\n");
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
	                reserveItems();
	                break;
	            case 2:
	            	restoreItems();
	                break;
	            case 3:
	            	viewReservation();
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
	 * @brief Handles the user login for reservation operations.
	 *
	 * This method prompts the user to register with their username. If the entered username is registered,
	 * it welcomes the user and sets them as the active user.
	 *
	 * @return True if the user login is successful, false otherwise.
	 * @throws IOException If an I/O error occurs during user input.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	boolean reservationScreenLogin() throws IOException, InterruptedException{
	    clearScreen();
	    out.print("Please register with your user name.\n");
	    out.print("Write your user name:\n");
	    scanner.nextLine(); 
	    String temporary_username = scanner.nextLine();
	    for (String registeredUserName : registered_user_name) {
	        if (registeredUserName.equals(temporary_username)) {
	            out.print("Welcome " + registeredUserName + "\n");
	            active_user = registeredUserName;
	            take_enter_input();
	            return true;
	        }
	    }
	    out.print("The username you entered is not registered. Please check your entry.\n");
	    take_enter_input();
	    return false;
	}
	/**
	 * @brief Manages the reservation operations for different types of items (books, movies, music).
	 *
	 * This method presents a menu to the user, allowing them to reserve different types of items,
	 * such as books, movies, or music. It handles the specific reservation logic based on the user's choice.
	 *
	 * @return True if the reservation operations are successfully completed, false otherwise.
	 * @throws IOException If an I/O error occurs during user input.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public boolean reserveItems() throws IOException, InterruptedException{
	    boolean isRunning = true;
	    while (isRunning) {
	        clearScreen();
	        out.print("1. Reserve Books\n");
	        out.print("2. Reserve Movies\n");
	        out.print("3. Reserve Music\n");
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
	                reserveBook();
	                break;
	            case 2:
	            	reserveMovie();
	                break;
	            case 3:
	                reserveMusic();
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
	 * @brief Reserves a book for the active user.
	 *
	 * This method prompts the user to enter the name of the book they want to reserve, and if the book is available,
	 * it reserves the book for the active user. The reserved book is added to the list of reserved items, and its availability
	 * in the catalog is updated.
	 *
	 * @return True if the book is successfully reserved, false otherwise.
	 * @throws IOException If an I/O error occurs during user input.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public boolean reserveBook() throws IOException, InterruptedException{
		clearScreen();
        out.print("Please write book name you want to reserve, please pay attention to upper and lower case letters.\n");
        out.print("(A correct example: Crime and Punishment):\n");
        scanner.nextLine();
        String bookQuery = scanner.nextLine();
        for (int i = 0; i < books.length; i++) {
            if (books[i].equals(bookQuery)) {
                reservedItems[reservedItemCount++] = books[i] + " is reserved by " + active_user;
                out.print("The book " + books[i] + " is available.\n");
                out.print(books[i] + " is reserved by " + active_user + "\n");
                books[i] = "";
                take_enter_input();
                return true;
            }
        }
        out.print("Sorry, the book is not available.\n");
        take_enter_input();
        return false;
    }
	/**
	 * @brief Reserves a movie for the active user.
	 *
	 * This method prompts the user to enter the name of the movie they want to reserve, and if the movie is available,
	 * it reserves the movie for the active user. The reserved movie is added to the list of reserved items, and its availability
	 * in the catalog is updated.
	 *
	 * @return True if the movie is successfully reserved, false otherwise.
	 * @throws IOException If an I/O error occurs during user input.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public boolean reserveMovie() throws IOException, InterruptedException{
		clearScreen();
	    out.print("Please write movie name you want to reserve, please pay attention to upper and lower case letters.\n");
	    out.print("(A correct example: Into the Wild):\n");
	    scanner.nextLine();
	    String movieQuery = scanner.nextLine();
	    for (int i = 0; i < movies.length; i++) {
            if (movies[i].equals(movieQuery)) {
                reservedItems[reservedItemCount++] = movies[i] + " is reserved by " + active_user;
                out.print("The movie " + movies[i] + " is available.\n");
                out.print(movies[i] + " is reserved by " + active_user + "\n");
                movies[i] = "";
                take_enter_input();
                return true;
            }
        }
	    out.print("Sorry, the movie is not available.\n");
	    take_enter_input();
	    return false;
	}
	/**
	 * @brief Reserves a music for the active user.
	 *
	 * This method prompts the user to enter the name of the music they want to reserve, and if the music is available,
	 * it reserves the music for the active user. The reserved music is added to the list of reserved items, and its availability
	 * in the catalog is updated.
	 *
	 * @return True if the music is successfully reserved, false otherwise.
	 * @throws IOException If an I/O error occurs during user input.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public boolean reserveMusic() throws IOException, InterruptedException{
		clearScreen();
	    out.print("Please write music name you want to reserve, please pay attention to upper and lower case letters.\n");
	    out.print("(A correct example: Castle of Glass):\n");
	    scanner.nextLine();
	    String musicQuery = scanner.nextLine();
	    for (int i = 0; i < musics.length; i++) {
            if (musics[i].equals(musicQuery)) {
                reservedItems[reservedItemCount++] = musics[i] + " is reserved by " + active_user;
                out.print("The music " + musics[i] + " is available.\n");
                out.print(musics[i] + " is reserved by " + active_user + "\n");
                musics[i] = "";
                take_enter_input();
                return true;
            }
        }
	    out.print("Sorry, the music is not available.\n");
	    take_enter_input();
	    return false;
	}
	/**
	 * @brief Restore items and clear reservations.
	 *
	 * This function allows the user to restore items and clear all reservations. 
	 * If the user confirms by entering 'Delete', all reservations are removed, 
	 * and the library's original item lists are restored.
	 *
	 * @throws InterruptedException if the thread is interrupted.
	 * @throws IOException if an I/O error occurs.
	 * @return true if the restoration is successful, false otherwise.
	 */
    public boolean restoreItems() throws InterruptedException, IOException {
        if (!viewReservation()) {
        	return false;
        }
        else {
            out.print("If you want to delete your all reservations, write 'Delete'. If you didn't, enter wrong input.\n");
            String deleteReservations = scanner.next();
            if (deleteReservations.equals("Delete")) {
                for (int i = 0; i < reservedItems.length; i++) {
                	reservedItems[i] = "";
                }
                reservedItemCount = 0;
                for(int j=0; j<5; j++) {
                	books[j] = originalBooks[j];
                	movies[j] = originalMovies[j];
                	musics[j] = originalMusics[j];
                }
                out.print("Your reservations has been cleaned.\n");
                take_enter_input();
                return true;
            } else {
                out.print("You entered wrong input!\n");
                take_enter_input();
                return false;
            }
        }
    }
    /**
     * @brief View user reservations.
     *
     * This function displays the items that the user has reserved.
     *
     * @throws InterruptedException if the thread is interrupted.
     * @throws IOException if an I/O error occurs.
     * @return true if reservations are found, false otherwise.
     */
    public boolean viewReservation() throws InterruptedException, IOException {
        boolean findReservation = false;
        clearScreen();
        for (int i = 0; i < reservedItems.length; i++) {
            if (reservedItems[i] != null && reservedItems[i].contains(active_user)) {
                out.print("The item " + reservedItems[i] + ".\n");
                findReservation = true;
                take_enter_input();
            }
        }
        
        if (!findReservation) {
            out.print("You have no borrowed material.\n");
            take_enter_input();
        }
        take_enter_input();
        return findReservation;
    }
    /**
     * @brief View and interact with the Events and Workshop schedule.
     *
     * This function allows the user to view upcoming events, register for events, 
     * or exit the Events and Workshop schedule.
     *
     * @throws IOException if an I/O error occurs.
     * @throws InterruptedException if the thread is interrupted.
     * @return true if the user successfully interacts with the schedule, false otherwise.
     */
    public boolean EventAndWorkshopSchedule() throws IOException, InterruptedException{
	    boolean isRunning = true;
	    while (isRunning) {
	        clearScreen();
	        out.print("1. View Events\n");
	        out.print("2. Register for Events\n");
	        out.print("3. Exit\n");
	        out.print("Enter your choice (1-3):");

	        if (!scanner.hasNextInt()) {
	            out.print("Invalid choice. Please enter a number.\n");
	            take_enter_input();
	            scanner.next();
	            continue;
	        }

	        int choice = scanner.nextInt();
	        switch (choice) {
	            case 1:
	            	viewEvents();
	                break;
	            case 2:
	            	if (registerForEvents()) break;
	            	else continue;
	            case 3:
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
     * @brief View upcoming events.
     *
     * This function displays information about upcoming events.
     *
     * @throws IOException if an I/O error occurs.
     * @throws InterruptedException if the thread is interrupted.
     */
    public void viewEvents() throws IOException, InterruptedException {
        clearScreen();
        out.print(events);
        take_enter_input();
    }
    /**
     * @brief Register for upcoming events.
     *
     * This function allows the user to register for upcoming events by entering their name
     * and selecting the desired event.
     *
     * @throws IOException if an I/O error occurs.
     * @throws InterruptedException if the thread is interrupted.
     * @return true if the registration is successful, false otherwise.
     */
    public boolean registerForEvents() throws IOException, InterruptedException {
        clearScreen();
        out.print(events);
        out.print("Please enter your name:\n");
        scanner.nextLine();
        String userName = scanner.nextLine();

        if (!eventUserCheck(userName)) {
            out.print("You entered an invalid username. Username must consist of letters.\n");
            take_enter_input();
            return false;
        }

        out.print("Please select the event you want to register:\n");
        String eventNo = scanner.next();
        if (!eventNoCheck(eventNo)) {
            out.print("You entered wrong option number. Please try again...\n");
            take_enter_input();
            return false;
        }

        out.print("A reservation has been made for " + userName + " for the event " + eventNo +
                    ". Simply stating your name at the entrance will be sufficient.\n");
        take_enter_input();
        return true;
    }
    /**
     * @brief Check if the entered event number is valid.
     *
     * This function checks if the entered event number is valid (1 or 2).
     *
     * @param eventNo The entered event number.
     * @return true if the event number is valid, false otherwise.
     */
    public static boolean eventNoCheck(String eventNo) {
        return eventNo.equals("1") || eventNo.equals("2");
    }
    /**
     * @brief Check if the entered username is valid.
     *
     * This function checks if the entered username consists of letters.
     *
     * @param userName The entered username.
     * @return true if the username is valid, false otherwise.
     */
    public static boolean eventUserCheck(String userName) {
        return userName.matches("[A-Za-z ]+");
    }
    /**
     * @brief Display library information.
     *
     * This function displays general information about the library.
     *
     * @throws IOException if an I/O error occurs.
     * @throws InterruptedException if the thread is interrupted.
     */
    public void libraryInformations() throws IOException, InterruptedException {
        clearScreen();
        out.print(lib_infos);
        take_enter_input();
    }
	/**
     * @brief Write library system data to a binary file.
     *
     * This function writes the current state of the library system data to a binary file.
     *
     * @param filename The name of the file to write the data.
     * @throws IOException if an I/O error occurs.
     */
    public void writeBinary(String filename) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
        	out.writeObject(reservedItems);
        	out.writeObject(reservedItemCount);
        	out.writeObject(books);
        	out.writeObject(movies);
        	out.writeObject(musics);
        	out.writeObject(registered_user_name);
        	out.writeObject(active_user);
        	out.writeObject(events);
        	out.writeObject(lib_infos);            
        }
    }
}
