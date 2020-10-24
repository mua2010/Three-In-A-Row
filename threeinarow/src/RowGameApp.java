import controller.RowGameController;
import view.RowGameGUI;
import model.RowGameModel;

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
        String gameType = "THREE_IN_A_ROW";
        RowGameModel gameModel = new RowGameModel(gameType, rows, cols); // Observable | PropertyChangeSupport
        RowGameController gameController = new RowGameController();
        RowGameGUI gameView = new RowGameGUI(gameModel); // Observers | PropertyChangeListener
        
        gameModel.setView(gameView);
        gameController.setModel(gameModel);
        gameController.resetModel();

        gameView.getGui().setVisible(true);
    }
}
