package console.program;

import java.time.*;

public class Course {
    private String name;
    private int capacity;
    private String year;
    private boolean online;
    private DayOfWeek day;
    private LocalTime time;
    private float duration;

    /**
     * The empty constructor for the course.
     */
    public Course() {
        this.name = "Placeholder Name";
        this.capacity = 0;
        this.year = "";
        this.online = false;
        this.day = DayOfWeek.SUNDAY;
        this.time = LocalTime.MIDNIGHT;
        this.duration = 0.0f;
    }

    /**
     * The full constructor for the course.
     */
    public Course(String name, int capacity, String year, boolean online, DayOfWeek day, LocalTime time, float duration) {
        this.name = name;
        this.capacity = capacity;
        this.year = year;
        this.online = online;
        this.day = day;
        this.time = time;
        this.duration = duration;
    }

    /**
     * The getter and setter methods.
     */
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getYear() {
        return year;
    }
    public void setOnline(boolean online) {
        this.online = online;
    }
    public boolean isOnline() {
        return online;
    }
    public void setDay(DayOfWeek day) {
        this.day = day;
    }
    public DayOfWeek getDay() {
        return day;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
    public LocalTime getTime() {
        return time;
    }
    public void setDuration(float duration) {
        this.duration = duration;
    }
    public float getDuration() {
        return duration;
    }
}
