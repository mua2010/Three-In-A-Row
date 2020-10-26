package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.GameModel;
import model.GameModel.Player;

/**
 * Class that updates the game status
 */
public class RowGameStatusView implements RowGameView {
	protected static final String PLAYER_1_TURN = "Player 1 to play 'X'";
	protected static final String PLAYER_2_TURN = "Player 2 to play 'O'";
	private JTextArea playerturn = new JTextArea();
	private JPanel messages = new JPanel(new FlowLayout());
	private GameModel gameModel;

	public JTextArea getPlayerturn(){
		return this.playerturn;
	}

	public JPanel getMessages(){
		return this.messages;
	}

	public void setMessages(JPanel msgs){
		this.messages = msgs;
	}

	public RowGameStatusView(GameModel gameModel) {
		super();
		this.gameModel = gameModel;

		messages.setBackground(Color.white);
		messages.add(playerturn);
	}

	public void update() {
		if (gameModel.getFinalResult() == null) {

			if (gameModel.getPlayer().equals(Player.X.getPlayer())) {
				playerturn.setText(PLAYER_1_TURN);
			} else {
				playerturn.setText(PLAYER_2_TURN);
			}
		} else {
			playerturn.setText(gameModel.getFinalResult());
		}
	}
}
