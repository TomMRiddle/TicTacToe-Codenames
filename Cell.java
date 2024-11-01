import static utils.Ansi.*;
public abstract class Cell {
    protected String content;
    protected String color;
    protected int id;

    public Cell(int id) {
        this.content = " ";
        this.color = WHITE;
        this.id = id;
    }

    public abstract void setContent(String content);

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
