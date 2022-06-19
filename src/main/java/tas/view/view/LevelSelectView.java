package main.java.tas.view.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.tas.controller.observer.SceneActionObserver;
import main.java.tas.view.ViewAction;

/**
 * Class that builds the inventory view. Class that implements a
 * {@link ViewAction}.
 */
public class LevelSelectView implements ViewAction {

	private JPanel rootPanel = new JPanel(new GridLayout(0, 1, 5, 10));
	private JButton buttonList[];
	private int nLevels;

	/**
	 * Constructor that builds the view for the level select menu.
	 * 
	 * @param nLevesIn the number of levels
	 */
	public LevelSelectView(int nLevelsIn) {
		nLevels = nLevelsIn;
		buttonList = new JButton[nLevels];
		int counter = 0;
		for (JButton button : buttonList) {
			counter++;
			button = new JButton(Integer.toString(counter));
			this.buttonList[counter - 1] = button;
			this.rootPanel.add(button);
		}
	}

	/** {@inheritDoc} */
	@Override
	public JPanel getPanel() {
		return this.rootPanel;
	}

	/** {@inheritDoc} */
	@Override
	public void setActionObserver(SceneActionObserver levelSelectController) {
		for (int counter = 0; counter < nLevels; counter++) {
			buttonList[counter].addActionListener(levelSelectController.getActionListener());
		}
	}
}
