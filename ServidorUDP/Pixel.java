
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

           
            colorRGB = color.getRed() + "," + color.getGreen() + "," + color.getBlue();
            //colorRGB = String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
            // b);

        } catch (AWTException e) {
            e.printStackTrace();
        }
        return colorRGB;
    }

    public String RedColor(int x, int y) {
        String colorRed="";
        try {
            Robot robot = new Robot();
            Color color = robot.getPixelColor(x, y);
            colorRed = Integer.toString(color.getRed());
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return colorRed;
    }
    public String GreenColor(int x, int y) {
        String colorGreen="";
        try {
            Robot robot = new Robot();
            Color color = robot.getPixelColor(x, y);
            colorGreen = Integer.toString(color.getGreen());
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return colorGreen;
    }
    public String BlueColor(int x, int y) {
        String colorBlue="";
        try {
            Robot robot = new Robot();
            Color color = robot.getPixelColor(x, y);
            colorBlue = Integer.toString(color.getBlue());
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return colorBlue;
    }
}
