import java.util.*;

public class Score implements Comparable{
    
    private String playerName;
    public int points;
    
    public Score (String playerName, int points){
        this.playerName=playerName;
        this.points=points;
    } 
    
    public int compareTo(Score score){  
        if(points==score.points){
            return 0;
        }
        else if(points>score.points){
            return 1;
        }
        else{
            return -1;
        }
    }
}
    
