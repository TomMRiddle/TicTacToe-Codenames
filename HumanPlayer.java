import static utils.Ansi.*;

public class HumanPlayer extends Player<TicTacToeBoard> {
    private final String symbol;

    public HumanPlayer(String name, String symbol) {
        super(name);
        this.symbol = symbol;
    }

    @Override
    public void takeTurn(TicTacToeBoard board) {

        System.out.println(CLS + board);
        System.out.println(getName() + ", ange cellnummer (1-9): ");

        while (true) {
            int cellId = ScannerSingleton.getInstance().getNextLineInt(1, 9);

            TicTacToeCell cell = board.getCellById(cellId);
            if (cell.getOwner() != null) {
                System.out.println("Den cellen är redan tagen! Välj en annan.");
                continue;
            }

            cell.setContent(symbol);
            cell.setOwner(this);
            break;
        }
    }
}
