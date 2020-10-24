package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

// import javax.swing.JButton;
import javax.swing.JPanel;

import adapter.Adapter;
import model.GameModel;

public class RowGameBoardView implements RowGameView {
    // CHANGE: Made class variables private
    private GameJButton[][] blocks;
    private JPanel gamePanel = new JPanel(new FlowLayout());
    private int  rows;
    private int cols;

    // Made class getters and setters
    public GameJButton[][] getBlocks(){
        return this.blocks;
    }
    public void setBlocks(GameJButton[][] blocks){
        this.blocks = blocks;
    }
    public JPanel getGamePanel(){
        return this.gamePanel;
    }
    public void setGamePanel(JPanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public RowGameBoardView(GameModel gameModel, Adapter gameAdapter) {
        super();
        this.rows = gameModel.getRows();
        this.cols = gameModel.getCols();
        this.blocks = new GameJButton[this.rows][this.cols];
        JPanel game = new JPanel(new GridLayout(this.rows, this.cols));
        gamePanel.add(game, BorderLayout.CENTER);

        // Initialize a JButton for each cell of the 3x3 game board.
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                blocks[row][column] = new GameJButton(row, column);
                blocks[row][column].setPreferredSize(new Dimension(75, 75));
                game.add(blocks[row][column]);
                blocks[row][column].addActionListener(gameAdapter);
            }
        }
    }

    /**
     * Updates the game view after the game model changes state.
     *
     * @param gameModel The current game model
     */
    public void update(GameModel gameModel) {
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
    protected void updateBlock(GameModel gameModel, int row, int col) {
        blocks[row][col].setText(gameModel.getBlocksData()[row][col].getContents());
        blocks[row][col].setEnabled(gameModel.getBlocksData()[row][col].getIsLegalMove());
    }
}