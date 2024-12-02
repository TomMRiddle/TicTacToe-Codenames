import java.util.Scanner;


public class MenuLogic {
    private final MenuDisplay display;

    public MenuLogic() {
        this.display = new MenuDisplay();
    }

    public void start() {
        display.displayWelcomeBanner();
        int gameChoice = display.getGameSelection();
        int playerCount = display.getPlayerCount(gameChoice);
        String[] playerNames = getPlayerNames(playerCount);

        ReadRules rules = new ReadRules();

        if (gameChoice == 1) {
            startTicTacToe(playerCount, playerNames);
        } else {
            rules.gameRules();
            startCodenames(playerNames);
        }
    }

    private String[] getPlayerNames(int playerCount) {
        String[] playerNames = new String[playerCount];
        display.displayPlayerNameHeader();

        for (int i = 0; i < playerCount; i++) {
            display.displayPlayerNamePrompt(i + 1);
            playerNames[i] = ScannerSingleton.getInstance().getNextLine().trim();
        }
        return playerNames;
    }

    private int[] selectSpymasters(String[] allPlayers) {
        int redTeamSize = (allPlayers.length + 1) / 2;
        int[] spymasterIndices = new int[2];

        // Röda laget visar spymaster
        display.displaySpymasterSelection(allPlayers, 0, redTeamSize, "RÖDA");
        spymasterIndices[0] = ScannerSingleton.getInstance().getNextLineInt(1, redTeamSize) - 1;

        // Blåa laget visar spymaster
        display.displaySpymasterSelection(allPlayers, redTeamSize, allPlayers.length - redTeamSize, "BLÅ");
        spymasterIndices[1] = redTeamSize + ScannerSingleton.getInstance().getNextLineInt(1, allPlayers.length - redTeamSize) - 1;

        return spymasterIndices;
    }

    private void startTicTacToe(int playerCount, String[] playerNames) {
        display.displayGameDetails(1, playerCount, playerNames);
        TicTacToeGame.start(playerCount, playerNames);
    }

    private void startCodenames(String[] playerNames) {
        boolean playAgain = true;
        int[] spymasterIndices = selectSpymasters(playerNames);

        while (playAgain) {
            display.displayCodenamesGameDetails(playerNames, spymasterIndices);
            System.out.println("\nTryck ENTER för att starta spelet...");
            ScannerSingleton.getInstance().pressEnterToContinue();

            CodenamesGame.start(playerNames, spymasterIndices);
            System.out.println("Vill du spela igen? (ja/nej): ");
            String playAgainInput = ScannerSingleton.getInstance().getNextLine().toLowerCase();

            if (!playAgainInput.contains("nej")) {
                int choice = display.getSpymasterChoice();
                if (choice == 1) {
                    spymasterIndices = selectSpymasters(playerNames);
                }
                playAgain = true;
            } else {
                playAgain = false;
                System.out.println("Tack för en god match!");
            }
        }
    }
}
