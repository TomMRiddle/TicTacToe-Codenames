package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Scoreboard {
    private static final Map<String, Scoreboard> INSTANCES = new HashMap<>();
    private final String gameType;
    private final Map<String, int[]> scores;
    private final String csvFileName;

    private Scoreboard(String gameType) {
        this.scores = new HashMap<>();
        this.gameType = gameType;
        this.csvFileName = "scoreboard_" + gameType + ".csv";
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
        Path path = Paths.get(csvFileName);
        if (!Files.exists(path)) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFileName))) {
                writer.println("Home base.Player,Away base.Player,Result");
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

                    String homePlayer = gameData.get("Home base.Player");
                    String awayPlayer = gameData.get("Away base.Player");
                    int result = Integer.parseInt(gameData.get("Result"));

                    updateScores(homePlayer, awayPlayer, result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateScores(String homePlayer, String awayPlayer, int result) {
        final int WIN = 0;
        final int LOSS = 1;
        final int DRAW = 2;
        switch (result) {

            case 0: // Draw
                scores.computeIfAbsent(homePlayer, _ -> new int[3])[DRAW]++;
                scores.computeIfAbsent(awayPlayer, _ -> new int[3])[DRAW]++;
                break;
            case 1: // Home base.Player wins
                scores.computeIfAbsent(homePlayer, _ -> new int[3])[WIN]++;
                scores.computeIfAbsent(awayPlayer, _ -> new int[3])[LOSS]++;
                break;
            case 2: // Away base.Player wins
                scores.computeIfAbsent(homePlayer, _ -> new int[3])[LOSS]++;
                scores.computeIfAbsent(awayPlayer, _ -> new int[3])[WIN]++;
                break;
        }
    }

    public void addScore(String homePlayer, String awayPlayer, int result) {
        updateScores(homePlayer, awayPlayer, result);
        Path path = Paths.get(csvFileName);
        if (!Files.exists(path)) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFileName))) {
                writer.println("Home base.Player,Away base.Player,Result");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            String line = homePlayer + "," + awayPlayer + "," + result;
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printScoreboard() {
        System.out.println("Spel: " + gameType);
        System.out.printf("%-15s %-5s %-7s %-8s %-11s%n",
                "Namn", "Vinst", "Förlust", "Oavgjort", "Vinstfaktor");
        // Create a list from the entries of the map
        List<Map.Entry<String, int[]>> entryList = getEntries();

        // Print the sorted entries
        for (Map.Entry<String, int[]> entry : entryList) {
            String player = entry.getKey().length() > 20 ? entry.getKey().substring(0, 17) + "..." : entry.getKey();
            int[] scoresArray = entry.getValue();
            int wins = scoresArray[0];
            int losses = scoresArray[1];
            int draws = scoresArray[2];

            // Calculate win ratio
            double winRatio = calculateWinRatio(scoresArray);

            System.out.printf("%-15s %5d %7d %8d %11.2f%n",
                    player, wins, losses, draws, winRatio);
        }
    }

    private List<Map.Entry<String, int[]>> getEntries() {
        List<Map.Entry<String, int[]>> entryList = new ArrayList<>(scores.entrySet());

        // Sort the list by win ratio
        entryList.sort((e1, e2) -> {
            double winRatio1 = calculateWinRatio(e1.getValue());
            double winRatio2 = calculateWinRatio(e2.getValue());

            return Double.compare(winRatio2, winRatio1); // Sort in descending order
        });
        return entryList;
    }

    private double calculateWinRatio(int[] scores) {
        int wins = scores[0];
        int losses = scores[1];
        int draws = scores[2];

        if (losses + draws == 0) {
            return wins;
        } else {
            return (double) wins / (losses + draws);
        }
    }
}