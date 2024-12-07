package com.klu.HibernateDemo;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HQLoperations {

    private static SessionFactory sf;

    public static void main(String[] args) {
        // Initialize SessionFactory once
        sf = HibernateUtil.getSessionFactory();
        HQLoperations operations = new HQLoperations();

        // Create a Scanner to handle menu input
        Scanner scanner = new Scanner(System.in);
        
        boolean exit = false;
        while (!exit) {
            System.out.println("\nSelect an Operation:");
            System.out.println("1. Insert Student Details");
            System.out.println("2. Fetch Student by ID");
            System.out.println("3. Update Student by ID");
            System.out.println("4. Delete Student by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        operations.insertStudent(scanner);
                        break;
                    case 2:
                        operations.fetchStudentById(scanner);
                        break;
                    case 3:
                        operations.updateStudentById(scanner);
                        break;
                    case 4:
                        operations.deleteStudentById(scanner);
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Clear the invalid input
            }
        }

        // Close the SessionFactory at the end
        sf.close();
        scanner.close();
    }

    // Method to insert student details into the database
    public void insertStudent(Scanner scanner) {
        Session session = sf.openSession();
        Transaction transaction = null;
        String continueInput = "yes"; // Initialize with a default value
        try {
            transaction = session.beginTransaction();

            do {
                System.out.print("Enter Student ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                // Check if the student ID already exists
                Student existingStudent = session.get(Student.class, id);
                if (existingStudent != null) {
                    System.out.println("Student ID already exists. Please enter a different ID.");
                    continue; // Skip to the next iteration of the loop
                }

                System.out.print("Enter Student Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Student Gender: ");
                String gender = scanner.nextLine();
                System.out.print("Enter Student Department: ");
                String department = scanner.nextLine();
                System.out.print("Enter Program: ");
                String program = scanner.nextLine();
                System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
                String dob = scanner.nextLine();
                System.out.print("Enter Contact Number: ");
                String contactNumber = scanner.nextLine();
                System.out.print("Enter CGPA: ");
                double cgpa = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline
                System.out.print("Enter Number of Backlogs: ");
                int backlogs = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                System.out.print("Has Graduated (true/false): ");
                boolean graduationStatus = scanner.nextBoolean();
                scanner.nextLine(); // Consume the newline

                // Create a new Student object and set values
                Student student = new Student(); // Use default constructor
                student.setId(id);
                student.setName(name);
                student.setGender(gender);
                student.setDepartment(department);
                student.setProgram(program);
                student.setDateOfBirth(java.sql.Date.valueOf(dob));
                student.setContactNumber(contactNumber);
                student.setCgpa(cgpa);
                student.setBacklogs(backlogs);
                student.setGraduationStatus(graduationStatus);

                session.save(student); // Save the new student

                // Prompt for continuation
                System.out.print("Do you want to add another student (yes/no)? ");
                continueInput = scanner.nextLine();
            } while (continueInput.equalsIgnoreCase("yes"));

            transaction.commit();
            System.out.println("Student(s) Added Successfully");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback transaction on error
            e.printStackTrace();
        } finally {
            session.close(); // Ensure session is closed
        }
    }

    // Method to fetch student details by ID
    public void fetchStudentById(Scanner scanner) {
        Session session = sf.openSession();
        try {
            System.out.print("Enter Student ID to fetch details: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            Student student = session.get(Student.class, id);
            if (student != null) {
                System.out.println("Student Found: ");
                System.out.println("ID: " + student.getId());
                System.out.println("Name: " + student.getName());
                System.out.println("Gender: " + student.getGender());
                System.out.println("Department: " + student.getDepartment());
                System.out.println("Program: " + student.getProgram());
                System.out.println("Date of Birth: " + student.getDateOfBirth());
                System.out.println("Contact Number: " + student.getContactNumber());
                System.out.println("CGPA: " + student.getCgpa());
                System.out.println("Backlogs: " + student.getBacklogs());
                System.out.println("Graduation Status: " + student.isGraduationStatus());
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("Error fetching student: " + e.getMessage());
        } finally {
            session.close(); // Ensure session is closed
        }
    }

    // Method to update student details by ID
    public void updateStudentById(Scanner scanner) {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            System.out.print("Enter Student ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            Student student = session.get(Student.class, id);
            if (student != null) {
                System.out.println("Enter new details for the student:");
                System.out.print("Enter Student Name: ");
                student.setName(scanner.nextLine());
                System.out.print("Enter Student Gender: ");
                student.setGender(scanner.nextLine());
                System.out.print("Enter Student Department: ");
                student.setDepartment(scanner.nextLine());
                System.out.print("Enter Program: ");
                student.setProgram(scanner.nextLine());
                System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
                student.setDateOfBirth(java.sql.Date.valueOf(scanner.nextLine()));
                System.out.print("Enter Contact Number: ");
                student.setContactNumber(scanner.nextLine());
                System.out.print("Enter CGPA: ");
                student.setCgpa(scanner.nextDouble());
                System.out.print("Enter Number of Backlogs: ");
                student.setBacklogs(scanner.nextInt());
                scanner.nextLine(); // Consume the newline
                System.out.print("Has Graduated (true/false): ");
                student.setGraduationStatus(scanner.nextBoolean());

                // Update the student object in the session
                session.update(student);
                transaction.commit();
                System.out.println("Student Updated Successfully.");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback transaction on error
            System.out.println("Error updating student: " + e.getMessage());
        } finally {
            session.close(); // Ensure session is closed
        }
    }

    // Method to delete student by ID
    public void deleteStudentById(Scanner scanner) {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            System.out.print("Enter Student ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
                transaction.commit();
                System.out.println("Student Deleted Successfully.");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback transaction on error
            System.out.println("Error deleting student: " + e.getMessage());
        } finally {
            session.close(); // Ensure session is closed
        }
    }
}
