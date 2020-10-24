import controller.RowGameController;
import view.RowGameGUI;
import model.GameModel;
import model.ThreeInARowGameModel;

public class RowGameApp {
    // enum GameType {
    //     TicTacToe,
    //     ThreeInARow
    // }
    /**
     * Starts a new game in the GUI. MY proposed fix is implemented partially, I
     * will make necessary changes to keep the view code inside view folder. same for
     * model and controller
     */
    public static void main(String[] args) {
        // int gameType = -1;
        // if (args.length > 0) 
        //     gameType = args[0];
        // else {
        //     System.err.println("Please provide a game type Argument");
        //     System.exit(1);
        // }
        
        // try {
        //     gameType = GameType.valueOf(gameType).name();
        // } catch (IllegalArgumentException iAE) {
        //     System.err.println("Please provide a valid game type Argument");
        //     System.exit(1);
        // }
        int rows = 3;
        int cols = 3;
        // "TIC_TAC_TOE"
        final String GAME_TYPE = "THREE_IN_A_ROW";
        GameModel gameModel = new ThreeInARowGameModel(GAME_TYPE, rows, cols); // Observable | PropertyChangeSupport
        RowGameController gameController = new RowGameController();
        RowGameGUI gameView = new RowGameGUI(gameModel, gameController); // Observers | PropertyChangeListener
        
        gameModel.setView(gameView);
        gameController.initializeGame(gameModel);
        gameView.getGui().setVisible(true);
    }
}
