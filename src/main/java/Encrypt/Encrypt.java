package Encrypt;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

import static Validate.FileValidator.isСipherKeyValid;
import static Validate.FileValidator.validateFileName;

public class Encrypt {
    public static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', ' '};

    public static void encryptTextFromFile() {

        System.out.println("You have entered Encrypt!");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, specify the full path to the source file with the text (not encoded)");
        //String pathFileNotEncodedText = C:\Users\Vendetta\IdeaProjects\CaesarCipher\src\main\java\Files\NotEncodedText1.txt
        String pathFileNotEncodedText = scanner.next();

        System.out.println("Validating file with the text (not encoded)...");

        validateFileName(pathFileNotEncodedText);

        System.out.println("Please, specify the full path to the file to record the results with encoded text");
        //String pathFileEncodedText = C:\Users\Vendetta\IdeaProjects\CaesarCipher\src\main\java\Files\EncodedText1.txt;
        String pathFileEncodedText = scanner.next();

        System.out.println("Validating file to record the results with encoded text...");

        validateFileName(pathFileEncodedText);

        int cipherKey;
        do {
            System.out.println("Please, enter the key which will be used to encrypt: ");
            cipherKey = scanner.nextInt();
            if(isСipherKeyValid(cipherKey)) {
                System.out.println("So, the key for encryption: " + cipherKey);
                System.out.println("Start encryption text...");
                break;
            } else {
                System.out.println("Unfortunately, you entered the wrong key. Try again, choose a number from 1 to 26.");
            }
        } while(true);

        try (FileReader reader = new FileReader(pathFileNotEncodedText);
             FileWriter writer = new FileWriter(pathFileEncodedText)) {
            char[] buffer = new char[75000];
            while (reader.ready()) {
                int real = reader.read(buffer);
                for (int i = 0; i < real; i++) {
                    char letter = buffer[i];
                    char lowLetter = Character.toLowerCase(letter);
                    for (int j = 0; j < ALPHABET.length; j++) {
                        if (lowLetter == ALPHABET[j]) {
                            if (j + cipherKey >= ALPHABET.length) {
                                int overIndex = (j + cipherKey) % ALPHABET.length;
                                char newLetter = ALPHABET[overIndex];
                                buffer[i] = newLetter;
                                continue;
                            }
                            char newLetter = ALPHABET[j + cipherKey];
                            buffer[i] = Character.isUpperCase(letter) ? Character.toUpperCase(newLetter) : newLetter;
                            break;
                        }
                    }
                }
                writer.write(buffer, 0, real);
            }
            System.out.println("The text was successfully encrypted and written to a file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}