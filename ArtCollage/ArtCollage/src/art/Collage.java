package art;
import java.awt.Color;

/*
 * This class contains methods to create and perform operations on a collage of images.
 * 
 * @author Ana Paula Centeno
 */ 

public class Collage {

    // The orginal picture
    private Picture originalPicture;

    // The collage picture is made up of tiles.
    // Each tile consists of tileDimension X tileDimension pixels
    // The collage picture has collageDimension X collageDimension tiles
    private Picture collagePicture;

    // The collagePicture is made up of collageDimension X collageDimension tiles
    // Imagine a collagePicture as a 2D array of tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    // Imagine a tile as a 2D array of pixels
    // A pixel has three components (red, green, and blue) that define the color 
    // of the pixel on the screen.
    private int tileDimension;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 150
     * 2. initializes originalPicture with the filename image
     * 3. initializes collagePicture as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see constructors for the Picture class).
     * 4. update collagePicture to be a scaled version of original (see scaling filter on Week 9 slides)
     * 
     * @param filename the image filename
     */
    public Collage (String filename) {
        this.collageDimension = 4;
        this.tileDimension = 150;
        originalPicture = new Picture(filename);
        // originalPicture.show();
        collagePicture = new Picture(tileDimension * collageDimension, tileDimension * collageDimension);
        // collagePicture.show();
        scale(originalPicture, collagePicture);

        // WRITE YOUR CODE HERE
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes originalPicture with the filename image
     * 3. initializes collagePicture as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collagePicture to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */    
    public Collage (String filename, int td, int cd) {
        this.collageDimension = cd;
        this.tileDimension = td;
        originalPicture = new Picture(filename);
        collagePicture = new Picture(tileDimension * collageDimension, tileDimension * collageDimension);
        scale(originalPicture, collagePicture);
        // number 4 still isn't done and idk about number 3.
        // WRITE YOUR CODE HERE
    }


    /*
     * Scales the Picture @source into Picture @target size.
     * In another words it changes the size of @source to make it fit into
     * @target. Do not update @source. 
     *  
     * @param source is the image to be scaled.
     * @param target is the 
     */
    public static void scale (Picture source, Picture target) {
        int width = source.width();
        int height = source.height();
        int targetWidth = target.width();
        int targetHeight = target.height();

        for (int targetCol = 0; targetCol < targetWidth; targetCol++) {
            for (int targetRow = 0; targetRow < targetHeight; targetRow++) {
                int sourceCol = targetCol * width / targetWidth;
                int sourceRow = targetRow * height / targetHeight;
                Color color = source.get(sourceCol, sourceRow);
                target.set(targetCol, targetRow, color);
            }
        }
        source.show();
        target.show();
        // WRITE YOUR CODE HERE
    }

     /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */   
    public int getCollageDimension() {
        return collageDimension;
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */    
    public int getTileDimension() {
        return tileDimension;
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    
    public Picture getOriginalPicture() {
        return originalPicture;
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    
    public Picture getCollagePicture() {
        return collagePicture;
    }

    /*
     * Display the original image
     * Assumes that original has been initialized
     */    
    public void showOriginalPicture() {
        originalPicture.show();
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */    
    public void showCollagePicture() {
        collagePicture.show();
    }

    /*
     * Updates collagePicture to be a collage of tiles from original Picture.
     * collagePicture will have collageDimension x collageDimension tiles, 
     * make a tile of tileDimension x tileDimension pixels by scaling down the original picture.
     * update the collage picture to be a collage of these tiles. Meaning, each tile is repeated in the image.
     * the collage picture will have collageDimension x collageDimension tiles.
     */    
    public void makeCollage () {
        for (int i = 0; i < collageDimension; i++) {
            for (int j = 0; j < collageDimension; j++) {
                for (int tcol = 0; tcol<tileDimension; tcol++) {
                    for (int trow = 0; trow < tileDimension; trow++) {
                        int scol = ((tcol * originalPicture.width()) / (tileDimension));
                        int srow = ((trow * originalPicture.height()) / (tileDimension));
                        Color color = originalPicture.get(scol, srow);
                        collagePicture.set(tcol + (i * tileDimension), trow + (j * tileDimension), color);
                    }
                }
            }
        }
        // WRITE YOUR CODE HERE
    }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     * 1. get the pixel's color green intensity.
     * 2. update/set the pixel with only it's green component.
     * Color color = picture.get(col, row);
     * int g = color.getGreen();
     * color.set(col, row, new Color(0, g, 0));
     */
    public void colorizeTile (String component,  int collageCol, int collageRow) {
        int tileDim = getTileDimension();
        for (int col = 0; col < tileDim; col++) {
            for (int row = 0; row < tileDim; row++) {
                Color color = collagePicture.get(collageCol, collageRow);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                collagePicture.set(col, row, new Color(r, 0, 0));
                collagePicture.set(col, row, new Color(0, g, 0));
                collagePicture.set(col, row, new Color(0,0,b));
            }
        // WRITE YOUR CODE HERE
        }
    }
    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {  
        Picture tile = new Picture(filename);
        for (int col = collageCol * tileDimension; col < (collageCol + 1) * tileDimension; col++) {
            for (int row = collageRow * tileDimension; row < (collageCol + 1) * tileDimension; row++) {
                Color color = tile.get(col % tileDimension, row % tileDimension);
                collagePicture.set(col, row, color);
            }
        }
        // WRITE YOUR CODE HERE
    }

    /*
     * Grayscale tile at (collageCol, collageRow)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     * colorizes a tile in the collage picture using red, green, or blue component.
     * to colorize a tile "green" do for every pixel:
     * update the pixel's color of a tile ot have the grayscale value computed by applying the toGray() mtehod provided.
     */
    public void grayscaleTile (int collageCol, int collageRow) {
        for (int col = collageCol * tileDimension; col < (collageCol + 1) * tileDimension; col++) {
            for (int row = collageRow * tileDimension; row < (collageRow + 1) * tileDimension; row++) {
                Color color = collagePicture.get(col, row);
                Color gray = toGray(color);
                collagePicture.set(col, row, gray);
            }
        }
        // WRITE YOUR CODE HERE 
    }

    /**
     * Returns the monochrome luminance of the given color as an intensity
     * between 0.0 and 255.0 using the NTSC formula
     * Y = 0.299*r + 0.587*g + 0.114*b. If the given color is a shade of gray
     * (r = g = b), this method is guaranteed to return the exact grayscale
     * value (an integer with no floating-point roundoff error).
     *
     * @param color the color to convert
     * @return the monochrome luminance (between 0.0 and 255.0)
     */
    private static double intensity(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        if (r == g && r == b) return r;   // to avoid floating-point issues
        return 0.299*r + 0.587*g + 0.114*b;
    }

    /**
     * Returns a grayscale version of the given color as a {@code Color} object.
     *
     * @param color the {@code Color} object to convert to grayscale
     * @return a grayscale version of {@code color}
     */
    private static Color toGray(Color color) {
        int y = (int) (Math.round(intensity(color)));   // round to nearest int
        Color gray = new Color(y, y, y);
        return gray;
    }

    /*
     * Closes the image windows
     */
    public void closeWindow () {
        if ( originalPicture != null ) {
            originalPicture.closeWindow();
        }
        if ( collagePicture != null ) {
            collagePicture.closeWindow();
        }
    }
}

