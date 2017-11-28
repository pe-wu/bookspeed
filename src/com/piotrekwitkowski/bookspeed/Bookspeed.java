package com.piotrekwitkowski.bookspeed;

import java.util.ArrayList;

public class Bookspeed {

    private static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) throws BookNotFoundException, InterruptedException {
        int booksNumber = 100000;
        if ((args.length != 0)) booksNumber = Integer.parseInt(args[0]);
        System.out.println("Adding " + booksNumber + " books...");

        for (int i = 0; i<booksNumber; i++) {
            books.add(new Book(new BookDetails("ISBN", "author", "booktitle")));
        }
        System.out.println(booksNumber + " books added!");

        books.add(new Book(new BookDetails("ISBN2", "author2", "Special Title")));
        System.out.println((booksNumber+1) + " special book added!");

        System.out.println("3...");
        Thread.sleep(1000);
        System.out.println("2...");
        Thread.sleep(1000);
        System.out.println("1...");
        Thread.sleep(1000);

        System.out.println("Looking for special book in O(n)...");
        Book specialBook = getBookByTitle("Special Title");

        if (specialBook != null) {
            System.out.println(specialBook.getBookDetails().getTitle());
        }
    }


    private static Book getBookByTitle(String bookname) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getBookDetails().getTitle().equals(bookname)) {
                return book;
            }
        }
        throw new BookNotFoundException();
    }

    private static class Book {
        private BookDetails bookDetails;
        private int borrowsIndex;
        private User currentUser;

        Book(BookDetails bookDetails) {
            this.bookDetails = bookDetails;
            this.borrowsIndex = 0;
        }

        BookDetails getBookDetails() {
            return bookDetails;
        }
    }

    private static class BookDetails {
        private String isbn;
        private String author;
        private String title;

        BookDetails(String isbn, String author, String title) {
            this.isbn = isbn;
            this.author = author;
            this.title = title;
        }

        String getTitle() {
            return title;
        }

    }

    private static class BookNotFoundException extends Exception {
    }

    private class User {
        private String name;
        private int borrowedBooksIndex = 0;

        User(String name) {
            this.name = name;
        }
    }
}
