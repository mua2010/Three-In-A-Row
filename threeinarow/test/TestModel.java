import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import model.RowBlockModel;
import model.RowGameModel;
import view.RowGameGUI;
import controller.RowGameController;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestModel {
    private RowGameModel gameModel;

    @Before
    public void setUp() {
        gameModel = new RowGameModel(3, 3);
    }

    @After
    public void tearDown() {
        gameModel = null;
    }

    @Test
    public void testNewGame() {
        RowGameController gameController = new RowGameController();
        RowGameGUI gameView = new RowGameGUI(gameModel); // Observers | PropertyChangeListener
        gameModel.setView(gameView);
        gameController.setModel(gameModel);
        gameController.resetModel();

        assertEquals("1", gameModel.getPlayer());
        assertEquals(9, gameModel.getMovesLeft());

        gameModel.reset();
        // Checking if only the bottom row is activated and 
        // rest of the blocks are false
        RowBlockModel[][] blocksData = gameModel.getBlocksData();
        for (int r = 0; r < blocksData.length - 1; r++) {
            for (int c = 0; c < blocksData[0].length; c++) {
                assertEquals(false, blocksData[r][c].getIsLegalMove());
            }
        }
        for (int c = 0; c < blocksData[0].length; c++) {
            assertEquals(true, blocksData[2][c].getIsLegalMove());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewBlockViolatesPrecondition() {
        RowBlockModel block = new RowBlockModel(null, 0, 0);
        block.setContents(null);
    }

    @Test
    public void testGamePlayisWinCheck() {
        RowGameController gameController = new RowGameController();
        RowGameGUI gameView = new RowGameGUI(gameModel); // Observers | PropertyChangeListener
        gameModel.setView(gameView);
        gameController.setModel(gameModel);
        gameController.resetModel();

        // Vertical Win Check
        gameModel.getBlocksData()[2][0].setContents("X");
        assertEquals(false, gameModel.isWin(2, 0));
        gameModel.getBlocksData()[1][0].setContents("X");
        assertEquals(false, gameModel.isWin(1, 0));
        gameModel.getBlocksData()[0][0].setContents("X");
        assertEquals(true, gameModel.isWin(0, 0));

        // This test case also tests reset method
        gameModel.reset();

        // Horizontal Win Check
        gameModel.getBlocksData()[1][0].setContents("X");
        assertEquals(false, gameModel.isWin(1, 0));
        gameModel.getBlocksData()[1][1].setContents("X");
        assertEquals(false, gameModel.isWin(1, 1));
        gameModel.getBlocksData()[1][2].setContents("X");
        assertEquals(true, gameModel.isWin(1, 2));

        gameModel.reset();
        
        // Diagonal Win Check
        gameModel.getBlocksData()[0][2].setContents("X");
        assertEquals(false, gameModel.isWin(0, 2));
        gameModel.getBlocksData()[1][1].setContents("X");
        assertEquals(false, gameModel.isWin(1, 1));
        gameModel.getBlocksData()[2][0].setContents("X");
        assertEquals(true, gameModel.isWin(2, 0));
    }
}
