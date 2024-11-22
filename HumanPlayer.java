import java.util.Scanner;

public class HumanPlayer extends Player<TicTacToeBoard> {
    private final String symbol;
    public HumanPlayer(String name, String symbol) {
        super(name);
        this.symbol = symbol;

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
        } while ( (cellId > 9) || (cellId < 1) || board.getCellById(cellId).toString().equals(" "));
        TicTacToeCell cell = board.getCellById(cellId);
        cell.setContent(symbol);
        cell.setOwner(this);
    }
}
