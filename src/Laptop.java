public class Laptop {

    //Value of the laptop
    private int value;
    // x coordinate of the laptop
    private double x;
    // y coordinate of the laptop
    private double y;
    //wireless range of the laptop
    private double wirelessTransmissionRange;

    public Laptop(int value, double x, double y, double wirelessTransmissionRange) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.wirelessTransmissionRange = wirelessTransmissionRange;
    }

    public int getValue() {
        return value;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWirelessTransmissionRange() {
        return wirelessTransmissionRange;
    }

}
