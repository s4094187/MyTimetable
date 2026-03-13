package console.program;

/**
 * The Main class is the entry point of the console program.
 */
public class Main {
	
	public static void main(String args[]){
		Timetable timetable = new Timetable("course.csv");
		timetable.run();
	}

}
