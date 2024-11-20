import java.util.*;

public class Scoreboard {

    private String gameName;
    private List<Score> highscores;    

    public Scoreboard (String gameName, List<Score> highscores) {
        this.gameName=gameName; 
        this.highscores=highscores;       
    } 

    public void setHighscores(){
        highscores = new ArrayList<>();
    }

    public void addScore(){
        highscores.add(Score);
    }
   
    }
   

    
    




