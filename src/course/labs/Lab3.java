package course.labs;

import java.util.Random;

public class Lab3 {
    public final static int raceDuration = 1000; //store the length of the race (can be accessed anywhere in code)

    public static void main (String[] arg) {

        Engine engine = new Engine(90);
        Vehicle c1 = new Car(1,engine,3);
        Car c2 = new Car(2,new Engine(100),4);
        Truck t1 = new Truck(3,engine.get_speed(),2,250);
        Motorcycle m1 = new Motorcycle(4, new Engine(95), 1);

        Vehicle[] allVehicles = new Vehicle[4];

        allVehicles[0] = c1;
        allVehicles[1] = c2;
        allVehicles[2] = t1;
        allVehicles[3] = m1;

        for (int i = 0; i < 25; i++) {
            while (true) /*this will run until a race participant crosses the finish line (passes raceDuration)*/ {
                int max = 0;
                //tell the cars to "go" one by one
                for (int j = 0; j < allVehicles.length; j++) {
                    Vehicle v = allVehicles[j];

                    v.Go();

                    System.out.println(v);
                    max = Math.max(max, v.RaceProgress);
                }
                System.out.println();
                //check to see if someone has won the race
                if (max > raceDuration) {
                    break;
                }
            }
            System.out.println("We have a winner!!! \n*** Vehicle " + Lab3.GetFurthestVehicle(allVehicles) + " ***\n");
        }

        System.out.println("The season has ended. The wins for each Vehicle have been totaled.\n");
        for (int i = 0; i < 4; i++)
            System.out.println("Vehicle " +(i+1)+ " Wins: " + allVehicles[i].get_wins());
        System.out.println("\n The season's winner has been decided!\n*** Vehicle " +
                            Lab3.GetMostWins(allVehicles) + " ***");
    }

    //just a helper method to find out which vehicle won the race
    public static int GetFurthestVehicle (Vehicle[] allVehicles) {
        int max=0;
        int VIN=0;
        int index = 0;
        for (int i=0; i<allVehicles.length;i++) {
            if (max < allVehicles[i].RaceProgress) {
                max = allVehicles[i].RaceProgress;
                VIN = allVehicles[i].VIN;
                index = i;
            }
        }
        allVehicles[index].increase_wins();
        return VIN;
    }

    public static int GetMostWins(Vehicle[] allVehicles) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < allVehicles.length; i++) {
            if (max < allVehicles[i].get_wins()) {
                max = allVehicles[i].get_wins();
                index = i;
            }
        }
        return index+1;
    }
}

class Engine
{
    private int speed;
    public Engine (int speed) {
        this.speed = speed;
    }

    public Engine (Engine e) {
        this(e.get_speed());
    }

    public int SpeedModifier () {
        //returns speed/2 to maxSpeed
        return (int)(Math.random()*speed/2)+speed/2;
    }

    public int get_speed () {
        return speed;
    }
}

abstract class Vehicle extends Object
{
    final int max_occupancy;
    private int passengers;
    int VIN;
    private int wins;
    int RaceProgress;
    private Engine engine;

    Vehicle (int vin, Engine e, int max_occupancy) {
        passengers = 1;
        RaceProgress = 0;
        VIN = vin;
        engine = new Engine(e);
        this.max_occupancy = max_occupancy;
    }

    Vehicle (int vin, int speed, int max_occupancy) {
        this(vin, new Engine(speed), max_occupancy);
    }

    abstract public void Go ();

    public String toString () {
        return "Vehicle: "+VIN+
                " Progress: "+RaceProgress+ " " +super.toString();
    }

    public boolean equals (Object other) {
        return this.VIN == ((Vehicle)other).VIN;
    }

    public void change_num_passengers (int pass) {
        pass = Math.abs(pass);
        if (pass > max_occupancy) {
            passengers = max_occupancy;
            System.out.println("Vehicle: " + VIN + "| Error >> Attempt to exceed max occupancy of vehicle.");
        } else if (pass < 1) {
            passengers = 1;
            System.out.println("Vehicle: " + VIN + ". Error >> Attempt to remove all passengers from vehicle.");
        } else {
            passengers = pass;
        }
    }

    public int SpeedModifier() {
        return engine.SpeedModifier();
    }

    public int get_passengers () {
        return passengers;
    }

    public void reset () {
        RaceProgress = 0;
    }

    public void increase_wins () {
        wins++;
    }

    public int get_wins () {
        return wins;
    }
}

class Car extends Vehicle  {
    /**
     * Car Constructor (no return specified)
     * @param i = (0,100)
     * @param passengers
     * @param e
     */

    Car (int i, Engine e, int passengers) {
        super(i,new Engine(e),5);//calling the constructor in Vehicle
        change_num_passengers(passengers);
    }

    public String toString () {
        return "Car::"+super.toString();
    }

    public void Go () {
        RaceProgress += SpeedModifier() - 10 * (get_passengers()-1);
    }
}

class Truck extends Vehicle {

    int towWeight;

    Truck (int i, int horsepower, int passengers, int towWeight) {
        super(i,horsepower,3);
        change_num_passengers(passengers);
        this.towWeight = towWeight;
    }

    public String toString () {
        return "Truck::"+super.toString();
    }

    public void Go () {
        RaceProgress += SpeedModifier() - (0.1f * towWeight);
    }
}

class Motorcycle extends Vehicle {
    boolean crashed = false;

    Motorcycle (int i, Engine e, int passengers) {
        super(i,new Engine(e),1);
        change_num_passengers(passengers);
    }

    public String toString () {
        return "Motorcycle::"+super.toString();
    }

    public void reset () {
        RaceProgress = 0;
        crashed = false;
    }

    public void Go () {
        Random rand = new Random(System.currentTimeMillis());
        int chance = rand.nextInt(100);
        if (chance < 2) {
            System.out.println("Vehicle: " + VIN + "| Motorcycle Crash");
            crashed = true;
        }
        if (!crashed) {
            RaceProgress += SpeedModifier() - 20 * get_passengers();
        }
    }
}
