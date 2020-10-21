import controller.RowGameController;

public class RowGameApp {
    /**
     * Starts a new game in the GUI.
     * MY proposed fix is implemented partially, I will make
     * necessart changes to keep the view code inside view folder 
     * same for model and controller
     */
    public static void main(String[] args) {
        RowGameController game = new RowGameController();
        game.startUp();
    }
}
