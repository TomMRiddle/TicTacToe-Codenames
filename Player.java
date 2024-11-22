public abstract class Player<T extends Board> {
    protected String name;

    public Player(String name) {
        this.name = name;

    }
    public String getName() {
        return name;
    }
    public abstract void takeTurn(T board);

}

