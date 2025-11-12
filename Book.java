import java.io.Serializable;

public class Book implements Serializable, Comparable<Book> {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private boolean isIssued;

    public Book(int bookId, String title, String author, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = false;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean isIssued() { return isIssued; }

    public void markAsIssued() { this.isIssued = true; }
    public void markAsReturned() { this.isIssued = false; }

    public void displayBookDetails() {
        System.out.println("Book ID: " + bookId + ", Title: " + title + ", Author: " + author +
                ", Category: " + category + ", Issued: " + isIssued);
    }

    @Override
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return bookId + "," + title + "," + author + "," + category + "," + isIssued;
    }

    public static Book fromString(String data) {
        String[] parts = data.split(",");
        if (parts.length == 5) {
            Book b = new Book(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]);
            b.isIssued = Boolean.parseBoolean(parts[4]);
            return b;
        }
        return null;
    }
}
