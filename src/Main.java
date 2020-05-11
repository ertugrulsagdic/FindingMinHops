import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        int numberOfLaptops = 0;
        //List of laptops
        List<Laptop> laptopList = new ArrayList<>();

        try {
            //gets the default application path
            String path = System.getProperty("user.dir");
            //opens a file and reads the file
            File file = new File(path + "\\" + args[0]);
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNextLine()) {
                //if i = 0, that means we read the first line, which is numberoflaptops, of the file
                if (i == 0) {
                    numberOfLaptops = Integer.parseInt(scanner.nextLine());
                    System.out.println("Number of laptops: " + numberOfLaptops);
                }
                //the rest of the input are, x coordinate, y coordinate, and, wireless range of the laptop
                else {
                    String line = scanner.nextLine();
                    if (!line.isEmpty()) {
                        //split the read line into x, y, and r
                        String[] details = line.split("\t");
                        double x = Double.parseDouble(details[0]);
                        double y = Double.parseDouble(details[1]);
                        double wirelessTransmissionRange = Double.parseDouble(details[2]);
                        Laptop laptop = new Laptop(i - 1, x, y, wirelessTransmissionRange);
                        laptopList.add(laptop);
                    }
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("-------------------------");
        printLaptopList(laptopList);
        System.out.println("-------------------------");
        //create graph
        LocationGraph locationGraph = new LocationGraph(numberOfLaptops);

        //add laptops into the location graph
        for (Laptop laptop : laptopList) {
            locationGraph.addLaptop(laptop);
        }

        //create distances in the location graph
        for (int i = 0; i < laptopList.size(); i++) {
            for (int j = 0; j < laptopList.size(); j++) {
                locationGraph.addDistance(laptopList.get(i), laptopList.get(j));
            }

        }

        //Print adjacency matrix
        printDistanceMatrix(locationGraph.getDistances());
        System.out.println("-------------------------");
        String filename = args[0].replace(".txt", "");
        try {
            //create the output file
            BufferedWriter outputFile = new BufferedWriter(new FileWriter(filename + "output.txt"));

            for (int i = 0; i < laptopList.size(); i++) {
                //calls the BFS based algorithm from 0-0 to 0-number of laptops
                ArrayList<Laptop> path = pathFinder(locationGraph, laptopList.get(0), laptopList.get(i));
                printHop(path, laptopList.get(0), laptopList.get(i));
                //writes the outputs to the output file
                outputFile.write(Integer.toString(path.size() - 1) + "\n");
            }

            outputFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static ArrayList<Laptop> pathFinder(LocationGraph locationGraph, Laptop sourceLaptop, Laptop destinationLaptop) {
        //array to store current path
        ArrayList<Laptop> path = new ArrayList<>();
        //queue which stores paths
        Queue<ArrayList<Laptop>> pathQueue = new LinkedList<>();
        //visit array to keep if the laptop visited or not
        boolean[] visited = new boolean[locationGraph.getMaxNumberOfLaptops()];
        //add souceLaptop into the path
        path.add(sourceLaptop);
        pathQueue.add(path);
        visited[sourceLaptop.getValue()] = true;

        //loop until queue become empty
        while (pathQueue.size() != 0) {
            path = pathQueue.remove();
            Laptop currentLaptop = path.get(path.size() - 1);

            //if the current laptop equals to destination laptop return the path
            if (currentLaptop == destinationLaptop) {
                return path;
            } else {
                //traverse to all the laptops that can be reached from the current laptop
                for (int i = 0; i < locationGraph.getMaxNumberOfLaptops(); i++) {
                    if ((locationGraph.getDistances()[currentLaptop.getValue()][i].getDistance() > 0) && (!visited[i])) {
                        ArrayList<Laptop> newPath = new ArrayList<>(path);
                        newPath.add(locationGraph.getLaptops()[i]);
                        //push new path to queue
                        pathQueue.add(newPath);
                        visited[i] = true;
                    }
                }
            }
        }
        //if the node is unreachable from source to destination remmove the laptos from path
        if(path.get(path.size() - 1) != destinationLaptop){
            while(path.get(path.size() - 1) != sourceLaptop){
                path.remove(path.size() - 1);
            }
        }

        return path;
    }

    public static void printHop(ArrayList<Laptop> path, Laptop sourceLaptop, Laptop destinationLaptop) {

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).getValue());
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println("\nFrom the laptop " + sourceLaptop.getValue() + " to laptop " + destinationLaptop.getValue() + ": There are " + (path.size() - 1) + " hops");
    }

    public static void printDistanceMatrix(Distance[][] distances) {

        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances.length; j++) {
                System.out.print(distances[i][j].getDistance() + " ");
            }
            System.out.println();
        }
    }

    public static void printLaptopList(List<Laptop> laptopList) {
        for (Laptop laptop : laptopList) {
            System.out.println("Value: " + laptop.getValue());
            System.out.println("x coordinate: " + laptop.getX());
            System.out.println("y coordinate: " + laptop.getY());
            System.out.println("wireless range: " + laptop.getWirelessTransmissionRange());
        }
    }

}
