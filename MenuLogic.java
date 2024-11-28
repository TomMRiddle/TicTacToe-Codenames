import java.util.Scanner;

public class MenuLogic {
    private final MenuDisplay display;
    private final MenuPlayerManager playerManager;

    public MenuLogic() {
        this.display = new MenuDisplay();
        this.playerManager = new MenuPlayerManager();
    }

    public void start() {
        display.displayWelcomeBanner();
        int gameChoice = getValidInput("game", 1, 2);
        int playerCount = getValidInput("players", gameChoice == 1 ? 1 : 4, gameChoice == 1 ? 2 : 8);
        String[] playerNames = playerManager.getPlayerNames(playerCount);

        ReadRules rules = new ReadRules();
        
        if (gameChoice == 1) {
            startTicTacToe(playerCount, playerNames);
        } else {
            rules.gameRules();
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
                int choice = Integer.parseInt(ScannerSingleton.getNextLine());
                if (choice >= min && choice <= max) return choice;
                display.showInvalidChoice();
            } catch (NumberFormatException e) {
                display.showInvalidChoice();
            }
        }
    }

    private void startTicTacToe(int playerCount, String[] playerNames) {
        display.displayGameDetails(1, playerCount, playerNames);
        TicTacToeGame.start(playerCount, playerNames);
    }

    private void startCodenames(String[] playerNames) {
        int[] spymasterIndices = playerManager.selectSpymasters(playerNames);
        display.displayCodenamesGameDetails(playerNames, spymasterIndices);
        System.out.println("\nTryck ENTER för att starta spelet...");
        ScannerSingleton.getNextLine();
        CodenamesGame.start(playerNames, spymasterIndices);
    }
}