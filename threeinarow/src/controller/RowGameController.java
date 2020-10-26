package controller;

import model.GameModel;
import view.GameJButton;

/**
 * Game Controller that notifies the model to update the game state
 * when a event happens (Reset or move)
 */
public class RowGameController {

	private GameModel gameModel;

	// call the reset method of model to set up the game
	public void initializeGame(GameModel gameModel) {
		this.gameModel = gameModel;
		sendResetRequest();
	}

	// Send a reset request to model and update the view (Note: the updateView exists in model)
	public void sendResetRequest() {
		this.gameModel.reset();
		this.gameModel.updateView();
	}

	// Send a reqest to update the state of the game when ever a button is pressed / move is made
	public void sendUpdateGameStateRequest(GameJButton buttonPressed) {
		this.gameModel.move(buttonPressed.getRow(), buttonPressed.getCol());
		this.gameModel.updateView();
	}
}
