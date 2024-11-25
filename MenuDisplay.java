import static utils.Ansi.*;

public class MenuDisplay {
    public void displayWelcomeBanner() {
        System.out.println("""
            ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
            │                                                                                              │
            │     ___  ______________ _____ _____ _____          _   _  ___________ _____ _______   __     │
            │     |  \\/  |  _  | ___ \\_   _|_   _/  ___|        | | | ||  _  | ___ \\_   _|  ___\\ \\ / /     │
            │     | .  . | | | | |_/ / | |   | | \\ `--.         | | | || | | | |_/ / | | | |__  \\ V /      │
            │     | |\\/| | | | |    /  | |   | |  `--. \\        | | | || | | |    /  | | |  __| /   \\      │
            │     | |  | \\ \\_/ / |\\ \\  | |  _| |_/\\__/ /        \\ \\_/ /\\ \\_/ / |\\ \\  | | | |___/ /^\\ \\     │
            │     \\_|  |_/\\___/\\_| \\_| \\_/  \\___/\\____/          \\___/  \\___/\\_| \\_| \\_/ \\____/\\/   \\/     │
            │                                                                                              │
            │                                  VÄLKOMMEN TILL VÅRA SPEL!                                   │
            │                                                                                              │
            │                                  Tre i Rad & Codenames                                       │
            │                                                                                              │
            └──────────────────────────────────────────────────────────────────────────────────────────────┘
            """);
    }

    public void showGameSelectionMenu() {
        System.out.println("""
            ┌──────────────────────────────────┐
            │         VÄLJ SPEL ATT SPELA:     │
            │                                  │
            │        1. TRE I RAD              │
            │        2. CODENAMES              │
            │                                  │
            │        Välj mellan (1-2):        │
            └──────────────────────────────────┘
            """);
    }

    public void showPlayerCountMenu(int gameChoice) {
        if (gameChoice == 1) {
            System.out.println("""
                ┌──────────────────────────────────┐
                │      VÄLJ ANTAL SPELARE:         │
                │                                  │
                │      1. EN SPELARE (MOT DATORN)  │
                │      2. TVÅ SPELARE              │
                │                                  │
                │      Välj antal (1-2):           │
                └──────────────────────────────────┘
                """);
        } else {
            System.out.println("""
                ┌──────────────────────────────────────┐
                │      VÄLJ ANTAL SPELARE:             │ 
                │                                      │
                │      Lagen kommer automatiskt        │
                │      delas upp baserat på vilken     │
                │      ordning namnen skrivs in i.     │
                │      T.ex: De första 3 spelarna      │
                │      går till lag röd, och de        │
                │      resterande spelarna går         │
                │      till lag blå.                   │
                │                                      │
                │      Välj antal (4-8):               │ 
                │                                      │
                └──────────────────────────────────────┘
                """);
        }
    }

    public void showInvalidChoice() {
        System.out.println("""
            ┌──────────────────────────────────┐
            │    Felaktigt val! Försök igen    │
            └──────────────────────────────────┘
            """);
    }

    public void displayGameDetails(int gameChoice, int playerCount, String[] playerNames) {
        System.out.println("""
            ┌──────────────────────────────────┐
            │         SPELÖVERSIKT             │
            │                                  │""");
        
        System.out.printf("│        SPEL: %-20s│%n", getGameName(gameChoice));
        System.out.printf("│        ANTAL SPELARE: %-11d│%n", playerCount);
    
        System.out.println("│         SPELARE:                 │");
        for (String name : playerNames) {
            System.out.printf("│         - %-22s │%n", name);
        }
        
        System.out.println("""
            │                                  │
            └──────────────────────────────────┘""");
    }

    public void displayCodenamesGameDetails(String[] allPlayers) {
        int redTeamSize = (allPlayers.length + 1) / 2;
        String[] redTeam = new String[redTeamSize];
        String[] blueTeam = new String[allPlayers.length - redTeamSize];
        
        System.arraycopy(allPlayers, 0, redTeam, 0, redTeamSize);
        System.arraycopy(allPlayers, redTeamSize, blueTeam, 0, allPlayers.length - redTeamSize);
        
        System.out.println("""
            ┌──────────────────────────────────┐
            │         SPELÖVERSIKT             │
            │                                  │
            │         RÖDA LAGET               │""");
        
        System.out.printf("│          - %-22s│%n", redTeam[0] + " (Spymaster)");
        for (int i = 1; i < redTeam.length; i++) {
            System.out.printf("│          - %-22s│%n", redTeam[i]);
        }
        
        System.out.println("""
            │                                  │
            │         BLÅA LAGET               │""");
        
        System.out.printf("│          - %-22s│%n", blueTeam[0] + " (Spymaster)");
        for (int i = 1; i < blueTeam.length; i++) {
            System.out.printf("│          - %-22s│%n", blueTeam[i]);
        }
        
        System.out.println("""
            │                                  │
            └──────────────────────────────────┘""");
    }

    public void showGameResult(TicTacToeBoard board) {
        System.out.println(board);
        if(!board.isDraw()) {
            System.out.println(board.getWinner().getName() + " vinner!");
        } else {
            System.out.println("Det blev oavgjort!");
        }
    }

    private String getGameName(int gameChoice) {
        return gameChoice == 1 ? "TRE I RAD" : "CODENAMES";
    }
}