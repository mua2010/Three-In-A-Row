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
 * Test Class to test Game Views
 */
public class TestView {
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
     * Tests the JTextArea of Game Panel
     */
    @Test
    public void testNewGameStatusTextView() {
        // check if player 1 (X) goes first
        assertEquals("Player 1 to play 'X'", gameView.getGameStatusView().getPlayerturn().getText());
        gameModel.move(2, 0);
        gameView.update();
        // check if the second move is by player 2 (O)
        assertEquals("Player 2 to play 'O'", gameView.getGameStatusView().getPlayerturn().getText());
    }

    /**
     * Tests the ButtonArea of Game Panel
     */
    @Test
    public void testButtonPressView() {
        gameModel.move(2, 0);
        gameView.update();
        assertEquals("X",  gameView.getGameBoardView().getButtonText(2, 0));

        gameModel.move(2, 1);
        gameView.update();
        assertEquals("O", gameView.getGameBoardView().getButtonText(2, 1));
    }
}


