package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library_StreamAPI {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Преступление и наказание", "Федор Дрсоевский", 1866));
        books.add(new Book("Евгений Онегин", "Алесандр Пушкин", 1833));
        books.add(new Book("Война и мир", "Лев Толстой", 1869));
        books.add(new Book("Мастер и Маргарита", "Михаил Булгаков", 1967));
        books.add(new Book("Мастер и Маргарита", "Михаил Булгаков", 1967));

        List<Book> findAuthor = books.stream()
                .filter(book -> "Лев Толстой"
                        .equals(book.getAuthor()))
                .toList();
        System.out.println("Книги Льва Толстого: " + findAuthor);

        List<Book> findByYear = books.stream()
                .filter(book -> book.getYear() > 1900).toList();
        System.out.println("Книги после 1900 г.: " + findByYear);

        List<String> uniqueBooks = books.stream()
                .map(Book::getTitle)
                .distinct() // Метод .distinct() используется для удаления повторяющихся элементов из потока данных
                .toList();
        System.out.printf("Уникальные книги: %s", uniqueBooks);

    }
}
