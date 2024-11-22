import java.util.*;

public class Scoreboard {

    private String gameName;
    private List<Score> highscores;    

    public Scoreboard (String gameName) {
        this.gameName=gameName; 
        highscores = new ArrayList<>();       
    } 

    public void setHighscores(){
        
    }

    public void addScore(){
        highscores.add(Score);
    }
    
    public void printScoreboard(){
        System.out.println("SCOREBOARD");
        for(int i=0; i<11; i++){
            System.out.println(highscores[i]);
        }
    }
}
   

    
    




