package model;

import view.RowGameGUI;

public class GameModel implements RowGameRulesStrategy {

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
    private BlockModel[][] blocksData;
    private int  rows;
    private int cols;
    private String gameType;
    private String player;
    private int movesLeft;
    private String finalResult = null;
    private RowGameGUI gameView;

    // Change: Game takes dimensions in constructor
    /**
     * GameModel Constructor
     */
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
        player = Player.X.getPlayer();
        movesLeft = rows * cols;
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

    public void setView(RowGameGUI gameView) {
        this.gameView = gameView;
    }

    public void updateView() {
        this.gameView.update(this);
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
        this.player = Player.X.getPlayer();
        this.movesLeft = rows * cols;
        this.finalResult = null;
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
        String currentPlayerSymbol;
        if (player.equals(Player.X.getPlayer()))
            currentPlayerSymbol = Player.X.toString();
        else
            currentPlayerSymbol = Player.O.toString();

        movesLeft--;

        blocksData[row][col].setContents(currentPlayerSymbol);
        blocksData[row][col].setIsLegalMove(false);

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
	}
    
}
