package model;

import view.RowGameGUI;

public class GameModel implements RowGameRulesStrategy {

    public static final String PLAYER_1 = "X";
    public static final String PLAYER_2 = "O";
    private static final String GAME_END_NOWINNER = "Game ends in a draw";
    private static final String PLAYER_1_WINS = "Player 1 wins!";
    private static final String PLAYER_2_WINS = "Player 2 wins!";

    // CHANGES: Made class vars private and added getters and setters
    protected BlockModel[][] blocksData;
    protected int  rows;
    protected int cols;
    private String gameType;
    private String player;
    private int movesLeft;
    private String finalResult = null;
    private RowGameGUI gameView;

    // GameModel Constructor
    public GameModel(String gameType, int rows, int cols) {
        super();
        this.gameType = gameType;
        this.rows = rows;
        this.cols = cols;
        blocksData = new BlockModel[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                blocksData[row][col] = new BlockModel(this);
            } // end for col
        } // end for row
        player = PLAYER_1;
        movesLeft = rows * cols;
    }

    // This method is called after initializing the game
    public void setView(RowGameGUI gameView) {
        this.gameView = gameView;
    }

    // This method is called after any move is made / game resets
    public void updateView() {
        this.gameView.update();
    }

    public String getGameType() {
        return this.gameType;
    }
    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public String getFinalResult() {
        return this.finalResult;
    }

    public String getPlayer() {
        return this.player;
    }

    public int getMovesLeft() {
        return this.movesLeft;
    }

    public BlockModel[][] getBlocksData() {
        return this.blocksData;
    }

    /**
	 * Resets the game to be able to start playing again.
	 */
    @Override
    public void reset() {
        for (int row = 0; row < rows; row++) {
			for (int column = 0; column < cols; column++) {
				this.blocksData[row][column].reset();
			}
		}
        this.player = PLAYER_1;
        this.movesLeft = rows * cols;
        this.finalResult = null;
    }
    
    /**
	 * Moves the current player into the given block.
	 *
	 * @param row The row index to be moved to by the current player
     * @param col The col index to be moved to by the current player
	 */
    @Override
    public void move(int row, int col) {
        // Ignore the move if not a legal move
        if (!blocksData[row][col].getIsLegalMove())
            return;

        movesLeft--;

        // set the content of the block to the player move symbol
        blocksData[row][col].setContents(player);
        // make that block illegal
        blocksData[row][col].setIsLegalMove(false);

        // Check for isWin condition only when at least 3 moves are made
        if (movesLeft < rows*cols - 2) {
            if (isWin(row, col, player)) {
                // Set the final result based on the winner
                if (player.equals(PLAYER_1))
                    this.finalResult = PLAYER_1_WINS;
                else
                    this.finalResult = PLAYER_2_WINS;
                endGame();
            }
            // Game is Tie when no more moves left to play (all blocks filled)
            else if (movesLeft  == 0) {
                this.finalResult = GAME_END_NOWINNER;
            }
        }

        // change player
        if (player.equals(PLAYER_1))
            this.player = PLAYER_2;
        else
            this.player = PLAYER_1;      
    }

    /**
	 * Check if the current move resulted in a win
	 *
	 * @param row The row index to be moved to by the current player
     * @param col The col index to be moved to by the current player
	 */
    @Override
    public boolean isWin(int row, int col, String player) {
        int rowCounter = 0;
		int colCounter = 0;
		int leftDiagonalCounter = 0;
        int rightDiagonalCounter = 0;

        int boardLength = blocksData.length;
        int boardLastRowIndex = boardLength - 1;
        
        int counter = 0;
        while (counter < boardLength) {
            if (blocksData[row][counter].getContents().equals(player))
                rowCounter++;
            if (blocksData[counter][col].getContents().equals(player))
                colCounter++;
            if (blocksData[boardLastRowIndex - counter][counter].getContents().equals(player))
                leftDiagonalCounter++;
            if (blocksData[counter][counter].getContents().equals(player))
                rightDiagonalCounter++;
            counter++;
        }

        if (rowCounter == boardLength 
            || colCounter == boardLength
            || leftDiagonalCounter == boardLength 
            || rightDiagonalCounter == boardLength)
                return true;
        return false;
    }

    @Override
    public boolean isTie() {
        return movesLeft == 0;
    }

    /**
	 * Ends the game disallowing further player turns.
	 */
	public void endGame() {
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < cols; column++) {
				this.blocksData[row][column].setIsLegalMove(false);
			}
		}
	}
    
}
