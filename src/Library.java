import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private static final String BOOKS_FILE = "books.txt";
    private static final String MEMBERS_FILE = "members.txt";

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public void issueBook(int bookId) {
        for (Book b : books) {
            if (b.getBookId() == bookId) {
                if (!b.isIssued()) {
                    b.setIssued(true);
                    System.out.println("Book issued successfully.");
                    return;
                } else {
                    throw new IllegalArgumentException("Book is already issued.");
                }
            }
        }
        throw new IllegalArgumentException("Book not found.");
    }

    public void returnBook(int bookId) {
        for (Book b : books) {
            if (b.getBookId() == bookId) {
                if (b.isIssued()) {
                    b.setIssued(false);
                    System.out.println("Book returned successfully.");
                    return;
                } else {
                    throw new IllegalArgumentException("Book was not issued.");
                }
            }
        }
        throw new IllegalArgumentException("Book not found.");
    }

    // Is method ko theek kar diya gaya hai (Line 50 wala error fix)
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        for (Book b : books) {
            String status = b.isIssued() ? "Issued" : "Available";
            System.out.println(b.getBookId() + " - " + b.getTitle() + " by " + b.getAuthor() + " | Status: " + status);
        }
    }

    // Step 3: File Persistence (Save)
    public void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book b : books) {
                bw.write(b.toString());
                bw.newLine();
            }
            try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(MEMBERS_FILE))) {
                for (Member m : members) {
                    bw2.write(m.toString());
                    bw2.newLine();
                }
            }
            System.out.println("Data saved to files.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Step 3: File Persistence (Load)
    public void loadData() {
        books.clear();
        members.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Book b = new Book(Integer.parseInt(parts[0]), parts[1], parts[2]);
                    b.setIssued(Boolean.parseBoolean(parts[3]));
                    books.add(b);
                }
            }
        } catch (IOException e) {
            System.out.println("No previous books found.");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(MEMBERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    members.add(new Member(Integer.parseInt(parts[0]), parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("No previous members found.");
        }
    }
}