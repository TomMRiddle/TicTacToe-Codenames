public class TicTacToeCell extends Cell {
    public TicTacToeCell(int id) {
        super(id);
    }
    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
