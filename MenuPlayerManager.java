import java.util.Scanner;

public class MenuPlayerManager {
    private Scanner scanner;

    public MenuPlayerManager() {
        this.scanner = new Scanner(System.in);
    }

    public String[] getPlayerNames(int playerCount) {
        String[] playerNames = new String[playerCount];

        System.out.println("""
            ┌──────────────────────────────────┐
            │    VÄLJ SPELARNAMN FÖR SPELET    │
            │                                  │
            │  Skriv in namn för varje spelare │
            └──────────────────────────────────┘
            """);

        for (int i = 0; i < playerCount; i++) {
            while (true) {
                System.out.println("""
                    ┌──────────────────────────────────┐
                    │       Skriv in Spelare %d:        │
                    └──────────────────────────────────┘
                    """.formatted(i + 1));
                
                String name = scanner.nextLine().trim();
                if (!name.isEmpty()) {
                    playerNames[i] = name;
                    break;
                }
                System.out.println("""
                    ┌──────────────────────────────────┐
                    │   Namn får inte vara tomt!       │
                    └──────────────────────────────────┘
                    """);
            }
        }
        return playerNames;
    }
}