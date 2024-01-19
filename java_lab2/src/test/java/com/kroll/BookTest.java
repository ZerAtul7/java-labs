package com.kroll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("Книга 1", "Автор 1", "ISBN1", 2023);
    }

    @Test
    public void testBookConstructor() {
        assertEquals("Книга 1", book.getTitle());
        assertEquals("Автор 1", book.getAuthor());
        assertEquals("ISBN1", book.getIdentifier());
        assertEquals(2023, book.getYear());
    }

    @Test
    public void testBookToString() {
        String expected = "Книга: Книга 1 (Автор: Автор 1, Рік видання: 2023, ISBN: ISBN1)";
        assertEquals(expected, book.toString());
    }
}
