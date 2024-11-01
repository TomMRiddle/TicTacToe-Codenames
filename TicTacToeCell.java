public class TicTacToeCell extends Cell {
    public TicTacToeCell(int id) {
        super(id);
    }
    @Override
    public void setContent(String content) {
        this.content = content;
    }

    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
