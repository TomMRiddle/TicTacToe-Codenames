import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import static utils.Ansi.*;

public class SpymasterPlayer extends Player<CodenamesBoard>{
    Scanner spyscan;
    private final String teamColor;

    public SpymasterPlayer(String name, String teamColor) {
        super(name);
        this.teamColor = teamColor;
        spyscan = new Scanner(System.in);
    }

    @Override
    public void takeTurn(CodenamesBoard board) {
        System.out.println(CLS);
        // Visa varning men rensa inte skärmen
        System.out.println("\n=== VARNING ===");
        System.out.println("Det är dags för Spymaster från " + teamColor + "Det " + (teamColor.equals(BLUE) ? "blåa" : "röda") + " laget" + RESET + " att spela.");
        System.out.println("Alla agenter, titta bort från skärmen!");
        System.out.println("Tryck ENTER för att fortsätta...");
        System.out.println("==============\n");
        spyscan.nextLine();
        System.out.println(CLS);
        String clue;
        int cluenumber;
        boolean tryAgain=true;
        board.setSpymasterView(true);
        System.out.println("Ge en ledtråd med ett ord och en siffra för antalet agenter. Exempel: TRÄD 5. Ordet får inte vara på spelplanen, och siffran får inte överstiga kvarvarande agenter");
        System.out.println(board);
        System.out.println(getName() + ", spymaster från det" + teamColor + " " + (teamColor.equals(BLUE) ? "blå" : "röda") + RESET + " lagets tur.");

        do {
            System.out.println("Ange ditt ord: ");
            clue = spyscan.nextLine();

            for (List<CodenamesCell> cellRow : board.cells) {
                for (CodenamesCell cell : cellRow) {
                    if (clue.equalsIgnoreCase(cell.toString())){
                        System.out.println("\nVälj ett ord som inte finns på spelplanen!");
                        tryAgain = true;
                        break;
                    }
                    else{
                        tryAgain = false;
                    }
                }
                if (tryAgain){
                    break;
                }
            }
        } while (tryAgain);

        do {
            System.out.println("\nAnge din siffra: ");
            cluenumber = spyscan.nextInt();

            int missingAgentCounter=0;

            for (List<CodenamesCell> cellRow : board.cells) {
                for (CodenamesCell cell : cellRow) {
                    if(!cell.isRevealed() && cell.getColor().equals(teamColor)){
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
        board.setNumberOfGuesses(cluenumber);
        board.setClue(clue);
    }
    public String getTeamColor() {
        return teamColor;
    }
}
