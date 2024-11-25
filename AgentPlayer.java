import java.util.List;
import java.util.Scanner;
import static utils.Ansi.*;

public class AgentPlayer extends Player<CodenamesBoard> {
    private final String teamColor;
    private final Scanner scanner;

    public AgentPlayer(String name, String teamColor) {
        super(name);
        scanner = new Scanner(System.in);
        this.teamColor = teamColor;
    }

    @Override
    public void takeTurn(CodenamesBoard board) {
        int totalGuesses = board.getNumberOfGuesses();
        String clue = board.getClue();
        System.out.println(CLS);
        System.out.println("Spymaster's ledtråd: \n" +clue+" "+totalGuesses);
        board.setSpymasterView(false);
        System.out.println(board);

        System.out.println(getName() + " från det" + teamColor + " " + (teamColor.equals(BLUE) ? "blå" : "röda") + RESET + " lagets tur.");
//        System.out.println("Skriv ett nummer för att gissa ett ord (1-25):");
//
//        int cellId = scanner.nextInt();

        guessWord(board, totalGuesses); //totalGuesses ska komma från spymasterns tur
    }

    public String getTeamColor() {
        return teamColor;
    }

    // Metod för att gissa ett ord, baserat på cellId
    public void guessWord(CodenamesBoard board, int totalGuesses) {
        int correctGuesses = 0;
        int remainingGuesses = totalGuesses;

        while (remainingGuesses > 0) {
            System.out.println("Skriv ett nummer för att gissa ett ord (1-25): ");

            // Hantera & felhantera inmatning av cellId
            int cellId;
            while (true) {
                try {
                    cellId = Integer.parseInt(scanner.nextLine().trim());
                    if (cellId < 1 || cellId > 25) {
                        System.out.println("Ogiltigt nummer. Välj mellan 1 och 25: ");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Ogiltig input. Ange ett nummer: ");
                }
            }

            CodenamesCell cell = board.getCellById(cellId);

            // Kontrollera om cellen redan är avslöjad
            if (cell.isRevealed()) {
                System.out.println("Detta ord har redan gissats. Försök igen.");
                continue;
            }

            // Avslöja cellen
            cell.reveal();
            System.out.println(CLS);
            System.out.println("Spymaster's ledtråd: \n" +board.getClue()+" "+totalGuesses);
            System.out.println(getName() + " gissar på: " + cell.toString());
            System.out.println(board);

            // feedback på gissningarna
            if (cell.getColor().equals(BRIGHT_BLACK)) {
                System.out.println("Lönnmördaren avslöjad! Spelet är över.");
                break;
            } else if (cell.getColor().equals(teamColor)) {
                System.out.println("Bra gissat! Detta är en " + teamColor + (teamColor.equals(BLUE) ? "blå" : "röd") + RESET + " agent! :D");
                correctGuesses++;
                remainingGuesses--;
            } else if (cell.getColor().equals(BRIGHT_WHITE)) {
                System.out.println("Fel! Detta var en oskyldig åskådare.");
                break;
            } else {
                String oppositeColor = teamColor.equals(BLUE) ? RED : BLUE;
                System.out.println("Fel! Detta var en " + oppositeColor + (oppositeColor.equals(BLUE) ? "blå" : "röd") + RESET + " agent :(");
                break;
            }

            if (board.checkWin()) {
                return;
            }

            System.out.println("Kvarstående gissningar: " + remainingGuesses);

            // Ge extra gissning om alla är korrekta
            if (correctGuesses == totalGuesses && remainingGuesses == 0) {
                System.out.println("Ni gissade rätt på alla och får en extra gissning!");
                remainingGuesses++;
            }

            // Fråga om att fortsätta om gissningar
            while(true) {
                if (remainingGuesses > 0) {
                    System.out.println("Vill ni fortsätta gissa? (ja/nej)");
                    String userResponse = scanner.nextLine().trim().toLowerCase();
    
                    if (userResponse.equals("ja")) {
                        break;
                    } else if (userResponse.equals("nej")) {
                        remainingGuesses = 0;
                        break;
                    } else {
                        System.out.println("Ogiltig input. Svara med ja eller nej.");
                    }
                }
            } 
        }
        System.out.println("Rundan är över. Tryck ENTER för att fortsätta.");
        scanner.nextLine();
    }
}

