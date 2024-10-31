import java.util.*;
import static utils.Ansi.*;

public class Grid {
    private final int columns;
    private final int rows;
    private  String[][] gridData;
    private final String[][] colorCodes;
    private static final String INNOCENT = BRIGHT_WHITE;
    private static final String ASSASSIN = RESET;
    private static final String TEAM1 = BLUE;
    private static final String TEAM2 = RED;

    public Grid(String[][] gridData, String[][] colorCodes, int padding) {
        this.gridData = gridData;
        this.colorCodes = colorCodes;

        // Ensure the grid dimensions match the data
        int cellSize = getCellSize(gridData, colorCodes, padding);

        // Pad strings to the nearest odd number of cells
        String[][] paddedGridData = new String[gridData.length][gridData[0].length];
        for (int i = 0; i < gridData.length; i++) {
            for (int j = 0; j < gridData[i].length; j++) {
                if (gridData[i][j] != null) {
                    int cellPaddingLeft = (cellSize - gridData[i][j].length()) / 2;
                    int cellPaddingRight = cellSize - gridData[i][j].length() - cellPaddingLeft;
                    paddedGridData[i][j] = String.format("%" + cellPaddingLeft + "s%s%" + cellPaddingRight + "s", "", gridData[i][j], "");
                } else {
                    paddedGridData[i][j] = " ".repeat(cellSize);
                }
            }
        }

        // Assign the padded data to the grid
        this.columns = gridData[0].length;
        this.rows = gridData.length * 3; // Add an empty row before and after each cell row

        // Create a new grid with empty rows
        String[][] newGridData = new String[this.rows][this.columns];
        for (int i = 0, k = 0; i < this.rows; i++) {
            if (i % 3 == 1) { // Place original cell row in the middle of each block of three rows
                System.arraycopy(paddedGridData[k++], 0, newGridData[i], 0, columns);
            } else { // Fill with empty strings for the other two rows
                for (int j = 0; j < columns; j++) {
                    newGridData[i][j] = "\u00A0".repeat(cellSize);
                }
            }
        }

        this.gridData = newGridData;
    }

    private static int getCellSize(String[][] gridData, String[][] colorCodes, int padding) {
        if (gridData.length != colorCodes.length || gridData[0].length != colorCodes[0].length) {
            throw new IllegalArgumentException("Grid dimensions do not match the provided data");
        }

        // Calculate the size of the grid based on the longest string length and padding
        int maxStringLength = 0;
        for (String[] gridDatum : gridData) {
            for (String s : gridDatum) {
                if (s != null) {
                    maxStringLength = Math.max(maxStringLength, s.length());
                }
            }
        }

        // Calculate the cell size including padding
        return maxStringLength + padding * 2;
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String colorCode = colorCodes[i / 3][j]; // Adjust index to use correct color codes
                System.out.print(BG + BOLD + colorCode + gridData[i][j] + RESET);
            }
            System.out.println();
        }
    }


    public static String[][] generateRandomStringMatrix(String[] inputArray) {
        // Ensure there are enough elements in the input array to fill a 5x5 matrix.
        if (inputArray.length < 25) {
            throw new IllegalArgumentException("Input array must contain at least 25 elements.");
        }

        String[][] result = new String[5][5];
        List<String> inputList = new ArrayList<>(Arrays.asList(inputArray));
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // Randomly select an index from the input list and get the corresponding string.
                int randomIndex = random.nextInt(inputList.size());
                result[i][j] = inputList.get(randomIndex);

                // Remove the selected element to ensure it's not reused in the same matrix
                inputList.remove(randomIndex);
            }
        }

        return result;
    }

    public static String[][] randomizedSecretGrid() {
        List<String> allMembers = new ArrayList<>();

        // Add 7 innocent
        for (int i = 0; i < 7; i++) {
            allMembers.add(INNOCENT);
        }
        // Add 1 assassin

        allMembers.add(ASSASSIN);

        // Add 8 agents from each team

        for (int i = 0; i < 8; i++) {
            allMembers.add(TEAM1);
            allMembers.add(TEAM2);
        }

        // Add a random extra agent from one of the teams (choose randomly between Team1 and Team2)
        if (Math.random() < 0.5) {
            allMembers.add(TEAM1);
        } else {
            allMembers.add(TEAM2);
        }

        // Shuffle the list to randomize the order
        Collections.shuffle(allMembers);

        // Create a new grid with randomized order
        String[][] gridData = new String[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int index = i * 5 + j;
                if (index < allMembers.size()) {
                    gridData[i][j] = allMembers.get(index);
                }
            }
        }

        return gridData;
    }

    public static void main(String[] args) {
        // Example usage
        int padding = 2;

        String[] inputArray = {
                "Hello", "World", "Java", "Grid", "Word",
                "This", "Is", "A", "Test", "BOO!",
                "ANSI", "Colors", "Here", "Too", "Heehaw!",
                "Padded", "Cells", "Centered!", "Banana is loong", "Minions",
                "Stuff", "Random", "Go go go!", "Banjo", "Gru",
                "Duck", "Quack!", "Duckling", "Ugly", "Swan"
        };

        String[][] gridData = generateRandomStringMatrix(inputArray);

        String[][] colorCodes = randomizedSecretGrid();

        Grid grid = new Grid(gridData, colorCodes, padding);
        grid.print();
    }
}

//    public class Grid {
//    public static void main(String[] args) {
//        Player player1 = new HumanPlayer("Ahmed", 'X');
//        Player player2 = new ComputerPlayer("Wall-E", 'O');
//
//        System.out.println(player1.getName() + " spelar med " + player1.getSymbol());
//        System.out.println(player2.getName() + " spelar med " + player2.getSymbol());
//    }
//    }
