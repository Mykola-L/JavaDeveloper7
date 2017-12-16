package net.test.controller;

import net.test.view.ComparisonDraw;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class ComparisonController {

    static String getExtension(String fileName) {
        int index = fileName.lastIndexOf('.') + 1;
        return index == -1 ? null : fileName.substring(index);
    }

    public static BufferedImage readImageFromResources(String path) throws URISyntaxException, IOException {
        return ImageIO.read(new File(ComparisonDraw.class.getClassLoader().getResource(path).toURI().getPath()));
    }

    public static void saveImage(String path, BufferedImage image) throws IOException {
        new File(path).mkdirs();
        ImageIO.write(image, getExtension(path), new File(path));
    }
}
