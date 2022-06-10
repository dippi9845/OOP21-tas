package main.java.tas.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.java.tas.model.Entity;
import main.java.tas.model.GameSpecs;
import main.java.tas.utils.Position;

/**
 * Class that implements a square version of the {@link JPanel}.
 */
public class SquarePanel extends AdaptivePanel {

	private static final long serialVersionUID = 1L;
	private final HashMap<Entity, AdaptiveLabel> entityLables = new HashMap<Entity, AdaptiveLabel>();
	private final HashMap<String, AdaptiveLabel> textLables = new HashMap<String, AdaptiveLabel>();
	private final ImageLoader imGetter = new ImageLoaderImpl();
	private String bgImageName = null;
	private BufferedImage bgImage = null;

	private GameSpecs gameSpecs = new GameSpecs();

	/**
	 * Set up the SquarePanel.
	 */
	public SquarePanel() {
		super();
		setAdaptive();
	}

	/**
	 * Set up the SquarePanel with an image
	 * 
	 * @param bgImage the image of the background
	 */
	public SquarePanel(String bgImageName) {
		this();
		setBgImage(bgImageName);
	}

	/**
	 * Set up the SquarePanel with a line.
	 * 
	 * @param linesPoints the line points NOTE: The input number must be greater
	 *                    than 1
	 * @param color       the color of the line
	 * @param thickness   the thickness of the line
	 */
	public SquarePanel(List<Position> linesPoints, Color color, int thickness) {
		this();
		setLine(linesPoints, color, thickness);
	}

	/**
	 * Creates a label for the given entity and generate its image.
	 * 
	 * @param e the enemy that needs a label
	 */
	public void addEntity(Entity e) {
		AdaptiveLabel entityLabel = new AdaptiveLabel();
		try {
			entityLabel.setIcon(new ImageIcon(imGetter.getImageByEntity(e, this.getPreferredSize())));
		} catch (FileNotFoundException e1) {
			System.out.println(e1);
		}
		entityLables.put(e, entityLabel);
		this.add(entityLabel);
	}

	/**
	 * Add a text label to the SquarePanel.
	 * 
	 * @param text   is the text that will be shown
	 * @param id     the id of the label
	 * @param anchor the position of the label (NW, NE, SW, SE)
	 * @throws IllegalArgumentException if the anchor is not in the list
	 */
	public void addTextLabel(String text, String id, String anchor) throws IllegalArgumentException {
		AdaptiveLabel tempLabel = new AdaptiveLabel();
		this.add(tempLabel);
		tempLabel.setText(text);
		tempLabel.setFont("Verdana", 1, 20);
		tempLabel.setForeground(Color.WHITE);

		this.textLables.put(id, tempLabel);

		switch (anchor) {
		case "NW":
			tempLabel.setPosition(
					new Position(tempLabel.getPreferredSize().getWidth(), tempLabel.getPreferredSize().getHeight()));
			break;
		case "NE":
			tempLabel.setPosition(
					new Position(this.gameSpecs.getGameUnits().width - tempLabel.getPreferredSize().getWidth(),
							tempLabel.getPreferredSize().getHeight()));
			break;
		case "SW":
			tempLabel.setPosition(new Position(tempLabel.getPreferredSize().getWidth(),
					this.gameSpecs.getGameUnits().height - tempLabel.getPreferredSize().getHeight()));
			break;
		case "SE":
			tempLabel.setPosition(
					new Position(this.gameSpecs.getGameUnits().width - tempLabel.getPreferredSize().getWidth(),
							this.gameSpecs.getGameUnits().height - tempLabel.getPreferredSize().getHeight()));
			break;
		default:
			throw new IllegalArgumentException("The anchor must be one of: NW, NE, SW, SE");

		}

	}

	/**
	 * Return the label of the given id. NOTE: returns null if the label does not
	 * exist.
	 * 
	 * @param id of the label
	 * @return the label
	 */
	public AdaptiveLabel getTextLabel(String id) {
		if (!this.textLables.containsKey(id)) {
			return null;
		}
		return this.textLables.get(id);
	}

	/**
	 * Removes the text label from the panel.
	 * 
	 * @param id of the text Label
	 */
	public void removeTextLabel(String id) {
		if (!this.textLables.containsKey(id)) {
			return;
		}

		this.remove(this.textLables.get(id));
		this.validate();
		this.repaint();
		this.textLables.remove(id);
	}

	/**
	 * Method that set all the components of the panel to be resized with it.
	 */
	private void setAdaptive() {
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {

				// redraws all entities
				for (Map.Entry<Entity, AdaptiveLabel> entityMap : entityLables.entrySet()) {
					try {
						entityMap.getValue().setIcon(
								new ImageIcon(imGetter.getImageByEntity(entityMap.getKey(), getPreferredSize())));
					} catch (FileNotFoundException e1) {
						System.out.println(e1);
					}
				}

				// redraws all textLabels
				for (Map.Entry<String, AdaptiveLabel> textLabelMap : textLables.entrySet()) {
					textLabelMap.getValue().redraw();
				}

				// re-scale the background image
				if (bgImage != null) {
					try {
						bgImage = imGetter.getImageByName(bgImageName, getPreferredSize());
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	/**
	 * Allows to redraw a given entity. NOTE: The entity must be added first with
	 * {@link #addEntity} .
	 * 
	 * @param e      the entity that has to be redrawn
	 * @param newPos the position where the entity will be drawn
	 */
	public void redrawEntity(Entity e, Position newPos) {
		this.entityLables.get(e).setPosition(newPos);
	}

	/**
	 * Method that removes the label of a given entity. NOTE: The entity must be
	 * added first with {@link #addEntity}.
	 * 
	 * @param e the entity that has to be removed
	 */
	public void removeEntity(Entity e) {
		this.remove(this.entityLables.get(e));
		this.revalidate();
		this.repaint();
		this.entityLables.remove(e);
	}

	/** {@inheritDoc} */
	@Override
	protected void paintComponent(Graphics g) {
		if (this.bgImage != null) {
			g.drawImage(this.bgImage, 0, 0, null);
		}
		super.paintComponent(g);
	}

	/**
	 * @param bgImage image of the background
	 */
	public void setBgImage(String bgImageName) {
		this.bgImageName = bgImageName;
		try {
			this.bgImage = imGetter.getImageByName(bgImageName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Allows to write the object.
	 * 
	 * @param stream
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	/**
	 * Allows to read the object.
	 * 
	 * @param stream
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}

}
