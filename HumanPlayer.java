import java.util.Objects;
import java.util.Scanner;

public class HumanPlayer extends Player<TicTacToeBoard> {
    public HumanPlayer(String name, String symbol) {
        super(name, symbol);
    }
    Scanner scanner = new Scanner(System.in);
    @Override
    public void takeTurn(TicTacToeBoard board) {

        System.out.println(board);
        System.out.println(getName() + ", enter cell number: ");
        int cellId;

        do {
            cellId = scanner.nextInt();
            scanner.nextLine();
        } while ( (cellId > 9) || (cellId < 1) || !Objects.equals(board.getCellById(cellId).toString(), " "));
        TicTacToeCell cell = board.getCellById(cellId);
        cell.setContent(getSymbol());
        cell.setOwner(this);
    }
}
