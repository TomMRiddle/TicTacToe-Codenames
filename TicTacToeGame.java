import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicTacToeGame {
    private static final TicTacToeBoard board = new TicTacToeBoard();
    private static final List<Player<TicTacToeBoard>> players= new ArrayList<>();

    public static void main(String[] args) {
        players.add(new HumanPlayer("Victor", "X"));
        players.add(new ComputerPlayer("Computer", "O"));

        Collections.shuffle(players);
        boolean gameloop = true;
        while(gameloop) {
            for (Player<TicTacToeBoard> player : players) {
                player.makeMove(board);
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
    }

}
