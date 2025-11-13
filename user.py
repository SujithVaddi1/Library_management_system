from .exceptions import BookNotFoundException


class User:
    def __init__(self, name: str, library):
        self.name = name
        self.library = library

    def add_book_to_library(self, book):
        self.library.add_book(book)

    def remove_book_from_library(self, title):
        try:
            self.library.remove_book(title)
        except BookNotFoundException as e:
            print(e)

    def view_books(self):
        self.library.list_books()
