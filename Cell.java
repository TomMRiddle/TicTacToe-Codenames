import static utils.Ansi.*;
public abstract class Cell {
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

    public abstract void setContent(String content);
    public abstract void setOwner(Player player);
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

    public abstract Player getOwner();
}
