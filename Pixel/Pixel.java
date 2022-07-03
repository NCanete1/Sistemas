package Trabajo3.Pixel;

import java.awt.Color;
import java.awt.Robot;
import java.awt.AWTException;

import java.util.Timer;
import java.util.TimerTask;

public class Pixel {
    public String PixelColor(int x, int y) {
        String colorRGB = "";
        
        try {
            Robot robot = new Robot();

            // The pixel color information at 20, 20
            Color color = robot.getPixelColor(x, y);

           
            colorRGB = "[" +color.getRed() + "," + color.getGreen() + "," + color.getBlue()+"]";
            //colorRGB = String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
            // b);

        } catch (AWTException e) {
            e.printStackTrace();
        }
        return colorRGB;
    }

    public int RedColor(int x, int y) {
        int colorRed=0;
        try {
            Robot robot = new Robot();
            Color color = robot.getPixelColor(x, y);
            colorRed = color.getRed();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return colorRed;
    }
    public int GreenColor(int x, int y) {
        int colorGreen=0;
        try {
            Robot robot = new Robot();
            Color color = robot.getPixelColor(x, y);
            colorGreen = color.getGreen();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return colorGreen;
    }
    public int BlueColor(int x, int y) {
        int colorBlue=0;
        try {
            Robot robot = new Robot();
            Color color = robot.getPixelColor(x, y);
            colorBlue = color.getBlue();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return colorBlue;
    }
}
