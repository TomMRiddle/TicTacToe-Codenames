import java.util.List;

import static utils.Ansi.*;

public class CodenamesGame {
    //only use main until we have a main class with the menu
    public static void main(String[] args) {
        System.out.println(BRIGHT_GREEN + """ 
         ######    #######   ########   ########  ##    ##     ###     ##     ##  ########   ######\s
        ##    ##  ##     ##  ##     ##  ##        ###   ##    ## ##    ###   ###  ##        ##    ##\s
        ##        ##     ##  ##     ##  ##        ####  ##   ##   ##   #### ####  ##        ##\s
        ##        ##     ##  ##     ##  ######    ## ## ##  ##     ##  ## ### ##  ######     ######\s
        ##        ##     ##  ##     ##  ##        ##  ####  #########  ##     ##  ##              ##\s
        ##    ##  ##     ##  ##     ##  ##        ##   ###  ##     ##  ##     ##  ##        ##    ##\s
         ######    #######   ########   ########  ##    ##  ##     ##  ##     ##  ########   ######
        """ + RESET);
        Board<CodenamesCell> board = new CodenamesBoard();
        System.out.println(board);
        for(List<CodenamesCell> cells : board.getAllCells()) {
            for(CodenamesCell cell : cells) {
                cell.toggleSpymasterView();
            }
        }
        System.out.println(board);
    }
}
