import java.util.Objects;
import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, String symbol) {
        super(name, symbol);
    }

    @Override
    public void makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(board);
        System.out.println(getName() + ", enter cell number: ");
        int cellId;

        do {
            cellId = scanner.nextInt();
        } while ( (cellId > 9) || (cellId < 1) || !Objects.equals(board.getCellById(cellId).toString(), " "));
        Cell cell = board.getCellById(cellId);
        cell.setContent(getSymbol());
        cell.setOwner(this);
    }
}
