import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scoreboard {
    private static List<String[]> scores;
    private static final String CSV_FILE_NAME = "scores.csv";

    private Scoreboard() {
        //prevent instantiation
        throw new AssertionError();
    }
    public static void printScoreboard(String game) {

    }
    private static void load() {
        List<String[]> scores = new ArrayList<>();

    }
    private boolean save() {
        File csvOutputFile = new File(CSV_FILE_NAME);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            scores.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public void addScore(String[] score) {
        scores.add(score);
        boolean saved = save();
        if(saved) {
            System.out.println("Score saved");
        }
    }
    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }
    public String escapeSpecialCharacters(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null");
        }
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
