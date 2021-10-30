package book_borrowing_system;

import java.util.Date;

public class Borrowing {

    private String borowNo;
    private Date borrowingDate;
    private Book[] books;
    private Member member;
    private int numBooksBorrowing;
    static int currBorrowingIndex = 0;

    public Borrowing() {
    }

    public Borrowing(String borowNo, Date borrowingDate, Book[] books, Member member, int numBooksBorrowing) {
        this.borowNo = borowNo;
        this.borrowingDate = borrowingDate;
        this.books = books;
        this.member = member;
        this.numBooksBorrowing = numBooksBorrowing;
    }

    public String getBorowNo() {
        return borowNo;
    }

    public void setBorowNo(String borowNo) {
        this.borowNo = borowNo;
    }

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public Book getBooks(int index) {
        return books[index];
    }

    public void setBooks(Book books, int index) {
        this.books[index] = books;
    }

    public Member getMember() {
        return member;
    }

    public Book[] getAllBooks() {
        return books;
    }
    
    
    public void setMember(Member member) {
        this.member = member;
    }

    public int getNumBooksBorrowing() {
        return numBooksBorrowing;
    }

    public void setNumBooksBorrowing(int numBooksBorrowing) {
        this.numBooksBorrowing = numBooksBorrowing;
    }

    public static int getCurrBorrowingIndex() {
        return currBorrowingIndex;
    }

    public static void incCurrBorrowingIndex() {
        Borrowing.currBorrowingIndex++;
    }

}
