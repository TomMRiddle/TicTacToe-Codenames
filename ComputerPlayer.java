import java.util.Objects;
import java.util.Random;

public class ComputerPlayer extends Player<TicTacToeBoard> {
    public ComputerPlayer(String name, String symbol) {
        super(name, symbol);
    }


    // "Logik" för datorn
    @Override
    public void takeTurn(TicTacToeBoard board) {

        // Vinnande drag
        int winningMove = findWinningMove(board, getSymbol());
        if(winningMove != -1) {
            takeTurn(board, winningMove);
            return;
        }

        // Blockerande drag
        String opponentSymbol = getSymbol().equals("X") ? "O" : "X";
        int blockingMove = findWinningMove(board, opponentSymbol);
        if(blockingMove != -1) {
            takeTurn(board, blockingMove);
            return;
        }


        // Random-drag om inget vinnande/blockerande drag finns
        Random random = new Random();
        int cellId;

        do {
            cellId = random.nextInt(8)+1;
        } while (!Objects.equals(board.getCellById(cellId).toString(), " "));
        TicTacToeCell cell = board.getCellById(cellId);
        cell.setContent(getSymbol());
        cell.setOwner(this);
    }

    private int findWinningMove(TicTacToeBoard board, String symbol) {
        for (int i = 1; i <= 9; i++) {
            if(board.getCellById(i).toString().equals(" ")) {
                board.getCellById(i).setContent(symbol);
                board.checkWin();
                if(board.getWinner() != null || board.isDraw()) {
                    return i;
                }
                board.getCellById(i).setContent(" ");
            }
        }
        return -1;
    }

    private void takeTurn(TicTacToeBoard board, int cellId) {
        board.getCellById(cellId).setContent(getSymbol());
        board.getCellById(cellId).setOwner(this);
    }
}