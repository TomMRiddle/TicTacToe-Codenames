import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        
        if (gameChoice == 1) {  // Tre i Rad
            displayGameDetails(gameChoice, playerCount, playerNames);
            startTicTacToe(playerCount, playerNames);
        } else {  // Codenames
            displayCodenamesGameDetails(playerNames);
            startCodenames(playerNames);
        }
    }

    private void displayWelcomeBanner() {
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

    private int selectGame() {
        while (true) {
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

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) return choice;
                System.out.println("""
                    ┌──────────────────────────────────┐
                    │    Felaktigt val! Välj 1 eller 2 │
                    └──────────────────────────────────┘
                    """);
            } catch (NumberFormatException e) {
                System.out.println("""
                    ┌──────────────────────────────────┐
                    │      Skriv en siffra (1-2)!      │
                    └──────────────────────────────────┘
                    """);
            }
        }
    }

    private int selectPlayerNumber(int gameChoice) {
        while (true) {
            if (gameChoice == 1) {  // Tre i rad
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

                try {
                    int playerCount = Integer.parseInt(scanner.nextLine());
                    if (playerCount >= 1 && playerCount <= 2) return playerCount;
                    System.out.println("""
                        ┌──────────────────────────────────┐
                        │  Felaktigt val! Välj mellan 1-2  │
                        └──────────────────────────────────┘
                        """);
                } catch (NumberFormatException e) {
                    System.out.println("""
                        ┌──────────────────────────────────┐
                        │      Skriv en siffra (1-2)!      │
                        └──────────────────────────────────┘
                        """);
                }
            } else {  // Codenames
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

                try {
                    int playerCount = Integer.parseInt(scanner.nextLine());
                    if (playerCount >= 4 && playerCount <= 8) return playerCount;
                    System.out.println("""
                        ┌──────────────────────────────────┐
                        │  Felaktigt val! Välj mellan 4-8  │
                        └──────────────────────────────────┘
                        """);
                } catch (NumberFormatException e) {
                    System.out.println("""
                        ┌──────────────────────────────────┐
                        │      Skriv en siffra (4-8)!      │
                        └──────────────────────────────────┘
                        """);
                }
            }
        }
    }

    private String[] getPlayerNames(int playerCount) {
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

    private void displayGameDetails(int gameChoice, int playerCount, String[] playerNames) {
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

    private static class TeamPlayers {
        String[] players;
        String spymaster;
        
        TeamPlayers(String[] players) {
            this.players = players;
            this.spymaster = null;
        }
    }

    private void displayCodenamesGameDetails(String[] allPlayers) {
        int redTeamSize = (allPlayers.length + 1) / 2;  // 
        
        // Delar upp spelarna i lag
        String[] redTeamPlayers = new String[redTeamSize];
        String[] blueTeamPlayers = new String[allPlayers.length - redTeamSize];
        
        System.arraycopy(allPlayers, 0, redTeamPlayers, 0, redTeamSize);
        System.arraycopy(allPlayers, redTeamSize, blueTeamPlayers, 0, allPlayers.length - redTeamSize);
        
        TeamPlayers redTeam = new TeamPlayers(redTeamPlayers);
        TeamPlayers blueTeam = new TeamPlayers(blueTeamPlayers);
        
        // Varje lag väljer spymaster
        selectSpymaster(redTeam, "RÖTT");
        selectSpymaster(blueTeam, "BLÅTT");
        
        // Visar lagen och dess spelare + deras positioner
        displayTeams(redTeam, blueTeam);
    }

    private void selectSpymaster(TeamPlayers team, String teamName) {
        while (true) {
            System.out.println("""
                ┌──────────────────────────────────┐
                │   VÄLJ SPYMASTER FÖR %s LAG    │
                │                                  │
                │   Tillgängliga spelare:          │""".formatted(teamName));

            for (int i = 0; i < team.players.length; i++) {
                System.out.printf("│   %d. %-28s│%n", (i + 1), team.players[i]);
            }

            System.out.println("""
                │                                  │
                │   Välj nummer (1-%d):             │
                └──────────────────────────────────┘""".formatted(team.players.length));

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= team.players.length) {
                    team.spymaster = team.players[choice - 1];
                    break;
                }
                System.out.println("""
                    ┌──────────────────────────────────┐
                    │  Felaktigt val! Välj mellan 1-%d │
                    └──────────────────────────────────┘""".formatted(team.players.length));
            } catch (NumberFormatException e) {
                System.out.println("""
                    ┌──────────────────────────────────┐
                    │      Skriv en siffra!            │
                    └──────────────────────────────────┘
                    """);
            }
        }
    }

    private void displayTeams(TeamPlayers redTeam, TeamPlayers blueTeam) {
        System.out.println("""
            ┌──────────────────────────────────┐
            │         SPELÖVERSIKT             │
            │                                  │
            │         RÖTT LAG                 │""");
        
        System.out.printf("│         SPYMASTER:%-15s│%n", redTeam.spymaster);
        System.out.println("│         AGENTER:                 │");
        for (String player : redTeam.players) {
            if (!player.equals(redTeam.spymaster)) {
                System.out.printf("│          - %-22s│%n", player);
            }
        }
        
        System.out.println("""
            │                                  │
            │         BLÅTT LAG                │""");
            
        System.out.printf("│        SPYMASTER: %-15s│%n", blueTeam.spymaster);
        System.out.println("│           AGENTER:               │");
        for (String player : blueTeam.players) {
            if (!player.equals(blueTeam.spymaster)) {
                System.out.printf("│          - %-22s│%n", player);
            }
        }
        
        System.out.println("""
            │                                  │
            └──────────────────────────────────┘""");
    }

    private String getGameName(int gameChoice) {
        return gameChoice == 1 ? "TRE I RAD" : "CODENAMES";
    }

    private String padRight(String str, int length) {
        if (length <= 0) return "";
        return " ".repeat(length);
    }

    private void startTicTacToe(int playerCount, String[] playerNames) {
        TicTacToeBoard board = new TicTacToeBoard();
        List<Player<TicTacToeBoard>> players = new ArrayList<>();
        
        // Setup players based on game mode
        if (playerCount == 1) {
            players.add(new HumanPlayer(playerNames[0], "X"));
            players.add(new ComputerPlayer("Computer", "O"));
        } else {
            players.add(new HumanPlayer(playerNames[0], "X"));
            players.add(new HumanPlayer(playerNames[1], "O"));
        }

        // Start game loop
        Collections.shuffle(players);
        boolean gameloop = true;
        while(gameloop) {
            for (Player<TicTacToeBoard> player : players) {
                player.takeTurn(board);
                if(board.checkWin() || board.isDraw()) {
                    gameloop = false;
                    break;
                }
            }
        }
        System.out.println(board);
        if(!board.isDraw()) {
            System.out.println(board.getWinner().getName() + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    private void startCodenames(String[] playerNames) {
        CodenamesGame game = new CodenamesGame();
        game.main(null);  // Start the Codenames game
    }
}