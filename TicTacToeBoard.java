import java.util.ArrayList;
import java.util.List;

public class TicTacToeBoard extends Board<TicTacToeCell> {

    public TicTacToeBoard() {
        initialize(3, 3);
    }

    @Override
    protected void initialize(int numRows, int numCols) {
        cells = new ArrayList<>();
        int cellId = 1;
        for (int i = 0; i < numRows; i++) {
            List<TicTacToeCell> row = new ArrayList<>();

            for (int j = 0; j < numCols; j++) {
                row.add(new TicTacToeCell(cellId));
                cellId++;
            }

            cells.add(row);
        }
    }
    @Override
    protected void checkWin() {
        // Check rows
        int numCols = cells.getFirst().size();
        int numRows = cells.size();
        for (List<TicTacToeCell> cell : cells) {
            if (cell.getFirst().getOwner() != null &&
                    cell.getFirst().getOwner().equals(cell.get(1).getOwner()) &&
                    cell.get(1).getOwner().equals(cell.getLast().getOwner())) {
                winner = cell.getFirst().getOwner();
            }
        }

        // Check columns
        for (int col = 0; col < numCols; col++) {
            if (cells.getFirst().get(col).getOwner() != null &&
                    cells.getFirst().get(col).getOwner().equals(cells.get(1).get(col).getOwner()) &&
                    cells.get(1).get(col).getOwner().equals(cells.getLast().get(col).getOwner())) {
                winner = cells.getFirst().get(col).getOwner();
            }
        }

        // Check diagonals
        if(cells.get(1).get(1) !=  null) {
            if ((cells.getFirst().getFirst().getOwner() != null &&
                    cells.getFirst().getFirst().getOwner().equals(cells.get(1).get(1).getOwner()) &&
                    cells.get(1).get(1).getOwner().equals(cells.getLast().getLast().getOwner()))
                    || (cells.getFirst().getLast().getOwner() != null &&
                    cells.getFirst().getLast().getOwner().equals(cells.get(1).get(1).getOwner()) &&
                    cells.get(1).get(1).getOwner().equals(cells.getLast().getFirst().getOwner()))) {
                winner = cells.get(1).get(1).getOwner();
            }
        }

        // No winner check if board is full
        if (winner == null) {
            boolean allFilled = true;
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    if (cells.get(row).get(col).getOwner() == null) {
                        allFilled = false;
                        break;
                    }
                }
                if (!allFilled) {
                    break;
                }
            }
            if (allFilled) {
                draw = true;
            }
        }
    }
    public Player getWinner() {
        return winner;
    }
    public boolean isDraw() {
        return draw;
    }
}
