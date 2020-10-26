import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import model.BlockModel;
import model.GameModel;
import model.TicTacToeModel;
import view.RowGameGUI;
import controller.RowGameController;

/**
 * Test class tests Game Model functionality
 */
public class TestTicTacToeModel {
    private GameModel gameModel;
    private RowGameController gameController;
    private RowGameGUI gameView;

    private BlockModel[][] blocksData;

    @Before
    public void setUp() {
        gameModel = new TicTacToeModel("TIC_TAC_TOE", 3, 3);
        gameController = new RowGameController();
        gameView = new RowGameGUI(gameModel, gameController); // Observers | PropertyChangeListener
        
        gameModel.setView(gameView);
        gameController.initializeGame(gameModel);

        blocksData = gameModel.getBlocksData();
    }

    @After
    public void tearDown() {
        gameModel = null;
        gameController = null;
        gameView = null;
    }

    /**
     * After the game gets initialized, check initial vars
     */
    @Test
    public void testNewGame() {
        assertEquals(3, gameModel.getRows());
        assertEquals(3, gameModel.getCols());
        assertEquals("1", gameModel.getPlayer());
        assertEquals(9, gameModel.getMovesLeft());
        assertEquals(null, gameModel.getFinalResult());
    }

    /**
     * Test Reset Game Method
     */
    @Test
    public void testResetGame() {
        // reset Game
        gameModel.reset();
        testNewGame();
        // check if all the blocks are valid moves
        for (int r = 0; r < blocksData.length; r++) {
            for (int c = 0; c < blocksData[0].length; c++) {
                assertEquals(true, blocksData[r][c].getIsLegalMove());
                assertEquals("", blocksData[r][c].getContents());
            }
        }
    }

    /**
     * Tests if a null block model never gets initialized
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNewBlockViolatesPrecondition() {
        BlockModel block = new BlockModel(null);
        block.setContents(null);
    }

    @Test
    public void testIsWinMethod() {
        // Vertical Win Check
        blocksData[2][0].setContents("O");
        assertEquals(false, gameModel.isWin(2, 0, "O"));
        blocksData[1][0].setContents("O");
        assertEquals(false, gameModel.isWin(1, 0, "O"));
        blocksData[0][0].setContents("O");
        assertEquals(true, gameModel.isWin(0, 0, "O"));

        // This test case also tests reset method
        gameModel.reset();

        // Horizontal Win Check
        blocksData[1][0].setContents("O");
        assertEquals(false, gameModel.isWin(1, 0, "O"));
        blocksData[1][1].setContents("O");
        assertEquals(false, gameModel.isWin(1, 1, "O"));
        blocksData[1][2].setContents("O");
        assertEquals(true, gameModel.isWin(1, 2, "O"));

        gameModel.reset();
        
        // Diagonal Win Check
        blocksData[0][2].setContents("O");
        assertEquals(false, gameModel.isWin(0, 2, "O"));
        blocksData[1][1].setContents("O");
        assertEquals(false, gameModel.isWin(1, 1, "O"));
        blocksData[2][0].setContents("O");
        assertEquals(true, gameModel.isWin(2, 0, "O"));
    }

    /**
     * Tests if player 'X' (1) wins
     */
    @Test
    public void testPlayer_X_Wins() {
        gameModel.move(0, 0); // X turn
        gameModel.move(1, 0); // O turn
        gameModel.move(1, 1); // X turn
        gameModel.move(2, 0); // O turn
        gameModel.move(2, 2); // X turn
        assertEquals(true, gameModel.isWin(2, 2, "X"));
        assertEquals("Player 1 wins!", gameModel.getFinalResult());
    }

    /**
     * Tests if a game is a tie
     */
    @Test
    public void testTwoPlayersTie() {
        gameModel.move(2, 0); // X turn
        gameModel.move(2, 1); // O turn
        gameModel.move(2, 2); // X turn
        gameModel.move(1, 2); // O turn
        gameModel.move(1, 0); // X turn
        gameModel.move(1, 1); // O turn
        gameModel.move(0, 2); // X turn
        gameModel.move(0, 0); // O turn
        gameModel.move(0, 1); // X turn
        assertEquals(true, gameModel.isTie());
        assertEquals("Game ends in a draw", gameModel.getFinalResult());
    }

    /**
     * Tests Legal Move
     */
    @Test
    public void testLegalMove() {
        gameModel.move(1, 1); // X turn
        assertEquals("X", blocksData[1][1].getContents());
        gameModel.move(0, 0); // X turn
        assertEquals("O", blocksData[0][0].getContents());
    }

    /**
     * Tests Illegal Move
     * if a player made a move, then that block is no more a legal move
     */
    @Test
    public void testIllegalMove() {
        gameModel.move(0, 0); // X turn
        // 2,0 will not work anymore beacuse player X made that move
        gameModel.move(0, 0); // O turn
        assertEquals(false, blocksData[0][0].getIsLegalMove());
        assertEquals("X", blocksData[0][0].getContents());
    }
}
