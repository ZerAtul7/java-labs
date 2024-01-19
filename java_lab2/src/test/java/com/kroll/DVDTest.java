package com.kroll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DVDTest {
    private DVD dvd;

    @BeforeEach
    public void setUp() {
        dvd = new DVD("DVD 1", "Режисер 1", "2023", "DVDID1");
    }

    @Test
    public void testDVDConstructor() {
        assertEquals("DVD 1", dvd.getTitle());
        assertEquals("Режисер 1", dvd.getDirector());
        assertEquals("2023", dvd.getReleaseYear());
        assertEquals("DVDID1", dvd.getIdentifier());
    }

    @Test
    public void testDVDToString() {
        String expected = "DVD: DVD 1 (Режисер: Режисер 1, Рік випуску: 2023, Ідентифікатор: DVDID1)";
        assertEquals(expected, dvd.toString());
    }
}
