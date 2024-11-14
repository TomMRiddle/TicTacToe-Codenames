import static utils.Ansi.*;
public class CodenamesCell extends Cell {
    private String secret;
    private boolean spymasterView;
    private boolean revealed;
    public CodenamesCell(int cellId, String content, String secret) {
        super(cellId);
        this.content = content;
        this.secret = secret;
        this.color = BG;
    }
}
