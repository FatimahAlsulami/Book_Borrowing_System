package book_borrowing_system;



public class Book {

     private String bookNo;
    private String title;
    private String author;
    private int numCopies;
    private int remCopies;
    static int currBookIndex = 0;

    public Book() {
    }

    public Book(String bookNo, String title, String author, int numCopies, int remCopies) {
        this.bookNo = bookNo;
        this.title = title;
        this.author = author;
        this.numCopies = numCopies;
        this.remCopies = remCopies;
    }

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }

  
    public void setRemCopies(int remCopies) {
        this.remCopies = remCopies;
    }
      public int getRemCopies() {
        return remCopies;
    }


    public static int getCurrBookIndex() {
        return currBookIndex;
    }

    public static void inCurrBookIndex() {
        Book.currBookIndex++;
    }

}


