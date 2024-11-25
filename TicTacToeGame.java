import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static utils.Ansi.*;
import java.util.Scanner;

public class TicTacToeGame {
    private final TicTacToeBoard board;
    private final List<Player<TicTacToeBoard>> players;
    private final MenuDisplay display;
    
    public TicTacToeGame() {
        this.board = new TicTacToeBoard();
        this.players = new ArrayList<>();
        this.display = new MenuDisplay();
    }

    private void initializePlayers(int playerCount, String[] playerNames) {
        // Låt första spelaren välja symbol
        display.showSymbolSelectionMenu(playerNames[0]);
        String player1Symbol = getValidSymbolChoice() == 1 ? "X" : "O";
        String player2Symbol = player1Symbol.equals("X") ? "O" : "X";
        
        // Skapa spelarna med deras symboler
        players.add(new HumanPlayer(playerNames[0], player1Symbol));
        players.add(playerCount == 1 ? 
            new ComputerPlayer("Computer", player2Symbol) : 
            new HumanPlayer(playerNames[1], player2Symbol));
            
        // Slumpa vem som börjar
        Collections.shuffle(players);
    }

    private int getValidSymbolChoice() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 2) return choice;
                display.showInvalidChoice();
            } catch (NumberFormatException e) {
                display.showInvalidChoice();
            }
        }
    }

    private void playGame() {
        boolean gameloop = true;
        while (gameloop) {
            for (Player<TicTacToeBoard> player : players) {
                player.takeTurn(board);
                if (board.checkWin() || board.isDraw()) {
                    display.showGameResult(board);
                    gameloop = false;
                    break;
                }
            }
        }
    }

    public static void start(int playerCount, String[] playerNames) {
        TicTacToeGame game = new TicTacToeGame();
        Scanner scanner = new Scanner(System.in);

        do {
            game.initializePlayers(playerCount, playerNames);
            game.playGame();
            System.out.println("Vill du spela igen? (j/n): ");
        } while (scanner.nextLine().trim().toLowerCase().startsWith("j"));

        System.out.println("Tack för att du spelade!");
    }
}
