package main.java.tas.view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.tas.model.Entity;
import main.java.tas.model.menu.MenuModel;

/**
 * Class that builds the sandbox mode view.
 */
public class SandboxModeView implements ViewAction,ViewMouse  {
	
	private JPanel rootPanel = new JPanel(new BorderLayout());
	private JButton finishButton = new JButton("DONE");
	private JPanel gamePanel = new JPanel(new GridBagLayout());
	private SquarePanel gameBoard = new SquarePanel();
    
	/**
	 * Constructor that builds the sandbox mode view
	 * @param theModel the model
	 */
    public SandboxModeView(MenuModel theModel){
    	
		this.gameBoard.setBgImage("bgImage");
		this.gamePanel.add(this.gameBoard);
    	this.rootPanel.add(this.gamePanel, BorderLayout.CENTER);
    	this.rootPanel.add(this.finishButton, BorderLayout.SOUTH);
    	
    	
    }
    
    /**
     * 
     * @return the root panel
     */
	public JPanel getPanel() {
        return this.rootPanel;
    }
	
	/**
	 * 
	 * @return the game board
	 */
	public SquarePanel getGameBoard(){
		return this.gameBoard;
	}

	@Override
	public void drawEntity(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEntityLabel(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEntityLabel(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTextLabel(String text, String id, String anchor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AdaptiveLabel getTextLabel(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeTextLabel(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMouseObserver(SceneMouseObserver observer) {
		this.gameBoard.addMouseListener(observer.getMouseListener());
		
	}

	@Override
	public void setActionObserver(SceneActionObserver observer) {
		this.finishButton.addActionListener(observer.getActionListener());
		
	}

	
	
}