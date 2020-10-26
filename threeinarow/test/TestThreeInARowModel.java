import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import model.BlockModel;
import model.GameModel;
import model.ThreeInARowGameModel;
import view.RowGameGUI;
import controller.RowGameController;

/**
 * Test class tests Game Model functionality
 */
public class TestThreeInARowModel {
    private GameModel gameModel;
    private RowGameController gameController;
    private RowGameGUI gameView;

    private BlockModel[][] blocksData;

    @Before
    public void setUp() {
        gameModel = new ThreeInARowGameModel("THREE_IN_A_ROW", 3, 3);
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
        // check if only Top 2 rows of the blocks are false
        for (int r = 0; r < blocksData.length - 1; r++) {
            for (int c = 0; c < blocksData[0].length; c++) {
                assertEquals(false, blocksData[r][c].getIsLegalMove());
                assertEquals("", blocksData[r][c].getContents());
            }
        }
        // Checking if only the bottom row is activated after reset 
        for (int c = 0; c < blocksData[0].length; c++) {
            assertEquals(true, blocksData[2][c].getIsLegalMove());
            assertEquals("", blocksData[2][c].getContents());
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
        blocksData[2][0].setContents("X");
        assertEquals(false, gameModel.isWin(2, 0, "X"));
        blocksData[1][0].setContents("X");
        assertEquals(false, gameModel.isWin(1, 0, "X"));
        blocksData[0][0].setContents("X");
        assertEquals(true, gameModel.isWin(0, 0, "X"));

        // This test case also tests reset method
        gameModel.reset();

        // Horizontal Win Check
        blocksData[1][0].setContents("X");
        assertEquals(false, gameModel.isWin(1, 0, "X"));
        blocksData[1][1].setContents("X");
        assertEquals(false, gameModel.isWin(1, 1, "X"));
        blocksData[1][2].setContents("X");
        assertEquals(true, gameModel.isWin(1, 2, "X"));

        gameModel.reset();
        
        // Diagonal Win Check
        blocksData[0][2].setContents("X");
        assertEquals(false, gameModel.isWin(0, 2, "X"));
        blocksData[1][1].setContents("X");
        assertEquals(false, gameModel.isWin(1, 1, "X"));
        blocksData[2][0].setContents("X");
        assertEquals(true, gameModel.isWin(2, 0, "X"));
    }

    /**
     * Tests if player 'O' (2) wins
     */
    @Test
    public void testPlayer_O_Wins() {
        gameModel.move(2, 0); // X turn
        gameModel.move(2, 1); // O turn
        gameModel.move(1, 0); // X turn
        gameModel.move(1, 1); // O turn
        gameModel.move(2, 2); // X turn
        gameModel.move(0, 1); // O turn
        assertEquals(true, gameModel.isWin(0, 1, "O"));
        assertEquals("Player 2 wins!", gameModel.getFinalResult());
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
        gameModel.move(2, 0); // X turn
        assertEquals("X", blocksData[2][0].getContents());
        gameModel.move(1, 0); // X turn
        assertEquals("O", blocksData[1][0].getContents());
    }

    /**
     * Tests Illegal Move
     */
    @Test
    public void testIllegalMove() {
        // 0,0 will not work beacuse only valid moves are in bottom row
        gameModel.move(0, 0); // X turn
        assertEquals("", blocksData[0][0].getContents());
    }

    /**
     * Tests Illegal Move
     * if a player made a move, then that block is no more a legal move
     */
    @Test
    public void testIllegalMove1() {
        gameModel.move(2, 0); // X turn
        // 2,0 will not work anymore beacuse player X made that move
        gameModel.move(2, 0); // O turn
        assertEquals(false, blocksData[2][0].getIsLegalMove());
        assertEquals("X", blocksData[2][0].getContents());
    }
}
