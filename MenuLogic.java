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
            } else if (type.equals("players")) {
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
        
        // Låt första spelaren välja symbol
        display.showSymbolSelectionMenu(playerNames[0]);
        String player1Symbol = getValidInput("symbol", 1, 2) == 1 ? "X" : "O";
        String player2Symbol = player1Symbol.equals("X") ? "O" : "X";
        
        // Skapa spelarna med deras symboler
        players.add(new HumanPlayer(playerNames[0], player1Symbol));
        players.add(playerCount == 1 ? 
            new ComputerPlayer("Computer", player2Symbol) : 
            new HumanPlayer(playerNames[1], player2Symbol));

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