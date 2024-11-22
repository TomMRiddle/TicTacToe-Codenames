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
        String clue;
        int cluenumber;
        boolean tryAgain=false;
        board.setSpymasterView(true);
        System.out.println("Studera spelbrädet och fundera på en ledtråd som kan hjälpa dina agenter att gissa rätt! \nDin ledtråd ska bestå av ett ord följt av en siffra som representerar antalet agenter ditt ord passar in på. \nExempel: Träd 5.\nOBS! Ditt ord får inte vara ett av orden på aktuell spelplan. \nDin siffra får inte överstiga antalet agenter ditt lag har kvar att hitta.");
        System.out.println(board);

        do {
            tryAgain=false;
            System.out.println("Ange ditt ord: ");
            clue = spyscan.nextLine();

            for (List<CodenamesCell> cellRow : board.getAllCells()) {
                for (CodenamesCell cell : cellRow) {
                    if (clue.equalsIgnoreCase(cell.toString())){
                        System.out.println("\nVälj ett ord som inte finns på spelplanen!");
                        tryAgain = true;
                    }
                }
            }
        } while (tryAgain);

        do {
            tryAgain=false;
            System.out.println("\nAnge din siffra: ");
            cluenumber = spyscan.nextInt();

            int missingAgentCounter=0;

            for (List<CodenamesCell> cellRow : board.getAllCells()) {
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
        System.out.println(CLS+"Spymaster's ledtråd: \n" +clue+" "+cluenumber);
        board.setNumberOfGuesses(cluenumber);
    }
    public String getTeamColor() {
        return teamColor;
    }
}
