import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Library Management System ===");
        library.loadData(); // Load existing data

        boolean running = true;
        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Display Books");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Save Data");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Enter Book ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();
                        library.addBook(new Book(id, title, author));
                        System.out.println("Book added.");
                        break;
                    case "2":
                        System.out.print("Enter Member ID: ");
                        int memId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        library.registerMember(new Member(memId, name));
                        System.out.println("Member registered.");
                        break;
                    case "3":
                        library.displayBooks();
                        break;
                    case "4":
                        System.out.print("Enter Book ID to issue: ");
                        library.issueBook(Integer.parseInt(scanner.nextLine()));
                        break;
                    case "5":
                        System.out.print("Enter Book ID to return: ");
                        library.returnBook(Integer.parseInt(scanner.nextLine()));
                        break;
                    case "6":
                        library.saveData();
                        break;
                    case "7":
                        library.saveData();
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (IllegalArgumentException e) {
                // Step 4: Handle invalid actions with exceptions
                System.out.println("ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter correct data.");
            }
        }
        scanner.close();
    }
}