package Student;

import java.sql.*;
import java.util.Scanner;

public class StudentDAO {

    // ================= STUDENT REGISTRATION =================

    public void registerStudent(Scanner sc) {
        String sql = """
            INSERT INTO student
            (sid,name,father_name,mother_name,gender,dob,email,phone,
             address,department,year,username,password)
            VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)
            """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.print("Enter Student ID : ");
            ps.setInt(1, sc.nextInt());
            sc.nextLine();

            String[] prompts = {
                "Enter Name : ",
                "Enter Father Name : ",
                "Enter Mother Name : ",
                "Enter Gender : ",
                "Enter Date of Birth (YYYY-MM-DD) : ",
                "Enter Email : ",
                "Enter Phone : ",
                "Enter Address : ",
                "Enter Department : "
            };

            for (int i = 0; i < prompts.length; i++) {
                System.out.print(prompts[i]);
                ps.setString(i + 2, sc.nextLine());
            }

            System.out.print("Enter Year : ");
            ps.setInt(11, sc.nextInt());
            sc.nextLine();

            System.out.print("Enter Username : ");
            ps.setString(12, sc.nextLine());

            System.out.print("Enter Password : ");
            ps.setString(13, sc.nextLine());

            System.out.println(ps.executeUpdate() > 0
                    ? "\nStudent Registered Successfully..."
                    : "\nRegistration Failed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= STUDENT LOGIN =================

    public void studentLogin(Scanner sc) {
        String sql =
                "SELECT sid,name FROM student WHERE username=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            sc.nextLine();

            System.out.print("Enter Username : ");
            ps.setString(1, sc.nextLine());

            System.out.print("Enter Password : ");
            ps.setString(2, sc.nextLine());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\nLogin Successful...");
                    System.out.println("Welcome " + rs.getString("name"));
                    studentDashboard(sc, rs.getInt("sid"));
                } else {
                    System.out.println("\nInvalid Username or Password.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= STUDENT DASHBOARD =================

    public void studentDashboard(Scanner sc, int sid) {
        while (true) {
            System.out.println("""
                    
                    ====================================
                          STUDENT DASHBOARD
                    ====================================
                    1. View Profile
                    2. Register Course
                    3. View Registered Courses
                    4. View Marks
                    5. View Credits
                    6. View Attendance
                    7. Fee Status
                    8. Change Password
                    9. Logout
                    """);

            System.out.print("Enter Choice : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> viewProfile(sid);
                case 2 -> registerCourse(sc, sid);
                case 3 -> viewCourses(sid);
                case 4 -> viewMarks(sid);
                case 5 -> viewCredits(sid);
                case 6 -> viewAttendance(sid);
                case 7 -> feeStatus(sid);
                case 8 -> changePassword(sc, sid);

                case 9 -> {
                    System.out.println("\nLogged Out Successfully...");
                    return;
                }

                default -> System.out.println("Invalid Choice.");
            }
        }
    }

    // ================= VIEW PROFILE =================

    public void viewProfile(int sid) {
        String sql = "SELECT * FROM student WHERE sid=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, sid);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Student Not Found.");
                    return;
                }

                System.out.println("\n========== STUDENT PROFILE ==========");
                System.out.println("Student ID    : " + rs.getInt("sid"));
                System.out.println("Name          : " + rs.getString("name"));
                System.out.println("Father Name   : " + rs.getString("father_name"));
                System.out.println("Mother Name   : " + rs.getString("mother_name"));
                System.out.println("Gender        : " + rs.getString("gender"));
                System.out.println("Date of Birth : " + rs.getDate("dob"));
                System.out.println("Email         : " + rs.getString("email"));
                System.out.println("Phone         : " + rs.getString("phone"));
                System.out.println("Address       : " + rs.getString("address"));
                System.out.println("Department    : " + rs.getString("department"));
                System.out.println("Year          : " + rs.getInt("year"));
                System.out.println("Username      : " + rs.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= REGISTER COURSE =================

    public void registerCourse(Scanner sc, int sid) {
        try (Connection con = DBConnection.getConnection()) {

            System.out.println("\n========== AVAILABLE COURSES ==========");

            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM courses");
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    System.out.printf(
                            "%d  %-25s %d Credits%n",
                            rs.getInt("course_id"),
                            rs.getString("course_name"),
                            rs.getInt("credits")
                    );
                }
            }

            System.out.print("\nEnter Course ID : ");
            int cid = sc.nextInt();

            String checkSql =
                    "SELECT 1 FROM student_courses WHERE sid=? AND course_id=?";

            try (PreparedStatement ps = con.prepareStatement(checkSql)) {
                ps.setInt(1, sid);
                ps.setInt(2, cid);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("\nCourse Already Registered.");
                        return;
                    }
                }
            }

            try (PreparedStatement ps =
                         con.prepareStatement(
                                 "SELECT 1 FROM courses WHERE course_id=?")) {

                ps.setInt(1, cid);

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("\nInvalid Course ID.");
                        return;
                    }
                }
            }

            try (PreparedStatement ps =
                         con.prepareStatement(
                                 "INSERT INTO student_courses VALUES(?,?)")) {

                ps.setInt(1, sid);
                ps.setInt(2, cid);

                System.out.println(ps.executeUpdate() > 0
                        ? "\nCourse Registered Successfully..."
                        : "\nRegistration Failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= VIEW COURSES =================

    public void viewCourses(int sid) {
        String sql = """
            SELECT c.course_name,c.credits
            FROM student_courses sc
            JOIN courses c ON sc.course_id=c.course_id
            WHERE sc.sid=?
            """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, sid);

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("\n========== REGISTERED COURSES ==========");

                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.printf(
                            "%-25s %d Credits%n",
                            rs.getString("course_name"),
                            rs.getInt("credits")
                    );
                }

                if (!found)
                    System.out.println("No Courses Registered.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= VIEW MARKS =================

    public void viewMarks(int sid) {
        displaySubjects(
                sid,
                "marks",
                "MARKS",
                new String[]{
                    "java", "adv_java", "sql_marks",
                    "dbms", "python", "web_tech"
                },
                false
        );
    }

    // ================= VIEW CREDITS =================

    public void viewCredits(int sid) {
        displaySubjects(
                sid,
                "credits",
                "CREDITS",
                new String[]{
                    "java_credit", "adv_java_credit", "sql_credit",
                    "dbms_credit", "python_credit", "web_credit"
                },
                false
        );
    }

    // ================= VIEW ATTENDANCE =================

    public void viewAttendance(int sid) {
        displaySubjects(
                sid,
                "attendance",
                "ATTENDANCE",
                new String[]{
                    "java_att", "adv_java_att", "sql_att",
                    "dbms_att", "python_att", "web_att"
                },
                true
        );
    }

    // ================= COMMON SUBJECT DISPLAY =================

    private void displaySubjects(
            int sid,
            String table,
            String title,
            String[] columns,
            boolean percentage) {

        String[] subjects = {
            "Java",
            "Advanced Java",
            "SQL",
            "DBMS",
            "Python",
            "Web Technology"
        };

        String sql = "SELECT * FROM " + table + " WHERE sid=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, sid);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("\n" + title + " Details Not Available.");
                    return;
                }

                System.out.println("\n========== " + title + " ==========");

                for (int i = 0; i < subjects.length; i++) {
                    String value = percentage
                            ? rs.getDouble(columns[i]) + "%"
                            : String.valueOf(rs.getInt(columns[i]));

                    System.out.printf("%-20s : %s%n",
                            subjects[i], value);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= FEE STATUS =================

    public void feeStatus(int sid) {
        String sql = "SELECT * FROM fees WHERE sid=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, sid);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("\nFee Details Not Available.");
                    return;
                }

                System.out.println("\n========== FEE STATUS ==========");
                System.out.println("Total Fee    : " + rs.getDouble("total_fee"));
                System.out.println("Paid Fee     : " + rs.getDouble("paid_fee"));
                System.out.println("Pending Fee  : " + rs.getDouble("pending_fee"));
                System.out.println("Payment Date : " + rs.getDate("payment_date"));
                System.out.println("Status       : " + rs.getString("status"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= CHANGE PASSWORD =================

    public void changePassword(Scanner sc, int sid) {
        try (Connection con = DBConnection.getConnection()) {

            sc.nextLine();

            System.out.print("Enter Old Password : ");
            String oldPassword = sc.nextLine();

            String checkSql =
                    "SELECT 1 FROM student WHERE sid=? AND password=?";

            try (PreparedStatement ps = con.prepareStatement(checkSql)) {
                ps.setInt(1, sid);
                ps.setString(2, oldPassword);

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("\nOld Password Incorrect.");
                        return;
                    }
                }
            }

            System.out.print("Enter New Password : ");
            String newPassword = sc.nextLine();

            System.out.print("Confirm Password : ");
            String confirmPassword = sc.nextLine();

            if (!newPassword.equals(confirmPassword)) {
                System.out.println("\nPasswords Do Not Match.");
                return;
            }

            String updateSql =
                    "UPDATE student SET password=? WHERE sid=?";

            try (PreparedStatement ps = con.prepareStatement(updateSql)) {
                ps.setString(1, newPassword);
                ps.setInt(2, sid);

                System.out.println(ps.executeUpdate() > 0
                        ? "\nPassword Changed Successfully."
                        : "\nPassword Change Failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}