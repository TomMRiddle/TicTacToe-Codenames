import java.util.Scanner;

public class GameMenu {
    private Scanner scanner;

    public GameMenu() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        displayWelcomeBanner();
        int gameChoice = selectGame();
        int playerCount = selectPlayerCount(gameChoice);
        String[] playerNames = getPlayerNames(playerCount);

        // Lägg till Spel logik senare
        displayGameConfiguration(gameChoice, playerCount, playerNames);
    }

    private void displayWelcomeBanner() {
        System.out.println("\n" +
            "╔═════════════════════════════════════════════════════════════════════╗\n" +
            "║                                                                     ║\n" +
            "║   ##\\      ##\\                       ##\\     ##\\                ║\n" +
            "║   ###\\    ### |                      ## |    \\__|                 ║\n" +
            "║   ####\\  #### |  ######\\   ######\\ ######\\   ##\\   #######\\   ║\n" +
            "║   ##\\##\\## ## | ##  __##\\ ##  __##\\\\_##  _|  ## | ##  _____|   ║\n" +
            "║   ## \\###  ## | ## /  ## |## |  \\__| ## |    ## | \\######\\      ║\n" +
            "║   ## |\\#  /## | ## |  ## |## |       ## |##\\ ## |  \\____##\\     ║\n" +
            "║   ## | \\_/ ## | \\######  |## |  ##\\  \\####  |## | #######  |    ║\n" +
            "║##\\\\__|##\\  \\__|  \\______/ \\__|  ## |  \\____/ \\__| \\_______/║\n" +
            "║## |   ## | ######\\    ######\\ ######\\    ######\\  ##\\   ##\\   ║\n" +
            "║## |   ## |##  __##\\  ##  __##\\\\_##  _|  ##  __##\\ \\##\\ ##  |  ║\n" +
            "║\\##\\  ##  |## /  ## | ## |  \\__| ## |    ######## | \\####  /     ║\n" +
            "║ \\##\\##  / ## |  ## | ## |       ## |##\\ ##   ____| ##  ##<       ║\n" +
            "║  \\###  /  \\######  | ## |       \\####  |\\#######\\ ##  /\\##\\  ║\n" +
            "║   \\#  /    \\______/  \\__|        \\____/  \\_______|\\__/  \\__| ║\n" +
            "║    \\_/                                                             ║\n" +
            "║                                                                     ║\n" +
            "║                  VÄLKOMMEN TILL VÅRA SPEL!                          ║\n" +
            "║                                                                     ║\n" +
            "║                  Tre i Rad & Codenames                              ║\n" +
            "║                                                                     ║\n" +
            "╚═════════════════════════════════════════════════════════════════════╝"
        );
    }

    private int selectGame() {
        while (true) {
            System.out.println("\n" +
                "╔══════════════════════════════════════╗\n" +
                "║         VÄLJ SPEL ATT SPELA:         ║\n" +
                "║                                      ║\n" +
                "║        1. TRE I RAD                  ║\n" +
                "║        2. CODENAMES                  ║\n" +
                "║                                      ║\n" +
                "║        Välj mellan (1-2):            ║\n" +
                "╚═════════════════��════════════════════╝"
            );

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) return choice;
                System.out.println(
                    "╔══════════════════════════════════════╗\n" +
                    "║      Felaktigt val! Välj 1 eller 2   ║\n" +
                    "╚══════════════════════════════════════╝"
                );
            } catch (NumberFormatException e) {
                System.out.println(
                    "╔══════════════════════════════════════╗\n" +
                    "║        Skriv en siffra (1-2)!        ║\n" +
                    "╚══════════════════════════════════════╝"
                );
            }
        }
    }

    private int selectPlayerCount(int gameChoice) {
        while (true) {
            if (gameChoice == 1) {  // Tre i rad
                System.out.println("\n" +
                    "╔══════════════════════════════════════╗\n" +
                    "║      VÄLJ ANTAL SPELARE:             ║\n" +
                    "║                                      ║\n" +
                    "║        1. EN SPELARE (MOT DATORN)    ║\n" +
                    "║        2. TVÅ SPELARE                ║\n" +
                    "║                                      ║\n" +
                    "║        Välj antal (1-2):             ║\n" +
                    "╚══════════════════════════════════════╝"
                );

                try {
                    int antal = Integer.parseInt(scanner.nextLine());
                    if (antal >= 1 && antal <= 2) return antal;
                    System.out.println(
                        "╔══════════════════════════════════════╗\n" +
                        "║    Felaktigt val! Välj mellan 1-2    ║\n" +
                        "╚══════════════════════════════════════╝"
                    );
                } catch (NumberFormatException e) {
                    System.out.println(
                        "╔══════════════════════════════════════╗\n" +
                        "║        Skriv en siffra (1-2)!        ║\n" +
                        "╚══════════════════════════════════════╝"
                    );
                }
            } else {  // Codenames
                System.out.println("\n" +
                    "╔══════════════════════════════════════╗\n" +
                    "║      VÄLJ ANTAL SPELARE:             ║\n" +
                    "║                                      ║\n" +
                    "║        Välj antal (4-8):             ║\n" +
                    "║                                      ║\n" +
                    "╚══════════════════════════════════════╝"
                );

                try {
                    int antal = Integer.parseInt(scanner.nextLine());
                    if (antal >= 4 && antal <= 8) return antal;
                    System.out.println(
                        "╔══════════════════════════════════════╗\n" +
                        "║    Felaktigt val! Välj mellan 4-8    ║\n" +
                        "╚══════════════════════════════════════╝"
                    );
                } catch (NumberFormatException e) {
                    System.out.println(
                        "╔══════════════════════════════════════╗\n" +
                        "║        Skriv en siffra (4-8)!        ║\n" +
                        "╚══════════════════════════════════════╝"
                    );
                }
            }
        }
    }

    private String[] getPlayerNames(int playerCount) {
        String[] playerNames = new String[playerCount];

        System.out.println("\n" +
            "╔══════════════════════════════════════╗\n" +
            "║      VÄLJ SPELARNAMN FÖR SPELET      ║\n" +
            "║                                      ║\n" +
            "║    Skriv in namn för varje spelare   ║\n" +
            "╚══════════════════════════════════════╝"
        );

        for (int i = 0; i < playerCount; i++) {
            while (true) {
                System.out.println(
                    "╔══════════════════════════════════════╗\n" +
                    "║         Skriv in Spelare " + (i + 1) + ":         ║\n" +
                    "╚══════════════════════════════════════╝"
                );
                
                String name = scanner.nextLine().trim();

                if (!name.isEmpty()) {
                    playerNames[i] = name;
                    break;
                }
                System.out.println(
                    "╔══════════════════════════════════════╗\n" +
                    "║     Namn får inte vara tomt!         ║\n" +
                    "╚══════════════════════════════════════╝"
                );
            }
        }
        return playerNames;
    }

    private void displayGameConfiguration(int gameChoice, int playerCount, String[] playerNames) {
        StringBuilder playerList = new StringBuilder();
        for (int i = 0; i < playerCount; i++) {
            if (gameChoice == 1 && playerCount == 1 && i == 0) {
                // För enspelarläge i Tre i rad
                playerList.append(String.format("║    Spelare: %-20s    ║%n", playerNames[0]));
                playerList.append("║    Motståndare: Dator              ║%n");
            } else {
                // För alla andra lägen
                playerList.append(String.format("║    Spelare %d: %-20s ║%n", (i + 1), playerNames[i]));
            }
        }

        System.out.println("\n" +
            "╔══════════════════════════════════════╗\n" +
            "║          SPELET STARTAR!             ║\n" +
            "║                                      ║\n" +
            "║          SPELDETALJER                ║\n" +
            "║                                      ║\n" +
            String.format("║    Valt spel: %-20s    ║%n", (gameChoice == 1 ? "Tre i Rad" : "Codenames")) +
            String.format("║    Antal spelare: %-15d ║%n", playerCount) +
            "║                                      ║\n" +
            "║          SPELARLISTA                 ║\n" +
            "║                                      ║\n" +
            playerList +
            "║                                      ║\n" +
            "║          LYCKA TILL!                 ║\n" +
            "║                                      ║\n" +
            "╚══════════════════════════════════════╝"
        );
    }
}