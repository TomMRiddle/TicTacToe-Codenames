import static utils.Ansi.*;
public abstract class Cell implements ICell {
    protected String content;
    protected String color;
    protected int id;
    protected Player owner;

    public Cell(int id) {
        this.content = " ";
        this.color = WHITE;
        this.id = id;
        this.owner = null;
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return content;
    }

}
