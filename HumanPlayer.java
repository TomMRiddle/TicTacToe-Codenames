import java.util.Scanner;

public class HumanPlayer extends Player<TicTacToeBoard> {
    private final String symbol;
    private final Scanner scanner;

    public HumanPlayer(String name, String symbol) {
        super(name);
        this.symbol = symbol;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void takeTurn(TicTacToeBoard board) {

        System.out.println(board);
        int cellId;
        System.out.println(getName() + ", ange cellnummer (1-9): ");

        while (true) {
            try {
                cellId = scanner.nextInt();
                scanner.nextLine();

                if (cellId < 1 || cellId > 9) {
                    System.out.println("Ogiltigt val! Välj ett nummer mellan 1-9.");
                    continue;
                }

                TicTacToeCell cell = board.getCellById(cellId);
                if (cell.getOwner() != null) {
                    System.out.println("Den cellen är redan tagen! Välj en annan.");
                    continue;
                }

                cell.setContent(symbol);
                cell.setOwner(this);
                break;

            } catch (NumberFormatException e) {
                System.out.println("Ange ett giltigt nummer!");
                scanner.nextLine();
            }
        }
    }
}