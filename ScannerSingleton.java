import java.util.Scanner;

public class ScannerSingleton {
    private static ScannerSingleton instance = null;
    private final Scanner scanner;

    private ScannerSingleton() {
        scanner = new Scanner(System.in);
    }

    public static String getNextLine() {
        if (instance == null) {
            instance = new ScannerSingleton();
        }
        return instance.scanner.nextLine();
    }
    public static void close() {
        instance.scanner.close();
    }
}