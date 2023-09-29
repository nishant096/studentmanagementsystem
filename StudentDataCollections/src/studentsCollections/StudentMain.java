package studentsCollections;

public class StudentMain {
	public static void main(String[] args)
	{
		StudentInventory sI = new StudentInventory(15);
		
		
		Student s1 = new Student("Harish", 11017, 9.58);
		Student s2 = new Student("Nishant", 11015, 7.45);
		Student s3 = new Student("Nikhil", 14001, 7.5);
		Student s4 = new Student("Deepak", 11012, 8.4);
		Student s5 = new Student("Ganesh", 11014, 8.1);
		Student s6 = new Student("Devansh", 11010, 8.3);
		Student s7 = new Student("Pranay", 11013, 9.0);
		Student s8 = new Student("Kaushik", 14007, 7.6);
		Student s9 = new Student("Satyam", 14010, 8.5);
		Student s10 = new Student("Basvaraj", 11005, 6.45);
		
		sI.addStudent(s1);
		sI.addStudent(s2);
		sI.addStudent(s3);
		sI.addStudent(s4);
		sI.addStudent(s5);
		sI.addStudent(s6);
		sI.addStudent(s7);
		sI.addStudent(s8);
		sI.addStudent(s9);
		sI.addStudent(s10);
		
		System.out.println("Printing all students name: ");
		sI.returnStudentName();
		
		System.out.println();
		
		System.out.println("Printing the name of student according with cgpa: ");
		sI.printNameTopperWise();
		
		System.out.println();
		System.out.println("Checking the student has close cgpa with each other or not:");
		if(sI.isStudentCloseCgpa(s3, s4))
		{
			System.out.println("Yes: " + s3.getName() + " & " + s4.getName() + " are neighbor of each other and the difference of their cgpa"
					+ " is" + Math.abs(s3.getCgpa()-s4.getCgpa()));
		}
		else {
			System.out.println("No: " + s3.getName() + " & " + s4.getName() + " are not neighbor of each other and the difference of their cgpa"
					+ " is" + Math.abs(s3.getCgpa()-s4.getCgpa()));
		}
		
		sI.getAllStudents();
	}
}
