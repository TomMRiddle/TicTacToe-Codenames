import static utils.Ansi.*;

public class MenuDisplay {
    public void displayWelcomeBanner() {
        System.out.println(CLS+RED +
                "     ███▄ ▄███▓ ▒█████   ██▀███  ▄▄▄█████▓ ██▓  ██████       \n" +
                "    ▓██▒▀█▀ ██▒▒██▒  ██▒▓██ ▒ ██▒▓  ██▒ ▓▒▓██▒▒██    ▒       \n" +
                "    ▓██    ▓██░▒██░  ██▒▓██ ░▄█ ▒▒ ▓██░ ▒░▒██▒░ ▓██▄         \n" +
                "    ▒██    ▒██ ▒██   ██░▒██▀▀█▄  ░ ▓██▓ ░ ░██░  ▒   ██▒      \n" +
                "    ▒██▒   ░██▒░ ████▓▒░░██▓ ▒██▒  ▒██▒ ░ ░██░▒██████▒▒      \n" +
                "    ░ ▒░   ░  ░░ ▒░▒░▒░ ░ ▒▓ ░▒▓░  ▒ ░░   ░▓  ▒ ▒▓▒ ▒ ░      \n" +
                " "+BRIGHT_WHITE+"█████"+RED+" ░ "+BRIGHT_WHITE+"█████"+RED+"░  ░ ▒ ▒░   ░▒ ░ ▒░"+BRIGHT_WHITE+"█████"+RED+"     ▒ ░░ ░▒  ░ ░      \n" +
                BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"   ░░"+BRIGHT_WHITE+"███"+RED+"  ░ ░ ░ ▒    ░░   ░ "+BRIGHT_GREEN+"░"+BRIGHT_WHITE+"███"+RED+"      ▒ ░░  ░  ░        \n" +
                BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"    ░"+BRIGHT_WHITE+"███  ██████  ████████ ███████    ██████  █████ █████\n" +
                BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"    ░"+BRIGHT_WHITE+"███ ███"+BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░    "+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+" ░░"+BRIGHT_WHITE+"███\n" +
                BRIGHT_GREEN+" ░░"+BRIGHT_WHITE+"███   ███"+BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+" ░░░  ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"    ░"+BRIGHT_WHITE+"███████"+BRIGHT_GREEN+"  ░░░"+BRIGHT_WHITE+"█████"+BRIGHT_GREEN+"░ \n" +
                BRIGHT_GREEN+"  ░░░"+BRIGHT_WHITE+"█████"+BRIGHT_GREEN+"░  ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"      ░"+BRIGHT_WHITE+"███ ███"+BRIGHT_GREEN+"░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░░    "+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░░"+BRIGHT_WHITE+"███\n" +
                BRIGHT_GREEN+"    ░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"    ░░"+BRIGHT_WHITE+"██████  █████"+BRIGHT_GREEN+"     ░░"+BRIGHT_WHITE+"█████"+BRIGHT_GREEN+" ░░"+BRIGHT_WHITE+"██████  █████ █████\n" +
                BRIGHT_GREEN+"     ░░░      ░░░░░   ░░░░░       ░░░░░   ░░░░░░  ░░░░░ ░░░░░\n" +
                "\n" +
                "┌─────────────────────────────────────────────────────────┐\n" +
                "│                                                         │\n" +
                "│                VÄLKOMMEN TILL VÅRA SPEL!                │\n" +
                "│                  Tre i rad & Codenames                  │\n" +
                "│                                                         │\n" +
                "└─────────────────────────────────────────────────────────┘\n" +
                RESET);
    }

    public int getGameSelection() {
        System.out.println("""
            ┌──────────────────────────────────┐
            │         VÄLJ SPEL ATT SPELA:     │
            │                                  │
            │        1. TRE I RAD              │
            │        2. CODENAMES              │
            │                                  │
            │        Välj mellan (1-2):        │
            └──────────────────────────────────┘"""
        );
        return ScannerSingleton.getInstance().getNextLineInt(1,2);
    }

    public int getPlayerCount(int gameChoice) {
        displayWelcomeBanner();
        if (gameChoice == 1) {
            System.out.println("""
                ┌──────────────────────────────────┐
                │      VÄLJ ANTAL SPELARE:         │
                │                                  │
                │      1. EN SPELARE (MOT DATORN)  │
                │      2. TVÅ SPELARE              │
                │                                  │
                │      Välj antal (1-2):           │
                └──────────────────────────────────┘"""
            );
            return ScannerSingleton.getInstance().getNextLineInt(1,2);
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
                └──────────────────────────────────────┘"""
            );
            return ScannerSingleton.getInstance().getNextLineInt(4,8);
        }
    }

    public void showSymbolSelectionMenu(String playerName) {
        String formattedName = playerName.length() > 10 ? 
            playerName.substring(0, 10) : 
            String.format("%-10s", playerName);
    
        System.out.println(CLS+"""
            ┌──────────────────────────────────┐
            │         VÄLJ DIN SYMBOL          │
            │                                  │""");
        System.out.printf("│        %s välj:          │%n", formattedName);
        System.out.println(""" 
            │                                  │
            │         1. X                     │
            │         2. O                     │
            │                                  │
            │         Välj symbol (1-2):       │
            └──────────────────────────────────┘"""
        );
    }
    public void displayGameDetails(int gameChoice, int playerCount, String[] playerNames) {
        System.out.println(CLS+"""
            ┌──────────────────────────────────┐
            │         SPELÖVERSIKT             │
            │                                  │""");
        
        System.out.printf("│        SPEL: %-20s│%n", getGameName(gameChoice));
        System.out.printf("│        ANTAL SPELARE: %-11d│%n", playerCount);
    
        System.out.println("│        SPELARE:                  │");
        for (String name : playerNames) {
            System.out.printf("│        - %-22s  │%n", name);
        }
        
        System.out.println(""" 
            │                                  │
            └──────────────────────────────────┘""");
    }

    public void displayCodenamesGameDetails(String[] allPlayers, int[] spymasterIndices) {
        int redTeamSize = (allPlayers.length + 1) / 2;
        String[] redTeam = new String[redTeamSize];
        String[] blueTeam = new String[allPlayers.length - redTeamSize];
        
        System.arraycopy(allPlayers, 0, redTeam, 0, redTeamSize);
        System.arraycopy(allPlayers, redTeamSize, blueTeam, 0, allPlayers.length - redTeamSize);
        
        System.out.println(CLS+"""
            ┌──────────────────────────────────┐
            │         SPELÖVERSIKT             │
            │                                  │
            │         RÖDA LAGET               │""");
        
        for (int i = 0; i < redTeam.length; i++) {
            if (i == spymasterIndices[0]) {
                System.out.printf("│          - %-22s│%n", redTeam[i] + " (Spymaster)");
            } else {
                System.out.printf("│          - %-22s│%n", redTeam[i]);
            }
        }
        
        System.out.println(""" 
            │                                  │
            │         BLÅA LAGET               │""");
        
        for (int i = 0; i < blueTeam.length; i++) {
            if (i + redTeamSize == spymasterIndices[1]) {
                System.out.printf("│          - %-22s│%n", blueTeam[i] + " (Spymaster)");
            } else {
                System.out.printf("│          - %-22s│%n", blueTeam[i]);
            }
        }
        
        System.out.println(""" 
            │                                  │
            └──────────────────────────────────┘""");
    }

    public void showGameResult(TicTacToeBoard board) {
        System.out.println(CLS+board);
        if(!board.isDraw()) {
            System.out.println(board.getWinner().getName() + " Vann!");
        } else {
            System.out.println("Det blev oavgjort!");
        }
        Scoreboard.getInstance("Tre i rad").printScoreboard();
    }
    private String getGameName(int gameChoice) {
        return gameChoice == 1 ? "TRE I RAD" : "CODENAMES";
    }

    public int getSpymasterChoice() {
        System.out.println(CLS + "VÄLJ SPYMASTER ALTERNATIV\n");
        System.out.println("1. Välj nya spymasters");
        System.out.println("2. Behåll samma spymasters\n");
        
        return ScannerSingleton.getInstance().getNextLineInt(1, 2);
    }
}