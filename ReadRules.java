import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ReadRules {
    public void gameRules() {
        System.out.println("Vill du/ni se spelreglerna?");
        System.out.println("1. Ja");
        System.out.println("2. Nej");
        System.out.print("Skriv in ett nummer (1 eller 2): ");
        int choice = ScannerSingleton.getInstance().getNextLineInt(1,2);
        if (choice == 1) {
            showGameRules();
        } else if (choice == 2) {
            System.out.println("Lycka till!");
        }
    }

    public static void showGameRules() {
        // Ange URL:n till regelboken
        String url = "https://raw.githubusercontent.com/TomMRiddle/TicTacToe-Codenames/6c7f22282299bb95cb6c3fa203aa71d9c5707c3b/CN_rules_ENG_web.pdf";

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


