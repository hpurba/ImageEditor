package editor;

public class Image {

    // Variables
    private int width;
    private int height;
    private Pixel[][] pixelMap; // Private 2D array of pixels

    // Initialization without input variables
    public Image(){}

    // Initialization with input width and height
    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixelMap = new Pixel[width][height];
    }

    // Set the pixel map for the image
    public void setPixelMap(Pixel[][] pixelMap) {
        this.pixelMap = pixelMap;
    }

    // Return the 2D pixel Array
    public Pixel[][] getPixelMap() { return pixelMap; }
}
