import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import model.BlockModel;
import model.GameModel;
import model.ThreeInARowGameModel;
import view.GameJButton;
import view.RowGameGUI;
import controller.RowGameController;

/**
 * Test class to test Game Controller
 */
public class TestController {
    private GameModel gameModel;
    private RowGameController gameController;
    private RowGameGUI gameView;

    @Before
    public void setUp() {
        gameModel = new ThreeInARowGameModel("THREE_IN_A_ROW", 3, 3);
        gameController = new RowGameController();
        gameView = new RowGameGUI(gameModel, gameController); // Observers | PropertyChangeListener
        
        gameModel.setView(gameView);
        gameController.initializeGame(gameModel);
    }

    @After
    public void tearDown() {
        gameModel = null;
        gameController = null;
        gameView = null;
    }

    /**
     * Tests the controller by sending in a request to update game state
     */
    @Test
    public void testUpdateGameState() {
        GameJButton moveX = new GameJButton(2, 2);
        gameController.sendUpdateGameStateRequest(moveX);
        assertEquals("X", gameModel.getBlocksData()[2][2].getContents());

        GameJButton moveO = new GameJButton(2, 1);
        gameController.sendUpdateGameStateRequest(moveO);
        assertEquals("O", gameModel.getBlocksData()[2][1].getContents());
    }

    /**
     * Tests the controller by sending in a request to RESET game state
     */
    @Test
    public void testResetGameState() {
        // alter game state
        gameController.sendUpdateGameStateRequest(new GameJButton(2, 1));
        // reset game
        gameController.sendResetRequest();
        // check if the move made at (2,1) is reset
        assertEquals("", gameModel.getBlocksData()[2][1].getContents());
    }
}