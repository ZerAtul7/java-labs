package com.kroll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void testAddBook() {
        Library library = new Library();
        Book book = new Book("Книга 1", "Автор 1", "ISBN1", 2023);
        library.addBook(book);
        assertTrue(library.getItems().contains(book));
    }

    @Test
    public void testRemoveBookByIsbn() {
        Library library = new Library();
        Book book1 = new Book("Книга 1", "Автор 1", "ISBN1", 2023);
        Book book2 = new Book("Книга 2", "Автор 2", "ISBN2", 2019);
        library.addBook(book1);
        library.addBook(book2);
        library.removeItemByIsbn("ISBN1");
        assertFalse(library.getItems().contains(book1));
        assertTrue(library.getItems().contains(book2));
    }

    @Test
    public void testAddDVD() {
        Library library = new Library();
        DVD dvd = new DVD("DVD 1", "Режисер 1", "2023", "DVD1");
        library.addDVD(dvd);
        assertTrue(library.getItems().contains(dvd));
    }

    @Test
    public void testFindItemByTitle() {
        Library library = new Library();
        Book book = new Book("Книга 1", "Автор 1", "ISBN1", 2023);
        library.addBook(book);
        Item foundItem = library.findItemByTitle("Книга 1");
        assertEquals(book, foundItem);
    }
}


