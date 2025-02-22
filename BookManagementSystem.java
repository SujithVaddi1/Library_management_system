import java.util.ArrayList;
import java.util.Scanner;

// Custom exception for book not found
class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}

// Book Class (Encapsulation)
class Book {
    private String title;
    private String author;
    private double price;

    // Constructor
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getters and Setters
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Method to display book details
    public void displayBookInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", Price: $" + price);
    }

    // Method to compare books based on their title
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return title.equalsIgnoreCase(book.title); // Comparison based on title
    }

    @Override
    public int hashCode() {
        return title.toLowerCase().hashCode(); // hashCode based on title
    }
}

// Library Class (Collection Management)
class Library {
    private ArrayList<Book> books;

    // Constructor
    public Library() {
        books = new ArrayList<>();
    }

    // Add book to library
    public synchronized void addBook(Book book) {
        try {
            // Simulate delay
            Thread.sleep(500);
            if (books.contains(book)) {
                System.out.println("This book already exists in the library.");
            } else {
                books.add(book);
                System.out.println("Book added: " + book.getTitle());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error adding book.");
        }
    }

    // Remove book from library
    public synchronized void removeBook(String title) throws BookNotFoundException {
        try {
            // Simulate delay
            Thread.sleep(500);
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    books.remove(book);
                    System.out.println("Book removed: " + title);
                    return;
                }
            }
            throw new BookNotFoundException("Book not found: " + title);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error removing book.");
        }
    }

    // Display all books in the library
    public synchronized void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                book.displayBookInfo();
            }
        }
    }
}

// User Class (Interaction)
class User {
    private String name;

    // Constructor
    public User(String name) {
        this.name = name;
    }

    // Get user name
    public String getName() {
        return name;
    }

    // Method for user to add a book to library
    public void addBookToLibrary(Library library, Book book) {
        library.addBook(book);
    }

    // Method for user to remove a book from library
    public void removeBookFromLibrary(Library library, String bookTitle) {
        try {
            library.removeBook(bookTitle);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method for user to list all books in library
    public void viewBooks(Library library) {
        library.listBooks();
    }
}

// Main Class (Testing the Implementation with Threading)
public class BookManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Create a user
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        User user = new User(userName);

        // Create a separate thread for the menu loop
        Thread menuThread = new Thread(() -> {
            while (true) {
                try {
                    // Show menu options
                    System.out.println("\n--- Book Management System ---");
                    System.out.println("1. Add a book");
                    System.out.println("2. Remove a book");
                    System.out.println("3. View all books");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline left by nextInt()

                    switch (choice) {
                        case 1:
                            // Add a book
                            System.out.print("Enter book title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter book author: ");
                            String author = scanner.nextLine();
                            System.out.print("Enter book price: ");
                            double price;
                            try {
                                price = Double.parseDouble(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid price format. Please try again.");
                                break;
                            }
                            Book book = new Book(title, author, price);
                            user.addBookToLibrary(library, book);
                            break;

                        case 2:
                            // Remove a book
                            System.out.print("Enter the title of the book to remove: ");
                            String bookTitle = scanner.nextLine();
                            user.removeBookFromLibrary(library, bookTitle);
                            break;

                        case 3:
                            // View all books
                            System.out.println("\nBooks in Library:");
                            user.viewBooks(library);
                            break;

                        case 4:
                            // Exit the program
                            System.out.println("Exiting the program. Goodbye, " + user.getName() + "!");
                            scanner.close();
                            return;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });

        // Start the menu thread
        menuThread.start();
    }
}
