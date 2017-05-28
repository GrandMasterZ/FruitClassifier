import colorthief.ColorThief;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class FruitClassifier {


    public static void main(String args[]) throws Exception {
        BufferedImage img = null;
        img = ImageIO.read(new File("./ImagesDir/apple2.png"));
        int[] result = ColorThief.getColor(img);
        Color col = new Color(result[0], result[1], result[2]);
    }
}