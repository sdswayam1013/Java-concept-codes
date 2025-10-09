// Design a system to keep track of library books.
// Each record should store who added the book (librarian’s name).
// Each book should have a unique book ID.
// The system should allow printing the details of a book including who added it and its ID.


// Base entity representing common information
class LibraryRecord {
    private final String addedBy;  // librarian's name

    public LibraryRecord(String addedBy) {
        this.addedBy = addedBy;
    }

    public void logAddedBy() {
        System.out.println("Added by Librarian: " + addedBy);
    }
}

// Book entity extending base record
public class Book extends LibraryRecord {
    private final String bookId;

    public Book(String bookId, String addedBy) {
        super(addedBy); // call parent constructor
        this.bookId = bookId;
    }

    public void displayDetails() {
        super.logAddedBy();  // show librarian info
        System.out.println("Book ID: " + bookId);
    }

   
    public static void main(String[] args) {
        Book book = new Book("BK101", "Mr. Das");
        book.displayDetails();
    }
}
