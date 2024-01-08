/**
 * @file LibrarysystemApp.java
 * @brief Main class for the Library System application.
 * @details This file contains the main method to run the Library System application.
 * 
 * @package com.bera.librarysystem
 * @brief The com.bera.librarysystem package contains the main class and entry point for the Library System application.
 */
package com.bera.enes.librarysystem;
import java.io.IOException;
/**
 * @brief Main class for the Library System application.
 *
 * This class contains the main method to run the Library System application.
 */
public class LibrarysystemApp {
	/**
     * @brief Main method to start the Library System application.
     *
     * This method creates an instance of the Librarysystem class, invokes the mainMenu method,
     * and writes the library system data to a binary file.
     *
     * @param args Command line arguments (not used in this application).
     * @throws IOException if an I/O error occurs.
     * @throws InterruptedException if the thread is interrupted.
     */
	public static void main(String[] args) throws IOException, InterruptedException {
		Librarysystem librarysystem = new Librarysystem(System.in, System.out);
		librarysystem.mainMenu();
		librarysystem.writeBinary("LibraryData.bin");
	  }
}
