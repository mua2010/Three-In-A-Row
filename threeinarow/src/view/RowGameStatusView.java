package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.RowGameModel;

public class RowGameStatusView implements RowGameView {
	// CHANGE: Made playerturn and messages to private
	// and create getter and setter for messages
	private JTextArea playerturn = new JTextArea();
	private JPanel messages = new JPanel(new FlowLayout());

	public JPanel getMessages(){
		return this.messages;
	}

	public void setMessages(JPanel msgs){
		this.messages = msgs;
	}

	public RowGameStatusView() {
		super();

		messages.setBackground(Color.white);
		messages.add(playerturn);
	}

	public void update(RowGameModel gameModel) {
		if (gameModel.getFinalResult() == null) {
			if (gameModel.getPlayer().equals("1")) {
				playerturn.setText("Player 1 to play 'X'");
			} else {
				playerturn.setText("Player 2 to play 'O'");
			}
		} else {
			playerturn.setText(gameModel.getFinalResult());
		}
	}
}
