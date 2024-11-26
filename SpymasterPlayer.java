import java.util.List;
import java.util.Scanner;
import static utils.Ansi.*;

public class SpymasterPlayer extends Player<CodenamesBoard>{
    Scanner spyscan;
    private final String teamColor;

    public SpymasterPlayer(String name, String teamColor) {
        super(name);
        this.teamColor=teamColor;
        spyscan = new Scanner(System.in);
    }

    @Override
    public void takeTurn(CodenamesBoard board) {
            // Visa varning men rensa inte skärmen
            System.out.println("\n=== VARNING ===");
            System.out.println("Det är dags för Spymaster från " + teamColor + "Det " + (teamColor.equals(BLUE) ? "blåa" : "röda") + " laget" + RESET + " att spela.");
            System.out.println("Alla agenter, titta bort från skärmen!");
            System.out.println("Tryck ENTER för att fortsätta...");
            System.out.println("==============\n");
            spyscan.nextLine();

        String clue;
        int cluenumber;
        boolean tryAgain;
        board.setSpymasterView(true);
        System.out.println("Studera spelbrädet och fundera på en ledtråd som kan hjälpa dina agenter att gissa rätt! \nDin ledtråd ska bestå av ett ord följt av en siffra som representerar antalet agenter ditt ord passar in på. Exempel: TRÄD 5.\nOBS! Ditt ord får inte vara ett av orden på aktuell spelplan. Din siffra får inte överstiga antalet agenter ditt lag har kvar att hitta.");
        System.out.println(board);

        do {
            System.out.println("Ange ditt ord: ");
            clue = spyscan.nextLine();
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
            cluenumber = spyscan.nextInt();
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
