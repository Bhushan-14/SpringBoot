package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import connection.DatabaseConfig;
import entity.Employee;

public class EmployeeDao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an operation:");
            System.out.println("1. Insert Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Display Employees");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee Name: ");
                    String empName = scanner.nextLine();

                    System.out.print("Enter Employee Address: ");
                    String empAddress = scanner.nextLine();

                    System.out.print("Enter Employee Email: ");
                    String empEmail = scanner.nextLine();

                    Employee employee = new Employee(0, empName, empAddress, empEmail);

                    try {
                        insertEmployee(employee);
                    } catch (SQLException e) {
                        System.out.println("Error inserting employee: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter Employee ID to Update: ");
                    int empIdToUpdate = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter New Employee Name: ");
                    String newEmpName = scanner.nextLine();

                    System.out.print("Enter New Employee Address: ");
                    String newEmpAddress = scanner.nextLine();

                    System.out.print("Enter New Employee Email: ");
                    String newEmpEmail = scanner.nextLine();

                    Employee updatedEmployee = new Employee(empIdToUpdate, newEmpName, newEmpAddress, newEmpEmail);

                    try {
                        updateEmployee(updatedEmployee);
                    } catch (SQLException e) {
                        System.out.println("Error updating employee: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter Employee ID to Delete: ");
                    int empIdToDelete = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        deleteEmployee(empIdToDelete);
                    } catch (SQLException e) {
                        System.out.println("Error deleting employee: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        displayEmployees();
                    } catch (SQLException e) {
                        System.out.println("Error displaying employees: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static void insertEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO employees (empName, empAddress, empEmail) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, employee.getEmpName());
            pstmt.setString(2, employee.getEmpAddress());
            pstmt.setString(3, employee.getEmpEmail());

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Inserted Successfully!");
            } else {
                System.out.println("Insertion failed. Please try again.");
            }
        }
    }

    public static void updateEmployee(Employee employee) throws SQLException {
        String query = "UPDATE employees SET empName = ?, empAddress = ?, empEmail = ? WHERE empId = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, employee.getEmpName());
            pstmt.setString(2, employee.getEmpAddress());
            pstmt.setString(3, employee.getEmpEmail());
            pstmt.setInt(4, employee.getEmpId());

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Updated Successfully!");
            } else {
                System.out.println("Update failed. Please try again.");
            }
        }
    }

    public static void deleteEmployee(int empId) throws SQLException {
        String query = "DELETE FROM employees WHERE empId = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, empId);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Deleted Successfully!");
            } else {
                System.out.println("Deletion failed. Please try again.");
            }
        }
    }

    public static void displayEmployees() throws SQLException {
        String query = "SELECT * FROM employees";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int empId = rs.getInt("empId");
                String empName = rs.getString("empName");
                String empAddress = rs.getString("empAddress");
                String empEmail = rs.getString("empEmail");

                System.out.println("ID: " + empId + ", Name: " + empName + ", Address: " + empAddress + ", Email: " + empEmail);
            }
        }
    }
}
