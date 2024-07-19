
import java.awt.Color;

public class Collage {

    private Picture originalPicture;
    private Picture collagePicture;
    private int collageDimension;
    private int tileDimension;
    
    public Collage (String filename) {
        collageDimension = 4;
        tileDimension = 150;
        originalPicture = new Picture(filename);
        collagePicture = new Picture(tileDimension * collageDimension, tileDimension * collageDimension);
        
        scale(originalPicture, collagePicture);
        // didn't do number 4 yet. SLIDE 67 from week 10

        // WRITE YOUR CODE HERE
    }
    public Collage (String filename, int td, int cd) {
        this.collageDimension = cd;
        this.tileDimension = td;
        originalPicture = new Picture(filename);
        collagePicture = new Picture(tileDimension * collageDimension, tileDimension * collageDimension);
        scale(originalPicture, collagePicture);
    }
    public static void scale (Picture source, Picture target) {
        int originalWidth = source.width();
        int originalHeight = source.height();
        int targetWidth = target.width();
        int targetHeight = target.height();
        for (int targetCol = 0; targetCol < originalWidth; targetCol++) {
            for (int targetRow = 0; targetRow < originalHeight; targetRow++) {
                int sourceCol = targetCol * originalWidth / targetWidth;
                int sourceRow = targetRow * originalHeight / targetHeight;
                Color color = source.get(sourceCol, sourceRow);
                target.set(targetCol, targetRow, color);
            }
        }
        source.show();
        target.show();
    }
    public int getCollageDimension() {
        return collageDimension;
    }
    public int getTileDimension() {
        return tileDimension;
    }
    public Picture getOriginalPicture() {
        return originalPicture;
    }
    public Picture getCollagePicture() {
        return collagePicture;
    }
    public void showOriginalPicture() {
        originalPicture.show();
    }
    public void showCollagePicture() {
        collagePicture.show();
    }
    public void makeCollage () {
        
        // WRITE YOUR CODE HERE
    }
    public void colorizeTile (String component,  int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE
    }
    public void replaceTile (String filename,  int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE
    }
    public void grayscaleTile (int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE
    }
    private static double intensity(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        if (r == g && r == b) return r;   // to avoid floating-point issues
        return 0.299*r + 0.587*g + 0.114*b;
    }
    private static Color toGray(Color color) {
        int y = (int) (Math.round(intensity(color)));   // round to nearest int
        Color gray = new Color(y, y, y);
        return gray;
    }
    public void closeWindow () {
        if ( originalPicture != null ) {
            originalPicture.closeWindow();
        }
        if ( collagePicture != null ) {
            collagePicture.closeWindow();
        }
    }
}

