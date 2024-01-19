package com.kroll;

public class PalindromeChecker {

    public static boolean isPalindrome(String word) {
        String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
        int left = 0;
        int right = cleanWord.length() - 1;

        while (left < right) {
            if (cleanWord.charAt(left) != cleanWord.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}



