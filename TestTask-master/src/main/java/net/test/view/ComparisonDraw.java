package net.test.view;

import net.test.model.ComparisonModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import static net.test.controller.ComparisonController.readImageFromResources;
import static net.test.controller.ComparisonTools.*;

public class ComparisonDraw {

    private static final int COUNTER = 2;
    public static int threshold = 5;

    public static BufferedImage drawTheDifference(String nameFirstImg, String nameSecondImg) throws IOException, URISyntaxException {
        BufferedImage bufferedImage1 = readImageFromResources(nameFirstImg);
        BufferedImage bufferedImage2 = readImageFromResources(nameSecondImg);

        // check images for valid
        checkCorrectImage(bufferedImage1, bufferedImage2, nameFirstImg, nameSecondImg);
        BufferedImage outImg = deepCopy(bufferedImage2);

        Graphics2D graphics = outImg.createGraphics();
        graphics.setColor(Color.RED);

        int[][] matrix = populateTheMatrixOfTheDifferences(bufferedImage1, bufferedImage2);
        int lastNumberCount = groupRegions(matrix);
        drawRectangles(matrix, graphics, COUNTER, lastNumberCount);
        return outImg;
    }

    public static int[][] populateTheMatrixOfTheDifferences(BufferedImage image1, BufferedImage image2) {
        int[][] matrix = new int[image1.getWidth()][image1.getHeight()];
        for (int y = 0; y < image1.getHeight(); y++) {
            for (int x = 0; x < image1.getWidth(); x++) {
                matrix[x][y] = isDifferent(x, y, image1, image2) ? 1 : 0;
            }
        }
        return matrix;
    }

    private static int groupRegions(int[][] matrix) {
        int regionCount = COUNTER;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 1) {
                    joinToRegion(matrix, row, col, regionCount);
                    regionCount++;
                }
            }
        }
        return regionCount;
    }

    private static void joinToRegion(int[][] matrix, int row, int col, int regionCount) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length || matrix[row][col] != 1) return;

        matrix[row][col] = regionCount;

        for (int i = 0; i < threshold; i++) {
            // goes to all directions.
            joinToRegion(matrix, row - 1 - i, col, regionCount);
            joinToRegion(matrix, row, col + 1 + i, regionCount);
            joinToRegion(matrix, row + 1 + i, col, regionCount);
            joinToRegion(matrix, row, col - 1 - i, regionCount);

            joinToRegion(matrix, row - 1 - i, col - 1 - i, regionCount);
            joinToRegion(matrix, row - 1 - i, col + 1 + i, regionCount);
            joinToRegion(matrix, row + 1 + i, col + 1 + i, regionCount);
            joinToRegion(matrix, row + 1 + i, col - 1 - i, regionCount);
        }
    }

    private static void drawRectangles(int[][] matrix, Graphics2D graphics, int counter, int lastNumberCount) {
        if (counter > lastNumberCount) return;
        ComparisonModel comparisonModel = createRectangle(matrix, counter);
        graphics.drawRect(comparisonModel.getMinY(), comparisonModel.getMinX(), comparisonModel.getWidth(), comparisonModel.getHeight());
        drawRectangles(matrix, graphics, ++counter, lastNumberCount);
    }
}
