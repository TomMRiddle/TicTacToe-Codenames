import java.util.Objects;
import java.util.Random;

public class ComputerPlayer extends Player<TicTacToeBoard> {
    public ComputerPlayer(String name, String symbol) {
        super(name, symbol);
    }

    @Override                   //"Logik" för datorn
    public void takeTurn(TicTacToeBoard board) {
        Random random = new Random();
        int cellId;

        do {
            cellId = random.nextInt(9)+1;
        } while (!Objects.equals(board.getCellById(cellId).toString(), " "));
        TicTacToeCell cell = board.getCellById(cellId);
        cell.setContent(getSymbol());
        cell.setOwner(this);
    }
}



