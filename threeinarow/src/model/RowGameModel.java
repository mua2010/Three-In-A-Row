package model;

import javax.swing.JButton;

import view.RowGameGUI;

public class RowGameModel implements RowGameRulesStrategy{
    public enum Player {
        X ("1"),
        O ("2"),
        ; 

        private final String player;
    
        private Player(String player) {
            this.player = player;
        }
        public String getPlayer() {
            return this.player;
        }
    }

    private static final String GAME_END_NOWINNER = "Game ends in a draw";
    private static final String PLAYER_1_WINS = "Player 1 wins!";
    private static final String PLAYER_2_WINS = "Player 2 wins!";
    // CHANGES: Made class vars private and added getters and setters
    private RowBlockModel[][] blocksData;
    private int  rows;
    private int cols;

    /**
     * The current player taking their turn
     */
    private String player;
    private int movesLeft;

    private String finalResult = null;

    // private String gameType;
    private RowGameGUI gameView;

    public RowGameModel(int rows, int cols) {
        super();
        this.rows = rows;
        this.cols = cols;
        blocksData = new RowBlockModel[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                blocksData[row][col] = new RowBlockModel(this);
            } // end for col
        } // end for row
        player = Player.X.getPlayer();
        movesLeft = rows * cols;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public void setView(RowGameGUI gameView) {
        this.gameView = gameView;
    }

    public String getFinalResult() {
        return this.finalResult;
    }

    // public void setFinalResult(String finalResult) {
    //     this.finalResult = finalResult;
    // }

    public String getPlayer() {
        return this.player;
    }

    // public void setPlayer(String player) {
    //     this.player = player;
    // }

    public int getMovesLeft() {
        return this.movesLeft;
    }

    // public void setMovesLeft(int movesLeft) {
    //     this.movesLeft = movesLeft;
    // }

    public RowBlockModel[][] getBlocksData() {
        return this.blocksData;
    }

    public void setBlocksData(RowBlockModel[][] blocksData) {
        this.blocksData = blocksData;
    }

    /**
	 * Resets the game to be able to start playing again.
	 */
    @Override
    public void reset() {
        for (int row = 0; row < rows; row++) {
			for (int column = 0; column < cols; column++) {
				this.blocksData[row][column].reset();
				// Enable the bottom row
				this.blocksData[row][column].setIsLegalMove(row == this.blocksData.length - 1);
			}
		}
        this.player = Player.X.getPlayer();
        this.movesLeft = rows * cols;
        this.finalResult = null;
		this.gameView.update(this);
    }

    @Override
    public void move(int row, int col) {
        
    }

    /**
     * 
     * @param block
     * @return the row and col index of the block in the game board
     */
    private int[] getBlockIndex(JButton block) {
        int[] ans = new int[2]; 
        JButton[][] boardViewBlocks = gameView.getGameBoardView().getBlocks();
        for (int r = 0; r < blocksData.length; r++) {
            for (int c = 0; c < blocksData[0].length; c++) {
                if (block == boardViewBlocks[r][c]) {
                    ans[0] = r;
                    ans[1] = c;
                    break;
                }
            }
        }
        return ans;
    }
    
    /**
	 * Moves the current player into the given block.
	 *
	 * @param block The block to be moved to by the current player
	 */
    public void move1(JButton block) {
        String currentPlayerSymbol;
        if (player.equals(Player.X.getPlayer()))
            currentPlayerSymbol = Player.X.toString();
        else
            currentPlayerSymbol = Player.O.toString();

        movesLeft--;

        int[] blockIndex = getBlockIndex(block);
        int row = blockIndex[0];
        int col = blockIndex[1];

        blocksData[row][col].setContents(currentPlayerSymbol);
        blocksData[row][col].setIsLegalMove(false);

        if (row != 0)
            blocksData[row-1][col].setIsLegalMove(true);

        if (movesLeft < rows*cols - 2) {
            if (isWin(row, col)) {
                if (player.equals(Player.X.getPlayer()))
                    this.finalResult = PLAYER_1_WINS;
                else
                    this.finalResult = PLAYER_2_WINS;
                endGame();
            }
            else if (movesLeft  == 0) {
                this.finalResult = GAME_END_NOWINNER;
            }
        }

        // change player
        if (player.equals(Player.X.getPlayer()))
            this.player = Player.O.getPlayer();
        else
            this.player = Player.X.getPlayer();

        gameView.update(this);
    }


    @Override
    public boolean isWin(int row, int col) {
        int rowCounter = 0;
		int colCounter = 0;
		int leftDiagonalCounter = 0;
        int rightDiagonalCounter = 0;
        
        String playerSymbol = Player.X.toString();
		if (player == Player.O.getPlayer())
            playerSymbol = Player.O.toString();

        int boardLength = blocksData.length;
        int boardLastRowIndex = boardLength - 1;
        
        int counter = 0;
        while (counter < boardLength) {
            if (blocksData[row][counter].getContents().equals(playerSymbol))
                rowCounter++;
            if (blocksData[counter][col].getContents().equals(playerSymbol))
                colCounter++;
            if (blocksData[boardLastRowIndex - counter][counter].getContents().equals(playerSymbol))
                leftDiagonalCounter++;
            if (blocksData[counter][counter].getContents().equals(playerSymbol))
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
		gameView.update(this);
	}
}
