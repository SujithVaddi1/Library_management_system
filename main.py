from .library import Library
from .user import User
from .Book import Book


def main():
    library = Library()
    user_name = input("Enter your name: ")
    user = User(user_name, library)

    while True:
        try:
            print("\n--- Book Management System ---")
            print("1. Add a book")
            print("2. Remove a book")
            print("3. View all books")
            print("4. Exit")
            choice = input("Enter your choice: ")

            if choice == "1":
                title = input("Enter book title: ")
                author = input("Enter book author: ")

                # Validate price input
                try:
                    price = float(input("Enter book price: "))
                except ValueError:
                    print("Invalid price format. Please try again.")
                    continue

                book = Book(title, author, price)
                user.add_book_to_library(book)

            elif choice == "2":
                book_title = input("Enter the title of the book to remove: ")
                user.remove_book_from_library(book_title)

            elif choice == "3":
                print("\nBooks in Library:")
                user.view_books()

            elif choice == "4":
                print(f"Exiting the program. Goodbye, {user.name}!")
                break

            else:
                print("Invalid choice. Please try again.")

        except Exception as e:
            print(f"Error: {e}")

if __name__ == "__main__":
    main()
