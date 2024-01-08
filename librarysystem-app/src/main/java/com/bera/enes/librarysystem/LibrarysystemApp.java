package com.bera.enes.librarysystem;
import java.io.IOException;
public class LibrarysystemApp {

	public static void main(String[] args) throws IOException, InterruptedException {
		Librarysystem librarysystem = new Librarysystem(System.in, System.out);
		librarysystem.mainMenu();
	  }

}
