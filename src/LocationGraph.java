import java.util.LinkedList;

public class LocationGraph {

    //number of laptops in the location graph
    private int numberOfLaptops;
    //total number of laptops
    private int maxNumberOfLaptops;
    //Collection of all laptops
    private Laptop[] laptops;
    //Adjacency matrix of the graph
    private Distance[][] distances;


    public LocationGraph(int maxNumberOfLaptops) {
        this.maxNumberOfLaptops = maxNumberOfLaptops;
        laptops = new Laptop[maxNumberOfLaptops];
        distances = new Distance[maxNumberOfLaptops][maxNumberOfLaptops];
    }

    //adds laptop to the graph
    public void addLaptop(Laptop laptop) {
        laptops[numberOfLaptops] = laptop;
        for (int i = 0; i < numberOfLaptops; i++) {
            distances[numberOfLaptops][i] = new Distance(laptop, laptop);
            distances[i][numberOfLaptops] = new Distance(laptop, laptop);
        }
        numberOfLaptops++;
    }

    //adds the distance from source to destination
    public void addDistance(Laptop sourceLaptop, Laptop destinationLaptop) {
        int row = indexOfLaptop(sourceLaptop);
        int column = indexOfLaptop(destinationLaptop);
        if(row == column){
            distances[row][column] = new Distance(destinationLaptop, sourceLaptop);
        }
        else{
            distances[row][column] = new Distance(destinationLaptop, sourceLaptop);
            distances[column][row] = new Distance(destinationLaptop, sourceLaptop);
        }

    }

    //finds the index of the laptop
    public int indexOfLaptop(Laptop laptop) {
        int index = 0;
        while (laptop != laptops[index]) {
            index++;
        }
        return index;
    }

    public int getMaxNumberOfLaptops() {
        return maxNumberOfLaptops;
    }

    public Laptop[] getLaptops() {
        return laptops;
    }

    public Distance[][] getDistances() {
        return distances;
    }


}
