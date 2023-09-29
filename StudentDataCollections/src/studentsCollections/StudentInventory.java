package studentsCollections;
import java.util.*;

public class StudentInventory {
	private ArrayList<Student> allStudents;
	private int classStrength;
	
	public StudentInventory(int classStrength)
	{
		this.classStrength = classStrength;
		this.allStudents = new ArrayList<>();
	}
	
	public ArrayList<Student> getAllStudents()
	{
		return allStudents;
	}
	
	public Student getStudent(Student s)
	{
		Student studentToGet = null;
		for(Student sLooper : allStudents)
		{
			if(sLooper.equals(s))
			{
				studentToGet = sLooper;
			}
		}
		return studentToGet;
	}
	
	public void addStudent(Student s)
	{
		allStudents.add(s);
	}
	
	public void removeStudent(Student s)
	{
		Student studentToRemove = null;
		for(Student sLooper : allStudents)
		{
			if(sLooper.equals(s))
			{
				studentToRemove = sLooper;
			}
		}
		allStudents.remove(studentToRemove);
	}
	
	public boolean isSameCgpa(Student s1, Student s2)
	{
		double cgpa1 = s1.getCgpa();
		double cgpa2 = s2.getCgpa();
		
		if(cgpa1 == cgpa2)
		{
			return true;
		}
		return false;
	}
	
	public void returnStudentName()
	{
		for(Student sLooper : allStudents)
		{
			System.out.print("The name of Students is: " + sLooper.getName() + " and roll number is: " + sLooper.getId() + ", ");
			System.out.println();
		}
	}
	
	public void printNameTopperWise()
	{
		Collections.sort(allStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                // Compare by CGPA (descending order)
                if (s1.getCgpa() != s2.getCgpa()) {
                    return Double.compare(s2.getCgpa(), s1.getCgpa());
                }
                // If CGPA is the same, compare by name (ascending order)
                else {
                    return s1.getName().compareTo(s2.getName());
                }
            }
        });
		int index = 1;
		for(Student sLooper : allStudents)
		{
			 boolean eligible = isEligible(sLooper); // Check eligibility for the current student
		       String eligibilityStatus = eligible ? "Eligible" : "Not eligible";
			System.out.println(index + ": Roll number: " + sLooper.getId() + " " + sLooper.getName() + " with cgpa of: " + sLooper.getCgpa() + " Eligibility: " + eligibilityStatus + " for placement");
			index++;
		}
	}
	
	public boolean isStudentCloseCgpa(Student s1, Student s2)
	{
		double cgpa1 = s1.getCgpa();
		double cgpa2 = s2.getCgpa();
		
		if(cgpa1 - cgpa2 <= 0.5)
		{
			return true;
		}
		return false;
	}
	
	public boolean isEligible(Student s)
	{
		return s.getCgpa()>=7.0;
	}
}
