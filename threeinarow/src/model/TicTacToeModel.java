package model;

public class TicTacToeModel extends GameModel {

    // Constructor
    public TicTacToeModel(String gameType, int rows, int cols) {
        super(gameType, rows, cols);
    }

    @Override
    public void reset() {
        super.reset();
        for (int row = 0; row < super.rows; row++) {
            for (int column = 0; column < super.cols; column++) {
                // Enable all the blocks
                super.blocksData[row][column].setIsLegalMove(true);
            }
        }
    }

    @Override
    public void move(int row, int col) {
        super.move(row, col);
    }
}
