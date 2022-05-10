package main.java.tas.view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.beans.Introspector;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

import main.java.tas.utils.GameSpecs;
import main.java.tas.model.Entity;

/**
 * Class that implements an {@link ImageLoader}
 */
public class ImageLoaderImpl implements ImageLoader {
    
    private final String RESOURCE_PATH = "res" + System.getProperty("file.separator") + "images";
    private final HashMap<String, BufferedImage> imagesMap = new HashMap<String, BufferedImage>();
    
    /**
     * Set up the loader
     */
    public ImageLoaderImpl() {
        loadAllImages();
    }
    
    /**
     * Loads all of the images in the {@link #RESOURCE_PATH}
     */
    private void loadAllImages() {
        try (Stream<Path> paths = Files.walk(Paths.get(RESOURCE_PATH))) {
            paths
                .filter(Files::isRegularFile)
                .forEach(filePath -> loadImage(filePath.toFile()));
        } catch (IOException e) {
            System.out.println(e);
        } 
    }
    
    /**
     * Loads a requested file
     * @param file that is requested
     */
    private void loadImage(File file) {
        try {
            this.imagesMap.put(file.getName().replaceFirst("[.][^.]+$", ""), ImageIO.read(file));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public BufferedImage getImageByEntity(Entity entity, Dimension CanvasDimension) throws FileNotFoundException {
        if (CanvasDimension.width == 0 || CanvasDimension.height == 0) {
            CanvasDimension = GameSpecs.GAME_UNITS;
        }
        
        String entityName = Introspector.decapitalize(entity.getClass().getSimpleName());
        if (!this.imagesMap.containsKey(entityName)) {
            throw new FileNotFoundException("The given entity (" + entity + ") has no image in the 'res' folder");
        }
        
        Dimension newImageDimension = getNewImageDimension(CanvasDimension, entity.getBodyDimension());
        return scaleImage(this.imagesMap.get(entityName),
                newImageDimension);
    }
    
    /**
     * Gets the dimension of the new image
     * @param canvasDimension the actual dimension of the canvas
     * that contain the image
     * @param originalImageDimension the original dimension of the image
     * @return the new dimension of the image
     */
    private Dimension getNewImageDimension(Dimension canvasDimension, Dimension originalImageDimension) {
        int newX = (int)((double)canvasDimension.width / ((double)GameSpecs.GAME_UNITS.width / (double)originalImageDimension.width));
        int newY = (int)((double)canvasDimension.height / ((double)GameSpecs.GAME_UNITS.height / (double)originalImageDimension.height));
        
        return new Dimension(newX, newY);
    }
    
    /**
     * Scale an image to a new dimension
     * @param src the image that has to be scaled
     * @param newDimension the new dimension of the image
     * @return the scaled image
     */
    private BufferedImage scaleImage(BufferedImage src, Dimension newDimension) {
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
