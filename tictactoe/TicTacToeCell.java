package tictactoe;

import base.Cell;
import base.Player;

public class TicTacToeCell extends Cell {
    private Player<TicTacToeBoard> owner;

    public TicTacToeCell(int cellId) {
        super(cellId);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Player<TicTacToeBoard> getOwner() {
        return owner;
    }

    public void setOwner(Player<TicTacToeBoard> owner) {
        this.owner = owner;
    }

    public String getTheContent() {
        return content;
    }
}
