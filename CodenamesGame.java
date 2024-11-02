import java.util.List;

public class CodenamesGame {
    //only use main until we have a main class with the menu
    public static void main(String[] args) {
        Board board = new CodenamesBoard();
        System.out.println(board);
        for(List<Cell> cells : board.getAllCells()) {
            for(Cell cell : cells) {

            }
        }
    }
}
