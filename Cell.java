import static utils.Ansi.*;
public abstract class Cell {
    protected String content;
    protected String color;

    public Cell() {
        this.content = "x";
        this.color = WHITE;
    }

    public abstract void setContent(String content);

    public String getColor() {
        return color;
    }
    @Override
    public String toString() {
        return content;
    }

}
