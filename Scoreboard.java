import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Scoreboard {
    private static final String COMMA_DELIMITER = ", ";
    private static Scoreboard INSTANCE;
    private final Map<String, int[]> scores;
    private static final String CSV_FILE_NAME = "scores.csv";

    public Scoreboard() {
        scores = new HashMap<>();
        load();
    }
    //singleton
    public static synchronized Scoreboard getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Scoreboard();
        }
        return INSTANCE;
    }

    private void load() {
        if (!Files.exists(Paths.get(CSV_FILE_NAME))) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_NAME))) {
                writer.println("Game Type,Home Player,Away Player,Result");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(CSV_FILE_NAME))) {
            String[] headers = reader.readLine().split(",");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == headers.length) {
                    Map<String, String> gameData = new HashMap<>();
                    for (int i = 0; i < headers.length; i++) {
                        gameData.put(headers[i], parts[i]);
                    }

                    String gameType = gameData.get("Game Type");
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
        switch (result) {
            case 0: // Draw
                scores.computeIfAbsent(homePlayer, k -> new int[3])[2]++;
                scores.computeIfAbsent(awayPlayer, k -> new int[3])[2]++;
                break;
            case 1: // Home Player wins
                scores.computeIfAbsent(homePlayer, k -> new int[3])[0]++;
                scores.computeIfAbsent(awayPlayer, k -> new int[3])[1]++;
                break;
            case 2: // Away Player wins
                scores.computeIfAbsent(homePlayer, k -> new int[3])[1]++;
                scores.computeIfAbsent(awayPlayer, k -> new int[3])[0]++;
                break;
        }
    }

    public void addScore(String gameType, String homePlayer, String awayPlayer, int result) {
        updateScores(homePlayer, awayPlayer, result);

        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_NAME, true))) {
            Path path = Paths.get(CSV_FILE_NAME);
            if (!Files.exists(path) || !Files.readAllLines(path).get(0).contains("Game Type")) {
                writer.println("Game Type,Home Player,Away Player,Result");
            }
            writer.println(gameType + "," + homePlayer + "," + awayPlayer + "," + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printScoreboard(String gameType) {
        System.out.println("Spel: " + gameType);
        System.out.printf("%-15s %-5s %-7s %-8s%n",
                "Namn", "Vinst", "Förlust", "Oavgjort");
        for (Map.Entry<String, int[]> entry : scores.entrySet()) {
            String player = entry.getKey().length() > 20 ? entry.getKey().substring(0, 17) + "..." : entry.getKey();
            int[] scores = entry.getValue();
            System.out.printf("%-15s %5d %7d %8d%n",
                    player, scores[0], scores[1], scores[2]);
        }
    }
    private static String convertToCSV(List<String> fields) {
        return String.join(COMMA_DELIMITER, fields);
    }
}
