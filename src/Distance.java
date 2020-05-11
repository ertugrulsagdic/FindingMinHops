public class Distance {

    //The distance between one laptop to other
    private double distance;
    private Laptop destinationLaptop;

    public Distance(Laptop destinationLaptop, Laptop sourceLaptop) {
        this.destinationLaptop = destinationLaptop;
        this.distance = calculateDistance(destinationLaptop, sourceLaptop);
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Laptop getDestinationLaptop() {
        return destinationLaptop;
    }

    public void setDestinationLaptop(Laptop destinationLaptop) {
        this.destinationLaptop = destinationLaptop;
    }

    //Calculates the distance between source laptop and destination laptop
    private double calculateDistance(Laptop destinationLaptop, Laptop sourceLaptop) {
        double x = sourceLaptop.getX();
        double y = sourceLaptop.getY();
        double x1 = destinationLaptop.getX();
        double y1 = destinationLaptop.getY();
        double range = destinationLaptop.getWirelessTransmissionRange() + sourceLaptop.getWirelessTransmissionRange();
        double distance = Math.sqrt(Math.pow((x - x1), 2) + Math.pow((y - y1), 2));
        //If the wireless transmission range is less than the distance between two laptops, there are no connection between them
        if(range < distance){
            return 0;
        }
        else{
            return distance;
        }
    }
}
