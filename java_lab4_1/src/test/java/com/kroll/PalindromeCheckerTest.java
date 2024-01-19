package com.kroll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PalindromeCheckerTest {
    @Test
    public void testIsPalindromeForPalindromes() {
        assertTrue(PalindromeChecker.isPalindrome("deified"));
        assertTrue(PalindromeChecker.isPalindrome("detartrated"));
        assertTrue(PalindromeChecker.isPalindrome("redivider"));
        assertTrue(PalindromeChecker.isPalindrome("repaper"));
        assertTrue(PalindromeChecker.isPalindrome("rotavator"));
    }
    @Test
    public void testIsPalindromeForNonPalindromes() {
        assertFalse(PalindromeChecker.isPalindrome("abobus"));
        assertFalse(PalindromeChecker.isPalindrome("bobster"));
        assertFalse(PalindromeChecker.isPalindrome("slaves"));
        assertFalse(PalindromeChecker.isPalindrome("dragon"));
        assertFalse(PalindromeChecker.isPalindrome("dutisgood"));
    }
    @Test
    public void testIsPalindromeForEmptyString() {
        assertTrue(PalindromeChecker.isPalindrome(""));
    }
    @Test
    public void testIsPalindromeForSingleCharacter() {
        assertTrue(PalindromeChecker.isPalindrome("a"));
        assertTrue(PalindromeChecker.isPalindrome("z"));
    }
    @Test
    public void testIsPalindromeForWhitespace() {
        assertTrue(PalindromeChecker.isPalindrome("Aibohphobia"));
        assertTrue(PalindromeChecker.isPalindrome("A man, a plan, a cat, a ham, a yak, a yam, a hat, a canal: Panama!"));
        assertTrue(PalindromeChecker.isPalindrome("Evil rats on no star live."));
    }
}

