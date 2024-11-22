import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.Ansi.*;

public class CodenamesGame {
    private static final String RED = "RED";
    private static final String BLUE = "BLUE";

    public static void start(String[] playerNames, int[] spymasterIndices) {
        boolean playAgain = true;
        while (playAgain) {
            CodenamesBoard board = new CodenamesBoard();
            List<Player<CodenamesBoard>> players = createTeams(playerNames, spymasterIndices, board.getStartingTeam());
            playGame(players, board);
            playAgain = askToPlayAgain();
        }
        System.out.println("Thank you for playing!");
    }

    private static List<Player<CodenamesBoard>> createTeams(String[] playerNames, int[] spymasterIndices, String startingTeam) {
        List<Player<CodenamesBoard>> players = new ArrayList<>();
        int redTeamSize = (playerNames.length + 1) / 2;
        
        // Add starting team first, then other team
        addTeamPlayers(players, playerNames, spymasterIndices, startingTeam, redTeamSize);
        addTeamPlayers(players, playerNames, spymasterIndices, startingTeam.equals(RED) ? BLUE : RED, redTeamSize);
        
        return players;
    }

    private static void addTeamPlayers(List<Player<CodenamesBoard>> players, String[] playerNames, 
                                     int[] spymasterIndices, String team, int redTeamSize) {
        int spymasterIndex = team.equals(RED) ? spymasterIndices[0] : spymasterIndices[1];
        int startIndex = team.equals(RED) ? 0 : redTeamSize;
        int endIndex = team.equals(RED) ? redTeamSize : playerNames.length;

        players.add(new SpymasterPlayer(playerNames[spymasterIndex], team));
        for (int i = startIndex; i < endIndex; i++) {
            if (i != spymasterIndex) {
                players.add(new AgentPlayer(playerNames[i], team));
            }
        }
    }

    private static void playGame(List<Player<CodenamesBoard>> players, CodenamesBoard board) {
        int maxTurns = 4;  // Can adjust this value based on game requirements
        for (int turn = 0; turn < maxTurns; turn++) {
            for (Player<CodenamesBoard> player : players) {
                player.takeTurn(board);
            }
        }
    }

    private static boolean askToPlayAgain() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Would you like to play again? (yes/no): ");
            String userInput = scanner.nextLine().trim().toLowerCase();
            return userInput.contains("y");
        }
    }
}
