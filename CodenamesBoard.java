import java.util.*;
import static utils.Ansi.*;
import static utils.Words.*;

public class CodenamesBoard extends Board<CodenamesCell> {
    private int numberOfGuesses;
    private boolean spymasterView;
    private String startingTeam;
    private String winningTeam;
    private String clue;
    public CodenamesBoard() {
        spymasterView = false;
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

    public Queue<String> createSecrets() {
        List<String> secrets = new ArrayList<>();

        // Add 7 innocent
        for (int i = 0; i < 7; i++) {
            secrets.add(BRIGHT_WHITE);
        }
        // Add 1 assassin

        secrets.add(BRIGHT_BLACK);

        // Add 8 agents from each team

        for (int i = 0; i < 8; i++) {
            secrets.add(RED);
            secrets.add(BLUE);
        }

        // Add a random extra agent from one of the teams (choose randomly between Team1 and Team2)
        if (Math.random() < 0.5) {
            secrets.add(RED);
            startingTeam = RED;
        } else {
            secrets.add(BLUE);
            startingTeam = BLUE;
        }

        // Shuffle the list to randomize the order
        Collections.shuffle(secrets);
        return new LinkedList<>(secrets);
    }

    @Override
    protected boolean checkWin() {
        int redThreshold = (startingTeam.equals(RED) ? 9 : 8);
        int blueThreshold = (startingTeam.equals(BLUE) ? 9 : 8);
        long redCount = cells.stream()
                .flatMap(List::stream)
                .filter(cell -> RED.equals(cell.getColor()) && cell.isRevealed())
                .count();

        long blueCount = cells.stream()
                .flatMap(List::stream)
                .filter(cell -> BLUE.equals(cell.getColor()) && cell.isRevealed())
                .count();

        long assassin = cells.stream()
                .flatMap(List::stream)
                .filter(cell -> BRIGHT_BLACK.equals(cell.getColor()) && cell.isRevealed())
                .count();


        if (assassin == 1) {
            winningTeam = BRIGHT_BLACK;
            return true;
        }
        if (redCount >= redThreshold) {
            winningTeam = RED;
            return true;
        } else if (blueCount >= blueThreshold) {
            winningTeam = BLUE;
            return true;
        } else {
            return false;
        }
    }

    public void setSpymasterView(boolean spymasterView) {
        for (List<CodenamesCell> cellRow : cells) {
            for (CodenamesCell cell : cellRow) {
                cell.setSpymasterView(spymasterView);
            }
        }
        this.spymasterView = spymasterView;
    }
    public void reveal(int cellId) {
        getCellById(cellId).reveal();
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    @Override
    public String toString() {
        String legend = BG+BLUE+"Agent för lag blå"+RESET+" "+BG+RED+"Agent för lag röd"+RESET+" "+BG+BRIGHT_BLACK+"Lönnmördare"+RESET+" "+BG+BRIGHT_WHITE+"oskyldig åskådare"+RESET+"\n\n";
        if (spymasterView) { legend = "█avslöjat kodnamn█ "+legend; } else { legend = BG+BRIGHT_YELLOW+"Ej avslöjade kodnamn"+RESET+" "+legend; }
        return legend + super.toString();
    }
    public void setNumberOfGuesses(int numberOfGuesses) {
        this.numberOfGuesses = numberOfGuesses;
    }
    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }
    public void setClue(String clue) {
        this.clue = clue;
    }
    public String getClue() {
        return clue;
    }
    public String getStartingTeam() {
        return startingTeam;
    }
}
