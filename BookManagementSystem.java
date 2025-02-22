import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface BookOperations {
    void addBook(Book book);
    void removeBook(String title) throws BookNotFoundException;
    void listBooks();
}

class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}

class Book {
    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public void displayBookInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", Price: $" + price);
    }
}

class Library implements BookOperations {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    @Override
    public synchronized void addBook(Book book) {
        if (books.contains(book)) {
            System.out.println("This book already exists in the library.");
        } else {
            books.add(book);
            System.out.println("Book added: " + book.getTitle());
        }
    }

    @Override
    public synchronized void removeBook(String title) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                books.remove(book);
                System.out.println("Book removed: " + title);
                return;
            }
        }
        throw new BookNotFoundException("Book not found: " + title);
    }

    @Override
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

class User {
    private String name;
    private BookOperations bookOperations;

    public User(String name, BookOperations bookOperations) {
        this.name = name;
        this.bookOperations = bookOperations;
    }

    public void addBookToLibrary(Book book) {
        bookOperations.addBook(book);
    }

    public void removeBookFromLibrary(String bookTitle) {
        try {
            bookOperations.removeBook(bookTitle);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewBooks() {
        bookOperations.listBooks();
    }
}

public class BookManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        User user = new User(userName, library);

        while (true) {
            try {
                System.out.println("\n--- Book Management System ---");
                System.out.println("1. Add a book");
                System.out.println("2. Remove a book");
                System.out.println("3. View all books");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
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
                        user.addBookToLibrary(book);
                        break;
                    case 2:
                        System.out.print("Enter the title of the book to remove: ");
                        String bookTitle = scanner.nextLine();
                        user.removeBookFromLibrary(bookTitle);
                        break;
                    case 3:
                        System.out.println("\nBooks in Library:");
                        user.viewBooks();
                        break;
                    case 4:
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
    }
}
