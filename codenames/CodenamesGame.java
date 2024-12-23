package codenames;

import base.Player;

import java.util.ArrayList;
import java.util.List;

import static utils.Ansi.*;

public class CodenamesGame {

    public static void start(String[] playerNames, int[] spymasterIndices) {
        CodenamesBoard board = new CodenamesBoard();
        List<Player<CodenamesBoard>> players = createTeams(playerNames, spymasterIndices, board.getStartingTeam());
        playGame(players, board);
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
        boolean gameloop = true;
        while (gameloop) {
            for (Player<CodenamesBoard> player : players) {
                player.takeTurn(board);
                if (board.checkWin()) {
                    board.setSpymasterView(true);
                    System.out.println(board);
                    String winningTeam = board.getWinningTeam();
                    if (winningTeam.equals(BRIGHT_BLACK)) {
                        AgentPlayer agent = (AgentPlayer) player;
                        System.out.println("Det " + (agent.getTeamColor().equals(RED) ? BLUE + "blå" : RED + BG + "röda") + RESET + " laget har vunnit!");

                    } else {
                        System.out.println("Det " + (board.getWinningTeam().equals(RED) ? RED + BG + "röda" : BLUE + BG + "blå") + RESET + " laget har vunnit!");
                    }
                    gameloop = false;
                    break;
                }

            }
        }
    }
}
