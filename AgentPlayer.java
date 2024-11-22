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

        System.out.println(getName() + " från det" + teamColor + (teamColor.equals(BLUE) ? "blå" : "röda") + RESET + "lagets tur.");
        System.out.println("Skriv ett nummer för att gissa ett ord (1-25):");

        int cellId = scanner.nextInt();

        guessWord(board, 3); //totalGuesses ska komma från spymasterns tur
    }

    public String getTeamColor() {
        return teamColor;
    }

    // Metod för att gissa ett ord, baserat på cellId
    public void guessWord(CodenamesBoard board, int totalGuesses) {
        int correctGuesses = 0; // Räknar korrekta gissningar
        int remainingGuesses = totalGuesses; // Antal gissningar laget har kvar
        int cellId;

        while (remainingGuesses > 0) {
            System.out.println("Skriv ett nummer för att gissa ett ord (1-25):");
            cellId = scanner.nextInt();
            CodenamesCell cell = board.getCellById(cellId);

            // Kontrollera om cellId är ogiltigt eller cellen redan är avslöjad
            if (cell == null || cell.isRevealed()) {
                System.out.println(cell == null ? "Ogiltigt gissning." : "Detta ord har redan gissats.");
                continue;
            }

            // Kalla på metoden för att "reveala cell"
            cell.reveal();
            System.out.println(getName() + " gissar på: " + cell.toString());

            // Kontrollera cellens "hemlighet"
            if (cell.getColor().equals(BRIGHT_BLACK)) {
                System.out.println("Lönnmördaren avslöjad! Spelet är över.");
                break; // Förlorar och avslutar spelet
            } else if (cell.getColor().equals(teamColor)) {
                System.out.println("Bra gissat! Detta är en " + teamColor + "-agent.");
                correctGuesses++;
            } else if (cell.getColor().equals(BRIGHT_WHITE)) {
                System.out.println("Fel! Detta var en oskyldig åskådare.");
                break; //turen går över till nästa spelare
            } else {
                System.out.println("Fel! Detta var inte en " + teamColor + "-agent.");
                break; //turen går över till nästa spelare
            }

            // Minska kvarvarande gissningar
            remainingGuesses--;

            // Extra gissning om alla föregående var korrekta
            if (correctGuesses == totalGuesses && remainingGuesses == 0) {
                System.out.println("Ni gissade rätt på alla och får därmed en extra gissning!");
                remainingGuesses++;
            }

            if (remainingGuesses > 0) {
                System.out.println("Vill ni fortsätta gissa? (ja/nej)");
                String userResponse = scanner.nextLine().trim().toLowerCase();
                if (userResponse.equals("nej")) {
                    break;
                }
            }
        }
        System.out.println("Rundan är över.");
    }
}

