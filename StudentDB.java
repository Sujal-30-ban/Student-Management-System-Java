import java.sql.*;
import java.util.Scanner;

public class StudentDB {

    static Connection con;

    public static void connectDB() {
        try {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentdb",
                "root",
                "root"
            );
            System.out.println("Connected to Database!");
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void addStudent(int id, String name, int age, String branch) {
        try {
            String query = "INSERT INTO students VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setInt(3, age);
            pst.setString(4, branch);

            pst.executeUpdate();
            System.out.println("Student Added!");

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void searchStudent(int id) {
        try {
            String query = "SELECT * FROM students WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if(rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Branch: " + rs.getString("branch"));
            } else {
                System.out.println("Student not found");
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void updateStudent(int id, String newName) {
        try {
            String query = "UPDATE students SET name=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, newName);
            pst.setInt(2, id);

            int rows = pst.executeUpdate();

            if(rows > 0) {
                System.out.println("Student Updated!");
            } else {
                System.out.println("Student not found");
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteStudent(int id) {
        try {
            String query = "DELETE FROM students WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, id);

            int rows = pst.executeUpdate();

            if(rows > 0) {
                System.out.println("Student Deleted!");
            } else {
                System.out.println("Student not found");
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        connectDB();

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Add Student");
        System.out.println("2. Search Student");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        if(choice == 1) {

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Branch: ");
            String branch = sc.nextLine();

            addStudent(id, name, age, branch);

        }

        else if(choice == 2) {

            System.out.print("Enter ID to search: ");
            int id = sc.nextInt();

            searchStudent(id);
        }

        else if(choice == 3) {

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter new name: ");
            String name = sc.nextLine();

            updateStudent(id, name);
        }

        else if(choice == 4) {

            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();

            deleteStudent(id);
        }

        sc.close();
    }
}
