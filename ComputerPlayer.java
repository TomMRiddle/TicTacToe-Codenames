import java.util.Objects;
import java.util.Random;

public class ComputerPlayer extends Player {
    public ComputerPlayer(String name, String symbol) {
        super(name, symbol);
    }


    // "Logik" för datorn
    @Override
    public void makeMove(Board board) {

        // Vinnande drag
        int winningMove = findWinningMove(board, getSymbol());
        if(winningMove != -1) {
            makeMove(board, winningMove);
            return;
        }

        // Blockerande drag
        String opponentSymbol = getSymbol().equals("X") ? "O" : "X";
        int blockingMove = findWinningMove(board, opponentSymbol);
        if(blockingMove != -1) {
            makeMove(board, blockingMove);
            return;
        }


        // Random-drag om inget vinnadne/blockerande drag finns
        Random random = new Random();
        int cellId;

        do {
            cellId = random.nextInt(9)+1;
        } while (!Objects.equals(board.getCellById(cellId).toString(), " "));
        Cell cell = board.getCellById(cellId);
        cell.setContent(getSymbol());
    }

    private int findWinningMove(Board board, String symbol) {
        for (int i = 1; i <= 9; i++) {
            if(board.getCellById(i).toString().equals(" ")) {
                board.getCellById(i).setContent(symbol);
                if(checkWinner(board, symbol)) {
                    return i;
                }
                board.getCellById(i).setContent(" ");
            }
        }
        return -1;
    }

    private void makeMove (Board board, int cellId) {
        board.getCellById(cellId).setContent(getSymbol());
    }

    private boolean checkWinner(Board board, String symbol) {

        // Kontrollerar vinstdrag vertkialt
        for(int i = 0; i < 3; i++) {
            if(board.getCell(i, 0).toString().equals(symbol) &&
            board.getCell(i, 1).toString().equals(symbol) &&
            board.getCell(i, 2).toString().equals(symbol)) {
                return true;
            }
        }

        // Kontrollerar vinstdrag horisontellt
        for (int j = 0; j < 3; j++) {
            if(board.getCell(0, j).toString().equals(symbol) &&
            board.getCell(1, j).toString().equals(symbol) &&
            board.getCell(2, j).toString().equals(symbol)) {
                return true;
            }
        }

        // Kontrollerar diagonaler
        if (board.getCell(0, 0).toString().equals(symbol) &&
                board.getCell(1, 1).toString().equals(symbol) &&
                board.getCell(2, 2).toString().equals(symbol)) {
            return true;
        }
        if (board.getCell(0, 2).toString().equals(symbol) &&
                board.getCell(1, 1).toString().equals(symbol) &&
                board.getCell(2, 0).toString().equals(symbol)) {
            return true;
        }

        return false;
    }
}



