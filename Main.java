public class Main {
    public static void main(String[] args) {
        Board board = new TicTacToeBoard();
        System.out.println(board);
        board.getCellById(5).setContent("O");
        System.out.println(board);

    }
}
