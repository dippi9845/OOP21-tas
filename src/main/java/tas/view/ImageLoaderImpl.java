package main.java.tas.view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

import main.java.tas.model.Entity;
import main.java.tas.model.GameSpecs;

/**
 * Class that implements an {@link ImageLoader}.
 */
public class ImageLoaderImpl implements ImageLoader {

	private static String RESOURCE_PATH = "res" + System.getProperty("file.separator") + "images";
	private final HashMap<String, BufferedImage> imagesMap = new HashMap<String, BufferedImage>();

	private GameSpecs gameSpecs = new GameSpecs();

	/**
	 * Set up the loader.
	 */
	public ImageLoaderImpl() {
		loadAllImages();
	}

	/**
	 * Loads all of the images in the {@link #RESOURCE_PATH}.
	 */
	private void loadAllImages() {
		try (Stream<Path> paths = Files.walk(Paths.get(RESOURCE_PATH))) {
			paths.filter(Files::isRegularFile).forEach(filePath -> loadImage(filePath.toFile()));
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Loads a requested file.
	 * 
	 * @param file that is requested
	 */
	private void loadImage(final File file) {
		try {
			this.imagesMap.put(file.getName().replaceFirst("[.][^.]+$", ""), ImageIO.read(file));
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/** {@inheritDoc} */
	@Override
	public BufferedImage getImageByEntity(final Entity entity, Dimension CanvasDimension) throws FileNotFoundException {
		if (CanvasDimension.width == 0 || CanvasDimension.height == 0) {
			CanvasDimension = this.gameSpecs.getGameUnits();
		}

		String entityName = entity.getImageName();
		if (!this.imagesMap.containsKey(entityName)) {
			throw new FileNotFoundException("The given entity (" + entity + ") has no image in the 'res' folder");
		}

		Dimension newImageDimension = getNewImageDimension(CanvasDimension, entity.getBodyDimension());
		return scaleImage(this.imagesMap.get(entityName), newImageDimension);
	}

	/** {@inheritDoc} */
	@Override
	public BufferedImage getImageByName(final String imageName) throws FileNotFoundException {
		if (!this.imagesMap.containsKey(imageName)) {
			throw new FileNotFoundException("There is no image named (" + imageName + ") in the 'res' folder");
		}
		return this.imagesMap.get(imageName);
	}

	/** {@inheritDoc} */
	@Override
	public BufferedImage getImageByName(final String imageName, Dimension CanvasDimension) throws FileNotFoundException {
		return scaleImage(getImageByName(imageName), CanvasDimension);
	}

	/**
	 * Gets the dimension of the new image.
	 * 
	 * @param canvasDimension        the actual dimension of the canvas that contain
	 *                               the image
	 * @param originalImageDimension the original dimension of the image
	 * @return the new dimension of the image
	 */
	private Dimension getNewImageDimension(Dimension canvasDimension, Dimension originalImageDimension) {
		int newX = (int) ((double) canvasDimension.width
				/ ((double) this.gameSpecs.getGameUnits().width / (double) originalImageDimension.width));
		int newY = (int) ((double) canvasDimension.height
				/ ((double) this.gameSpecs.getGameUnits().height / (double) originalImageDimension.height));

		return new Dimension(newX, newY);
	}

	/**
	 * Scale an image to a new dimension.
	 * 
	 * @param src          the image that has to be scaled
	 * @param newDimension the new dimension of the image
	 * @return the scaled image
	 */
	private BufferedImage scaleImage(final BufferedImage src, final Dimension newDimension) {
		int newWidth = newDimension.width;
		int newHeight = newDimension.height;
		BufferedImage img = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		int originalWidth = src.getWidth();
		int originalHeight = src.getHeight();
		int[] ys = new int[newHeight];

		for (int y = 0; y < newHeight; y++)
			ys[y] = y * originalHeight / newHeight;
		for (int x = 0; x < newWidth; x++) {
			int newX = x * originalWidth / newWidth;
			for (int y = 0; y < newHeight; y++) {
				int col = src.getRGB(newX, ys[y]);
				img.setRGB(x, y, col);
			}
		}
		return img;
	}

}
