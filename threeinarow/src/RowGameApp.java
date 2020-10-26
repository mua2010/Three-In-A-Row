import controller.RowGameController;
import view.RowGameGUI;
import model.GameModel;
import model.ThreeInARowGameModel;
import model.TicTacToeModel;

public class RowGameApp {
    public static void main(String[] args) {
        // "THREE_IN_A_ROW" -> 0 
        // "TIC_TAC_TOE" -> 1
        int argument = 1;
        if (args.length > 0)
            try {
                argument = Integer.parseInt(args[0]);
            } catch (IllegalArgumentException iAE) {
                System.err.println("Please provide a valid game type Argument");
                System.err.println("0 -> THREE_IN_A_ROW | 1 -> TIC_TAC_TOE");
                System.exit(1);
            }
        else {
            System.err.println("Please provide a game type Argument");
            System.exit(1);
        }
        
        int rows = 3;
        int cols = 3;
        String GAME_TYPE;
        GameModel gameModel;

        if (argument == 0) {
            GAME_TYPE = "THREE_IN_A_ROW"; // 0 
            gameModel = new ThreeInARowGameModel(GAME_TYPE, rows, cols); // Observable | PropertyChangeSupport
        }   
        else {
            GAME_TYPE = "TIC_TAC_TOE"; // 1
            gameModel = new TicTacToeModel(GAME_TYPE, rows, cols); // Observable | PropertyChangeSupport
        }

        RowGameController gameController = new RowGameController();
        RowGameGUI gameView = new RowGameGUI(gameModel, gameController); // Observers | PropertyChangeListener
        
        gameModel.setView(gameView);
        gameController.initializeGame(gameModel);
        gameView.getGui().setVisible(true);
    }
}
