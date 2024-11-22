import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static utils.Ansi.*;

public class CodenamesGame {
    //only use main until we have a main class with the menu
    public static void main(String[] args) {
        start();
    }
    public static void start() {
        System.out.println(RED +
                "     ███▄ ▄███▓ ▒█████   ██▀███  ▄▄▄█████▓ ██▓  ██████       \n" +
                "    ▓██▒▀█▀ ██▒▒██▒  ██▒▓██ ▒ ██▒▓  ██▒ ▓▒▓██▒▒██    ▒       \n" +
                "    ▓██    ▓██░▒██░  ██▒▓██ ░▄█ ▒▒ ▓██░ ▒░▒██▒░ ▓██▄         \n" +
                "    ▒██    ▒██ ▒██   ██░▒██▀▀█▄  ░ ▓██▓ ░ ░██░  ▒   ██▒      \n" +
                "    ▒██▒   ░██▒░ ████▓▒░░██▓ ▒██▒  ▒██▒ ░ ░██░▒██████▒▒      \n" +
                "    ░ ▒░   ░  ░░ ▒░▒░▒░ ░ ▒▓ ░▒▓░  ▒ ░░   ░▓  ▒ ▒▓▒ ▒ ░      \n" +
                " "+BRIGHT_WHITE+"█████"+RED+" ░ "+BRIGHT_WHITE+"█████"+RED+"░  ░ ▒ ▒░   ░▒ ░ ▒░"+BRIGHT_WHITE+"█████"+RED+"     ▒ ░░ ░▒  ░ ░      \n" +
                BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"   ░░"+BRIGHT_WHITE+"███"+RED+"  ░ ░ ░ ▒    ░░   ░ "+BRIGHT_GREEN+"░"+BRIGHT_WHITE+"███"+RED+"      ▒ ░░  ░  ░        \n" +
                BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"    ░"+BRIGHT_WHITE+"███  ██████  ████████ ███████    ██████  █████ █████\n" +
                BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"    ░"+BRIGHT_WHITE+"███ ███"+BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░    "+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+" ░░"+BRIGHT_WHITE+"███\n" +
                BRIGHT_GREEN+" ░░"+BRIGHT_WHITE+"███   ███"+BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+" ░░░  ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"    ░"+BRIGHT_WHITE+"███████"+BRIGHT_GREEN+"  ░░░"+BRIGHT_WHITE+"█████"+BRIGHT_GREEN+"░ \n" +
                BRIGHT_GREEN+"  ░░░"+BRIGHT_WHITE+"█████"+BRIGHT_GREEN+"░  ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+" ░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"      ░"+BRIGHT_WHITE+"███ ███"+BRIGHT_GREEN+"░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░░    "+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"░░░"+BRIGHT_WHITE+"███\n" +
                BRIGHT_GREEN+"    ░░"+BRIGHT_WHITE+"███"+BRIGHT_GREEN+"    ░░"+BRIGHT_WHITE+"██████  █████"+BRIGHT_GREEN+"     ░░"+BRIGHT_WHITE+"█████"+BRIGHT_GREEN+" ░░"+BRIGHT_WHITE+"██████  █████ █████\n" +
                BRIGHT_GREEN+"     ░░░      ░░░░░░  ░░░░░       ░░░░░   ░░░░░░  ░░░░░ ░░░░░\n" +
                RESET);
        System.out.println(CLS);
        boolean playAgain = true;

        while (playAgain) {
            CodenamesBoard board = new CodenamesBoard();
            List<Player<CodenamesBoard>> players = new ArrayList<>();
            if (board.getStartingTeam() == RED) {
                players.add(new SpymasterPlayer("Spy1", RED));
                players.add(new AgentPlayer("Agent1", RED));
                players.add(new SpymasterPlayer("Spy2", BLUE));
                players.add(new AgentPlayer("Agent2", BLUE));
            } else {
                players.add(new SpymasterPlayer("Spy2", BLUE));
                players.add(new AgentPlayer("Agent2", BLUE));
                players.add(new SpymasterPlayer("Spy1", RED));
                players.add(new AgentPlayer("Agent1", RED));
            }

            boolean gameloop = true;
            int breaker = 0;
            while(gameloop) {
                for (Player<CodenamesBoard> player : players) {
                    player.takeTurn(board);
                }

                if(breaker == 4) {
                    gameloop = false;
                }
                breaker++;
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Would you like to play again? (yes/no): ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            playAgain = userInput.contains("y");
        }

        System.out.println("Thank you for playing!");
    }
}
