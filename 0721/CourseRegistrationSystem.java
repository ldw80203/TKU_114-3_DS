import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Course> courses = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Choose: ");

            if (choice == 1) {
                addCourse();
            } else if (choice == 2) {
                searchCourse();
            } else if (choice == 3) {
                registerCourse();
            } else if (choice == 4) {
                dropCourse();
            } else if (choice == 5) {
                deleteCourse();
            } else if (choice == 6) {
                printReport();
            } else if (choice == 7) {
                printAllCourses();
            } else if (choice != 0) {
                System.out.println("Invalid option.");
            }
        } while (choice != 0);

        System.out.println("Goodbye.");
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("=== Course Registration System ===");
        System.out.println("1. Add course");
        System.out.println("2. Search course");
        System.out.println("3. Register");
        System.out.println("4. Drop");
        System.out.println("5. Delete course");
        System.out.println("6. Report");
        System.out.println("7. List all courses");
        System.out.println("0. Exit");
    }

    public static Course findByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    public static void addCourse() {
        String code = readRequiredText("Code: ");
        if (findByCode(code) != null) {
            System.out.println("Code already exists.");
            return;
        }

        String name = readRequiredText("Name: ");
        int capacity = readPositiveInt("Capacity: ");
        courses.add(new Course(code, name, capacity));
        System.out.println("Course added.");
    }

    public static void searchCourse() {
        String code = readRequiredText("Code to search: ");
        Course found = findByCode(code);

        if (found == null) {
            System.out.println("Course not found.");
        } else {
            System.out.println(found);
        }
    }

    public static void registerCourse() {
        String code = readRequiredText("Code to register: ");
        Course found = findByCode(code);

        if (found == null) {
            System.out.println("Course not found.");
        } else if (found.enroll()) {
            System.out.println("Registration completed.");
        } else {
            System.out.println("Course is full.");
        }
    }

    public static void dropCourse() {
        String code = readRequiredText("Code to drop: ");
        Course found = findByCode(code);

        if (found == null) {
            System.out.println("Course not found.");
        } else if (found.drop()) {
            System.out.println("Course dropped.");
        } else {
            System.out.println("No enrolled students to drop.");
        }
    }

    public static void deleteCourse() {
        String code = readRequiredText("Code to delete: ");
        Course found = findByCode(code);

        if (found == null) {
            System.out.println("Course not found.");
            return;
        }

        courses.remove(found);
        System.out.println("Course deleted.");
    }

    public static void printReport() {
        int totalEnrollment = 0;
        int fullCourses = 0;

        for (Course course : courses) {
            totalEnrollment += course.getEnrolled();
            if (course.isFull()) {
                fullCourses++;
            }
        }

        System.out.println("Total courses: " + courses.size());
        System.out.println("Total enrollment: " + totalEnrollment);
        System.out.println("Full courses: " + fullCourses);
    }

    public static void printAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses.");
            return;
        }

        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String readRequiredText(String prompt) {
        String value;
        do {
            value = readText(prompt);
            if (value.isEmpty()) {
                System.out.println("This field cannot be blank.");
            }
        } while (value.isEmpty());
        return value;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private static int readPositiveInt(String prompt) {
        int value;
        do {
            value = readInt(prompt);
            if (value <= 0) {
                System.out.println("Please enter a number greater than 0.");
            }
        } while (value <= 0);
        return value;
    }
}
