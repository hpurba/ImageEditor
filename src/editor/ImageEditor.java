package editor;

/*
 THIS IS AN IMAGE EDITOR PROGRAM MADE BY HIKARU PURBA FOR CS 240 WINTER 2020 SEMESTER

 Command Line Syntax
 java ImageEditor inputFileName outputFileName {grayscale|invert|emboss|motionblur blurLength}

 blurLength is a non-negative integer. It is usually less than 25
 */

import java.io.*;
import java.util.Scanner;

public class ImageEditor {

    public static void main(String[] args) {
        // CORRECT INPUT GIVEN
        try {
            // Just testing Printing the arguments
            System.out.println("ARGUMENTS: " + args[0] + " " + args[1] + " " + args[2]);

            //Grab everything from input file
            File file = new File(args[0]);
            Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(args[0])));
            scanner.useDelimiter("((#[^\\n]*\\n)|(\\s+))+");

            //start reading the data
            scanner.next();                         // skip inputImage format, we already know its P3
            int width = scanner.nextInt();
            int height = scanner.nextInt();
            int maxColorValue = scanner.nextInt();  // Always 255

            // Image outputImage = new Image();    // Make output Image
            Pixel[][] pixel2DArray = new Pixel[height][width]; // size the 2D array for modified pixels

            // Grab all the pixels and put them into my pixel2DArray
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Pixel newPixel = new Pixel();
                    newPixel.setRed(scanner.nextInt());
                    newPixel.setGreen(scanner.nextInt());
                    newPixel.setBlue(scanner.nextInt());
                    pixel2DArray[i][j] = newPixel;
                }
            }
            // This is just testing making a new Image
            Image inputImage = new Image();
            inputImage.setPixelMap(pixel2DArray);
            scanner.close();                        // close the scanner

            // Output Image and Pixel 2Darray
            Image outputImage = new Image();
            Pixel[][] outputPixels = new Pixel[height][width];

            // switch case on {grayscale|invert|emboss|motionblur blurLength}
            switch (args[2]) {
                case "invert":  // Do 255 - color value in each pixel
                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width; j++) {
                            Pixel tempPixel = new Pixel();
                            tempPixel.setRed(maxColorValue - inputImage.getPixelMap()[i][j].getRed());
                            tempPixel.setGreen(maxColorValue - inputImage.getPixelMap()[i][j].getGreen());
                            tempPixel.setBlue(maxColorValue - inputImage.getPixelMap()[i][j].getBlue());
                            outputPixels[i][j] = tempPixel;
                        }
                    }
                    outputImage.setPixelMap(outputPixels);  // Sets the pixel map with inverted pixelArray
                    System.out.println("Invert Complete!");
                    break;
                case "grayscale":  // Do average of RGB-color values in each pixel
                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width; j++) {
                            Pixel newPixel = new Pixel();
                            int averageColorValue = (inputImage.getPixelMap()[i][j].getRed() +
                                    inputImage.getPixelMap()[i][j].getGreen() +
                                    inputImage.getPixelMap()[i][j].getBlue()) / 3;
                            newPixel.setRed(averageColorValue);
                            newPixel.setGreen(averageColorValue);
                            newPixel.setBlue(averageColorValue);
                            outputPixels[i][j] = newPixel;
                        }
                    }
                    outputImage.setPixelMap(outputPixels);
                    System.out.println("Grayscale Complete!");
                    break;
                case "motionblur":
                    int blurLength = Integer.parseInt(args[3]);
                    // n must be greater than 0!
                    if (blurLength <= 0){
                        System.out.println("Error! Usage: java ImageEditor in-file out-file {grayscale|invert|emboss|motionblur motion-blur-length}");
                        break;
                    }
                    else {
                        // Go through each row of pixels
                        for (int i = 0; i < height; i++){
                            // For each pixel in the row (column)
                            for (int j = 0; j < width; j++){
                                Pixel newPixel = new Pixel();
                                int sumRed = 0;
                                int sumGreen = 0;
                                int sumBlue = 0;
                                int length = 0;
                                // Adds all the pixel RGB Values up for the blurlength
                                for (int k = j; (k < width) && (k <= j + blurLength -1); k++, length++){
                                    sumRed +=  inputImage.getPixelMap()[i][k].getRed();
                                    sumGreen += inputImage.getPixelMap()[i][k].getGreen();
                                    sumBlue += inputImage.getPixelMap()[i][k].getBlue();
                                }
                                int avgRed = sumRed / length;
                                int avgGreen = sumGreen / length;
                                int avgBlue = sumBlue / length;
                                newPixel.setRed(avgRed);
                                newPixel.setGreen(avgGreen);
                                newPixel.setBlue(avgBlue);
                                outputPixels[i][j] = newPixel;
                            }
                        }
                        outputImage.setPixelMap(outputPixels);
                    }
                    System.out.println("motionblur Complete!");
                    break;

                case "emboss":
                    final int SPECIAL_NUM_128 = 128;
                    for (int i = 0; i < height; i++){
                        for (int j = 0; j < width; j++){
                            //TOP OR LEFT BOARDER
                            if ((i == 0) || (j == 0)){
                                Pixel newPixel = new Pixel();
                                newPixel.setBlue(SPECIAL_NUM_128);
                                newPixel.setGreen(SPECIAL_NUM_128);
                                newPixel.setRed(SPECIAL_NUM_128);
                                outputPixels[i][j] = newPixel;
                            }
                            // EMBOSS Algorithm NORMALLY
                            else{
                                Pixel newPixel = new Pixel();
                                int redDiff = inputImage.getPixelMap()[i][j].getRed() - inputImage.getPixelMap()[i-1][j-1].getRed();
                                int greenDiff = inputImage.getPixelMap()[i][j].getGreen() - inputImage.getPixelMap()[i-1][j-1].getGreen();
                                int blueDiff = inputImage.getPixelMap()[i][j].getBlue() - inputImage.getPixelMap()[i-1][j-1].getBlue();
                                // madDifference will use Math.max to grab the larges number
                                int maxDifference = Math.max(Math.abs(redDiff), Math.max(Math.abs(greenDiff), Math.abs(blueDiff)));

                                // This will put back our pos or negative
                                if (maxDifference == Math.abs(redDiff)){
                                    maxDifference = redDiff;
                                }
                                else if (maxDifference == Math.abs(greenDiff)){
                                    maxDifference = greenDiff;
                                }
                                else{
                                    maxDifference = blueDiff;
                                }

                                int finalPixelRGBVal;
                                finalPixelRGBVal = SPECIAL_NUM_128 + maxDifference;
                                if (finalPixelRGBVal < 0){
                                    finalPixelRGBVal = 0;
                                }
                                else if (finalPixelRGBVal > 255) {
                                    finalPixelRGBVal = 255;
                                }
                                else {
                                    // Do nothing, use the calculated value "finalPixelRGBVal"
                                }
                                newPixel.setRed(finalPixelRGBVal);
                                newPixel.setGreen(finalPixelRGBVal);
                                newPixel.setBlue(finalPixelRGBVal);
                                outputPixels[i][j] = newPixel;
                            }
                        }
                    }
                    outputImage.setPixelMap(outputPixels);
                    System.out.println("emboss Complete!");
                    break;
                // Incorrect Input Handler
                default: // Error
                    System.out.println("ERROR! java ImageEditor in-file out-file"
                            + " {grayscale|invert|emboss|motionblur blurLength}");
                    break;
            }

            // Output Image
            StringBuilder myOutputString = new StringBuilder();
            PrintWriter myWriter = new PrintWriter(new BufferedWriter(new FileWriter(args[1])));
            myOutputString.append("P3\n" + width + " " + height + "\n" + maxColorValue + "\n");
            for (int i = 0; i < height; i++){
                for (int j = 0; j < width; j++){
                    myOutputString.append(outputImage.getPixelMap()[i][j].getRed());
                    myOutputString.append(" ");
                    myOutputString.append(outputImage.getPixelMap()[i][j].getGreen());
                    myOutputString.append(" ");
                    myOutputString.append(outputImage.getPixelMap()[i][j].getBlue());
                    myOutputString.append("  ");
                }
                myOutputString.append("\n");
            }
            myWriter.write(myOutputString.toString());
            myWriter.flush();
            myWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // IF TERMINAL INPUT IS INCORRECT
        catch (Exception exception){
            System.out.println("ERROR! java ImageEditor in-file out-file" + " {grayscale|invert|emboss|motionblur blurLength}");
        }

    }

}
