package console.program;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Timetable {
    static String banner = new String(new char[80]).replace('\u0000', '-');//Formatting for banner
    ArrayList<Course> courses = new ArrayList<>(); //Stores all existing courses as arrayList
    ArrayList<Course> enrolled = new ArrayList<>(); //Stores enrolled Courses as arrayList to maintain consistency
    ArrayList<Course> search = new ArrayList<>(); //Stores search results
    //ArrayList is used instead of hashset. Easier to print courses and select from menu and make result match spec
    String stringInput; //User input in console
    int listNum; //For storing numbers to print lists
    Course result;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");//Allows for time inputs like 8:04

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
                    if (data[3].equals("Online")) {
                        online = true;
                    }
                    else if (data[3].equals("Face-to-face")) {
                        online = false;
                    }
                    else continue;
                    day = DayOfWeek.valueOf(data[4].toUpperCase());//Uppercase to retain enum
                    time = LocalTime.parse(data[5], formatter);
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
        System.out.println("Welcome to MyTimetable!");
        do {
            printMenu();

            stringInput = readUserInput();

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
        System.out.print("Please provide a brand: ");
        String searchTerm = readUserInput();
        System.out.println(banner + "\n" + "> Select from matching list\n" + banner);
        listNum = 1;
        for (Course course : courses) {
            result = course; //Set both result and searchTerm to uppercase to be case-insensitive
            searchTerm = searchTerm.toUpperCase();
            if (result.getName().toUpperCase().contains(searchTerm)) {
                search.add(result);
                System.out.printf("   %d) %s%n", listNum, result.getName());
                listNum++;
            }
        }
        //Select course from arrayList using
        System.out.printf("   %d) Go to main menu%n", listNum);
        System.out.print("Please select: ");
        stringInput = readUserInput();
        result = search.get(Integer.parseInt(stringInput) - 1); //-1 as arrayList starts at 0 and menu starts at 1

        //Check for dupe due to not using hashset for consistency
        if (enrolled.contains(result)) {
            System.out.println("You are already enrolled in this course.");
        } else {
            enrolled.add(result);
            System.out.printf("You have enrolled in the course %s!%n", result.getName());
        }
    }

    /**
     * The method to show enrolled courses.
     */
    public void showCourses() {
        if (enrolled.isEmpty()) {
            System.out.println("You don't have any courses enrolled.");
        }
        else {
            System.out.println(banner + "\n" + "You have enrolled into the following course(s):\n" + banner);
            printEnrolled();
        }
    }

    /**
     * The method to withdraw from a course.
     */
    public void withdraw() {
        if (enrolled.isEmpty()) {
            System.out.println("You don't have any courses enrolled.");
        }
        else {
            System.out.println(banner + "\n" + "Please choose a course to withdraw:\n" + banner);
            printEnrolled();

            System.out.print("Please select: ");
            stringInput = readUserInput();

            result = enrolled.remove(Integer.parseInt(stringInput) - 1);
            System.out.printf("You have withdrawn from %s!%n", result.getName());
        }
    }

    /**
     * Used by showCourses and withdraw to print enrolled courses.
     */
    public void printEnrolled() {
        listNum = 1;

        for (Course course : enrolled) {
            result = course;
            //Format
            System.out.printf("   %d) %s     %s     %s %s-%s%n",
                    listNum,
                    course.getName(),
                    course.getDelivery(),
                    course.getDay().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                    course.getTime(),
                    course.getTime().plusMinutes((long)(course.getDuration() * 60)));
            listNum++;
        }
    }
}
