package model;

public class ThreeInARowGameModel extends GameModel {
    
    // Change: Game takes dimensions in constructor
    public ThreeInARowGameModel(String gameType, int rows, int cols) {
        super(gameType, rows, cols);
    }

    /**
	 * Resets the game to be able to start playing again.
	 */
    @Override
    public void reset() {
        super.reset();
        for (int column = 0; column < super.getCols(); column++) {
            // Enable the bottom row
            super.getBlocksData()[super.getRows()-1][column].setIsLegalMove(true);
        }
    }
    
    /**
	 * Moves the current player into the given block.
	 *
	 * @param row The row index to be moved to by the current player
     * @param col The col index to be moved to by the current player
	 */
    // Change: move method takes dimensions
    @Override
    public void move(int row, int col) {
        super.move(row, col);
        // on each move, enable the box above the current box
        if (row != 0)
            super.getBlocksData()[row-1][col].setIsLegalMove(true);
    }
}
