package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import adapter.Adapter;
import model.RowGameModel;
import controller.RowGameController;

public class RowGameGUI implements RowGameView {
    // CHANGE: Made class variables private
    private JFrame gui;
    private RowGameBoardView gameBoardView;
    private JButton reset = new JButton("Reset");
    private RowGameStatusView gameStatusView;

    private RowGameModel gameModel;
    protected Adapter gameAdapter;

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
    public JButton getResetButton() {
        return this.reset;
    }

    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameGUI(RowGameModel gameModel, RowGameController gameController) {
        this.gameModel = gameModel;
        this.gameAdapter = new Adapter(this, gameController);
        this.gui = new JFrame(gameModel.getGameType());

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500, 350));
        gui.setResizable(true);

        gameBoardView = new RowGameBoardView(gameModel, this.gameAdapter);
        JPanel gamePanel = gameBoardView.getGamePanel();

        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);

        gameStatusView = new RowGameStatusView();
        // called the getter instead of the class variable
        JPanel messages = gameStatusView.getMessages();

        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);

        reset.addActionListener(this.gameAdapter);
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
