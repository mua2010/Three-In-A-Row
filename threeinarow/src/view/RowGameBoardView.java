package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.RowGameController;
import model.RowGameModel;

public class RowGameBoardView implements RowGameView {
    // CHANGE: Made class variables private
    private JButton[][] blocks;
    private JPanel gamePanel = new JPanel(new FlowLayout());
    private int  rows;
    private int cols;

    // Made class getters and setters
    public JButton[][] getBlocks(){
        return this.blocks;
    }
    public void setBlocks(JButton[][] blocks){
        this.blocks = blocks;
    }
    public JPanel getGamePanel(){
        return this.gamePanel;
    }
    public void setGamePanel(JPanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public RowGameBoardView(RowGameModel gameModel) {
        super();
        this.rows = gameModel.getRows();
        this.cols = gameModel.getCols();
        this.blocks = new JButton[this.rows][this.cols];
        JPanel game = new JPanel(new GridLayout(this.rows, this.cols));
        gamePanel.add(game, BorderLayout.CENTER);

        // Initialize a JButton for each cell of the 3x3 game board.
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                blocks[row][column] = new JButton();
                blocks[row][column].setPreferredSize(new Dimension(75, 75));
                game.add(blocks[row][column]);
                blocks[row][column].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int[] blockIndex = getBlockIndex((JButton) e.getSource());
                        int row = blockIndex[0];
                        int col = blockIndex[1];
                        gameModel.move(row, col);
                    }
                });
            }
        }
    }

    /**
     * Updates the game view after the game model changes state.
     *
     * @param gameModel The current game model
     */
    public void update(RowGameModel gameModel) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                this.updateBlock(gameModel, row, column);
            } // end for col
        } // end for row
    }

    /**
     * Updates the block view at the given row and column after the game model
     * changes state.
     *
     * @param gameModel The game model
     * @param row       The row that contains the block
     * @param column    The column that contains the block
     */
    protected void updateBlock(RowGameModel gameModel, int row, int col) {
        blocks[row][col].setText(gameModel.getBlocksData()[row][col].getContents());
        blocks[row][col].setEnabled(gameModel.getBlocksData()[row][col].getIsLegalMove());
    }

    /**
     * Returns the (x,y) coordinates of the block
     * 
     * @param block
     * @return the row and col index of the block in the game board
     */
    public int[] getBlockIndex(JButton block) {
        int[] ans = new int[2]; 
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (block == this.blocks[r][c]) {
                    ans[0] = r;
                    ans[1] = c;
                    break;
                }
            }
        }
        return ans;
    }
}
