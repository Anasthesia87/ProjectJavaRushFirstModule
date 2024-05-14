package Decrypt;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

import static Validate.FileValidator.isСipherKeyValid;
import static Validate.FileValidator.validateFileName;

public class Decrypt {

    public static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', ' '};

    public static void decryptTextFromFile() {

        System.out.println("You have entered Decrypt!");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, specify the full path to the source file with the text (encoded)");
        //String pathFileEncodedText = C:\Users\Vendetta\IdeaProjects\CaesarCipher\src\main\java\Files\EncodedText2.txt;
        String pathFileEncodedText = scanner.next();

        System.out.println("Validating file with the text (encoded)...");

        validateFileName(pathFileEncodedText);

        System.out.println("Please, specify the full path to the file to record the results with the decoded text");
        //String pathFileNotEncodedFile = C:\Users\Vendetta\IdeaProjects\CaesarCipher\src\main\java\Files\NotEncodedText2.txt;
        String pathFileNotEncodedText = scanner.next();

        System.out.println("Validating file to record the results with encoded text...");

        validateFileName(pathFileNotEncodedText);

        int cipherKey;
        do {
            System.out.println("Please, enter the key which will be used to decrypt: ");
            cipherKey = scanner.nextInt();
            if(isСipherKeyValid(cipherKey)) {
                System.out.println("So, the key for decryption: " + cipherKey);
                System.out.println("Start decryption text...");
                break;
            } else {
                System.out.println("Unfortunately, you entered the wrong key. Try again, choose a number from 1 to 26.");
            }
        } while(true);

        if (cipherKey > ALPHABET.length) {
            cipherKey = cipherKey % ALPHABET.length;
        }

        try (FileReader reader = new FileReader(pathFileEncodedText);
             FileWriter writer = new FileWriter(pathFileNotEncodedText)) {
            char[] buffer = new char[75000];
            while (reader.ready()) {
                int real = reader.read(buffer);
                for (int i = 0; i < real; i++) {
                    char letter = buffer[i];
                    char lowLetter = Character.toLowerCase(letter);
                    for (int j = 0; j < ALPHABET.length; j++) {
                        if (lowLetter == ALPHABET[j]) {
                            if (j - cipherKey < 0) {
                                char newLetter = ALPHABET[ALPHABET.length - (cipherKey - j)];
                                buffer[i] = newLetter;
                                continue;
                            }
                            char newLetter = ALPHABET[j - cipherKey];
                            buffer[i] = Character.isUpperCase(letter) ? Character.toUpperCase(newLetter) : newLetter;
                            break;
                        }
                    }
                }
                writer.write(buffer, 0, real);
            }
            System.out.println("The text was successfully decrypted and written to a file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

