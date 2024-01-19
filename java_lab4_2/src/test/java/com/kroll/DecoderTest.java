package com.kroll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecoderTest {

    @Test
    public void testDecode_emptyMessage() {
        String message = "";
        String expected = "";
        assertEquals(expected, Decoder.decode(message));
    }

    @Test
    public void testDecode_nullMessage() {
        String message = null;
        String expected = null;
        assertEquals(expected, Decoder.decode(message));
    }

    @Test
    public void testDecode_onlyNumbers() {
        String message = "12345";
        String expected = "aeiou";
        assertEquals(expected, Decoder.decode(message));
    }

    @Test
    public void testDecode_onlyConsonants() {
        String message = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        String expected = "cdfghjklmnpqrstvwxyzbCDFGHJKLMNPQRSTVWXYZB";
        assertEquals(expected, Decoder.decode(message));
    }

    @Test
    public void testDecode_mixedMessage() {
        String message = "G2kk4 V4qkc!";
        String expected = "Hello World!";
        assertEquals(expected, Decoder.decode(message));
    }
}
