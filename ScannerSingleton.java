import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class ScannerSingleton {
    private static ScannerSingleton instance = null;
    private final Scanner scanner;

    private ScannerSingleton() {
        scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    public static ScannerSingleton getInstance() {
        if (instance == null) {
            instance = new ScannerSingleton();
        }
        return instance;
    }
    public void pressEnterToContinue() {
        instance.scanner.nextLine();
    }
    public String getNextLine() {
        boolean invalid = true;
        String value = null;
        while (invalid) {
            value = instance.scanner.nextLine();
            if (value != null && !value.isEmpty()) {
                invalid = false;
            } else {
                System.out.println("Du måste skriva något");
            }
        }
        return value;
    }

    public String getNextLineWithForbiddenValues(List<String> forbiddenValues, String errorMessage) {
        boolean invalid = true;
        String value = null;
        while (invalid) {
            value = instance.getNextLine();
            invalid = forbiddenValues.stream()
                    .map(String::toLowerCase)
                    .toList()
                    .contains(value.toLowerCase().trim());
            if (invalid) {
                System.out.println(errorMessage);
            }
        }
        return value;
    }

    public int getNextLineInt(int min, int max) {
        boolean invalid = true;
        int value = 0;
        while (invalid) {
            try {
                value = Integer.parseInt(instance.scanner.nextLine());
                if (value >= min && value <= max) {
                    invalid = false;
                } else {
                    System.out.println("Skriv ett nummer (" + min + "-" + max + ")");
                }
            } catch (NumberFormatException e) {
                System.out.println("Skriv ett nummer (" + min + "-" + max + ")");
            }
        }
        return value;
    }

    public static void close() {
        instance.scanner.close();
    }
}