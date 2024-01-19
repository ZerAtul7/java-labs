package com.kroll;

public class Decoder {
    public static String decode(String message) {
        if (message == null || message.isEmpty()) {
            return message;
        }

        String decodedVowels = replaceVowels(message);
        String decodedConsonants = replaceConsonants(decodedVowels);

        return decodedConsonants;
    }

    private static String replaceVowels(String message) {
        return message
                .replaceAll("1", "a")
                .replaceAll("2", "e")
                .replaceAll("3", "i")
                .replaceAll("4", "o")
                .replaceAll("5", "u");
    }

    private static String replaceConsonants(String message) {
        char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};
        StringBuilder decodedMessage = new StringBuilder();

        for (char letter : message.toCharArray()) {
            if (Character.isLetter(letter)) {
                char lowerCaseLetter = Character.toLowerCase(letter);
                if (isConsonant(lowerCaseLetter)) {
                    char newLetter = getNewConsonant(consonants, lowerCaseLetter);
                    decodedMessage.append(Character.isUpperCase(letter) ? Character.toUpperCase(newLetter) : newLetter);
                } else {
                    decodedMessage.append(letter);
                }
            } else {
                decodedMessage.append(letter);
            }
        }
        return decodedMessage.toString();
    }

    private static boolean isConsonant(char letter) {
        return "bcdfghjklmnpqrstvwxyz".indexOf(letter) != -1;
    }

    private static char getNewConsonant(char[] consonants, char letter) {
        char lowerCaseLetter = Character.toLowerCase(letter);
        int index = new String(consonants).indexOf(lowerCaseLetter);
        int newIndex = (index + 1) % consonants.length;
        return consonants[newIndex];
    }
    //
}
