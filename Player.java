public abstract class Player<T extends Board> {
    protected String name;
    protected String symbol;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }
    public String getSymbol() {
        return symbol;
    }
    public abstract void takeTurn(T board);

}

