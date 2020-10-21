package model;

public class RowGameModel {
    public static final String GAME_END_NOWINNER = "Game ends in a draw";
    // CHANGES: Made class vars private and added getters and setters
    private RowBlockModel[][] blocksData = new RowBlockModel[3][3];

    /**
     * The current player taking their turn
     */
    private String player = "1";
    private int movesLeft = 9;

    private String finalResult = null;

    public RowGameModel() {
        super();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                blocksData[row][col] = new RowBlockModel();
            } // end for col
        } // end for row
    }

    public String getFinalResult() {
        return this.finalResult;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    public String getPlayer() {
        return this.player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getMovesLeft() {
        return this.movesLeft;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    public RowBlockModel[][] getBlocksData() {
        return this.blocksData;
    }

    public void setBlocksData(RowBlockModel[][] blocksData) {
        this.blocksData = blocksData;
    }
}
