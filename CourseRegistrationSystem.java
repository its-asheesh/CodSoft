import java.util.ArrayList;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int enrolled;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean enroll() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        } else {
            return false;
        }
    }

    public boolean drop() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        } else {
            return false;
        }
    }

    public int getAvailableSlots() {
        return capacity - enrolled;
    }
}

class Student {
    private String studentId;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.enroll()) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for " + course.getTitle());
        } else {
            System.out.println("Course is full. Cannot register.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course) && course.drop()) {
            registeredCourses.remove(course);
            System.out.println("Successfully dropped " + course.getTitle());
        } else {
            System.out.println("Cannot drop this course.");
        }
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample Courses
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Intro to Computer Science", "Basic computer science course", 3, "MWF 9-10AM"));
        courses.add(new Course("MATH123", "Calculus I", "Introduction to calculus", 2, "TTh 2-3:30PM"));

        // Sample Student
        Student student = new Student("S001", "John Doe");

        boolean running = true;
        while (running) {
            System.out.println("\nCourse Registration System");
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. List Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("\nAvailable Courses:");
                    for (Course course : courses) {
                        System.out.println(course.getCourseCode() + ": " + course.getTitle() + " (" + course.getAvailableSlots() + " slots available)");
                    }
                    break;

                case 2:
                    System.out.print("Enter course code to register: ");
                    String courseCode = scanner.next().toUpperCase();
                    Course courseToRegister = findCourse(courses, courseCode);
                    if (courseToRegister != null) {
                        student.registerCourse(courseToRegister);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter course code to drop: ");
                    String courseCodeToDrop = scanner.next().toUpperCase();
                    Course courseToDrop = findCourse(courses, courseCodeToDrop);
                    if (courseToDrop != null) {
                        student.dropCourse(courseToDrop);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;

                case 4:
                    System.out.println("\nRegistered Courses:");
                    if (student.getRegisteredCourses().isEmpty()) {
                        System.out.println("No registered courses.");
                    } else {
                        for (Course c : student.getRegisteredCourses()) {
                            System.out.println(c.getCourseCode() + ": " + c.getTitle());
                        }
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting system.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static Course findCourse(ArrayList<Course> courses, String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
