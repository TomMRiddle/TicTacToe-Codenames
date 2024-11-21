import java.util.List;
import java.util.Scanner;

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
        System.out.println("Studera spelbrädet och fundera på en ledtråd som kan hjälpa dina agenter att gissa rätt! \nDin ledtråd ska bestå av ett ord följt av en siffra som representerar antalet agenter ditt ord passar in på. Exempel: TRÄD 5.\nOBS! Ditt ord får inte vara ett av orden på aktuell spelplan. Din siffra får inte överstiga antalet agenter ditt lag har kvar att hitta.");
        
        String clue;
        int cluenumber;
        boolean tryAgain=true;
       
        do {
            System.out.println("Ange ditt ord: ");
            clue = spyscan.nextLine();
    
            for (List<CodenamesCell> cellRow : board.cells) {
                for (CodenamesCell cell : cellRow) {
                    if (clue.equals(cell.content)){
                        System.out.println("Välj ett ord som inte finns på spelplanen!");
                        tryAgain = true;
                    }
                    else{
                        tryAgain = false;
                    }                
                }
            }            
        } while (tryAgain);
       
        do {
            System.out.println("Ange din siffra: ");
            cluenumber = spyscan.nextInt();

            int revealCounter=0;

            for (List<CodenamesCell> cellRow : board.cells) {
                for (CodenamesCell cell : cellRow) {
                    if(cell.isRevealed()){
                        revealCounter++;
                    }
                }
            }
            if(cluenumber>revealCounter){
                System.out.println("Din siffra får inte överstiga lagets ofunna agenter. Försök igen!");
                tryAgain=true;
            }
            else {
                tryAgain=false;
            }
        } while (tryAgain);
        System.out.println("Fin ledtråd! \nTryck ENTER för att dölja spelbrädets färger och visa ledtråden för dina lagkamrater.");

        System.out.println("Spymaster's ledtråd: \n" +clue +cluenumber);        
    }
}
