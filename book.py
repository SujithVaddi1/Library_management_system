class Book:
    def __init__(self, title: str, author: str, price: float):
        self.title = title
        self.author = author
        self.price = price

    def display_book_info(self):
        print(f"Title: {self.title}, Author: {self.author}, Price: ₹{self.price}")
