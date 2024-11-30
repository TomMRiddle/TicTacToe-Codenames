import static utils.Ansi.*;

public abstract class Cell<T> {
    protected String content;
    protected String color;
    protected int id;

    public Cell(int cellId) {
        this.id = cellId;
        this.content = " ";
        this.color = WHITE;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return content;
    }

}
