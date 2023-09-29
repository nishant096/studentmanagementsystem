package studentsCollections;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StudentGUIApp1 extends JFrame {
    private StudentInventory studentInventory;
    private JTextArea outputTextArea;
    private JButton saveButton;
    private String selectedDirectoryPath; // Store the selected directory path

    public StudentGUIApp1(int classStrength) {
        studentInventory = new StudentInventory(classStrength);
        outputTextArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton getDetailsButton = new JButton("Get Student Details");
        JButton eligibilityButton = new JButton("Check Eligibility");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        getDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getStudentDetails();
            }
        });

        eligibilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkEligibility();
            }
        });

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectSaveDirectory();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(getDetailsButton);
        buttonPanel.add(eligibilityButton);
        buttonPanel.add(saveButton);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setTitle("Student Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // ... (other methods)
    private void addStudent() {
        String name = JOptionPane.showInputDialog("Enter Student Name:");
        if (name == null) return; // User canceled input
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID:"));
        double cgpa = Double.parseDouble(JOptionPane.showInputDialog("Enter CGPA:"));
        Student student = new Student(name, id, cgpa);
        studentInventory.addStudent(student);
        updateOutputText("Added Student: " + student.getName());
        saveDataToFile();
        
    }

    private void removeStudent() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID to Remove:"));
        Student studentToRemove = studentInventory.getStudent(new Student("", id, 0.0));
        if (studentToRemove != null) {
            studentInventory.removeStudent(studentToRemove);
            updateOutputText("Removed Student: " + studentToRemove.getName());
        } else {
            updateOutputText("Student with ID " + id + " not found.");
        }
        saveDataToFile();
    }

    private void getStudentDetails() {
        outputTextArea.setText("");
        for (Student student : studentInventory.getAllStudents()) {
            outputTextArea.append("Name: " + student.getName() + ", ID: " + student.getId() + ", CGPA: " + student.getCgpa() + "\n");
        }
    }
    
    private void checkEligibility() {
        outputTextArea.setText("");
        for (Student student : studentInventory.getAllStudents()) {
            String eligibilityStatus = studentInventory.isEligible(student) ? "Eligible" : "Not eligible";
            outputTextArea.append("Name: " + student.getName() + ", ID: " + student.getId() + ", CGPA: " + student.getCgpa() + ", Eligibility: " + eligibilityStatus + "\n");
        }
    }

    private void updateOutputText(String message) {
        outputTextArea.append(message + "\n");
    }

    private void selectSaveDirectory() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select Save Directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            selectedDirectoryPath = fileChooser.getSelectedFile().getAbsolutePath();
            JOptionPane.showMessageDialog(this, "Selected save directory: " + selectedDirectoryPath, "Directory Selected", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void saveDataToFile() {
        if (selectedDirectoryPath == null) {
            JOptionPane.showMessageDialog(this, "Please select a directory for saving data first.", "Directory Not Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String savePath = selectedDirectoryPath + "/student_data.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(savePath))) {
            for (Student student : studentInventory.getAllStudents()) {
                writer.write("Name: " + student.getName() + ", ID: " + student.getId() + ", CGPA: " + student.getCgpa() + "\n");
            }
            writer.flush();
            JOptionPane.showMessageDialog(this, "Student data saved successfully to " + savePath, "Save Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while saving data.", "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        int classStrength = 15;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentGUIApp(classStrength).setVisible(true);
            }
        });
    }
}
