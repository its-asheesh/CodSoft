import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalSubjects;
        double totalMarks = 0;

        System.out.print("Enter the number of subjects: ");
        totalSubjects = scanner.nextInt();

        for (int i = 1; i <= totalSubjects; i++) {
            System.out.print("Enter marks for subject " + i + ": ");
            totalMarks += scanner.nextDouble();
        }

        double average = totalMarks / totalSubjects;
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + average + "%");

        char grade;
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("Grade: " + grade);
        scanner.close();
    }
}
 
    

