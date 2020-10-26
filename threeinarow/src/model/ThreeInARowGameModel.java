package model;

public class ThreeInARowGameModel extends GameModel {
    
    public ThreeInARowGameModel(String gameType, int rows, int cols) {
        super(gameType, rows, cols);
    }

    @Override
    public void reset() {
        super.reset();
        for (int column = 0; column < super.getCols(); column++) {
            // Enable the bottom row
            super.getBlocksData()[super.getRows()-1][column].setIsLegalMove(true);
        }
    }
    
    @Override
    public void move(int row, int col) {
        super.move(row, col);
        // on each move, enable the box above the current box
        if (row != 0)
            super.getBlocksData()[row-1][col].setIsLegalMove(true);
    }
}
