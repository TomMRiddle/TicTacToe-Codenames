public class Scores {
    // Score som egen klass -> där finns spelarnamn och score samt funktion för att beräkna score. 
    
    //Metoder som score behöver innehålla:
    // - Spara/lagra spelare och wins/losses
    // - Beräkna score genom "vinster-förluster = score"


    public int calculateScore(int playerWins, int playerLosses) { //argument: player.wins, player.losses (eller gettermetod för dessa)
                
        int playerScore = playerWins - playerLosses;
        
        return playerScore;
    }
    // - Glömma spelare som hamnar utanför scoreboard


    
}
