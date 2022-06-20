package main.java.tas.view.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.tas.controller.observer.SceneActionObserver;
import main.java.tas.view.ViewAction;

/**
 * Class that builds the inventory view. Class that implements a
 * {@link ViewAction}.
 */
public class LevelSelectView implements ViewAction {

	private final JPanel rootPanel = new JPanel(new GridLayout(0, 1, 5, 10));
	private List <JButton> buttonList = new ArrayList <JButton>();

	/**
	 * Constructor that builds the view for the level select menu.
	 * 
	 * @param nLevelsIn the number of levels
	 */
	public LevelSelectView(int nLevelsIn) {
		for (int counter = 1; counter <= nLevelsIn; counter++) {
			JButton button = new JButton(Integer.toString(counter));
			this.rootPanel.add(button);
			buttonList.add(button);
		}
	}

	/** {@inheritDoc} */
	@Override
	public JPanel getPanel() {
		return this.rootPanel;
	}

	/** {@inheritDoc} */
	@Override
	public void setActionObserver(SceneActionObserver observer) {
		for (JButton button : this.buttonList) {
			button.addActionListener(observer.getActionListener());
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public List<JButton> getButtons() {
		return this.buttonList;
	}
}
