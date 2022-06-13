package main.java.tas.view;

import java.awt.GridBagLayout;
import java.awt.Color;

import javax.swing.JPanel;

import main.java.tas.controller.SceneController;
import main.java.tas.model.Entity;

/**
 * Class that implements a {@link ViewComponent}.
 */
public class GameView implements ViewMouse {

	private final JPanel rootCanvas = new JPanel(new GridBagLayout());
	private final SquarePanel gameBoard = new SquarePanel();

	/**
	 * Constructor that set up the component.
	 */
	public GameView() {
		this.rootCanvas.add(this.gameBoard);
		this.gameBoard.setLayout(null);

		this.rootCanvas.setBackground(Color.BLACK);
		this.gameBoard.setBgImage("bgImage");
	}

	/** {@inheritDoc} */
	@Override
	public void drawEntity(final Entity entity) {
		this.gameBoard.redrawEntity(entity, entity.getPosition());
	}

	/** {@inheritDoc} */
	@Override
	public void addEntityLabel(final Entity entity) {
		this.gameBoard.addEntity(entity);
	}

	/** {@inheritDoc} */
	@Override
	public void removeEntityLabel(final Entity entity) {
		this.gameBoard.removeEntity(entity);
	}

	/** {@inheritDoc} */
	@Override
	public void addTextLabel(final String text, final String id, final String anchor) {
		this.gameBoard.addTextLabel(text, id, anchor);
	}

	/** {@inheritDoc} */
	@Override
	public AdaptiveLabel getTextLabel(final String id) {
		return this.gameBoard.getTextLabel(id);
	}

	/** {@inheritDoc} */
	@Override
	public void removeTextLabel(final String id) {
		this.gameBoard.removeTextLabel(id);
	}

	/** {@inheritDoc} */
	@Override
	public JPanel getPanel() {
		return this.rootCanvas;
	}

	/**
	 * @return the game panel of the view
	 */
	public SquarePanel getGamePanel() {
		return this.gameBoard;
	}


	@Override
	public void setMouseObserver(final SceneMouseObserver observer) {
		this.gameBoard.addMouseListener(observer.getMouseListener());
		
	}

	@Override
	public void setObserver(SceneController observer) {
		// TODO Auto-generated method stub
		
	}

}
