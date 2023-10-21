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

       // booksArray.addAll(booksAdd);
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

        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        System.out.println("1 - Search by barcode \n2 - Search by title ");
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
        for (int i = 0; i < sz; i++) {
            System.out.println(booksArray.get(i).toString());

        } // end for loop

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

        book.setDueDate(toDate);
        book.setStatus("Out");
        System.out.println("Barcode: "+ book.getBarcode() + " Title: " + book.getTitle() +
                "Author: "+ book.getAuthor() + " Status: " + book.getStatus() + " Due Date: " + book.getDueDate());

        int sz = booksArray.size();
        editRecord(sz);
    //    UpdateArray(sz);

        int j = booksArray.indexOf(book);
        if (item.equals(book.getBarcode())) {
            System.out.println("You selected: " + booksArray.get(j).toString());
            if (booksArray.contains(booksArray.get(j))) {
                book.setDueDate(toDate);
                book.setStatus("Out");
                System.out.println("Status: " + book.getStatus() + " Due Date: " + book.getDueDate());


            } else {
                System.out.println("Invalid Selection.");
            }
        }



        /*-------------------------------------------------------
        if (booksArray.contains(booksArray.get(item))) {

            book.setDueDate(toDate);
            System.out.println(book.getDueDate());
            book.setStatus("Checked Out");
            System.out.println("Due date is: " + newDate);

            System.out.println(booksArray.get(item));
        } else {
            System.out.println("Item not found.");
        }
      -------------------------------------------------*/


      //  System.out.println(booksArray.get(item).toString());




    /* --------------------------------------------
        if (item.equals(booksArray.())) {
            System.out.println(code.getBarcode());
            LocalDate date = LocalDate.now();
            LocalDate newDate = date.plusDays(30);
            System.out.println("Item to checkout is: " + book.getTitle());
            System.out.println("Due date is: " + newDate);

            due.setDueDate(String.valueOf(newDate));
            newStatus.setStatus("Checked Out");
        } else {
            System.out.println("Item not found.");
        }
    ------------------------------------------*/


    //    int szOut = booksArray.size();
    //    UpdateArray(szOut);

    } // end checkout


    public static void editRecord(int sz) {
        String tempFile = "temp.txt";
        File oldFile = new File("BookList.txt");
        File newFile = new File(tempFile);
        Scanner x;

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            x = new Scanner(new File("BookList.txt"));
            x.useDelimiter("[,\n]");

            for (int i=0; i<sz; i++) {
                pw.write(booksArray.get(i).toString() + ("\n"));
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File("BookList.txt");
            newFile.renameTo(dump);

        } catch (IOException e) {
            System.out.println("Update Record error - " + e);
        }
    }

    /**
     * void method to request the array, obtain from the user
     * which item they would like to check in.  The method then
     * updates the status and the due date of the item, and
     * then updates the array.
     *
     */
    public static void checkIn() {

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
            inStatus.setStatus("In");
        } else {
            System.out.println("Item not found.");
        }

        int szIn = booksArray.size();
        UpdateArray(szIn);
    } // end checkin

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


    @Override
    public void editBookList(String barcode, String title, String author, String status, String dueDate) {
        super.editBookList(barcode, title, author, status, dueDate);
    }
} // end BookPackage.FileList



