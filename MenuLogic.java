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
        int gameChoice =  display.getGameSelection();
        int playerCount = display.getPlayerCount(gameChoice);
        String[] playerNames = playerManager.getPlayerNames(playerCount);

        ReadRules rules = new ReadRules();
        
        if (gameChoice == 1) {
            startTicTacToe(playerCount, playerNames);
        } else {
            rules.gameRules();
            startCodenames(playerNames);
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
        ScannerSingleton.getInstance().pressEnterToContinue();
        CodenamesGame.start(playerNames, spymasterIndices);
    }
}
