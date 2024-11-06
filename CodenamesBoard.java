import java.util.*;
import static utils.Ansi.*;
import static utils.Words.*;

public class CodenamesBoard extends Board<CodenamesCell> {

    public CodenamesBoard() {
        initialize(5,5);
    }
    @Override
    protected void initialize(int numRows, int numCols) {
        Collections.shuffle(words);
        Queue<String> content = new LinkedList<>(words.subList(0,25));
        Queue<String> secret = createSecrets();
        cells = new ArrayList<>();
        int cellId = 1;
        for (int i = 0; i < numRows; i++) {
            List<CodenamesCell> row = new ArrayList<>();

            for (int j = 0; j < numCols; j++) {

                row.add(new CodenamesCell(cellId, content.poll(), secret.poll()));
                cellId++;
            }

            cells.add(row);
        }
    }

    private Queue<String> createSecrets() {
        List<String> secrets = new ArrayList<>();

        // Add 7 innocent
        for (int i = 0; i < 7; i++) {
            secrets.add(WHITE);
        }
        // Add 1 assassin

        secrets.add(BLACK);

        // Add 8 agents from each team

        for (int i = 0; i < 8; i++) {
            secrets.add(RED);
            secrets.add(BLUE);
        }

        // Add a random extra agent from one of the teams (choose randomly between Team1 and Team2)
        if (Math.random() < 0.5) {
            secrets.add(RED);
        } else {
            secrets.add(BLUE);
        }

        // Shuffle the list to randomize the order
        Collections.shuffle(secrets);
        return new LinkedList<>(secrets);
    }

    @Override
    protected void checkWin() {

    }

    @Override
    public boolean isDraw() {
        //never possible in Codenames
        return false;
    }

    @Override
    public Player getWinner() {
        return null;
    }

}
