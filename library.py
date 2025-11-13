from exceptions import BookNotFoundException
from book import Book

class Library:
    def __init__(self):
        self.books = []

    def add_book(self, book: Book):
        if any(b.title.lower() == book.title.lower() for b in self.books):
            print("This book already exists in the library.")
        else:
            self.books.append(book)
            print(f"Book added: {book.title}")

    def remove_book(self, title: str):
        for book in self.books:
            if book.title.lower() == title.lower():
                self.books.remove(book)
                print(f"Book removed: {title}")
                return
        raise BookNotFoundException(f"Book not found: {title}")

    def list_books(self):
        if not self.books:
            print("No books available.")
        else:
            for book in self.books:
                book.display_book_info()
