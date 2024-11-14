import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.Ansi;

public class GameEngine {
    private TicTacToeBoard board;
    private List<Player<TicTacToeBoard>> players;
    
    public GameEngine() {
        this.board = new TicTacToeBoard();
        this.players = new ArrayList<>();
        
        // Skapa spelare och lägg till dem i spelarelistan
        players.add(new HumanPlayer("Victor", "X"));
        players.add(new ComputerPlayer("Computer", "O"));
        
        // Blanda spelare så att turordningen är slumpmässig
        Collections.shuffle(players);
    }

    // Starta spelet
    public void startGame() {
        displayWelcomeMessage();
        boolean gameLoop = true;

        while (gameLoop) {
            for (Player<TicTacToeBoard> player : players) {
                player.takeTurn(board);
                board.checkWin();

                // Kontrollera om spelet är slut (vinst eller oavgjort)
                if (board.getWinner() != null || board.isDraw()) {
                    gameLoop = false;
                    break;
                }
            }
        }

        displayGameResult();
    }

    private void displayWelcomeMessage() {
        System.out.println(Ansi.BRIGHT_YELLOW + """
          _____                       ____ \s
         |_ " _|         ___       U /"___|\s
           | |          |_"_|      \\| | u  \s
          /| |\\          | |        | |/__ \s
         u |_|U        U/| |\\u       \\____|\s
         _// \\\\_    .-,_|___|_,-.   _// \\\\ \s
        (__) (__)    \\_)-' '-(_/   (__)(__)
          _____           _           ____    \s
         |_ " _|      U  /"\\  u    U /"___|   \s
           | |         \\/ _ \\/     \\| | u     \s
          /| |\\        / ___ \\      | |/__    \s
         u |_|U       /_/   \\_\\      \\____|   \s
         _// \\\\_       \\\\    >>     _// \\\\    \s
        (__) (__)     (__)  (__)   (__)(__)   \s
          _____       U  ___ u     U _____ u \s
         |_ " _|       \\/"_ \\/     \\| ___"|/ \s
           | |         | | | |      |  _|"   \s
          /| |\\    .-,_| |_| |      | |___   \s
         u |_|U     \\_)-\\___/       |_____|  \s
         _// \\\\_         \\\\         <<   >>  \s
        (__) (__)       (__)       (__) (__)
        """ + Ansi.RESET);
    }

    private void displayGameResult() {
        System.out.println(board);
        if (!board.isDraw()) {
            System.out.println(board.getWinner().getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
