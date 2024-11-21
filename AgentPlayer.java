import java.util.List;
import java.util.Scanner;
import static utils.Ansi.*;

public class AgentPlayer extends Player<CodenamesBoard> {
    private final String teamColor;

    public AgentPlayer(String name, String teamColor) {
        super(name);
        this.teamColor = teamColor;
    }

    @Override
    public void takeTurn(CodenamesBoard board) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(getName() + " från laget " + teamColor + "s tur.");
        System.out.println("Skriv ett nummer för att gissa ett ord (1-25):");

        int cellId = scanner.nextInt();

        guessWord(board, cellId, 3);

        if (hasWon(board)) {
            System.out.println("Grattis! " + teamColor + " laget har vunnit!");
        }
    }

    public String getTeamColor() {
        return teamColor;
    }

    // Metod för att gissa ett ord, baserat på cellID
    public void guessWord(CodenamesBoard board, int cellId, int totalGuesses) {
        int correctGuesses = 0; // Räknar korrekta gissningar
        int remainingGuesses = totalGuesses; // Antal gissningar laget har kvar
        Scanner scanner = new Scanner(System.in);

        while (remainingGuesses > 0) {
            CodenamesCell cell = board.getCellById(cellId);

            // Kontrollera om cellId är ogiltigt eller cellen redan är avslöjad
            if (cell == null || cell.isRevealed()) {
                System.out.println(cell == null ? "Ogiltigt gissning." : "Detta ord har redan gissats.");
                return;
            }

            // Kalla på metoden för att "reveala cell"
            cell.reveal();
            System.out.println(getName() + " gissar på: " + cell.toString());

            // Kontrollera cellens "hemlighet"
            if (cell.getSecret().equals(BRIGHT_BLACK)) {
                System.out.println("Lönnmördaren avslöjad! Spelet är över.");
                break; // Avsluta spelet
            } else if (cell.getSecret().equals(teamColor)) {
                System.out.println("Bra gissat! Detta är en " + teamColor + "-agent.");
                correctGuesses++;
                cell.setColor(teamColor); // Markera cellen med lagets färg
            } else if (cell.getSecret().equals(BRIGHT_WHITE)) {
                System.out.println("Fel! Detta var en neutral agent.");
            } else {
                System.out.println("Fel! Detta var inte en " + teamColor + "-agent.");
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

                // Extra gissning
                System.out.println("Skriv ett nummer för att gissa ett ord (1-25):");
                cellId = scanner.nextInt();
            }
        }

        System.out.println("Rundan är över.");
    }


//    // Kontrollera om spelarens lag har vunnit
//    public boolean hasWon(CodenamesBoard board) {
//        int revealedAgents = 0;
//
//        // Gå igenom alla celler och räkna avslöjade agenter för laget
//        for (List<CodenamesCell> row : board.getAllCells()) {
//            for (CodenamesCell cell : row) {
//                if (cell.getSecret().equals(teamColor) && cell.isRevealed()) {
//                    revealedAgents++;
//                }
//            }
//        }
//
//        // Kontrollera om rätt antal agenter för laget har avslöjats för att vinna
//        if (teamColor.equals("RED") && revealedAgents == 9) {
//            return true;
//        } else if (teamColor.equals("BLUE") && revealedAgents == 8) {
//            return true;
//        }
//
//        return false;
//    }
}

