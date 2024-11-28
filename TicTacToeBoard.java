import java.util.ArrayList;
import java.util.List;
import static utils.Ansi.*;

public class TicTacToeBoard extends Board<TicTacToeCell> {
    private Player<TicTacToeBoard> winner;
    private boolean draw;
    public TicTacToeBoard() {
        cells = new ArrayList<>();
        int cellId = 1;
        for (int i = 0; i < 3; i++) {
            List<TicTacToeCell> row = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                row.add(new TicTacToeCell(cellId));
                cellId++;
            }

            cells.add(row);
        }
        winner = null;
    }

    @Override
    protected boolean checkWin() {
        // Kontrollerar vinstdrag vertikalt
        for (int i = 0; i < 3; i++) {
            if (!getCell(i, 0).toString().equals(" ") &&
                getCell(i, 0).toString().equals(getCell(i, 1).toString()) &&
                getCell(i, 1).toString().equals(getCell(i, 2).toString())) {

                if(getCell(i, 0).getOwner().equals(getCell(i, 1).getOwner()) &&
                    getCell(i, 1).getOwner().equals(getCell(i, 2).getOwner())) {
                    winner = getCell(i,0).getOwner();
                    getCell(i,0).setColor(GREEN);
                    getCell(i,1).setColor(GREEN);
                    getCell(i,2).setColor(GREEN);
                }
                return true;
            }
        }

        // Kontrollerar vinstdrag horisontellt
        for (int j = 0; j < 3; j++) {
            if (!getCell(0, j).toString().equals(" ") &&
            getCell(0, j).toString().equals(getCell(1, j).toString()) &&
            getCell(1, j).toString().equals(getCell(2, j).toString())) {

                if(getCell(0, j).getOwner().equals(getCell(1, j).getOwner()) &&
                getCell(1, j).getOwner().equals(getCell(2, j).getOwner())) {
                    winner = getCell(0, j).getOwner();
                    getCell(0,j).setColor(GREEN);
                    getCell(1,j).setColor(GREEN);
                    getCell(2,j).setColor(GREEN);
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
                getCell(0,0).setColor(GREEN);
                getCell(1,1).setColor(GREEN);
                getCell(2,2).setColor(GREEN);
            }
            return true;
        }
        if (!getCell(0, 2).toString().equals(" ") &&
        getCell(0, 2).toString().equals(getCell(1, 1).toString()) &&
        getCell(1, 1).toString().equals(getCell(2, 0).toString())) {

            if(getCell(0, 2).getOwner() == getCell(1, 1).getOwner() &&
            getCell(1, 1).getOwner() == getCell(2, 0).getOwner()) {
                winner = getCell(0, 2).getOwner();
                getCell(0, 2).setColor(GREEN);
                getCell(1,1).setColor(GREEN);
                getCell(2,0).setColor(GREEN);
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
