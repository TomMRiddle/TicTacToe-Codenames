import java.util.List;

import static utils.Ansi.*;

public class CodenamesGame {
    //only use main until we have a main class with the menu
    public static void main(String[] args) {
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
        Board<CodenamesCell> board = new CodenamesBoard();
        System.out.println(board);

    }
}
