import controller.RowGameController;
import view.RowGameGUI;
import model.RowGameModel;

public class RowGameApp {
    /**
     * Starts a new game in the GUI. MY proposed fix is implemented partially, I
     * will make necessary changes to keep the view code inside view folder. same for
     * model and controller
     */
    public static void main(String[] args) {
        RowGameModel gameModel = new RowGameModel(); // Observable | PropertyChangeSupport
        RowGameController gameController = new RowGameController();
        RowGameGUI gameView = new RowGameGUI(gameModel); // Observers | PropertyChangeListener
        
        gameModel.setView(gameView);
        gameController.setModel(gameModel);
        gameController.resetModel();

        gameView.getGui().setVisible(true);
    }
}
