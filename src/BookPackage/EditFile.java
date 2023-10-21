package BookPackage;
/*
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
 *          read the file
 *          delete an item
 *          checkout an item
 *          checkin an item
 */
public class EditFile extends BookList{

    static ArrayList<BookList> booksArray = new ArrayList<>();

    /**
     * ArrayList method to obtain the text file, scan the contents,
     * convert the string content to objects, and store the objects
     * in the array booksArray.
     * @param bookList                  Text file containing the list
     *                                  of string content.
     */
    public static void read(File bookList) {

        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(bookList));

            while ((line = br.readLine()) != null) {
                String[] items = line.split(",");
                BookList newList = getBookList(items);
                booksArray.add(newList);
            } // end while

        } catch(FileNotFoundException open) {
            System.out.println("Unable to open.");
        } catch (IOException read) {
            System.out.println("Unable to read file.");
        }
        System.out.println("File Read.");
        System.out.println();

        System.out.println("Number of books in list: " + booksArray.size());

    } // end read

    /**
     * Static method to access the array and store the variables
     * into the array.
     * @param items     array variable to access the list of
     *                  items.
     * @return          returns the updated array.
     */
    private static BookList getBookList(String[] items) {
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
        return newList;
    }

    public static void addItem() {

        int code;
        String addTitle;
        String addAuthor;
        String addStatus = "In";
        String addDate = "null";
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Enter the barcode: ");
        code = scanner.nextInt();
        String addCode = String.valueOf(code);
        System.out.print("Enter the title: ");
        addTitle = scanner1.nextLine();
        System.out.print("Enter the author: ");
        addAuthor = scanner2.nextLine();
        System.out.println();

        BookList newBook = new BookList();
        newBook.editBookList(addCode, addTitle, addAuthor, addStatus, addDate);
        booksArray.add(newBook);

        int sz = booksArray.size();
        UpdateArray(sz);

    }
    /**
     * void method to request the array, obtain from the user
     * which item they would like to delete.  The method then
     * deletes the item, updates the array, and prints the array.
     *
     */
    public static void deleteItem() {

        int result;
        int option;
        String[] array = new String[]{};

        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        System.out.println("To delete an item select: ");
        System.out.println("1 - To search by barcode \n2 - To search by title ");
        System.out.print("Enter option: ");
        option = sc.nextInt();
        System.out.println();
        if (option == 1) {
            System.out.print("Enter the barcode: ");
            result = sc.nextInt();
            String item = String.valueOf(result);
            booksArray.removeIf(book -> Objects.equals(book.getBarcode(), item));
        } else if (option == 2) {
            System.out.print("Enter the title: ");
            String title = sc1.nextLine();
            booksArray.removeIf(book -> Objects.equals(book.getTitle(), title));
        } else {
            System.out.println("Invalid Selection.");
        }

        int sz = booksArray.size();
        UpdateArray(sz);

    } // end deleteItem

    /**
     * void method to request the array, obtain from the user
     * which item they would like to check out.  The method then
     * updates the status and the due date of the item, and
     * then updates the array.
     *
     */
    public static void checkOut() {

        BookList book = new BookList();

        LocalDate date = LocalDate.now();
        LocalDate newDate = date.plusDays(30);
        String toDate = String.valueOf(newDate);

        int num;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter barcode to checkout: ");
        num = input.nextInt();
        String item = String.valueOf(num);
        int i;
        for (i=0; i<booksArray.size(); i++) {
            String[] array = String.valueOf(booksArray.get(i)).split(",");
            for(String a : array) {
                if (Objects.equals(a, item)) {
                    book.setBarcode(item);
                    book.setTitle(String.valueOf(array[1]));
                    book.setAuthor(String.valueOf(array[2]));
                    book.setStatus("Out");
                    book.setDueDate(toDate);
                    System.out.println("Checking out: \n"
                            + "Barcode: " + book.getBarcode() +"\nTitle: "+ book.getTitle() +"  Author: "
                            + book.getAuthor() +"\nStatus: "+ book.getStatus()
                            +"        Due Date: "+ book.getDueDate());
                    int sz = booksArray.size();
                    String cde = book.getBarcode();
                    String tle = book.getTitle();
                    String auth = book.getAuthor();
                    String stat = book.getStatus();
                    String dte = book.getDueDate();
                    String[] bookUpdate = new String[]{cde,tle,auth,stat,dte};

                    booksArray.removeIf(obj -> Objects.equals(obj.getBarcode(), item));

                    BookList newBook = new BookList();
                    newBook.editBookList(cde,tle,auth,stat,dte);
                    booksArray.add(newBook);

                    UpdateArray(sz);
                }
            }
        }
    } // end checkout

    /**
     * void method to request the array, obtain from the user
     * which item they would like to check in.  The method then
     * updates the status and the due date of the item, and
     * then updates the array.
     *
     */
    public static void checkIn() {

        BookList book = new BookList();

        int num;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter barcode to check in: ");
        num = input.nextInt();
        String item = String.valueOf(num);
        int i;
        for (i=0; i<booksArray.size(); i++) {
            String[] array = String.valueOf(booksArray.get(i)).split(",");
            for(String a : array) {
                if (Objects.equals(a, item)) {
                    book.setBarcode(item);
                    book.setTitle(String.valueOf(array[1]));
                    book.setAuthor(String.valueOf(array[2]));
                    book.setStatus("In");
                    book.setDueDate("null");

                    int sz = booksArray.size();
                    String cde = book.getBarcode();
                    String tle = book.getTitle();
                    String auth = book.getAuthor();
                    String stat = book.getStatus();
                    String dte = book.getDueDate();
                //    String[] bookUpdate = new String[]{cde,tle,auth,stat,dte};

                    booksArray.removeIf(obj -> Objects.equals(obj.getBarcode(), item));

                    BookList newBook = new BookList();
                    newBook.editBookList(cde,tle,auth,stat,dte);
                    booksArray.add(newBook);

                    System.out.println("Item Checking In: \n");
                    System.out.println("Barcode: " + book.getBarcode() +"\nTitle: "+ book.getTitle() +"  Author: "
                            + book.getAuthor() +"\nStatus: "+ book.getStatus()
                            +"        Due Date: "+ book.getDueDate());

                    UpdateArray(sz);
                }
            }
        }
    } // end checkin

    /**
     * Method to update the array data
     * @param sz        Parameter to hold the size of the array.
     */
    private static void UpdateArray(int sz) {

        try {
            FileWriter fw = new FileWriter("BookList.txt");
            Writer output = new BufferedWriter(fw);
            for (int i=0; i<sz; i++) {
                output.write(booksArray.get(i).toString() + ("\n"));
            }
            output.close();
            fw.close();
            System.out.println("File updated.");
        } catch (IOException fos) {
            System.out.println("fos error.");
        }
    }

    /**
     * Void method to override the BookList Method
     * @param barcode       Parameter to hold the due date
     * @param title         Parameter to hold the title
     * @param author        Parameter to hold the author
     * @param status        Parameter to hold the status
     * @param dueDate       Parameter to hold the dueDate
     */
    @Override
    public void editBookList(String barcode, String title, String author, String status, String dueDate) {
        super.editBookList(barcode, title, author, status, dueDate);
    }
} // end BookPackage.FileList




