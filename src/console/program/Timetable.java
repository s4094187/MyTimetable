package console.program;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Timetable {
    HashSet<Course> courses = new HashSet<>();
    HashSet<Course> enrolled = new HashSet<>();

    /**
     * The empty constructor for the timetable.
     */
    public Timetable() {

    }

    /**
     * The full constructor for the timetable.
     */
    public Timetable(String fileName) {
        Course newCourse;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            //Course initialization variables
            String name;
            int capacity;
            String year;
            boolean online;
            DayOfWeek day;
            LocalTime time;
            float duration;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                //Check if line is header. If not, convert values and add course to hashset.
                if (!data[0].equals("Course name")) {
                    name = data[0];
                    //Check if capacity is valid, if not set to -1 for n/a
                    if (!data[1].equals("n/a")) {
                        capacity = Integer.parseInt(data[1]);
                    }
                    else {
                        capacity = -1;
                    }
                    year = data[2];
                    //Check if delivery mode is valid
                    if (data[3].equals("online")) {
                        online = true;
                    }
                    else if (data[3].equals("face-to-face")) {
                        online = false;
                    }
                    else continue;
                    day = DayOfWeek.valueOf(data[4]);
                    time = LocalTime.parse(data[5]);
                    duration = Float.parseFloat(data[6]);

                    newCourse = new Course(name, capacity, year, online, day, time, duration);
                    courses.add(newCourse);
                }
            }
        } catch (IOException e) {
            System.out.println("Error opening file " + fileName);
        }
    }

    /**
     * The method to run the timetable.
     */
    public void run() {
        boolean exit = false;
        do {
            System.out.println("Welcome to MyTimetable!");
            printMenu();

            String stringInput = readUserInput();

            // Check the user input and continue with the next iteration
            // if no input is provided
            if (stringInput.isEmpty()) {
                System.out.println("Please select a valid menu option.");
                continue;
            }

            char input = stringInput.charAt(0);

            switch (input) {
                case '1':
                    this.search();
                    break;
                case '2':
                    this.showCourses();
                    break;
                case '3':
                    this.withdraw();
                    break;
                case '4':
                    exit = true;
                    break;
                default:
                    System.out.println("Please select a valid menu option.");
                    break;
            }
        } while (!exit);
    }

    /**
     * The utility method to print menu options.
     */
    public static void printMenu(){
        String banner = new String(new char[80]).replace('\u0000', '-');
        System.out.println(banner + "\n" + "> Select from main menu!\n" + banner);
        System.out.printf("   %s%n", "1) Search by keyword to enroll");
        System.out.printf("   %s%n", "2) Show my enrolled courses");
        System.out.printf("   %s%n", "3) Withdraw from a course");
        System.out.printf("   %s%n", "4) Exit");
        System.out.print("Please select: ");
    }

    /**
     * The utility method to read user input.
     */
    public static String readUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * The method to search by keyword to enroll.
     */
    public void search() {
        System.out.println("Please provide a brand: ");
    }

    /**
     * The method to show enrolled courses.
     */
    public void showCourses() {
        System.out.println("You don't have any courses enrolled.");
    }

    /**
     * The method to withdraw from a course.
     */
    public void withdraw() {
        System.out.println("You don't have any courses enrolled.");
    }
}
