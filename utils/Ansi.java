package utils;

public final class Ansi {
    //prevent instantiation
    public static final String RESET = "\033[0m";
    public static final String FG = "\033[27m";
    public static final String BG = "\033[7m";
    public static final String BOLD = "\033[1m";
    public static final String DIM = "\033[2m";
    public static final String ITALIC = "\033[3m";
    public static final String NO_VARIANT = "\033[22m";
    public static final String UNDERLINE = "\033[4m";
    public static final String NO_UNDERLINE = "\033[24m";
    public static final String BLACK = "\033[30m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String MAGENTA = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";
    public static final String BRIGHT_BLACK = "\033[90m";
    public static final String BRIGHT_RED = "\033[91m";
    public static final String BRIGHT_GREEN = "\033[92m";
    public static final String BRIGHT_YELLOW = "\033[93m";
    public static final String BRIGHT_BLUE = "\033[94m";
    public static final String BRIGHT_MAGENTA = "\033[95m";
    public static final String BRIGHT_CYAN = "\033[96m";
    public static final String BRIGHT_WHITE = "\033[97m";
    public static final String CURSOR_HIDE = "\033[?25l";
    public static final String CURSOR_SHOW = "\033[?25h";
    public static final String NBSP = "\u00A0";
    public static final String BELL = "\007";
    public static final String CUR_UP = "\033[A";
    public static final String CUR_DOWN = "\033[B";
    public static final String CUR_RIGHT = "\033[C";
    public static final String CUR_LEFT = "\033[D";
    public static final String CUR_LNDOWN = "\033[E";
    public static final String CUR_LNUP = "\033[F";
    public static final String CUR_COL = "\033[G";
    public static final String CUR_HOME = "\033[H";
    public static final String CUR_SAVE = "\0337";
    public static final String CUR_RESTORE = "\0338";
    public static final String CLEAR_SCREEN = CUR_HOME+"\033[J";
    public static final String CLEAR_LINE = "\033[K";
    public static final String CLS = "If you can read this message it means the screen was not cleared, most likely because you are running the code from inside an IDE\033\143";

    private Ansi(){
        throw new AssertionError();
    }
}
