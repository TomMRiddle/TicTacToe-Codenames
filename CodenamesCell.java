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
        spymasterView = false;
        revealed = false;
    }

    public void toggleSpymasterView() {
        String secretTemp = secret;
        secret = color;
        color = secretTemp;
        spymasterView = !spymasterView;
    }

    public void revealSecret() {
        if (spymasterView) {
            secret = color;
        } else {
            color = secret;
        }
        revealed = true;
    }

    public boolean isRevealed() {
        return revealed;
    }

}
