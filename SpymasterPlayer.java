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
        System.out.println("Nu är det "+ name+", spymastern för det "+teamColor+BG+(teamColor == RED ? "röda" : "blå")+RESET+" lagets tur");
        System.out.println("Studera spelbrädet och fundera på en ledtråd som kan hjälpa dina agenter att gissa rätt! \nDin ledtråd ska bestå av ett ord följt av en siffra som representerar antalet agenter ditt ord passar in på. \nExempel: Träd 5.\nOBS! Ditt ord får inte vara ett av orden på aktuell spelplan. \nDin siffra får inte överstiga antalet agenter ditt lag har kvar att hitta.");
        spyscan.nextLine();
        System.out.println("Skriv något och tryck enter för att visa spelbrädet");
        System.out.println(board);

        do {
            tryAgain=false;
            System.out.println("Ange ditt ord: ");
            clue = spyscan.nextLine();

            for (List<CodenamesCell> cellRow : board.getAllCells()) {
                for (CodenamesCell cell : cellRow) {
                    if (clue.trim().equalsIgnoreCase(cell.toString()) && !cell.isRevealed() || clue.trim().isEmpty()){
                        System.out.println("\nVälj ett ord som inte finns på spelplanen!");
                        tryAgain = true;
                        break;
                    }
                }
                if (tryAgain){
                    break;
                }
            }
        } while (tryAgain);

        do {
            tryAgain=false;
            System.out.println("\nAnge din siffra: ");
            cluenumber = spyscan.nextInt();
        System.out.println("Studera spelbrädet och fundera på en ledtråd som kan hjälpa dina agenter att gissa rätt! \nDin ledtråd ska bestå av ett ord följt av en siffra som representerar antalet agenter ditt ord passar in på. Exempel: TRÄD 5.\nOBS! Ditt ord får inte vara ett av orden på aktuell spelplan. Din siffra får inte överstiga antalet agenter ditt lag har kvar att hitta.");


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
        do {
            System.out.println("Ange ditt ord: ");
            clue = spyscan.nextLine();

            for (List<CodenamesCell> cellRow : board.cells) {
                for (CodenamesCell cell : cellRow) {
                    if (clue.equals(cell.content)){
                        System.out.println("\nVälj ett ord som inte finns på spelplanen!");
                        tryAgain = true;
                    }
                    else{
                        tryAgain = false;
                    }
                }
            }
        } while (tryAgain);

        do {
            System.out.println("\nAnge din siffra: ");
            cluenumber = spyscan.nextInt();

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
            else {
                tryAgain=false;
            }
        } while (tryAgain);
        System.out.println(CLS+"Spymaster's ledtråd: \n" +clue+" "+cluenumber);
    }
    public String getTeamColor() {
        return teamColor;
    }
}
