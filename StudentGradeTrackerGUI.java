import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentGradeTrackerGUI extends JFrame {
    private JTextField nameField, gradeField;
    private JTextArea resultArea;
    private JButton addGradeButton, addStudentButton, showSummaryButton;

    private ArrayList<Student> students = new ArrayList<>();
    private Student currentStudent = null;

    public StudentGradeTrackerGUI() {
        setTitle("Student Grade Tracker");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        nameField = new JTextField(20);
        gradeField = new JTextField(5);
        resultArea = new JTextArea(20, 40);
        resultArea.setEditable(false);

        addGradeButton = new JButton("Add Grade");
        addStudentButton = new JButton("Add Student");
        showSummaryButton = new JButton("Show Summary");

        add(new JLabel("Student Name:"));
        add(nameField);
        add(addStudentButton);
        add(new JLabel("Grade:"));
        add(gradeField);
        add(addGradeButton);
        add(showSummaryButton);
        add(new JScrollPane(resultArea));

        // Button Listeners
        addStudentButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                currentStudent = new Student(name);
                students.add(currentStudent);
                resultArea.append("Added student: " + name + "\n");
                nameField.setText("");
            }
        });

        addGradeButton.addActionListener(e -> {
            if (currentStudent == null) {
                JOptionPane.showMessageDialog(this, "Please add a student first.");
                return;
            }
            try {
                int grade = Integer.parseInt(gradeField.getText());
                currentStudent.addGrade(grade);
                resultArea.append("Added grade " + grade + " to " + currentStudent.name + "\n");
                gradeField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid number.");
            }
        });

        showSummaryButton.addActionListener(e -> {
            resultArea.append("\n===== Summary =====\n");
            for (Student s : students) {
                resultArea.append("Name: " + s.name + "\n");
                resultArea.append("Grades: " + s.grades + "\n");
                resultArea.append(String.format("Average: %.2f, Highest: %d, Lowest: %d\n\n",
                        s.getAverage(), s.getHighest(), s.getLowest()));
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGradeTrackerGUI app = new StudentGradeTrackerGUI();
            app.setVisible(true);
        });
    }

    // Inner class to represent a Student
    static class Student {
        String name;
        ArrayList<Integer> grades;

        Student(String name) {
            this.name = name;
            this.grades = new ArrayList<>();
        }

        void addGrade(int grade) {
            grades.add(grade);
        }

        double getAverage() {
            if (grades.isEmpty()) return 0;
            int sum = 0;
            for (int g : grades) sum += g;
            return (double) sum / grades.size();
        }

        int getHighest() {
            return grades.stream().max(Integer::compareTo).orElse(0);
        }

        int getLowest() {
            return grades.stream().min(Integer::compareTo).orElse(0);
        }
    }
}
