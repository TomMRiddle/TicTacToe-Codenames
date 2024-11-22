import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuLogic {
    private MenuDisplay display;
    private MenuPlayerManager playerManager;
    private Scanner scanner;

    public MenuLogic() {
        this.scanner = new Scanner(System.in);
        this.display = new MenuDisplay();
        this.playerManager = new MenuPlayerManager();
    }

    public void start() {
        display.displayWelcomeBanner();
        int gameChoice = getValidInput("game", 1, 2);
        int playerCount = getValidInput("players", gameChoice == 1 ? 1 : 4, gameChoice == 1 ? 2 : 8);
        String[] playerNames = playerManager.getPlayerNames(playerCount);
        
        if (gameChoice == 1) {
            display.displayGameDetails(gameChoice, playerCount, playerNames);
            startTicTacToe(playerCount, playerNames);
        } else {
            startCodenames(playerNames);
        }
    }

    private int getValidInput(String type, int min, int max) {
        while (true) {
            if (type.equals("game")) {
                display.showGameSelectionMenu();
            } else {
                display.showPlayerCountMenu(min == 1 ? 1 : 2);
            }

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) return choice;
                display.showInvalidChoice();
            } catch (NumberFormatException e) {
                display.showInvalidChoice();
            }
        }
    }

    private void startTicTacToe(int playerCount, String[] playerNames) {
        TicTacToeBoard board = new TicTacToeBoard();
        List<Player<TicTacToeBoard>> players = new ArrayList<>();
        
        if (playerCount == 1) {
            players.add(new HumanPlayer(playerNames[0], "X"));
            players.add(new ComputerPlayer("Computer", "O"));
        } else {
            players.add(new HumanPlayer(playerNames[0], "X"));
            players.add(new HumanPlayer(playerNames[1], "O"));
        }

        while (true) {
            for (Player<TicTacToeBoard> player : players) {
                player.takeTurn(board);
                if (board.checkWin() || board.isDraw()) {
                    display.showGameResult(board);
                    return;
                }
            }
        }
    }

    private void startCodenames(String[] playerNames) {
        int[] spymasterIndices = playerManager.selectSpymasters(playerNames);
        display.displayCodenamesGameDetails(playerNames, spymasterIndices);
        System.out.println("\nTryck ENTER för att starta spelet...");
        scanner.nextLine();
        CodenamesGame.start(playerNames, spymasterIndices);
    }
}