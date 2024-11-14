import java.util.List;

public class Scoreboard {

    private List<Score> highscores;
    private String gameName;

    public Scoreboard (String gameName, List<Score> highscores) {
        this.gameName=gameName;
        this.highscores=highscores;
    }

    public void newScore(){
        if (true) {
            Scoreboard newScoreboard = new Scoreboard(gameName, highscores);
        }
    } 

    @Override
    public String toString(){
        for (int i=0; i<highscores.length; i++) { 
            highscores;
        }
    }
   

    
    


}
}

