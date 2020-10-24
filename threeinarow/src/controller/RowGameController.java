package controller;

import model.RowGameModel;

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

	public void setModel(RowGameModel gameModel) {
		this.gameModel = gameModel;
	}

	public void sendRequest() {

	}

	public void resetModel() {
		this.gameModel.reset();
	}

}
