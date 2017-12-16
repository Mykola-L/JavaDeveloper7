package net.test;

import net.test.view.ComparisonDraw;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import static net.test.controller.ComparisonController.saveImage;

public class AppRunner {
    public static void main(String[] args) throws IOException, URISyntaxException{
        BufferedImage bufferedImage = ComparisonDraw.drawTheDifference("image1.png", "image2.png");
        saveImage("comparison/comparison.png", bufferedImage);
    }
}
