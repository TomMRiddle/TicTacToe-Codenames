import java.util.List;
import java.util.Scanner;
import static utils.Ansi.*;

public class SpymasterPlayer extends Player<CodenamesBoard>{
    private final String teamColor;

    public SpymasterPlayer(String name, String teamColor) {
        super(name);
        this.teamColor=teamColor;
    }

    @Override
    public void takeTurn(CodenamesBoard board) {
            // Visa varning men rensa inte skärmen
            System.out.println("\n=== VARNING ===");
            System.out.println("Det är dags för Spymaster från " + teamColor + "Det " + (teamColor.equals(BLUE) ? "blåa" : "röda") + " laget" + RESET + " att spela.");
            System.out.println("Alla agenter, titta bort från skärmen!");
            System.out.println("Tryck ENTER för att fortsätta...");
            System.out.println("==============\n");
            ScannerSingleton.getNextLine();

        String clue;
        int cluenumber;
        boolean tryAgain;
        board.setSpymasterView(true);
        System.out.println("Ge en ledtråd med ett ord och en siffra som visar hur många agenter ordet passar för. Exempel: TRÄD 5. Ordet får inte vara på spelplanen och siffran får inte överstiga antalet kvarvarande agenter.");
        System.out.println(board);

        do {
            System.out.println("Ange ditt ord: ");
            clue = ScannerSingleton.getNextLine();
            tryAgain = false;
            for (List<CodenamesCell> cellRow : board.cells) {
                for (CodenamesCell cell : cellRow) {
                    if (clue.equalsIgnoreCase(cell.toString()) && !cell.isRevealed()){
                        System.out.println("\nVälj ett ord som inte finns på spelplanen!");
                        tryAgain = true;
                    }
                }
            }
        } while (tryAgain);

        do {
            System.out.println("\nAnge din siffra: ");
            cluenumber = Integer.parseInt(ScannerSingleton.getNextLine());
            tryAgain = false;
            int missingAgentCounter=0;

            for (List<CodenamesCell> cellRow : board.cells) {
                for (CodenamesCell cell : cellRow) {
                    if(!cell.isRevealed() && cell.getColor()==teamColor){
                        missingAgentCounter++;
                    }
                }
            }
            if(cluenumber>missingAgentCounter){
                System.out.println("Din siffra får inte överstiga lagets ofunna agenter. Försök igen!");
                tryAgain=true;
            }
        } while (tryAgain);
        System.out.println("Spymaster's ledtråd: \n" +clue+" "+cluenumber);
        board.setNumberOfGuesses(cluenumber);
        board.setClue(clue);
    }
    public String getTeamColor() {
        return teamColor;
    }
}
