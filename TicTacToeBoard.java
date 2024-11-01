import java.util.ArrayList;
import java.util.List;

public class TicTacToeBoard extends Board{

    public TicTacToeBoard() {
        initialize(3, 3);
    }

    @Override
    protected List<List<Cell>> initialize(int numRows, int numCols) {
        cells = new ArrayList<>();
        int cellId = 1;
        for (int i = 0; i < numRows; i++) {
            List<Cell> row = new ArrayList<>();

            for (int j = 0; j < numCols; j++) {

                row.add(new TicTacToeCell(cellId));
                cellId++;
            }

            cells.add(row);
        }

        return cells;
    }
}
