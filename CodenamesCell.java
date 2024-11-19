import static utils.Ansi.*;
public class CodenamesCell extends Cell {
    private final String secret;
    private boolean spymasterView;
    private boolean revealed;

    public CodenamesCell(int cellId, String content, String secret) {
        super(cellId);
        this.content = content;
        this.secret = secret;
        this.color = BG;
        this.revealed = false;
        this.spymasterView = false;
    }
    public boolean isRevealed() {
        return revealed;
    }
    @Override
    public String toString() {
        String revealedIndicator = "█";
        return (revealed && spymasterView ? revealedIndicator + content +  revealedIndicator : content);
    }
    public void reveal() {
        this.revealed = true;
    }

    @Override
    public String getColor() {
        return (revealed || spymasterView ? secret : color);
    }
    public void setSpymasterView(boolean spymasterView) {
        this.spymasterView = spymasterView;
    }
}
