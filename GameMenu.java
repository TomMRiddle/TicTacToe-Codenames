import java.util.Scanner;

public class GameMenu {
    private Scanner scanner;

    public GameMenu() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        displayWelcomeBanner();
        int gameChoice = selectGame();
        int playerCount = selectPlayerNumber(gameChoice);
        String[] playerNames = getPlayerNames(playerCount);
        displayGameDetails(gameChoice, playerCount, playerNames);
    }

    private void displayWelcomeBanner() {
        System.out.println("""
            ╔══════════════════════════════════════════════════════════════════════════════════════════════╗
            ║                                                                                              ║
            ║     ___  ______________ _____ _____ _____          _   _  ___________ _____ _______   __     ║
            ║     |  \\/  |  _  | ___ \\_   _|_   _/  ___|        | | | ||  _  | ___ \\_   _|  ___\\ \\ / /     ║
            ║     | .  . | | | | |_/ / | |   | | \\ `--.         | | | || | | | |_/ / | | | |__  \\ V /      ║
            ║     | |\\/| | | | |    /  | |   | |  `--. \\        | | | || | | |    /  | | |  __| /   \\      ║
            ║     | |  | \\ \\_/ / |\\ \\  | |  _| |_/\\__/ /        \\ \\_/ /\\ \\_/ / |\\ \\  | | | |___/ /^\\ \\     ║
            ║     \\_|  |_/\\___/\\_| \\_| \\_/  \\___/\\____/          \\___/  \\___/\\_| \\_| \\_/ \\____/\\/   \\/     ║
            ║                                                                                              ║
            ║                                  VÄLKOMMEN TILL VÅRA SPEL!                                   ║
            ║                                                                                              ║
            ║                                  Tre i Rad & Codenames                                       ║
            ║                                                                                              ║
            ╚══════════════════════════════════════════════════════════════════════════════════════════════╝
            """);
    }

    private int selectGame() {
        while (true) {
            System.out.println("""
                ╔══════════════════════════════════════╗
                ║         VÄLJ SPEL ATT SPELA:         ║
                ║                                      ║
                ║        1. TRE I RAD                  ║
                ║        2. CODENAMES                  ║
                ║                                      ║
                ║        Välj mellan (1-2):            ║
                ╚══════════════════════════════════════╝
                """);

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) return choice;
                System.out.println("""
                    ╔══════════════════════════════════════╗
                    ║      Felaktigt val! Välj 1 eller 2   ║
                    ╚══════════════════════════════════════╝
                    """);
            } catch (NumberFormatException e) {
                System.out.println("""
                    ╔══════════════════════════════════════╗
                    ║        Skriv en siffra (1-2)!        ║
                    ╚══════════════════════════════════════╝
                    """);
            }
        }
    }

    private int selectPlayerNumber(int gameChoice) {
        while (true) {
            if (gameChoice == 1) {  // Tre i rad
                System.out.println("""
                    ╔══════════════════════════════════════╗
                    ║      VÄLJ ANTAL SPELARE:             ║
                    ║                                      ║
                    ║        1. EN SPELARE (MOT DATORN)    ║
                    ║        2. TVÅ SPELARE                ║
                    ║                                      ║
                    ║        Välj antal (1-2):             ║
                    ╚══════════════════════════════════════╝
                    """);

                try {
                    int playerCount = Integer.parseInt(scanner.nextLine());
                    if (playerCount >= 1 && playerCount <= 2) return playerCount;
                    System.out.println("""
                        ╔══════════════════════════════════════╗
                        ║    Felaktigt val! Välj mellan 1-2    ║
                        ╚══════════════════════════════════════╝
                        """);
                } catch (NumberFormatException e) {
                    System.out.println("""
                        ╔══════════════════════════════════════╗
                        ║        Skriv en siffra (1-2)!        ║
                        ╚══════════════════════════════════════╝
                        """);
                }
            } else {  // Codenames
                System.out.println("""
                    ╔══════════════════════════════════════╗
                    ║      VÄLJ ANTAL SPELARE:             ║
                    ║                                      ║
                    ║        Välj antal (4-8):             ║
                    ║                                      ║
                    ╚══════════════════════════════════════╝
                    """);

                try {
                    int playerCount = Integer.parseInt(scanner.nextLine());
                    if (playerCount >= 4 && playerCount <= 8) return playerCount;
                    System.out.println("""
                        ╔══════════════════════════════════════╗
                        ║    Felaktigt val! Välj mellan 4-8    ║
                        ╚══════════════════════════════════════╝
                        """);
                } catch (NumberFormatException e) {
                    System.out.println("""
                        ╔══════════════════════════════════════╗
                        ║        Skriv en siffra (4-8)!        ║
                        ╚══════════════════════════════════════╝
                        """);
                }
            }
        }
    }

    private String[] getPlayerNames(int playerCount) {
        String[] playerNames = new String[playerCount];

        System.out.println("""
            ╔══════════════════════════════════════╗
            ║      VÄLJ SPELARNAMN FÖR SPELET      ║
            ║                                      ║
            ║    Skriv in namn för varje spelare   ║
            ╚══════════════════════════════════════╝
            """);

        for (int i = 0; i < playerCount; i++) {
            while (true) {
                System.out.println("""
                    ╔══════════════════════════════════════╗
                    ║         Skriv in Spelare {0}:          ║
                    ╚══════════════════════════════════════╝
                    """.replace("{0}", String.valueOf(i + 1)));
                
                String name = scanner.nextLine().trim();

                if (!name.isEmpty()) {
                    playerNames[i] = name;
                    break;
                }
                System.out.println("""
                    ╔══════════════════════════════════════╗
                    ║     Namn får inte vara tomt!         ║
                    ╚══════════════════════════════════════╝
                    """);
            }
        }
        return playerNames;
    }

    private void displayGameDetails(int gameChoice, int playerCount, String[] playerNames) {
        System.out.println("""
            ╔══════════════════════════════════════╗
            ║                                      ║
            ║                                      ║
            ║           NU STARTAR SPELET.         ║
            ║              LYCKA TILL!             ║
            ║                                      ║
            ║                                      ║
            ╚══════════════════════════════════════╝
            """);
    }

    private String getGameName(int gameChoice) {
        return gameChoice == 1 ? "Tre i Rad" : "Codenames";
    }
}