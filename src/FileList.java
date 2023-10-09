/**
 * File Name: 	LibraryTest.java
 * Author:     	Roslyn Gilmour
 * Course:		CEN-3024C
 * Professor:	Mary Walauskis
 * Description:	This program will provide a Switch menu to obtain the
 *              user's menu choice.
 *              The first choice is to read a text file.  The readFile
 *              method will call the readFile method to read the file.
 *              This method will then convert the contents to objects
 *              and store the object values in an array for later use.
 *              The user can then select to either list the array
 *              contents via the listFile method, or remove a line item
 * 				from the array and return the list.
 * Date:		10/8/23
 */
import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * No return class that contains the methods to process the
 * user's menu choices.
 *          read
 *          list
 *          delete
 *          checkout
 *          checkin
 */
public class FileList {

    static ArrayList<BookList> booksArray = new ArrayList<>();
    private static ArrayList<BookList> books;

    /**
     * ArrayList method to obtain the text file, scan the contents,
     * convert the string content to objects, and store the objects
     * in the array booksArray.
     * @param bookList                  Text file containing the list
     *                                  of string content.
     * @return                          Returns the array containing the
     *                                  list of items as objects.
     * @throws FileNotFoundException    Exception to catch the condition
     *                                  where the file is not found.
     */
    public static ArrayList<BookList> read(File bookList) throws FileNotFoundException {

        Scanner s = new Scanner(bookList);

        while (s.hasNextLine()) {
            String line = s.nextLine();

            String[] items = line.split(",");

            // put all items into BookList object
            //int barcode = Integer.parseInt(items[0]);
            String barcode = items[0];
            String title = items[1];
            String author = items[2];
            String status = items[3];
            String dueDate = items[4];

            BookList newList = new BookList();
            newList.setBarcode(barcode);
            newList.setTitle(title);
            newList.setAuthor(author);
            newList.setStatus(status);
            newList.setDueDate(dueDate);
            booksArray.add(newList);

            System.out.println(newList.getBarcode());


        } // end while
        System.out.println("File Read.");
        System.out.println();

        return booksArray;
    } // end read

    /**
     * void method to list the contents of the array.
     */
    public void list(File bookList) throws FileNotFoundException {


        System.out.printf("--------------------------------------------------" +
                "--------------------------------------------------------%n");
        System.out.printf("|    ID    |    Title                            |" +
                "    Author            |    Status       |   Due Date   |%n");
        System.out.printf("--------------------------------------------------" +
                "--------------------------------------------------------%n");

        Scanner s = new Scanner(bookList);

        while (s.hasNextLine()) {
            String line = s.nextLine();

            String[] items = line.split(",");

            System.out.printf("| %-8s | %-35s | %-20s | %-15s | %-12s |%n", items[0], items[1], items[2], items[3], items[4]);


        } // end while
        System.out.println();
        System.out.printf("--------------------------------------------------" +
                "--------------------------------------------------------%n");
        System.out.println();
        System.out.println();

    } // end list

    /**
     * void method to request the array, obtain from the user
     * which item they would like to delete.  The method then
     * deletes the item, updates the array, and prints the array.
     * @param books            ArrayList parameter to obtain
     *                              the contents of the array.
     */
    public static void deleteItem(ArrayList<BookList> books) {

       // String content = Files.readString(Paths.get(bookList.toURI()));
       // LinkedList<String> newList = new LinkedList<>(Collections.singletonList(content));

        // print each line
        for(BookList str : books)
            System.out.println(str);

        int result;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter barcode number to be removed: ");
        result = input.nextInt();
        String item = String.valueOf(result);

       // Scanner s = new Scanner(String.valueOf(newList));
        Scanner s = new Scanner(String.valueOf(books));
        while (s.hasNextLine()) {
            String line = s.nextLine();

            String[] items = line.split(",");


            for (int i=0; i < items.length; i++) {
                if (item.equals(items[i])){
                   books.remove(result);
                }
            } // end for loop

        } // end while
        System.out.println(books);

    } // end DeleteItem

    /**
     * void method to request the array, obtain from the user
     * which item they would like to checkout.  The method then
     * updates the status and the due date of the item, and
     * then updates the array.
     * @param books            ArrayList parameter to obtain
     *                              the contents of the array.
     */
    public static void checkOut(ArrayList<BookList> books) throws IOException {


        //  String content = Files.readString(Paths.get(bookList.toURI()));

     //   List<String> newList = Arrays.asList(content);

        BookList obj = new BookList();
        BookList newStatus = new BookList();
        BookList due = new BookList();
        BookList code = new BookList();

        Scanner input = new Scanner(System.in);
        int num;
        System.out.println("Enter barcode to checkout: ");
        num = input.nextInt();
        String item = String.valueOf(num);

        if (item.equals(code.getBarcode())) {
            LocalDate date = LocalDate.now();
            LocalDate newDate = date.plusDays(30);
            System.out.println("Item to checkout is: " + obj.getTitle());
            System.out.println("Due date is: " + newDate);

            due.setDueDate(String.valueOf(newDate));
            newStatus.setStatus("Checked Out");
        } else {
            System.out.println("Item not found.");
        }
       // return newList;

    } // end checkout

    /**
     * void method to request the array, obtain from the user
     * which item they would like to checkin.  The method then
     * updates the status and the due date of the item, and
     * then updates the array.
     * @param books            ArrayList parameter to obtain
     *                              the contents of the array.
     */
    public static void checkIn(ArrayList<BookList> books) {

        BookList inObj = new BookList();
        BookList inBarcode = new BookList();
        BookList inDue = new BookList();
        BookList inStatus = new BookList();

        Scanner input = new Scanner(System.in);
        int num;
        System.out.println("Enter barcode to checkin: ");
        num = input.nextInt();
        String item = String.valueOf(num);

        if (item.equals(inBarcode.getBarcode())) {
            System.out.println("Item to checkin is: " + inObj.getTitle());
            inDue.setDueDate("null");
            inStatus.setStatus("in");
        } else {
               System.out.println("Item not found.");
        }
    } // end checkin



} // end FileList




