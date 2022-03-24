package main.java.view;

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

import javax.imageio.ImageIO;

import main.java.model.Entity;

public class ImageLoaderImpl implements ImageLoader {
    
    private static final int DEFAULT_RESOLUTION = 720;  //TODO: forse sarebbe meglio fare una classe a parte per sta roba, perche viene usata come magic number in GameView
    private static final int DEFAULT_IMAGE_SIZE = 100;
    private static final String RESOURCE_PATH = "src" + System.getProperty("file.separator") + "main" + System.getProperty("file.separator") + "resources";
    HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
    
    public ImageLoaderImpl() {
        loadAllImages();
    }
    
    private void loadAllImages() {
        try (Stream<Path> paths = Files.walk(Paths.get(RESOURCE_PATH))) {
            paths
                .filter(Files::isRegularFile)
                .forEach(filePath -> loadImage(filePath.toFile()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
    
    private void loadImage(File file) {
        try {
            this.images.put(file.getName().replaceFirst("[.][^.]+$", ""), ImageIO.read(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getImageByEntity(Entity entity, Dimension CanvasDimension) {
        if (CanvasDimension.width == 0 || CanvasDimension.height == 0) {
            CanvasDimension.width = DEFAULT_RESOLUTION;
            CanvasDimension.height  = DEFAULT_RESOLUTION;
        }
        return scale(images.get(Introspector.decapitalize(entity.getClass().getSimpleName())), getNewImageDimension(CanvasDimension.width), getNewImageDimension(CanvasDimension.height));
    }
    
    private int getNewImageDimension(int dimension) {
        return (int)((double)dimension/((double)DEFAULT_RESOLUTION/(double)DEFAULT_IMAGE_SIZE));
    }
    
    private BufferedImage scale(BufferedImage src, int newWidth, int newHeight) {
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
