package passoff;


public class Pixel {
    private int red;
    private int green;
    private int blue;

    public Pixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return red == pixel.red &&
                green == pixel.green &&
                blue == pixel.blue;
    }
    @Override
    public String toString() {
        return "(" +
                "r=" + red +
                ", g=" + green +
                ", b=" + blue +
                ')';
    }




    // MY OWN CODE
//    public Pixel(){
//        red = 0;
//        green = 0;
//        blue = 0;
//    }

    public int getRed() {
        return red;
    }
    public void setRed(int red) {
        this.red = red;
    }
    public int getGreen() {
        return green;
    }
    public void setGreen(int green) {
        this.green = green;
    }
    public int getBlue() {
        return blue;
    }
    public void setBlue(int blue) {
        this.blue = blue;
    }
}
