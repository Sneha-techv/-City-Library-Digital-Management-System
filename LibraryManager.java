import java.io.*;
import java.util.*;

public class LibraryManager {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();
        manager.loadFromFile();
        manager.mainMenu();
        manager.saveToFile();
        System.out.println("Exiting... Data saved successfully!");
    }

    public void mainMenu() {
        int choice;
        do {
            System.out.println("\n===== City Library Digital Management System =====");
            System.out.println("1. Add Book\n2. Add Member\n3. Issue Book\n4. Return Book\n5. Search Books\n6. Sort Books\n7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> addMember();
                case 3 -> issueBook();
                case 4 -> returnBook();
                case 5 -> searchBooks();
                case 6 -> sortBooks();
                case 7 -> saveToFile();
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 7);
    }

    private void addBook() {
        try {
            System.out.print("Enter Book ID: ");
            int id = sc.nextInt(); sc.nextLine();
            if (books.containsKey(id)) { System.out.println("Book ID already exists!"); return; }
            System.out.print("Enter Title: "); String title = sc.nextLine();
            System.out.print("Enter Author: "); String author = sc.nextLine();
            System.out.print("Enter Category: "); String category = sc.nextLine();
            books.put(id, new Book(id, title, author, category));
            saveBooksToFile();
            System.out.println("Book added successfully!");
        } catch (Exception e) { System.out.println("Error adding book: " + e.getMessage()); }
    }

    private void addMember() {
        try {
            System.out.print("Enter Member ID: ");
            int id = sc.nextInt(); sc.nextLine();
            if (members.containsKey(id)) { System.out.println("Member ID already exists!"); return; }
            System.out.print("Enter Name: "); String name = sc.nextLine();
            System.out.print("Enter Email: "); String email = sc.nextLine();
            members.put(id, new Member(id, name, email));
            saveMembersToFile();
            System.out.println("Member added successfully!");
        } catch (Exception e) { System.out.println("Error adding member: " + e.getMessage()); }
    }

    private void issueBook() {
        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();
        System.out.print("Enter Member ID: ");
        int memberId = sc.nextInt();

        Book b = books.get(bookId);
        Member m = members.get(memberId);

        if (b == null || m == null) { System.out.println("Invalid IDs!"); return; }
        if (b.isIssued()) { System.out.println("Book already issued!"); return; }

        b.markAsIssued();
        m.addIssuedBook(bookId);
        saveToFile();
        System.out.println("Book issued successfully!");
    }

    private void returnBook() {
        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();
        System.out.print("Enter Member ID: ");
        int memberId = sc.nextInt();

        Book b = books.get(bookId);
        Member m = members.get(memberId);

        if (b == null || m == null) { System.out.println("Invalid IDs!"); return; }

        b.markAsReturned();
        m.returnIssuedBook(bookId);
        saveToFile();
        System.out.println("Book returned successfully!");
    }

    private void searchBooks() {
        System.out.print("Enter search keyword: ");
        sc.nextLine();
        String keyword = sc.nextLine().toLowerCase();
        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().contains(keyword) ||
                b.getAuthor().toLowerCase().contains(keyword) ||
                b.getCategory().toLowerCase().contains(keyword)) {
                b.displayBookDetails();
            }
        }
    }

    private void sortBooks() {
        List<Book> list = new ArrayList<>(books.values());
        System.out.println("Sort by: 1. Title  2. Author");
        int opt = sc.nextInt(); sc.nextLine();

        if (opt == 1)
            Collections.sort(list);
        else
            list.sort(Comparator.comparing(Book::getAuthor));

        list.forEach(Book::displayBookDetails);
    }

    // ---------- File Handling ----------
    private void loadFromFile() {
        books.clear();
        members.clear();
        loadBooksFromFile();
        loadMembersFromFile();
    }

    private void loadBooksFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Book b = Book.fromString(line);
                if (b != null) books.put(b.getBookId(), b);
            }
        } catch (IOException ignored) {}
    }

    private void loadMembersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("members.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Member m = Member.fromString(line);
                if (m != null) members.put(m.getMemberId(), m);
            }
        } catch (IOException ignored) {}
    }

    private void saveBooksToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"))) {
            for (Book b : books.values()) bw.write(b.toString() + "\n");
        } catch (IOException e) { System.out.println("Error saving books."); }
    }

    private void saveMembersToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("members.txt"))) {
            for (Member m : members.values()) bw.write(m.toString() + "\n");
        } catch (IOException e) { System.out.println("Error saving members."); }
    }

    public void saveToFile() {
        saveBooksToFile();
        saveMembersToFile();
    }
}
