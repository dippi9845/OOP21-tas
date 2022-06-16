package main.java.tas.view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.tas.controller.SceneActionObserver;
import main.java.tas.controller.SceneMouseObserver;

/**
 * Class that builds the sandbox mode menu view.
 * Class that implements a {@link ViewAction}, {@link ViewMouse}.
 */
public class SandboxModeView implements ViewAction,ViewMouse  {
	
	private JPanel rootPanel = new JPanel(new BorderLayout());
	private JButton finishButton = new JButton("DONE");
	private JPanel gamePanel = new JPanel(new GridBagLayout());
	private SquarePanel gameBoard = new SquarePanel();
    
	/**
	 * Constructor that builds the sandbox mode view.
	 * @param theModel the model
	 */
    public SandboxModeView(){
		this.gameBoard.setBgImage("bgImage");
		this.gamePanel.add(this.gameBoard);
    	this.rootPanel.add(this.gamePanel, BorderLayout.CENTER);
    	this.rootPanel.add(this.finishButton, BorderLayout.SOUTH);
    }
    
    /**
     * @return the root panel
     */
	public JPanel getPanel() {
        return this.rootPanel;
    }
	
	/**
	 * @return the game board
	 */
	public SquarePanel getGameBoard(){
		return this.gameBoard;
	}

	/** {@inheritDoc} */
	@Override
	public void setMouseObserver(SceneMouseObserver observer) {
		this.gameBoard.addMouseListener(observer.getMouseListener());
	}

	/** {@inheritDoc} */
	@Override
	public void setActionObserver(SceneActionObserver observer) {
		this.finishButton.addActionListener(observer.getActionListener());
	}
}