package model;

public class TicTacToeModel extends GameModel {

    public TicTacToeModel(String gameType, int rows, int cols) {
        super(gameType, rows, cols);
    }

    @Override
    public void reset() {
        super.reset();
        for (int row = 0; row < super.getRows(); row++) {
            for (int column = 0; column < super.getCols(); column++) {
                // Enable all the blocks
                super.getBlocksData()[row][column].setIsLegalMove(true);
            }
        }
    }

    @Override
    public void move(int row, int col) {
        super.move(row, col);
    }
}
