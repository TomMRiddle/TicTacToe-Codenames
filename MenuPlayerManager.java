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

    public int[] selectSpymasters(String[] allPlayers) {
        int redTeamSize = (allPlayers.length + 1) / 2;
        int[] spymasterIndices = new int[2]; // Index 0 for red team, Index 1 for blue team
        
        // Select Red Team Spymaster
        System.out.println("""
            ┌─────────────────────────────────────┐
            │  VÄLJ SPYMASTER FÖR DET RÖDA LAGET  │
            │                                     │
            │     Välj nummer för spymaster:      │
            └─────────────────────────────────────┘
            """);
            
        for (int i = 0; i < redTeamSize; i++) {
            System.out.printf("%d. %s%n", i + 1, allPlayers[i]);
        }
        
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= redTeamSize) {
                    spymasterIndices[0] = choice - 1;
                    break;
                }
                System.out.println("Ogiltigt val! Välj ett nummer mellan 1 och " + redTeamSize);
            } catch (NumberFormatException e) {
                System.out.println("Ogiltigt val! Ange ett nummer.");
            }
        }

        // Select Blue Team Spymaster
        System.out.println("""
            ┌─────────────────────────────────────┐
            │  VÄLJ SPYMASTER FÖR DET BLÅA LAGET  │
            │                                     │
            │     Välj nummer för spymaster:      │
            └─────────────────────────────────────┘
            """);
            
        for (int i = redTeamSize; i < allPlayers.length; i++) {
            System.out.printf("%d. %s%n", i - redTeamSize + 1, allPlayers[i]);
        }
        
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= allPlayers.length - redTeamSize) {
                    spymasterIndices[1] = redTeamSize + choice - 1;
                    break;
                }
                System.out.println("Ogiltigt val! Välj ett nummer mellan 1 och " + (allPlayers.length - redTeamSize));
            } catch (NumberFormatException e) {
                System.out.println("Ogiltigt val! Ange ett nummer.");
            }
        }

        return spymasterIndices;
    }
}