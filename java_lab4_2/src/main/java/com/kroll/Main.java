package com.kroll;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the encrypted message (or type 'exit' to quit): ");
            String encryptedMessage = scanner.nextLine();

            if (encryptedMessage.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            String decodedMessage = Decoder.decode(encryptedMessage);
            System.out.println("Decoded message: " + decodedMessage);
        }
    }
    //
}
