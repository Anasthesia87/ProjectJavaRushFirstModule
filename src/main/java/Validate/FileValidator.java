package Validate;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class FileValidator {
    private static final String FILENAME_PATTERN = "^[a-zA-Z0-9._-]+\\.txt$";
    private static final int ALPHABET_SIZE = 26;

    public static void validateFileName(String filePath) throws IllegalArgumentException {
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString();

        if(!Pattern.matches(FILENAME_PATTERN, fileName)) {
            throw new IllegalArgumentException("Unfortunately, the file name does not match the required format: " + FILENAME_PATTERN);
        }

    }

    public static boolean isСipherKeyValid(int cipherKey) {

        return cipherKey >= 0 && cipherKey < ALPHABET_SIZE;
    }
}
