import java.util.List;

import static utils.Ansi.*;

public abstract class Board {
    protected List<List<Cell>> cells;

    public Board() {
    }
    public Board(int numRows, int numCols) {
        this.cells = initialize(numRows, numCols);
    }

    protected abstract List<List<Cell>> initialize(int numRows, int numCols);

    public Cell getCell(int row, int col) {

        return cells.get(row).get(col);
    }
    public Cell getCellById(int id) {
        int numCols = cells.getFirst().size();
        int cellsCount = numCols * cells.size();
        if (id > cellsCount || id < 1) {
            int row = (id - 1) / numCols;
            int col = (id - 1) % numCols;
            return cells.get(row).get(col);
        } else {
            throw new IllegalArgumentException("Invalid cell id: " + id);
        }
    }

    public List<List<Cell>> getAllCells() {
        return cells;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.getFirst().size(); j++) {
                String colorCode = cells.get(i).get(j).getColor(); // Adjust index to use correct color codes
                out.append(BG + BOLD).append(colorCode).append(cells.get(i).get(j));
                out.append(RESET);
            }
            out.append("\n");
        }
        return out.toString();
    }
}