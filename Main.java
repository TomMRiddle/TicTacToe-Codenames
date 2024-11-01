import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Board board = new TicTacToeBoard();
        System.out.println(board);
        List<Player> players= new ArrayList<Player>();
        players.add(new HumanPlayer("Victor", "X"));
        players.add(new ComputerPlayer("Computer", "O"));
        Player winner = null;
        Collections.shuffle(players);
        System.out.println(board);
        while(winner == null) {
            for (Player player : players) {
                player.makeMove(board);
                System.out.println(board);
            }
        }
        System.out.println("the winner is: " + winner.getName());
    }
}
