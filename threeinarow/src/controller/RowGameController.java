package controller;

import model.RowGameModel;
import view.GameJButton;

/**
 * Java implementation of the 3 in a row game, using the Swing framework.
 *
 * This quick-and-dirty implementation violates a number of software engineering
 * principles and needs a thorough overhaul to improve readability,
 * extensibility, and testability.
 */
public class RowGameController {
	// public static final String GAME_END_NOWINNER = "Game ends in a draw";

	private RowGameModel gameModel;

	/**
	 * Creates a new game initializing the GUI.
	 */
	public RowGameController() {
		// gameModel.reset();
	}

	public void initializeGame(RowGameModel gameModel) {
		this.gameModel = gameModel;
		sendResetRequest();
	}

	public void sendResetRequest() {
		this.gameModel.reset();
		this.gameModel.updateView();
	}

	public void sendUpdateGameStateRequest(GameJButton buttonPressed) {
		this.gameModel.move(buttonPressed.getRow(), buttonPressed.getCol());
		this.gameModel.updateView();
	}
}
