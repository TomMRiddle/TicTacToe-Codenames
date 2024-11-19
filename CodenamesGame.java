import java.io.PrintWriter;
import java.util.List;

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
        CodenamesBoard board = new CodenamesBoard();
        board.reveal(9);
        System.out.println(board);
        board.setSpymasterView(true);
        System.out.println(board);
    }
}
