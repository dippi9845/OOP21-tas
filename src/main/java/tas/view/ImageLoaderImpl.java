package main.java.tas.view;

import main.java.tas.utils.Size;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Stream;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

import main.java.tas.model.Entity;
import main.java.tas.utils.GameSpecs;

/**
 * Class that implements an {@link ImageLoader}.
 */
public class ImageLoaderImpl implements ImageLoader {

	private static String RESOURCE_PATH = "images";
	private final HashMap<String, BufferedImage> imagesMap = new HashMap<String, BufferedImage>();

	private GameSpecs gameSpecs = new GameSpecs();

	private FileSystem fileSystem = null;

	/**
	 * Set up the loader.
	 */
	public ImageLoaderImpl() {
		loadAllImages(RESOURCE_PATH);

		if (fileSystem != null) {
			try {
				fileSystem.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Loads all of the images in the {@link #RESOURCE_PATH}. I took inspiration
	 * from:
	 * https://stackoverflow.com/questions/66341081/not-able-to-read-resource-files-from-src-test-resources-in-sts
	 * 
	 * @param path the path where are the images
	 */
	private void loadAllImages(String path) {
		URI uri = null;
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			uri = classLoader.getResource(path).toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		Path myPath = null;
		if (uri != null) {
			// checking if the code is in a jar
			if (uri.getScheme().equals("jar")) {
				try {
					if (this.fileSystem == null) {
						this.fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				myPath = this.fileSystem.getPath(path);
			} else {
				myPath = Paths.get(uri);
			}
		}
		Stream<Path> walk = null;
		try {
			walk = Files.walk(myPath, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (walk != null) {
			// loading all the images
			for (Iterator<Path> it = walk.iterator(); it.hasNext();) {
				Path analizedFile = it.next().getFileName();
				String file = null;
				if (analizedFile != null) {
					file = analizedFile.toString();

					Path p = Paths.get(path).getFileName();

					if (isPathDirectory(file)) {
						// checks if the the analized file is not the current file
						if (!file.equals(p.toString())) {
							loadAllImages(path + "/" + file);
						}
					} else {
						loadImage(path + "/" + file);
					}
				}
			}
		}

	}

	/**
	 * Returns true if the given path is a directory
	 * 
	 * @param myPath the path to be analyzed
	 * @return true if the given path is a directory
	 */
	private boolean isPathDirectory(String myPath) {
		File test = new File(myPath);

		if (!test.exists()) {
			return test.getName().lastIndexOf('.') == -1;
		} else {
			return test.isDirectory();
		}
	}

	/**
	 * Loads a requested file.
	 * 
	 * @param file that is requested
	 */
	private void loadImage(final String file) {
		Path p = Paths.get(file).getFileName();
		String myFile = "";
		if (p != null) {
			myFile = p.toString();
		}
		final InputStream in = ClassLoader.getSystemResourceAsStream(file);

		try {
			this.imagesMap.put(myFile.replaceFirst("[.][^.]+$", ""), ImageIO.read(in));
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** {@inheritDoc} */
	@Override
	public BufferedImage getImageByEntity(final Entity entity, Size CanvasDimension) throws FileNotFoundException {
		if (CanvasDimension.getWidth() == 0 || CanvasDimension.getHeight() == 0) {
			CanvasDimension = this.gameSpecs.getGameUnits();
		}

		String entityName = entity.getEntityName().toLowerCase();

		if (!this.imagesMap.containsKey(entityName)) {
			throw new FileNotFoundException("The given entity (" + entity + ") has no image in the 'res' folder");
		}

		Size newImageDimension = getNewImageDimension(CanvasDimension, entity.getBodyDimension());
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
	public BufferedImage getImageByName(final String imageName, final Size CanvasDimension)
	        throws FileNotFoundException {
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
	private Size getNewImageDimension(final Size canvasDimension, final Size originalImageDimension) {
		int newX = (int) ((double) canvasDimension.getWidth()
		        / ((double) this.gameSpecs.getGameUnits().getWidth() / (double) originalImageDimension.getWidth()));
		int newY = (int) ((double) canvasDimension.getHeight()
		        / ((double) this.gameSpecs.getGameUnits().getHeight() / (double) originalImageDimension.getHeight()));

		return new Size(newX, newY);
	}

	/**
	 * Scale an image to a new dimension. This code was taken by
	 * https://stackoverflow.com/questions/4216123/how-to-scale-a-bufferedimage
	 * 
	 * @param src          the image that has to be scaled
	 * @param newDimension the new dimension of the image
	 * @return the scaled image
	 */
	private BufferedImage scaleImage(final BufferedImage src, final Size newDimension) {
		final BufferedImage resizedImg = new BufferedImage((int) newDimension.getWidth(),
		        (int) newDimension.getHeight(), BufferedImage.TRANSLUCENT);

		final Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(src, 0, 0, (int) newDimension.getWidth(), (int) newDimension.getHeight(), null);
		g2.dispose();
		return resizedImg;
	}

}
