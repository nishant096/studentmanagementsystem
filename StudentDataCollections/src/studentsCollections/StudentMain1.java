package studentsCollections;
import java.util.*;

public class StudentMain1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the class strength: ");
        int classStrength = scanner.nextInt();
        
        StudentInventory sI = new StudentInventory(classStrength);
        
        for (int i = 0; i < classStrength; i++) {
            System.out.println("Enter student details for Student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.next();
            System.out.print("ID: ");
            int id = scanner.nextInt();
            System.out.print("CGPA: ");
            double cgpa = scanner.nextDouble();
            
            Student student = new Student(name, id, cgpa);
            sI.addStudent(student);
        }
        
        System.out.println("Printing all students' names:");
        sI.returnStudentName();
        
        System.out.println("\nPrinting the names of students according to CGPA:");
        sI.printNameTopperWise();
        
        System.out.println("\nChecking if students have close CGPA with each other or not:");
        Student s3 = sI.getAllStudents().get(2); // Example student
        Student s4 = sI.getAllStudents().get(3); // Example student
        if (sI.isStudentCloseCgpa(s3, s4)) {
            System.out.println("Yes: " + s3.getName() + " & " + s4.getName() + " are neighbors of each other, and the difference in their CGPA is " + Math.abs(s3.getCgpa() - s4.getCgpa()));
        } else {
            System.out.println("No: " + s3.getName() + " & " + s4.getName() + " are not neighbors of each other, and the difference in their CGPA is " + Math.abs(s3.getCgpa() - s4.getCgpa()));
        }
        
        scanner.close();
    }
}
