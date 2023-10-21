package BookPackage;
/**
 * File Name: 	LibraryTest.java
 * Author:     	Roslyn Gilmour
 * Course:		CEN-3024C
 * Professor:	Mary Walauskis
 * Description:	This program will open a String file called BookPackage.BookList.txt.
 * 				It will call the readFile method to read the file,
 * 				and return the list.
 * 				The main method will then call the ? method
 * 				to create the new file data-sorted.txt, and then write
 * 				the sorted data to the new file.
 * Date:		10/8/23
 */

import java.io.*;
import java.util.*;

/**
 * BookPackage.Main class contains main method
 */
public class Main {

    /**
     * main class to obtain the text file, display the switch menu,
     * and obtain the menu choice from the user.
     * @param args          No args String containing text.
     */
    public static void main(String[] args)  {

        System.out.println();
        System.out.println();
        System.out.println("Welcome to the Library!");
        System.out.println();

        // get file name to open
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        File bookList = new File(scanner.nextLine());

        char choice;
        do {
            choice = getChoice();

            switch (choice) {

                case 'R': // read file
                    FileList.read(bookList);
                    break;
                case 'L': // list all items in the file
                    FileList.list(bookList);
                    break;
                case 'A': // add a new item
                    FileList.addItem();
                    break;
                case 'D': // delete or remove item from file
                   FileList.deleteItem();
                    break;
                case 'C': // Checkout an item
                    FileList.checkOut();
                    break;
                case 'I': // Checkin an item
                    FileList.checkIn();
                    break;
                case 'Q': //quit
                    System.out.println("Thank you. Have a nice day.");
                    break;
                default: // input error catch
                    System.out.println("Invalid Selection.  Please try again");
                    break;
            } // end switch

        } while (choice != 'Q');

    } // end main

    /**
     * Method to display the menu and obtain the user's menu choice.
     * @return      returns the user's text choice.
     */
    public static char getChoice(){
        char result;
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("MENU OPTIONS: ");
        System.out.println("R - Read the file");
        System.out.println("L - List all items");
        System.out.println("A - Add a new item");
        System.out.println("D - Delete an item");
        System.out.println("C - Checkout an item");
        System.out.println("I - Checkin an item");
        System.out.println("Q - Quit program\n");
        System.out.print("Enter selection: ");
        System.out.println();
        result = input.nextLine().toUpperCase().charAt(0); // converts the character entered into a capital letter

        return result;
    } // end method getChoice
} // end BookPackage.Main