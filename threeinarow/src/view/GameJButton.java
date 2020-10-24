package view;

import javax.swing.JButton;

public class GameJButton extends JButton{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int row;
    private int col;

    public GameJButton(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
