package console.program;

import java.util.Scanner;

public class Timetable {

    /**
     * The constructor for the timetable.
     */
    public Timetable() {

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
        System.out.println("Search");
    }

    /**
     * The method to show enrolled courses.
     */
    public void showCourses() {
        System.out.println("Show courses");
    }

    /**
     * The method to withdraw from a course.
     */
    public void withdraw() {
        System.out.println("Withdraw");
    }
}
