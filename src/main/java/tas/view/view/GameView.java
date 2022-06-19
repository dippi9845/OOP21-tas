package main.java.tas.view.view;

import java.awt.GridBagLayout;
import java.awt.Color;

import javax.swing.JPanel;

import main.java.tas.controller.SceneMouseObserver;
import main.java.tas.model.Entity;
import main.java.tas.view.SquarePanel;
import main.java.tas.view.ViewMouse;

/**
 * Class that implements a {@link ViewComponent}, {@link ViewMouse}.
 */
public class GameView implements ViewMouse,ViewComponent {

	private final JPanel rootCanvas = new JPanel(new GridBagLayout());
	private final SquarePanel gameBoard = new SquarePanel();

	/**
	 * Constructor that set up the component.
	 */
	public  GameView() {
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
	public JPanel getPanel() {
		return this.rootCanvas;
	}

	/**
	 * @return the game panel of the view
	 */
	public SquarePanel getGamePanel() {
		return this.gameBoard;
	}

	/** {@inheritDoc} */
	@Override
	public void setMouseObserver(final SceneMouseObserver observer) {
		this.gameBoard.addMouseListener(observer.getMouseListener());
	}
}
