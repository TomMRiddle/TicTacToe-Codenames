import java.util.Objects;
import java.util.Random;

public class ComputerPlayer extends Player {
    public ComputerPlayer(String name, String symbol) {
        super(name, symbol);
    }

    @Override                   //"Logik" för datorn
    public void makeMove(Board board) {
        Random random = new Random();
        int cellId;

        do {
            cellId = random.nextInt(8)+1;
        } while (!Objects.equals(board.getCellById(cellId).toString(), " "));
        Cell cell = board.getCellById(cellId);
        cell.setContent(getSymbol());
    }
}



