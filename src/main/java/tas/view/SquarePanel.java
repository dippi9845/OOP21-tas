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
import java.util.Optional;

import javax.swing.ImageIcon;

import main.java.tas.model.Entity;
import main.java.tas.utils.Dimension;
import main.java.tas.utils.Position;

/**
 * Class that implements a square version {@link AdaptivePanel}.
 */
public class SquarePanel extends AdaptivePanel {

	private static final long serialVersionUID = 1L;
	private final HashMap<Entity, AdaptiveLabel> entityLables = new HashMap<Entity, AdaptiveLabel>();
	private final ImageLoader imGetter = new ImageLoaderImpl();
	private Optional<String> bgImageName = Optional.empty();
	private Optional<BufferedImage> bgImage = Optional.empty();

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
	 * @param bgImageName name of the background image
	 */
	public SquarePanel(final String bgImageName) {
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
	public SquarePanel(final List<Position> linesPoints, final Color color, final int thickness) {
		this();
		setLine(linesPoints, color, thickness);
	}

	/**
	 * Creates a label for the given entity and generate its image.
	 * 
	 * @param e the enemy that needs a label
	 */
	public void addEntity(final Entity e) {
		AdaptiveLabel entityLabel = new AdaptiveLabel();
		try {
			entityLabel.setIcon(new ImageIcon(imGetter.getImageByEntity(e,
			        new Dimension(this.getPreferredSize().getWidth(), this.getPreferredSize().getHeight()))));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		entityLables.put(e, entityLabel);
		this.add(entityLabel);
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
						entityMap.getValue().setIcon(new ImageIcon(imGetter.getImageByEntity(entityMap.getKey(),
						        new Dimension(getPreferredSize().getWidth(), getPreferredSize().getHeight()))));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}

				// re-scale the background image
				if (bgImage.isPresent()) {
					try {
						bgImage = Optional.of(imGetter.getImageByName(bgImageName.get(),
						        new Dimension(getPreferredSize().getWidth(), getPreferredSize().getHeight())));
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
	public void redrawEntity(final Entity e, final Position newPos) {
		this.entityLables.get(e).setPosition(newPos);
	}

	/**
	 * Method that removes the label of a given entity. NOTE: The entity must be
	 * added first with {@link #addEntity}.
	 * 
	 * @param e the entity that has to be removed
	 */
	public void removeEntity(final Entity e) {
		this.remove(this.entityLables.get(e));
		this.revalidate();
		this.repaint();
		this.entityLables.remove(e);
	}

	/** {@inheritDoc} */
	@Override
	protected void paintComponent(Graphics g) {
		if (this.bgImage != null) {
			g.drawImage(this.bgImage.get(), 0, 0, null);
		}
		super.paintComponent(g);
	}

	/**
	 * @param bgImageName name of the background image
	 */
	public void setBgImage(final String bgImageName) {
		this.bgImageName = Optional.of(bgImageName);
		try {
			this.bgImage = Optional.of(imGetter.getImageByName(bgImageName));
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
