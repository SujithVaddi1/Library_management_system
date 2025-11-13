# Book_management
Detailed Architecture

The project follows a modular, object-oriented architecture, separating concerns across multiple Python files. Each module handles one responsibility, promoting better maintainability and readability.

Modules Overview
File	Responsibility
book.py	Defines the Book entity with properties and representation methods
library.py	Manages the collection of books and core operations (add, remove, list)
user.py	Provides an interface for users to interact with the library
exceptions.py	Contains custom exception classes
main.py	Acts as the entry point and handles CLI interaction

The architecture uses composition:
User → interacts with → Library → manages → Books


OOP Concepts Used
 1. Encapsulation

Each class encapsulates its own data and behavior:

Book encapsulates title, author, and price

Library encapsulates its book list and operations

User hides implementation details of how books are managed

All attributes (title, author, price) are accessed only through class methods.

 2. Abstraction

Users interact with simplified methods like:

add_book_to_library()

view_books()

They don’t need to know how the library is storing books internally.

 3. Inheritance (Optional via Exceptions)

BookNotFoundException inherits from Python’s built-in Exception class:

class BookNotFoundException(Exception):
    pass


This allows custom error handling while reusing existing exception behavior.

 4. Composition

The User object has a Library object.

The Library object has many Book objects.

Composition is used instead of inheritance because:

relationships are “has-a”, not “is-a”

more flexible and realistic for real-world modeling

 5. Polymorphism (Implicit in Python)

Python's dynamic behavior and overriding of display_book_info() enables polymorphism:

book.display_book_info()


Any object with this method can be passed, giving polymorphic behavior.
