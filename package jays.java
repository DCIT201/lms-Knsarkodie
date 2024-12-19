package jays.work;

class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook() {
        isAvailable = true;
    }
}

class Library {
    private Book[] books;
    private int bookCount;

    public Library(int capacity) {
        books = new Book[capacity];
        bookCount = 0;
    }

    public void addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount] = book;
            bookCount++;
        } else {
            System.out.println("Library is at full capacity.");
        }
    }

    public void displayBooks() {
        for (int i = 0; i < bookCount; i++) {
            Book book = books[i];
            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Available: " + book.isAvailable());
        }
    }

    public Book findBook(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null;
    }
}

class Patron {
    private String name;
    private Book borrowedBook;

    public Patron(String name) {
        this.name = name;
        this.borrowedBook = null; // Initially, no book is borrowed
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        if (borrowedBook == null && book.isAvailable()) {
            borrowedBook = book;
            book.borrowBook();
            System.out.println(name + " borrowed " + book.getTitle());
        } else if (borrowedBook != null) {
            System.out.println(name + " already has a borrowed book.");
        } else {
            System.out.println("The book is not available.");
        }
    }

    public void returnBook() {
        if (borrowedBook != null) {
            System.out.println(name + " returned " + borrowedBook.getTitle());
            borrowedBook.returnBook();
            borrowedBook = null;
        } else {
            System.out.println(name + " has no borrowed book to return.");
        }
    }
}

class App {
    public static void main(String[] args) {
        Library library = new Library(5);

        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));

        library.displayBooks();

        Patron patron = new Patron("Alice");

        Book bookToBorrow = library.findBook("1984");
        if (bookToBorrow != null) {
            patron.borrowBook(bookToBorrow);
        }

        library.displayBooks();

        patron.returnBook();

        library.displayBooks();
    }
}
