public class SpymasterPlayer extends Player<CodenamesBoard>{
    private final String teamColor;
    public SpymasterPlayer(String name, String teamColor) {
        super(name);
        this.teamColor = teamColor;
    }

    @Override
    public void takeTurn(CodenamesBoard board) {

    }
    public String getTeamColor() {
        return teamColor;
    }
}