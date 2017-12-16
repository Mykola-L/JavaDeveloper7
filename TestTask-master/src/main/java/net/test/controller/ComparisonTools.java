package net.test.controller;

import net.test.model.ComparisonModel;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ComparisonTools {

    public static BufferedImage deepCopy(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        WritableRaster raster = image.copyData(null);
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static void checkCorrectImage(BufferedImage imgOne, BufferedImage imgSecond, String nameFirstImg, String nameSecondImg) {
        if (imgOne.getHeight() != imgSecond.getHeight() ||
                imgOne.getWidth() != imgSecond.getWidth() ||
                !ComparisonController.getExtension(nameFirstImg).equalsIgnoreCase(ComparisonController.getExtension(nameSecondImg))) {
            throw new IllegalArgumentException("Images dimensions mismatch.");
        }
    }

    public static boolean isDifferent(int x, int y, BufferedImage image1, BufferedImage image2) {
        final int rgb = 3;
        boolean result = false;

        int[] pixelImg1 = image1.getRaster().getPixel(x, y, new int[rgb]);
        int[] pixelImg2 = image2.getRaster().getPixel(x, y, new int[rgb]);

        double mod1 = Math.sqrt(pixelImg1[0] * pixelImg1[0] + pixelImg1[1] * pixelImg1[1] + pixelImg1[2] * pixelImg1[2]);
        double mod2 = Math.sqrt(pixelImg2[0] * pixelImg2[0] + pixelImg2[1] * pixelImg2[1] + pixelImg2[2] * pixelImg2[2]);

        double mod3 = Math.sqrt(
                Math.abs(pixelImg1[0] - pixelImg2[0]) * Math.abs(pixelImg1[0] - pixelImg2[0]) +
                        Math.abs(pixelImg1[1] - pixelImg2[1]) * Math.abs(pixelImg1[1] - pixelImg2[1]) +
                        Math.abs(pixelImg1[2] - pixelImg2[2]) * Math.abs(pixelImg1[2] - pixelImg2[2]));
        double changePixelImage1 = mod3 / mod1;
        double changePixelImage2 = mod3 / mod2;
        if (changePixelImage1 > 0.1 && changePixelImage2 > 0.1) result = true;
        return result;
    }

    public static ComparisonModel createRectangle(int[][] matrix, int counter) {
        ComparisonModel rectangle = new ComparisonModel();

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                if (matrix[y][x] == counter) {
                    if (x < rectangle.getMinX()) rectangle.setMinX(x);
                    if (y < rectangle.getMinY()) rectangle.setMinY(y);

                    if (x > rectangle.getMaxX()) rectangle.setMaxX(x);
                    if (y > rectangle.getMaxY()) rectangle.setMaxY(y);
                }
            }
        }
        return rectangle;
    }
}
