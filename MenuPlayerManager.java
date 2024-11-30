import java.util.Scanner;

import static utils.Ansi.*;

public class MenuPlayerManager {


    public String[] getPlayerNames(int playerCount) {
        String[] playerNames = new String[playerCount];

        System.out.println(CLS + """
                ┌──────────────────────────────────┐
                │    VÄLJ SPELARNAMN FÖR SPELET    │
                │                                  │
                │  Skriv in namn för varje spelare │
                └──────────────────────────────────┘"""
        );

        for (int i = 0; i < playerCount; i++) {

            System.out.printf("""
                            ┌──────────────────────────────────┐
                            │       Skriv in Spelare %d:        │
                            └──────────────────────────────────┘
                            """
                    , i + 1);

            playerNames[i] = ScannerSingleton.getInstance().getNextLine().trim();
        }
        return playerNames;
    }

    public int[] selectSpymasters(String[] allPlayers) {
        int redTeamSize = (allPlayers.length + 1) / 2;
        int[] spymasterIndices = new int[2];

        // Det röda laget väljer Spymaster
        System.out.println(CLS + """
                ┌─────────────────────────────────────┐
                │  VÄLJ SPYMASTER FÖR DET RÖDA LAGET  │
                │                                     │
                │     Välj nummer för spymaster:      │
                └─────────────────────────────────────┘"""
        );

        for (int i = 0; i < redTeamSize; i++) {
            System.out.printf("%d. %s%n", i + 1, allPlayers[i]);
        }

        spymasterIndices[0] = ScannerSingleton.getInstance().getNextLineInt(1, redTeamSize) - 1;

        // Det blåa laget väljer Spymaster
        System.out.println(CLS + """
                ┌─────────────────────────────────────┐
                │  VÄLJ SPYMASTER FÖR DET BLÅ LAGET   │
                │                                     │
                │     Välj nummer för spymaster:      │
                └─────────────────────────────────────┘"""
        );

        for (int i = redTeamSize; i < allPlayers.length; i++) {
            System.out.printf("%d. %s%n", i - redTeamSize + 1, allPlayers[i]);
        }

        spymasterIndices[1] = redTeamSize + ScannerSingleton.getInstance().getNextLineInt(1, allPlayers.length - redTeamSize) - 1;


        return spymasterIndices;
    }
}