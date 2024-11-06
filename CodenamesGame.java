import java.util.List;

public class CodenamesGame {
    //only use main until we have a main class with the menu
    public static void main(String[] args) {
        Board<CodenamesCell> board = new CodenamesBoard();
        System.out.println(board);
        for(List<CodenamesCell> cells : board.getAllCells()) {
            for(CodenamesCell cell : cells) {
                cell.toggleSpymasterView();
            }
        }
    }
}
