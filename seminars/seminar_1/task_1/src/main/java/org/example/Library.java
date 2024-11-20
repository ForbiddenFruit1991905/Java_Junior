package org.example;


import java.util.ArrayList;
import java.util.List;

public class Library {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Преступление и наказание", "Федор Дрсоевский", 1866));
        books.add(new Book("Евгений Онегин", "Алесандр Пушкин", 1833));
        books.add(new Book("Война и мир", "Лев Толстой", 1869));
        books.add(new Book("Мастер и Маргарита", "Михаил Булгаков", 1967));

        List<Book> findAuthor = new ArrayList<>();
        for (Book book : books) {
            if ("Лев Толстой".equals(book.getAuthor())){
                findAuthor.add(book);
            }
        }
        System.out.println("Книги Льва Толстого: " + findAuthor);

        List<Book> findByYear = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() > 1900){
                findByYear.add(book);
            }
        }
        System.out.println("Книги после 1900 г.: " + findByYear);

        List<String> uniqueBooks = new ArrayList<>();
        for (Book book : books) {
            if (/*book.getTitle() != null*/!uniqueBooks.contains((book.getTitle()))){
                uniqueBooks.add(book.getTitle());
            }
        }
        System.out.printf("Уникальные книги: %s", uniqueBooks);

    }
}
