import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ReadRules {
    public void gameRules() {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Vill du/ni se spelreglerna?");
            System.out.println("1. Ja");
            System.out.println("2. Nej");
            System.out.print("Skriv in ett nummer (1 eller 2): ");

            try {
                int choice = Integer.parseInt(ScannerSingleton.getNextLine());

                if (choice == 1) {
                    showGameRules();
                    validInput = true;
                } else if (choice == 2) {
                    System.out.println("Lycka till!");
                    validInput = true;
                } else {
                    System.out.println("Ogiltigt val. Välj 1 eller 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ogiltig inmatning. Ange en siffra.");
            }
        }
    }
    
    public static void showGameRules() {
        // Ange URL:n till länken
        String url = "https://github.com/TomMRiddle/TicTacToe-Codenames/blob/berggren00-patch-1/CN_rules_ENG_web.pdf";
        
        try {
            // Kontrollera om Desktop är tillgängligt
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                // Försök öppna webblänken i standardwebbläsaren
                desktop.browse(new URI(url));
                System.out.println("Webblänk öppnas...");
            } else {
                System.out.println("Din enhet stöds inte på detta system :()");
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println("Ett fel inträffade.");
            e.printStackTrace();
        }
    }
}


