package OpenCV;

import java.io.File;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Tomas
 */
public class Utils {
    
    private static Mat src; // image source
    private static int count = 0;
    
    public Utils() {}
    
    
    /*
        Generate edges of all files in directory
    */
    public void generateEdges(String path) {
        
        File imgFolder = new File(path);
        
        if(imgFolder.isDirectory()){
            for(final File image : imgFolder.listFiles()){
                // reading RGB image
                Mat rgbImage = Imgcodecs.imread(image.getAbsolutePath());

                // mat gray image holder
                Mat grayImage = new Mat();

                // mat canny image
                Mat cannyImage = new Mat();
                Mat detectedEdges = new Mat();

                // show rgb image
                Imgproc.cvtColor(rgbImage, grayImage, Imgproc.COLOR_BGR2GRAY);
                Imgproc.blur(grayImage, detectedEdges, new Size(3,3));
                Imgproc.Canny(detectedEdges, detectedEdges, 20, 150, 3, false);
                imwrite("ImagesDir/edges/" + count++ + ".png", detectedEdges);
            }
        }
        else {
            System.out.println("You need to provide path to directory of images!");
        }
    }
}
