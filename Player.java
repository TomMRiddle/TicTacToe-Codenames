public abstract class Player {
    protected String name;
    protected String symbol;
    protected int score;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = 0;
    }
    public String getName() {
        return name;
    }
    public String getSymbol() {

        return symbol;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public abstract void makeMove(Board board);
}

