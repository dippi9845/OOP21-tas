package main.java.tas.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.tas.controller.observer.SceneActionObserver;

/**
 * Class that builds the settings menu view. Class that implements a
 * {@link ViewAction}.
 */
public class SettingsView implements ViewAction {

	private JPanel rootPanel = new JPanel(new GridLayout(0, 1, 5, 10));
	private JLabel label;
	private JButton backButton;
	private List<JButton> buttonList = new ArrayList<JButton>();

	/**
	 * Constructor that builds the settings menu view
	 */
	public SettingsView() {
		label = new JLabel("Sorry there are no settings to mess with.");
		label.setFont(new Font("Serif", Font.PLAIN, 30));
		label.setHorizontalAlignment(JLabel.CENTER);
		this.rootPanel.add(label);
		backButton = new JButton("OK GO BACK THEN");
		this.rootPanel.add(backButton);
		this.buttonList.add(backButton);
	}

	/** {@inheritDoc} */
	public JPanel getPanel() {
		return this.rootPanel;
	}

	/** {@inheritDoc} */
	@Override
	public void setActionObserver(SceneActionObserver observer) {
		backButton.addActionListener(observer.getActionListener());
	}

	/** {@inheritDoc} */
	@Override
	public List<JButton> getButtons() {
		return this.buttonList;
	}
}