/*package course.labs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lab2 {

    // Add space, effectively clearing the screen
    public static void clear_screen(int lines) {
        for(int i = 0; i < lines; i++) System.out.println();
    }

    // Generates Random Tracks
    public static String generate_race_track() {
        Random rand = new Random(System.currentTimeMillis());
        String ret = "";
        for (int length = rand.nextInt(10)+40; length > 0; length--) {
            int chance = rand.nextInt(100);
            if (chance < 58)
                ret += '-';
            else if (chance < 72)
                ret += 'S';
            else if (chance < 86)
                ret += 'C';
            else if (chance < 100)
                ret += 'U';
        }
        return ret;
    }

    // Main
    public static void main(String[] args) throws InterruptedException {
        // Car Setup
        Car[] cars = new Car[6];
        cars[0] = new Car("Resources/Car1.txt");
        cars[1] = new Car("Resources/Car2.txt");
        cars[2] = new Car("Resources/Car3.txt");
        cars[3] = new Car("Resources/Car4.txt");
        cars[4] = new Car("Resources/Car5.txt");
        cars[5] = new Car("Resources/Car6.txt");

        // Race Setup
        String racetrack = "";
        boolean skip_races = false;
        String temp;
        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want to skip all races (Y/N): ");
        temp = sc.next();
        if (temp.contains("Y")) {
            skip_races = true;
        }
        sc.close();

        // Season Races (25)
        for (int i = 0; i < 25; i++) {
            // Random track generation
            racetrack = generate_race_track();
            boolean race = true;
            if (!skip_races)
                System.out.println("Race: "+(i+1));
            for (int j = 0; j < 6; j++)
                cars[j].reset();

            // Main Loop
            while (race) {
                // Stagger
                if (!skip_races)
                    Thread.sleep(10);
                // Update Cars and Wins
                for (int j = 0; j < 6; j++)
                    cars[j].update(racetrack);
                for (int j = 0; j < 6; j++)
                    if (cars[j].get_race_progress() >= racetrack.length()) {
                        cars[j].add_win();
                        race = false;
                        break;
                    }
                // Display Races
                if (race && !skip_races) {
                    ArrayList<Integer> duplicates = new ArrayList<Integer>();
                    ArrayList<Integer> duplicates_out = new ArrayList<Integer>();
                    // Display Main Line
                    for (int j = 0; j < racetrack.length(); j++) {
                        boolean no_dup = true;
                        boolean car_present = false;
                        for (int k = 0; k < 6; k++)
                            if ((int)cars[k].get_race_progress() == j)
                                if (no_dup) {
                                    System.out.print(cars[k].get_symbol());
                                    no_dup = false;
                                    car_present = true;
                                } else {
                                    duplicates.add(k);
                                }
                        if (car_present) continue;
                        System.out.print(racetrack.charAt(j));
                    }
                    System.out.println();
                    // Display Other lines
                    while (duplicates.size() != 0) {
                        // Move to line
                        for (int j = 0; j < duplicates.size(); j++) {
                            boolean no_dup = true;
                            for (int k = 0; k < duplicates_out.size(); k++)
                                if (duplicates.get(j) == duplicates_out.get(k))
                                    no_dup = false;
                            if (no_dup) {
                                duplicates_out.add(duplicates.get(j));
                                duplicates.remove(j);
                            }
                        }
                        // Show Line
                        for (int j = 0; j < racetrack.length(); j++) {
                            boolean car_present = false;
                            for (int k = 0; k < duplicates_out.size(); k++) {
                                if ((int)cars[k].get_race_progress() == j) {
                                    System.out.print(cars[duplicates_out.get(k)].get_symbol());
                                    car_present = true;
                                    duplicates_out.remove(k);
                                }
                            }
                            if (car_present) continue;
                            System.out.print(racetrack.charAt(j));
                        }
                        System.out.println();
                    }
                    // Put space between race updates
                    clear_screen(5);
                }
            }
        }

        // Find the car with the most wins and display the number of wins
        int pos = 0;
        int most_wins = 0;

        for (int i = 0; i < 6; i++)
            if (cars[i].get_wins() > most_wins) {
                most_wins = cars[i].get_wins();
                pos = i;
            }

        clear_screen(2);
        System.out.println("Winner: "+cars[pos].get_name()+", Wins: "+most_wins);
    }

}

// Car class
class Car {

    // Variables
    private String name;
    private float handle_s;
    private float handle_c;
    private float handle_u;
    private float top_speed;
    private float acceleration;
    private char symbol;
    private float cur_speed = 0;
    private float race_progress = 0;
    private int race_wins = 0;

    // Constructor, gets info from a file
    Car (String file_name) {
        read_file(file_name);
    }

    // Reads car's stats in from a file
    private void read_file(String file_name) {
        File file = new File(file_name);
        try {
            Scanner sc = new Scanner(file);
            String[] values = new String[7];
            for (int i = 0; sc.hasNextLine(); i++) {
                values[i] = sc.nextLine();
            }
            sc.close();
            name = get_name(values[0]);
            handle_s = get_float(values[1]);
            handle_c = get_float(values[2]);
            handle_u = get_float(values[3]);
            top_speed = get_float(values[4]);
            acceleration = get_float(values[5]);
            symbol = get_symbol(values[6]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String get_name(String s) {
        String n = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                i++;
                while (i != s.length() && s.charAt(i) != '+') {
                    n += s.charAt(i);
                    i++;
                }
                return n;
            }
        }
        return "";
    }

    // Reads from file to get float values for car stats
    private float get_float(String s) {
        String num = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                i++;
                while (i != s.length() && s.charAt(i) != '+') {
                    num += s.charAt(i);
                    i++;
                }
                return Float.parseFloat(num);
            }
        }
        return .0f;
    }

    // Reads from file to get the car's symbol
    private char get_symbol(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '-')
                return s.charAt(i+1);
        }
        return ' ';
    }

    // Updates the car based on the race track
    public void update(String race_track) {
        switch (race_track.charAt((int)race_progress)) {
            case '-':
                if (cur_speed < top_speed)
                    cur_speed += acceleration;
                else
                    cur_speed = top_speed;
                break;
            case 'S':
                if (cur_speed < top_speed*handle_s)
                    cur_speed += acceleration;
                else
                    cur_speed = top_speed*handle_s;
                break;
            case 'C':
                if (cur_speed < top_speed*handle_c)
                    cur_speed += acceleration;
                else
                    cur_speed = top_speed*handle_c;
                break;
            case 'U':
                if (cur_speed < top_speed*handle_u)
                    cur_speed += acceleration;
                else
                    cur_speed = top_speed*handle_u;
                break;
        }
        race_progress += cur_speed;
    }

    // Adds a win
    public void add_win() {
        race_wins++;
    }

    // Returns the name of the car
    public String get_name() {
        return name;
    }

    // Returns race_progress
    public float get_race_progress() {
        return race_progress;
    }

    // Returns wins
    public int get_wins() {
        return race_wins;
    }

    // Returns the symbol of the car
    public char get_symbol() {
        return symbol;
    }

    // Resets the car for the next race
    public void reset() {
        cur_speed = 0;
        race_progress = 0;
    }

}*/
