package tictactoe;

import base.Player;

import java.util.*;

public class ComputerPlayer extends Player<TicTacToeBoard> {
    private final String symbol;
    private final int[][] winnableLines;

    public ComputerPlayer(String name, String symbol) {
        super(name);
        this.symbol = symbol;
        winnableLines = new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}, // Rows
                {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, // Columns
                {1, 5, 9}, {3, 5, 7}             // Diagonals
        };
        Collections.shuffle(Arrays.asList(winnableLines)); //make it random and less predictable
    }


    // "Logik" för datorn
    @Override
    public void takeTurn(TicTacToeBoard board) {

        // Vinnande drag
        int winningMove = findWinningMove(board, symbol);
        if (winningMove != -1) {
            board.getCellById(winningMove).setContent(symbol);
            takeTurn(board, winningMove);
            return;
        }

        // Blockerande drag
        int blockingMove = findWinningMove(board, opponentSymbol(symbol));
        if (blockingMove != -1) {
            takeTurn(board, blockingMove);
            return;
        }

        //drag som kan leda till vinst
        int winnableMove = findWinnable(board, symbol);
        if (winnableMove != -1) {
            takeTurn(board, winnableMove);
            return;
        }

        // Random-drag om inget vinnande/blockerande drag finns
        Random random = new Random();
        int cellId;

        do {
            cellId = random.nextInt(9) + 1;
        } while (!board.getCellById(cellId).toString().equals(" "));
        board.getCellById(cellId).setContent(symbol);
        board.getCellById(cellId).setOwner(this);
    }

    private int findWinningMove(TicTacToeBoard board, String symbol) {
        for (int i = 1; i <= 9; i++) {
            if (board.getCellById(i).toString().equals(" ")) {
                // Try placing the symbol in this cell
                board.getCellById(i).setContent(symbol);

                // Check if this move results in a win for that player
                if (board.checkWin()) {
                    return i; // Return the winning cell index
                }

                // Revert the change to keep the board unchanged
                board.getCellById(i).setContent(" ");
            }
        }
        return -1;
    }

    private void takeTurn(TicTacToeBoard board, int cellId) {
        board.getCellById(cellId).setContent(symbol);
        board.getCellById(cellId).setOwner(this);
    }

    private int findWinnable(TicTacToeBoard board, String symbol) {
        List<Integer> winnablePositions = new ArrayList<>();

        // Check all lines
        for (int[] line : winnableLines) {
            checkLine(board, line, symbol, winnablePositions);
        }

        // Randomly return a winnable position or choose randomly from available cells
        if (!winnablePositions.isEmpty()) {
            return winnablePositions.get(new Random().nextInt(winnablePositions.size()));
        }

        // Fallback: Choose a random cell
        Random random = new Random();
        int cellId;

        do {
            cellId = random.nextInt(9) + 1;
        } while (!board.getCellById(cellId).toString().equals(" "));
        return cellId;
    }

    private void checkLine(TicTacToeBoard board, int[] line, String symbol, List<Integer> winnablePositions) {
        boolean hasEmptyCell = false;

        for (int cell : line) {
            if (board.getCellById(cell).toString().equals(symbol)) {
                // Skip already filled cells
                continue;
            }
            if (board.getCellById(cell).toString().equals(opponentSymbol(symbol))) {
                // Found an opponent's symbol, no winnable move here
                return;
            }
            hasEmptyCell = true;
        }

        if (hasEmptyCell) {
            for (int cell : line) {
                if (board.getCellById(cell).toString().equals(" ")) {
                    winnablePositions.add(cell);
                }
            }
        }
    }

    private String opponentSymbol(String symbol) {
        return symbol.equals("X") ? "O" : "X";
    }
}