import java.util.InputMismatchException;
import java.util.Scanner;

public class BasicJavaProject {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int numberofstudents = 0;
        String[] students = null;

        System.out.println("How many students do you have?");
        while (true) {
            try {
                numberofstudents = sc.nextInt();
                sc.nextLine(); // clear newline
                if (numberofstudents > 0) {
                    students = new String[numberofstudents];
                    break;
                } else {
                    System.out.println("Enter a positive number.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid integer value.");
                sc.nextLine(); // clear invalid input
            }
        }

        for (int i = 0; i < numberofstudents; i++) {
            System.out.println("Enter Student's name:");
            students[i] = sc.nextLine();
        }

        int gradecount = 0;
        int[][] grades = null;
        System.out.println("How many grades do you want to enter?");
        while (true) {
            try {
                gradecount = sc.nextInt();
                sc.nextLine(); // clear newline
                if (gradecount > 0) {
                    grades = new int[numberofstudents][gradecount];
                    break;
                } else {
                    System.out.println("Enter a positive number.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid integer value.");
                sc.nextLine(); // clear invalid input
            }
        }

        for (int j = 0; j < students.length; j++) {
            System.out.println("Enter grades for " + students[j]);
            for (int v = 0; v < grades[j].length; v++) {
                while (true) {
                    try {
                        System.out.print("Grade " + (v+1) + ": ");
                        grades[j][v] = sc.nextInt();
                        sc.nextLine(); // clear newline
                        break;
                    } catch (InputMismatchException ex) {
                        System.out.println("Please enter a valid integer.");
                        sc.nextLine();
                    }
                }
            }
        }

        boolean running = true;
        while (running) {
            printmenu();
            int input = 0;
            try {
                input = sc.nextInt();
                sc.nextLine(); // clear newline
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid integer (1-4).");
                sc.nextLine();
                continue;
            }
            switch(input) {
                case 1:
                    // search for user by name 
                    System.out.println("Enter Student Name To Get Grades");
                    String student = sc.nextLine();
                    getstudentgradesByName(students, grades, student);
                    break;
                case 2:
                    // search for user by index
                    System.out.println("Enter Index To Get Student Grades (0-" + (students.length-1) + "):");
                    int index = -1;
                    try {
                        index = sc.nextInt();
                        sc.nextLine();
                        getstudentsgradesByIndex(students, grades, index);
                    } catch (InputMismatchException ex) {
                        System.out.println("Please enter a valid integer.");
                        sc.nextLine();
                    }
                    break;
                case 3:
                    // print averages
                    getaverages(students, grades);
                    break;
                case 4:
                    System.out.println("Quitting Program");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Enter 1-4.");
                    break;
            }
        }
        sc.close();
    }

    public static void printmenu(){
        System.out.println("\nSelect an option: \n"
            + "1: Get student's grades by name \n"
            + "2: Get student's grades by index \n"
            + "3: Get averages \n"
            + "4: Quit"
        );
    }

    public static void getstudentgradesByName(String[] students, int[][] grades, String name) {
        boolean found = false;
        for (int i = 0; i < students.length; i++) {
            if (students[i].equalsIgnoreCase(name)) {
                System.out.print("Grades for " + students[i] + ": ");
                for (int j = 0; j < grades[i].length; j++) {
                    System.out.print(grades[i][j] + "\t");
                }
                System.out.println();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public static void getstudentsgradesByIndex(String[] students, int[][] grades, int index) {
        if (index >= 0 && index < students.length) {
            System.out.print("Grades for " + students[index] + ": ");
            for (int j = 0; j < grades[index].length; j++) {
                System.out.print(grades[index][j] + "\t");
            }
            System.out.println();
        } else {
            System.out.println("Invalid index. Please enter a number between 0 and " + (students.length - 1));
        }
    }

    public static void getaverages(String[] students, int[][] grades) {
        for (int i = 0; i < students.length; i++) {
            int sum = 0;
            for (int j = 0; j < grades[i].length; j++) {
                sum += grades[i][j];
            }
            double average = (double) sum / grades[i].length;
            System.out.printf("%s's average is %.2f\n", students[i], average);
        }
    }
}
