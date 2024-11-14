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
            cellId = random.nextInt(9)+1;
        } while (!board.getCellById(cellId).toString().equals(" "));
        board.getCellById(cellId).setContent(getSymbol());
        board.getCellById(cellId).setOwner(this);
    }

    private int findWinningMove(TicTacToeBoard board, String symbol) {
        for (int i = 1; i <= 9; i++) {
            if(board.getCellById(i).toString().equals(" ")) {
                board.getCellById(i).setContent(symbol);
                if(board.checkWin()) {
                    if(board.getCellById(i).toString().equals(getSymbol())) {
                        board.getCellById(i).setOwner(this);
                    } else {
                        return i;
                    }
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