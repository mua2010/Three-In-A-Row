package adapter;

import java.awt.event.*;

import controller.RowGameController;
import view.GameJButton;
import view.RowGameGUI;

public class Adapter implements ActionListener {
    private RowGameController gameController;
	private RowGameGUI gameView;
	
    public Adapter(RowGameGUI gameView, RowGameController gameController) {
        this.gameView = gameView;
        this.gameController = gameController;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameView.getResetButton()) {
            // reset
            gameController.sendResetRequest();
        }
        else {
            // ask controller to update game state with the move
            GameJButton buttonPressed = (GameJButton) e.getSource();
            gameController.sendUpdateGameStateRequest(buttonPressed);
        }
    }
}
