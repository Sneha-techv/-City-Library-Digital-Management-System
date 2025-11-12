import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable {
    private int memberId;
    private String name;
    private String email;
    private List<Integer> issuedBooks;

    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.issuedBooks = new ArrayList<>();
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void addIssuedBook(int bookId) { issuedBooks.add(bookId); }
    public void returnIssuedBook(int bookId) { issuedBooks.remove(Integer.valueOf(bookId)); }

    public void displayMemberDetails() {
        System.out.println("Member ID: " + memberId + ", Name: " + name + ", Email: " + email);
        System.out.println("Issued Books: " + issuedBooks);
    }

    @Override
    public String toString() {
        return memberId + "," + name + "," + email + "," + issuedBooks.toString().replaceAll("[\\[\\] ]", "");
    }

    public static Member fromString(String data) {
        String[] parts = data.split(",");
        if (parts.length >= 3) {
            Member m = new Member(Integer.parseInt(parts[0]), parts[1], parts[2]);
            for (int i = 3; i < parts.length; i++) {
                try { m.issuedBooks.add(Integer.parseInt(parts[i])); } catch (Exception e) {}
            }
            return m;
        }
        return null;
    }
}
