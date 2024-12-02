import java.util.ArrayList;
import java.util.List;

import static utils.Ansi.*;

public class SpymasterPlayer extends Player<CodenamesBoard> {
    private final String teamColor;

    public SpymasterPlayer(String name, String teamColor) {
        super(name);
        this.teamColor = teamColor;
    }

    @Override
    public void takeTurn(CodenamesBoard board) {
        // Visa varning men rensa inte skärmen
        System.out.println(CLS + "\n=== VARNING ===");
        System.out.println("Det är dags för Spymaster från " + teamColor + "Det " + (teamColor.equals(BLUE) ? "blåa" : "röda") + " laget" + RESET + " att spela.");
        System.out.println("Alla agenter, titta bort från skärmen!");
        System.out.println("Tryck ENTER för att fortsätta...");
        System.out.println("==============\n");
        ScannerSingleton.getInstance().pressEnterToContinue();

        board.setSpymasterView(true);
        System.out.println(CLS + teamColor + "Det " + (teamColor.equals(BLUE) ? "blåa" : "röda") + " lagets Spymaster: " + RESET + "Ange en ledtråd med ett ord och en siffra som visar hur många agenter ordet passar för. \nExempel: TRÄD 5. \nOBS! Ordet får inte finnas på spelplanen och siffran får inte överstiga antalet kvarvarande agenter på det egna laget.");
        System.out.println(board);

        List<String> forbiddenWords = new ArrayList<>();
        for (List<CodenamesCell> cellRow : board.getAllCells()) {
            for (CodenamesCell cell : cellRow) {
                if (!cell.isRevealed()) {
                    forbiddenWords.add(cell.toString());
                }
            }
        }

        System.out.println("Ange ett ord:");
        String clue = ScannerSingleton.getInstance().getNextLineWithForbiddenValues(
                forbiddenWords, "Välj ett ord som inte finns på spelplanen!"
        );

        int missingAgentCounter = 0;
        for (List<CodenamesCell> cellRow : board.cells) {
            for (CodenamesCell cell : cellRow) {
                if (!cell.isRevealed() && cell.getColor().equals(teamColor)) {
                    missingAgentCounter++;
                }
            }
        }

        System.out.println("\nAnge din siffra: ");
        int cluenumber = (ScannerSingleton.getInstance().getNextLineInt(1, missingAgentCounter));
        board.setNumberOfGuesses(cluenumber);
        board.setClue(clue);
    }

    public String getTeamColor() {
        return teamColor;
    }
}
