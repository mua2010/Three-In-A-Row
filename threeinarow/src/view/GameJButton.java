package view;

import javax.swing.JButton;

public class GameJButton extends JButton{
    /**
     * Child GameJButton with the ability to store the block's row and col inde
     */
    private static final long serialVersionUID = 1L;
    private int row;
    private int col;

    public GameJButton(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
