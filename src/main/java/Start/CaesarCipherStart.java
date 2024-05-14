package Start;

import java.io.IOException;
import java.util.Scanner;

import static Decrypt.Decrypt.decryptTextFromFile;
import static Encrypt.Encrypt.encryptTextFromFile;

public class CaesarCipherStart {

    public static void main(String[] args) throws IOException {

        while (true) {

            Scanner scan = new Scanner(System.in);
            System.out.println("\n\n--------***Caesar Cipher***--------");
            System.out.println("Please, choose one of the following options and press enter:");
            System.out.println("1. Encrypt Text");
            System.out.println("2. Decrypt Text");
            System.out.println("3. Quit");

            int input = scan.nextInt();

            if (input == 1) {
                encryptTextFromFile();
            } else if (input == 2) {
                decryptTextFromFile();
            } else if (input == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Please enter a valid choice");
            }
        } // end while
    }// end main
}



