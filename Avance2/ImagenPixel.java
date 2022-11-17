import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;

public class ImagenPixel {
  void generarImagen(int width, int height) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width/2)-width/2;
    int y = (screenSize.height/2)-height/2;
    File f = null; // file object
    Robot robot; //
    try {
      robot = new Robot();
      BufferedImage screen = robot.createScreenCapture(new Rectangle(x, y, width, height));
      f = new File("Output.jpg");
      ImageIO.write(screen, "jpg", f);
    } catch (IOException | AWTException e) {
      System.out.println("Error: " + e);
    }
  }

  void compresarImagen() throws IOException {
    File input = new File("Output.jpg");
    BufferedImage image = ImageIO.read(input);

    File compressedImageFile = new File("compress.jpg");
    OutputStream os = new FileOutputStream(compressedImageFile);

    Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
    ImageWriter writer = (ImageWriter) writers.next();

    ImageOutputStream ios = ImageIO.createImageOutputStream(os);
    writer.setOutput(ios);

    ImageWriteParam param = writer.getDefaultWriteParam();

    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    param.setCompressionQuality(0.02f);

    writer.write(null, new IIOImage(image, null, null), param);

    os.close();
    ios.close();
    writer.dispose();

  }

  void pixelGris() {
    BufferedImage img = null;
    File f = null;

    // read image
    try {
      f = new File("Output.jpg");
      img = ImageIO.read(f);
    } catch (IOException e) {
      System.out.println(e);
    }

    // get image width and height
    int width = img.getWidth();
    int height = img.getHeight();

    // convert to grayscale
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int p = img.getRGB(x, y);

        int a = (p >> 24) & 0xff;
        int r = (p >> 16) & 0xff;
        int g = (p >> 8) & 0xff;
        int b = p & 0xff;

        // calculate average
        int avg = (r + g + b) / 3;

        // replace RGB value with avg
        p = (a << 24) | (avg << 16) | (avg << 8) | avg;

        img.setRGB(x, y, p);
      }
    }
    // write image
    try {
      f = new File("Output.jpg");
      ImageIO.write(img, "jpg", f);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public void main(int width, int height) throws IOException {
    generarImagen(width, height);
    pixelGris();
    compresarImagen();
  }
}
