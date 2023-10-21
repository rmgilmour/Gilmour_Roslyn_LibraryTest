package BookPackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class EditFileTest {

    // Create an object to test
    BookList testBook;

    static ArrayList<BookList> testArray = new ArrayList<>();

    @BeforeEach
    void setUp() {
        // Supply test data to the testBook object
        testBook = new BookList();
        String barcode = "150";
        String title = "TestTitle";
        String author = "TestAuthor";
        String status = "TestStatusIn";
        String dueDate = "TestDateNull";

        testBook.editBookList(barcode,title,author,status,dueDate);
        testArray.add(testBook);

        for (BookList a : testArray){
            System.out.println(a);
        }
    }

    @Test
    @DisplayName("Add Item Test")
    void addItemTest_BookList() {

        /*
        testBook = new BookList();

        String[] testAdd = new String[]{"151", "Test Title2", "Test Author2", "TestStatIn2", "TestDateNull2"};
        String addBarcode = testAdd[0];
        String addTitle = testAdd[1];
        String addAuthor = testAdd[2];
        String addStatus = testAdd[3];
        String AddDueDate = testAdd[4];


        System.out.println("Test print: " + testAdd[1]);
        System.out.println("TestBook: " + testBook);
        testBook.editBookList(addBarcode,addTitle,addAuthor,addStatus,AddDueDate);
        testArray.add(testBook);

        */

        int code = 175;
        String addCode = String.valueOf(code);
        String addTitle = "Testing Title 3";
        String addAuthor = "Test Author 3";
        String addStatus = "In";
        String addDate = "null";

        BookList testBook = new BookList();
        testBook.editBookList(addCode, addTitle, addAuthor, addStatus, addDate);
        testArray.add(testBook);

        int sz = testArray.size();
        try {
            FileWriter file = new FileWriter("BookListTest.txt");
            Writer out = new BufferedWriter(file);
            for (int i=0; i<sz; i++) {
                out.write(testArray.get(i).toString() + ("\n"));
            }
            out.close();
            file.close();
            System.out.println("File updated.");
        } catch (IOException fos) {
            System.out.println("fos error.");
        }
        for (BookList a : testArray) {
            System.out.println(a);
        }

        //assertEquals will compare two values and display a
        //message if they aren't equal.
        assertEquals(testArray,EditFile.booksArray, "Error:" + "The addItemTest failed to add items to arrayList");

    }// end addItemTest

    @Test
    @DisplayName("Delete Item Test")
    void deleteItemTest_BookList() {

        //barcode
        int result = 150;
        String item = String.valueOf(result);
        //title
        String title = "TestTitle";

        // Delete by barcode
        testArray.removeIf(book -> Objects.equals(book.getBarcode(), item));

        // Delete by Title
        testArray.removeIf(book -> Objects.equals(book.getTitle(), title));

        for (BookList a : testArray) {
            System.out.println(a);
        }

        //assertEquals will compare two values and display a
        //message if they aren't equal.
        assertEquals(testArray,EditFile.booksArray, "Error:" +
                "The DeleteItemTest failed to add items to arrayList");
    }

    @Test
    @DisplayName("Check Out Item Test")
    void checkOutTest_BookList() {

        BookList bookOut = new BookList();

        LocalDate date = LocalDate.now();
        LocalDate newDate = date.plusDays(30);
        String toDate = String.valueOf(newDate);

        //int num;
        //Scanner input = new Scanner(System.in);
        //System.out.print("Enter barcode to checkout: ");
        //num = input.nextInt();
        //String item = String.valueOf(num);
        String item = "150";
        int j;
        for (j=0; j<testArray.size(); j++) {
            String[] array = String.valueOf(testArray.get(j)).split(",");
            for(String a : array) {
                if (Objects.equals(a, item)) {
                    bookOut.setBarcode(item);
                    bookOut.setTitle(String.valueOf(array[1]));
                    bookOut.setAuthor(String.valueOf(array[2]));
                    bookOut.setStatus("Out");
                    bookOut.setDueDate(toDate);
                    System.out.println("Checking out: \n"
                            + "Barcode: " + bookOut.getBarcode() +"\nTitle: "+ bookOut.getTitle() +"  Author: "
                            + bookOut.getAuthor() +"\nStatus: "+ bookOut.getStatus()
                            +"        Due Date: "+ bookOut.getDueDate());
                    int sz = testArray.size();
                    String cde = bookOut.getBarcode();
                    String tle = bookOut.getTitle();
                    String auth = bookOut.getAuthor();
                    String stat = bookOut.getStatus();
                    String dte = bookOut.getDueDate();

                    testArray.removeIf(obj -> Objects.equals(obj.getBarcode(), item));

                    BookList testBook = new BookList();
                    testBook.editBookList(cde,tle,auth,stat,dte);
                    testArray.add(testBook);


                    try {
                        FileWriter file = new FileWriter("BookListTest.txt");
                        Writer out = new BufferedWriter(file);
                        for (int i=0; i<sz; i++) {
                            out.write(testArray.get(i).toString() + ("\n"));
                        }
                        out.close();
                        file.close();
                        System.out.println("File updated.");
                    } catch (IOException fos) {
                        System.out.println("fos error.");
                    }
                }
            }
        }

        //assertEquals will compare two values and display a
        //message if they aren't equal.
        assertEquals(testArray,EditFile.booksArray, "Error:" +
                "The CheckOutTest failed to update items in the arrayList");
    }

    @Test
    @DisplayName("Check In Item Test")
    void checkInTest_BookList() {

        BookList bookIn = new BookList();

        LocalDate date = LocalDate.now();
        LocalDate newDate = date.plusDays(30);
        String toDate = String.valueOf(newDate);

        //int num;
       // Scanner input = new Scanner(System.in);
     //   System.out.print("Enter barcode to checkout: ");
     //   num = input.nextInt();
    //    String item = String.valueOf(num);
        String item = "150";
        int j;
        for (j=0; j<testArray.size(); j++) {
            String[] array = String.valueOf(testArray.get(j)).split(",");
            for(String a : array) {
                if (Objects.equals(a, item)) {
                    bookIn.setBarcode(item);
                    bookIn.setTitle(String.valueOf(array[1]));
                    bookIn.setAuthor(String.valueOf(array[2]));
                    bookIn.setStatus("Out");
                    bookIn.setDueDate(toDate);
                    System.out.println("Checking out: \n"
                            + "Barcode: " + bookIn.getBarcode() +"\nTitle: "+ bookIn.getTitle() +"  Author: "
                            + bookIn.getAuthor() +"\nStatus: "+ bookIn.getStatus()
                            +"        Due Date: "+ bookIn.getDueDate());
                    int sz = testArray.size();
                    String cde = bookIn.getBarcode();
                    String tle = bookIn.getTitle();
                    String auth = bookIn.getAuthor();
                    String stat = bookIn.getStatus();
                    String dte = bookIn.getDueDate();

                    testArray.removeIf(obj -> Objects.equals(obj.getBarcode(), item));

                    BookList testBookIn = new BookList();
                    testBookIn.editBookList(cde,tle,auth,stat,dte);
                    testArray.add(testBookIn);

                    try {
                        FileWriter file = new FileWriter("BookListTest.txt");
                        Writer out = new BufferedWriter(file);
                        for (int i=0; i<sz; i++) {
                            out.write(testArray.get(i).toString() + ("\n"));
                        }
                        out.close();
                        file.close();
                        System.out.println("File updated.");
                    } catch (IOException fos) {
                        System.out.println("fos error.");
                    }
                }
            }
        }

        //assertEquals will compare two values and display a
        //message if they aren't equal.
        assertEquals(testArray,EditFile.booksArray, "Error:" +
                "The CheckInTest failed to update items in the arrayList");
    }

}