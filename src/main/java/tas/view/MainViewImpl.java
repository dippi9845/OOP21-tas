package main.java.tas.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class that implements a {@link MainView}.
 */
public class MainViewImpl implements MainView {
	private static final String WINDOW_NAME = "Towers and Stuff";
	private static final Dimension SCREEN_SIZE = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
	private final Dimension defaultWindowSize = scaleDimension(SCREEN_SIZE, 2);
	private final Dimension minWindowSize = scaleDimension(SCREEN_SIZE, 5);

	private JFrame frame;
	private JPanel rootPanel;

	/**
	 * Constructor that set up the main view.
	 */
	public MainViewImpl() {
		createWindow();
	}

	/** {@inheritDoc} */
	@Override
	public void createWindow() {
		this.frame = new JFrame(WINDOW_NAME);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setMinimumSize(minWindowSize);
		this.frame.setPreferredSize(defaultWindowSize);
		this.frame.setResizable(true);

		this.rootPanel = new JPanel();
		this.frame.getContentPane().add(this.rootPanel);
		this.frame.pack();
	}

	/** {@inheritDoc} */
	@Override
	public JPanel getPanel() {
		return this.rootPanel;
	}

	/**
	 * Gets a scaled dimension.
	 * 
	 * @param dimension  that has to be scaled
	 * @param proportion the proportion that scales the dimension
	 * @return the scaled dimension
	 */
	private Dimension scaleDimension(final Dimension dimension, final double proportion) {
		return new Dimension((int) (dimension.getWidth() / proportion), (int) (dimension.getHeight() / proportion));
	}

	/** {@inheritDoc} */
	@Override
	public void show() {
		this.frame.setVisible(true);
	}

	/** {@inheritDoc} */
	@Override
	public void update() {
		this.frame.repaint();
		this.frame.revalidate();
	}

	/** {@inheritDoc} */
	@Override
	public void dispose() {
		this.frame.dispose();

	}

	/** {@inheritDoc} */
	@Override
	public void clearView() {
		this.rootPanel.removeAll();
		this.rootPanel.revalidate();
		this.rootPanel.repaint();
	}

	/** {@inheritDoc} */
	@Override
	public void destroyView() {
		this.frame.setVisible(false);
		dispose();
	}

}
