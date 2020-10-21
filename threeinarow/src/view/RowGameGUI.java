package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import model.RowGameModel;
import controller.RowGameController;

public class RowGameGUI implements RowGameView {
    // CHANGE: Made class variables private
    private JFrame gui = new JFrame("Three in a Row");
    private RowGameBoardView gameBoardView;
    private JButton reset = new JButton("Reset");
    private RowGameStatusView gameStatusView;

    private RowGameController gameController;

    // Made class getters and setters
    public JFrame getGui(){
        return this.gui;
    }
    public void setGui(JFrame gui){
        this.gui = gui;
    }
    public RowGameBoardView getGameBoardView(){
        return this.gameBoardView;
    }
    public void setGameBoardView(RowGameBoardView gameBoardView){
        this.gameBoardView = gameBoardView;
    }

    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameGUI(RowGameController gameController) {
        this.gameController = gameController;

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500, 350));
        gui.setResizable(true);

        gameBoardView = new RowGameBoardView(this.gameController);
        JPanel gamePanel = gameBoardView.gamePanel;

        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);

        gameStatusView = new RowGameStatusView(this.gameController);
        // called the getter instead of the class variable
        JPanel messages = gameStatusView.getMessages();

        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.resetGame();
            }
        });
    }

    /**
     * Updates the game view after the game model changes state.
     *
     * @param gameModel The current game model
     */
    public void update(RowGameModel gameModel) {
        gameBoardView.update(gameModel);

        gameStatusView.update(gameModel);
    }
}
