import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static utils.Ansi.*;
import java.util.Scanner;

public class TicTacToeGame {
    private static final TicTacToeBoard board = new TicTacToeBoard();
    private static final List<Player<TicTacToeBoard>> players= new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println( BRIGHT_YELLOW + """
          _____                       ____ \s
         |_ " _|         ___       U /"___|\s
           | |          |_"_|      \\| | u  \s
          /| |\\          | |        | |/__ \s
         u |_|U        U/| |\\u       \\____|\s
         _// \\\\_    .-,_|___|_,-.   _// \\\\ \s
        (__) (__)    \\_)-' '-(_/   (__)(__)\s
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
        (__) (__)       (__)       (__) (__)\s
        """ + RESET);
        players.add(new HumanPlayer("Victor", "X"));
        players.add(new ComputerPlayer("Computer", "O"));

        boolean playAgain = true;

        while (playAgain) {
            TicTacToeBoard board = new TicTacToeBoard();

            Collections.shuffle(players);    
            
            boolean gameloop = true;
            while(gameloop) {
                for (Player<TicTacToeBoard> player : players) {
                    player.takeTurn(board);
                    board.checkWin();
                    if(board.getWinner() != null || board.isDraw()) {
                        gameloop = false;
                        break;
                    }
                }
            }
            System.out.println(board);
            if(!board.isDraw()) {
                System.out.println(board.getWinner().getName() + " wins!");
            } else {
                System.out.println("It's a tie!");
            }
            
            System.out.println("Would you like to play again? (yes/no): ");
            String userInput = scan.nextLine().trim().toLowerCase();
            
            playAgain = userInput.contains("y");
        }

        System.out.println("Thank you for playing!");
    }

}
