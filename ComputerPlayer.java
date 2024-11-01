import java.util.Random;

public class ComputerPlayer extends Player {
    public ComputerPlayer(String name, char symbol) {
        super(name, symbol);
    }

    @Override                   //"Logik" för datorn
    public int[] makeMove(){
        Random random = new Random();
        int row, col;

        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != ' '); // board/grid?
        return new int[]{row, col};
    }
}



