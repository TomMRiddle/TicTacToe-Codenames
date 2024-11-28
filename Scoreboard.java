import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Scoreboard {
    private static final String COMMA_DELIMITER = ", ";
    private static final Map<String, Scoreboard> INSTANCES = new HashMap<>();
    private final String gameType;
    private final Map<String, int[]> scores;
    private final String CSV_FILE_NAME;

    private Scoreboard(String gameType) {
        this.scores = new HashMap<>();
        this.gameType = gameType;
        this.CSV_FILE_NAME = "scoreboard_" + gameType + ".csv";
        load();
    }
    //singleton
    public static synchronized Scoreboard getInstance(String gameType) {
        if (!INSTANCES.containsKey(gameType)) {
            INSTANCES.put(gameType, new Scoreboard(gameType));
        }
        return INSTANCES.get(gameType);
    }

    private void load() {
        Path path = Paths.get(CSV_FILE_NAME);
        if (!Files.exists(path)) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_NAME))) {
                writer.println("Home Player,Away Player,Result");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String[] headers = reader.readLine().split(",");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == headers.length) {
                    Map<String, String> gameData = new HashMap<>();
                    for (int i = 0; i < headers.length; i++) {
                        gameData.put(headers[i], parts[i]);
                    }
                    
                    String homePlayer = gameData.get("Home Player");
                    String awayPlayer = gameData.get("Away Player");
                    int result = Integer.parseInt(gameData.get("Result"));

                    updateScores(homePlayer, awayPlayer, result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateScores(String homePlayer, String awayPlayer, int result) {
        final int DRAW = 2;
        final int WIN = 0;
        final int LOSS = 1;
        switch (result) {

            case 0: // Draw
                scores.computeIfAbsent(homePlayer, _ -> new int[3])[DRAW]++;
                scores.computeIfAbsent(awayPlayer, _ -> new int[3])[DRAW]++;
                break;
            case 1: // Home Player wins
                scores.computeIfAbsent(homePlayer, _ -> new int[3])[WIN]++;
                scores.computeIfAbsent(awayPlayer, _ -> new int[3])[LOSS]++;
                break;
            case 2: // Away Player wins
                scores.computeIfAbsent(homePlayer, _ -> new int[3])[LOSS]++;
                scores.computeIfAbsent(awayPlayer, _ -> new int[3])[WIN]++;
                break;
        }
    }

    public void addScore(String homePlayer, String awayPlayer, int result) {
        updateScores(homePlayer, awayPlayer, result);

        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_NAME, true))) {
            Path path = Paths.get(CSV_FILE_NAME);
            if (!Files.exists(path)) {
                writer.println("Home Player,Away Player,Result");
            }
            writer.println(homePlayer + "," + awayPlayer + "," + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printScoreboard() {
        System.out.println("Spel: " + gameType);
        System.out.printf("%-15s %-5s %-7s %-8s%n",
                "Namn", "Vinst", "Förlust", "Oavgjort");
        // Create a list from the entries of the map
        List<Map.Entry<String, int[]>> entryList = new ArrayList<>(scores.entrySet());

        // Sort the list by the number of wins (first element in the array)
        entryList.sort(new Comparator<Map.Entry<String, int[]>>() {
            @Override
            public int compare(Map.Entry<String, int[]> e1, Map.Entry<String, int[]> e2) {
                return Integer.compare(e2.getValue()[0], e1.getValue()[0]);
            }
        });
        for (Map.Entry<String, int[]> entry : entryList) {
            String player = entry.getKey().length() > 20 ? entry.getKey().substring(0, 17) + "..." : entry.getKey();
            int[] scores = entry.getValue();
            System.out.printf("%-15s %5d %7d %8d%n",
                    player, scores[0], scores[1], scores[2]);
        }
    }
}
