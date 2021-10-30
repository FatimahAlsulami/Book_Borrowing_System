
package book_borrowing_system;

import java.util.Date;
import java.io.*;
import java.util.*;

public class Book_Borrowing_System {

/* 
Name:Fatima alsulami

 */
  private  static int uniqID = 1000;

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");

        File PrintFile = new File("output.txt");
        PrintWriter output = new PrintWriter(PrintFile);
        Scanner input = new Scanner(inputFile);

        int TotalNumberOfBook = input.nextInt();
        Book[] books = new Book[TotalNumberOfBook];

        int NumberOfMember = input.nextInt();
        Member[] member = new Member[NumberOfMember];

        int BorrowingBookAllowed = input.nextInt();
        Borrowing[] borrowing = new Borrowing[NumberOfMember * BorrowingBookAllowed];

        if (!inputFile.exists()) {
            System.out.println("the file does not exist");
            System.exit(0);
        }
        //------------------------------------------------------------------------------------------------------

        output.println("###########################################");
        output.println("***** Welcome to Library Borrowing System *****");
        output.println("###########################################");
        //------------------------------------------------------------------------------------------------------

        String comand = input.next();
        do {
            //"add_Book" command, the system will add the book details based on the total number of books available in the system 

            if (comand.equalsIgnoreCase("add_Book")) {
                inputBook(input, output, books);
//add_Member command, the system will add the member details taking into account the total number of members allowed in the system 
            } else if (comand.equalsIgnoreCase("add_Member")) {
                inputMember(input, output, member, NumberOfMember);
//Through this command, the system will create a new borrowing for a member based on the number of borrowing requests allowed in the system 
            } else if (comand.equalsIgnoreCase("Borrow")) {
                Borrowing(input, member, books, BorrowingBookAllowed, borrowing, output);

            } else if (comand.equalsIgnoreCase("Search_Borrowing")) {
                SearchBorrowing(borrowing, input, output);

            } else if (comand.equalsIgnoreCase("Book_Status")) {
                BookStatus(input, books, output);

            }

            comand = input.next();
        } while (!(comand.equalsIgnoreCase("Quit")));
        Quite(output);

    }
    //------------------------------------------------------------------------------------------------------

    
    //This method will be used to read the book details from the input file and add the book details to the system
    public static void inputBook(Scanner input, PrintWriter output, Book[] books) {
        Book p1 = new Book();
        p1.setBookNo(input.next());
        p1.setTitle(input.next());
        p1.setAuthor(input.next());
        p1.setNumCopies(input.nextInt());
        p1.setRemCopies(p1.getNumCopies());

        if (Book.currBookIndex < books.length) {

            output.println("Book " + p1.getBookNo() + " Successfully Added");
            output.println("###########################################");
            books[Book.currBookIndex] = p1;
            Book.currBookIndex++;

        } else {
            output.println("Book " + p1.getBookNo() + " Was not Added \n You exceed the maximum number of Books");
            output.println("###########################################");

        }
    }
//------------------------------------------------------------------------------------------------------

    
    //This method will be used to read the member details from the file and enter member details to the system
    public static void inputMember(Scanner input, PrintWriter output, Member[] member, int MaxNumberOfMember) {
        Member M1 = new Member();
        M1.setName(input.next());
        M1.setId(input.nextInt());
        M1.setType(input.next());
        if (Member.currMemberIndex < MaxNumberOfMember) {
            output.println("Member " + M1.getName() + " Successfully Added");
            output.println("###########################################");

            member[Member.currMemberIndex] = M1;
            Member.currMemberIndex++;

        } else {
            output.println("Member " + M1.getName() + " Was not Added. \n You exceed the maximum number of Member");
            output.println("###########################################");
        }
    }

//------------------------------------------------------------------------------------------------------
  
    public static void Borrowing(Scanner input, Member[] member, Book[] book, int BorrowingBookAllowed,
            Borrowing[] borrowing, PrintWriter output) {
        //check the member id that the member wants to borrow. If the member id is not in the system, the system should display an error message
        boolean foundID = false;
        int ID = input.nextInt();
        Member memberTMP = null;
        for (int i = 0; i < Member.getCurrMemberIndex(); i++) {
            if (member[i].getId() == ID) {

                memberTMP = member[i];
                foundID = true;
                break;
            }
        }
        if (!foundID) {
            output.println("Member " + ID + " was not registered in the System");
            output.println("###########################################");
            return;
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////
        
        
      
        int NumberOfBox = input.nextInt();
        int bookborrowed = 0;
        for (int i = 0; i < Borrowing.getCurrBorrowingIndex(); i++) {

            if (borrowing[i].getMember().getId() == ID) {
                bookborrowed += borrowing[i].getNumBooksBorrowing();
            }
        }

        int NumberOfBoxPLUSbookborrowed = (NumberOfBox + bookborrowed);
        if (NumberOfBoxPLUSbookborrowed > BorrowingBookAllowed) {
            output.println("---the required plus the previous borrowed books is exceeded the maximum of Allowed ");
            output.println("###########################################");
            return;
        }
        
          //will check the books’ no to be borrowed in current borrowing request 
        String[] IdBook = new String[NumberOfBox];
        Book[] booksTEMB = new Book[NumberOfBox];
        boolean flag = false;
        for (int i = 0; i < NumberOfBox; i++) {
            
            IdBook[i] = input.next();
            for (int j = 0; j < Book.getCurrBookIndex(); j++) {
                if (book[j].getBookNo().equals(IdBook[i])) {
                    booksTEMB[i] = book[j];
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                output.println("No Record found with the Book code " + IdBook[i]);
                output.println("###########################################");
                return;
            }
        }
          ////////////////////////////////////////////////////////////////////////////////////////////////
        // cheack remainig Book
        //If the number of requested copies exceed the number of remaining books for the required books, the system will display an error message 
        boolean REmaingBook = true;
        for (int i = 0; i < booksTEMB.length; i++) {
            // book[i]
            if (booksTEMB[i].getRemCopies() == 0) {
                REmaingBook = false;
                output.println("Number of remaining copies for " + booksTEMB[i].getBookNo() + " is not enough\n"
                        + "Member with Id   " + ID + " could not borrow  any Books");
                output.println("###########################################");

                return;
            }
 
        }
     
          ////////////////////////////////////////////////////////////////////////////////////////////////
        Book[] booksT = booksTEMB;
        Member members = memberTMP;
        Date borrowingDate = new Date();
            //unique borrowing record 
        String borowNo = "BR" + uniqID++;
        int numBooksBorrowing = NumberOfBox;
        for (int i = 0; i < booksTEMB.length; i++) {
           booksTEMB[i].setRemCopies(booksTEMB[i].getRemCopies() - 1);
            
        }
        
        
          ////////////////////////////////////////////////////////////////////////////////////////////////
        Borrowing b = new Borrowing(borowNo, borrowingDate, booksTEMB, memberTMP, numBooksBorrowing);
        borrowing[Borrowing.getCurrBorrowingIndex()] = b;
        Borrowing.incCurrBorrowingIndex();
        output.println("Member with Id " + ID + " Successfully Borrowed " + NumberOfBox + " Books");
        output.println("###########################################");
    }

//------------------------------------------------------------------------------------------------------
    
   /// display complete book status and details which includes title, author,  etc
    public static void BookStatus(Scanner input, Book[] books, PrintWriter output) {

        String flag = input.next();
        for (int i = 0; i < Book.getCurrBookIndex(); i++) {
            if (books[i].getBookNo().equalsIgnoreCase(flag)) {
                output.println(" Information for Book " + books[i].getBookNo());
                output.println(" Title: " + books[i].getTitle());
                output.println(" Author :" + books[i].getAuthor());
                output.println(" Number of copies :" + books[i].getNumCopies());
                output.println(" Number of Remaining copies: " + books[i].getRemCopies());
                output.println("###########################################\n");
                System.out.println();

                return;
            }
        }

        output.println("No Record found with the Book Code " + flag);
        output.println("###########################################");

    }
//------------------------------------------------------------------------------------------------------

    //print all books (info) in the borrwoing object 
    public static void ListBook(Book[] borrowing, PrintWriter output) {
        for (int i = 0; i < borrowing.length; i++) {
            output.print("Title: " + borrowing[i].getTitle());
            output.print(" Author:" + borrowing[i].getAuthor());
            output.print(" # of Copies:" + borrowing[i].getNumCopies());
            output.print(" # of Remaining Copies:" + borrowing[i].getRemCopies() + "\n");
            output.println("###########################################");
        }

    }
    //------------------------------------------------------------------------------------------------------

    
    //This method will search and display the details of a single borrowing given its BRN code.
    public static void SearchBorrowing(Borrowing[] borrowing, Scanner input, PrintWriter output) {
        String BB = input.next();

        for (int i = 0; i < Borrowing.getCurrBorrowingIndex(); i++) {
            if (borrowing[i].getBorowNo().equalsIgnoreCase(BB)) {
                output.println("Borrowing Information for  BR#  :" + BB);
                output.println(" Member Name: " + borrowing[i].getMember().getName());
                output.println(" Borrowing Date:" + borrowing[i].getBorrowingDate());
                output.println("Number of Borrowing books:" + borrowing[i].getNumBooksBorrowing());

                // print all books (info) in the borrwoing object **
                output.println("Books List: ");
                ListBook(borrowing[i].getAllBooks(), output);
                output.println();
                
                return;
            }
        }

        output.println("No Record found with the BR " + BB);
        output.println("###########################################");

    }
    //------------------------------------------------------------------------------------------------------

    public static void Quite(PrintWriter output) {

        output.println("Thank you for using the books booking System, Good Bye!");
        output.println();
        output.println("###########################################");
        output.flush();
        output.close();

    }

}




    
    
   
