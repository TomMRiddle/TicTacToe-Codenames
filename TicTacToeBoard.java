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
    protected boolean checkWin() {
        // Kontrollerar vinstdrag vertikalt
        for (int i = 0; i < 3; i++) {
            if (!getCell(i, 0).toString().equals(" ") &&
                getCell(i, 0).toString().equals(getCell(i, 1).toString()) &&
                getCell(i, 1).toString().equals(getCell(i, 2).toString())) {

                if(getCell(i, 0).getOwner() == getCell(i, 1).getOwner() &&
                    getCell(i, 1).getOwner() == getCell(i, 2).getOwner()) {
                    winner = getCell(i,0).getOwner();
                }
                return true;
            }
        }

        // Kontrollerar vinstdrag horisontellt
        for (int j = 0; j < 3; j++) {
            if (!getCell(0, j).toString().equals(" ") &&
            getCell(0, j).toString().equals(getCell(1, j).toString()) &&
            getCell(1, j).toString().equals(getCell(2, j).toString())) {

                if(getCell(0, j).getOwner() == getCell(1, j).getOwner() &&
                getCell(1, j).getOwner() == getCell(2, j).getOwner()) {
                    winner = getCell(0, j).getOwner();
                }
                return true;
            }
        }

        // Kontrollerar diagonaler
        if (!getCell(0, 0).toString().equals(" ") &&
        getCell(0, 0).toString().equals(getCell(1, 1).toString()) &&
        getCell(1, 1).toString().equals(getCell(2, 2).toString())) {

            if(getCell(0, 0).getOwner() == getCell(1, 1).getOwner() &&
            getCell(1, 1).getOwner() == getCell(2, 2).getOwner()) {
                winner = getCell(0,0).getOwner();
            }
            return true;
        }
        if (!getCell(0, 2).toString().equals(" ") &&
        getCell(0, 2).toString().equals(getCell(1, 1).toString()) &&
        getCell(1, 1).toString().equals(getCell(2, 0).toString())) {

            if(getCell(0, 2).getOwner() == getCell(1, 1).getOwner() &&
            getCell(1, 1).getOwner() == getCell(2, 0).getOwner()) {
                winner = getCell(0, 2).getOwner();
            }
            return true;
        }

        // No winner check if board is full
        boolean allFilled = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
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
            return false;
        }
        return false;
    }
    public Player<TicTacToeBoard> getWinner() {
        return winner;
    }
    public boolean isDraw() {
        return draw;
    }
}
