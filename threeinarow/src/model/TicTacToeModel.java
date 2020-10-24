package model;

public class TicTacToeModel extends GameModel {

    // Change: Game takes dimensions in constructor
    public TicTacToeModel(String gameType, int rows, int cols) {
        super(gameType, rows, cols);
    }

    @Override
    public void reset() {
        super.reset();

    }

    @Override
    public void move(int row, int col) {
        super.move(row, col);
    }
}
