public class Main {
    public static void main(String[] args) {
        final MenuLogic menu = new MenuLogic();
        menu.start();
        ScannerSingleton.close();
    }
}