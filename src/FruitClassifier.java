import OpenCV.Utils;
import colorthief.ColorThief;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.opencv.core.Core;

public class FruitClassifier {

    private static Utils util;      // openCV utilities
    private static final String imagePath = "./ImagesDir/images";

    public static void main(String args[]) throws Exception {
        BufferedImage img = null;
        img = ImageIO.read(new File("./ImagesDir/images/apple2.png"));
        int[] result = ColorThief.getColor(img);
        Color col = new Color(result[0], result[1], result[2]);
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        util = new Utils();
        
        if(new File(imagePath).exists()){
            // generating edges for all images in directory
            generateEdges(imagePath);
        }
        else {
            System.out.println(imagePath + " does not exist!");
        }
        
        
        
    }
    
    private static void generateEdges(String path) {
        util.generateEdges(path);
    }
    
}