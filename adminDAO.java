package Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class adminDAO {

    //===================== ADMIN LOGIN =====================

    public void adminLogin(Scanner sc) {

        try {

            Connection con = DBConnection.getConnection();

            sc.nextLine();

            System.out.print("Enter Username : ");
            String username = sc.nextLine();

            System.out.print("Enter Password : ");
            String password = sc.nextLine();

            String query = "SELECT * FROM admin WHERE username=? AND password=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n===================================");
                System.out.println("      ADMIN LOGIN SUCCESSFUL");
                System.out.println("===================================");

                adminDashboard(sc);

            }

            else {

                System.out.println("\nInvalid Username or Password.");

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    //===================== ADMIN DASHBOARD =====================

    public void adminDashboard(Scanner sc) {

        while (true) {

            System.out.println("\n=====================================");
            System.out.println("          ADMIN DASHBOARD");
            System.out.println("=====================================");

            System.out.println("1. View All Students");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Manage Courses");
            System.out.println("5. Manage Marks");
            System.out.println("6. Manage Credits");
            System.out.println("7. Manage Attendance");
            System.out.println("8. Manage Fees");
            System.out.println("9. Reports");
            System.out.println("10. Logout");

            System.out.print("\nEnter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

            case 1:

                viewAllStudents();

                break;

            case 2:

                updateStudent(sc);

                break;

            case 3:

                deleteStudent(sc);

                break;

            case 4:

                while(true){

                    System.out.println("\n==============================");
                    System.out.println("     COURSE MANAGEMENT");
                    System.out.println("==============================");
                    System.out.println("1. Add Course");
                    System.out.println("2. Update Course");
                    System.out.println("3. Delete Course");
                    System.out.println("4. Back");

                    System.out.print("Enter Choice : ");

                    int ch = sc.nextInt();

                    switch(ch){

                    case 1:
                        addCourse(sc);
                        break;

                    case 2:
                        updateCourse(sc);
                        break;

                    case 3:
                        deleteCourse(sc);
                        break;

                    case 4:
                        break;

                    default:
                        System.out.println("Invalid Choice");
                    }

                    if(ch==4)
                        break;
                }

                break;
            case 5:

                while(true){

                    System.out.println("\n1.Add Marks");
                    System.out.println("2.Update Marks");
                    System.out.println("3.Back");

                    int ch=sc.nextInt();

                    switch(ch){

                    case 1:
                        addMarks(sc);
                        break;

                    case 2:
                        updateMarks(sc);
                        break;

                    case 3:
                        break;
                    }

                    if(ch==3)
                        break;
                }

                break;

            case 6:

                while(true){

                    System.out.println("\n1.Add Credits");
                    System.out.println("2.Update Credits");
                    System.out.println("3.Back");

                    int ch=sc.nextInt();

                    switch(ch){

                    case 1:
                        addCredits(sc);
                        break;

                    case 2:
                        updateCredits(sc);
                        break;

                    case 3:
                        break;
                    }

                    if(ch==3)
                        break;
                }

                break;

            case 7:

                while(true){

                    System.out.println("\n1.Add Attendance");
                    System.out.println("2.Update Attendance");
                    System.out.println("3.Back");

                    int ch=sc.nextInt();

                    switch(ch){

                    case 1:
                        addAttendance(sc);
                        break;

                    case 2:
                        updateAttendance(sc);
                        break;

                    case 3:
                        break;
                    }

                    if(ch==3)
                        break;
                }

                break;

            case 8:

                while(true){

                    System.out.println("\n==========================");
                    System.out.println("      FEE MANAGEMENT");
                    System.out.println("==========================");
                    System.out.println("1. Update Fee");
                    System.out.println("2. View Fee");
                    System.out.println("3. Back");

                    System.out.print("Enter Choice : ");

                    int ch = sc.nextInt();

                    switch(ch){

                    case 1:
                        updateFees(sc);
                        break;

                    case 2:
                        viewFees(sc);
                        break;

                    case 3:
                        break;

                    default:
                        System.out.println("Invalid Choice");
                    }

                    if(ch==3)
                        break;
                }

                break;
            case 9:

                while(true){

                    System.out.println("\n==========================");
                    System.out.println("         REPORTS");
                    System.out.println("==========================");
                    System.out.println("1. Student Report");
                    System.out.println("2. Course Report");
                    System.out.println("3. Topper Report");
                    System.out.println("4. Attendance Report");
                    System.out.println("5. Fee Report");
                    System.out.println("6. Back");

                    System.out.print("Enter Choice : ");

                    int ch = sc.nextInt();

                    switch(ch){

                    case 1:
                        studentReport();
                        break;

                    case 2:
                        courseReport();
                        break;

                    case 3:
                        topperReport();
                        break;

                    case 4:
                        attendanceReport();
                        break;

                    case 5:
                        feeReport();
                        break;

                    case 6:
                        break;

                    default:
                        System.out.println("Invalid Choice");
                    }

                    if(ch==6)
                        break;
                }

                break;

            case 10:

                System.out.println("\nLogged Out Successfully.");

                return;

            default:

                System.out.println("\nInvalid Choice.");

            }

        }

    }

    //===================== VIEW ALL STUDENTS =====================

    public void viewAllStudents() {

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM student";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===============================================================================================================");

            System.out.printf("%-5s %-15s %-15s %-10s %-15s %-15s %-10s%n",
                    "ID",
                    "Name",
                    "Department",
                    "Year",
                    "Phone",
                    "Username",
                    "Gender");

            System.out.println("===============================================================================================================");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.printf("%-5d %-15s %-15s %-10d %-15s %-15s %-10s%n",

                        rs.getInt("sid"),

                        rs.getString("name"),

                        rs.getString("department"),

                        rs.getInt("year"),

                        rs.getString("phone"),

                        rs.getString("username"),

                        rs.getString("gender"));

            }

            if (!found) {

                System.out.println("\nNo Students Found.");

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

//===================== UPDATE STUDENT =====================

public void updateStudent(Scanner sc) {

  try {

      Connection con = DBConnection.getConnection();

      System.out.print("\nEnter Student ID : ");
      int sid = sc.nextInt();
      sc.nextLine();

      PreparedStatement check = con.prepareStatement(
              "SELECT * FROM student WHERE sid=?");

      check.setInt(1, sid);

      ResultSet rs = check.executeQuery();

      if (!rs.next()) {

          System.out.println("\nStudent Not Found.");
          return;
      }

      System.out.println("\nLeave blank if no change.\n");

      System.out.print("Enter Name (" + rs.getString("name") + ") : ");
      String name = sc.nextLine();
      if(name.isEmpty())
          name = rs.getString("name");

      System.out.print("Enter Father Name (" + rs.getString("father_name") + ") : ");
      String father = sc.nextLine();
      if(father.isEmpty())
          father = rs.getString("father_name");

      System.out.print("Enter Mother Name (" + rs.getString("mother_name") + ") : ");
      String mother = sc.nextLine();
      if(mother.isEmpty())
          mother = rs.getString("mother_name");

      System.out.print("Enter Email (" + rs.getString("email") + ") : ");
      String email = sc.nextLine();
      if(email.isEmpty())
          email = rs.getString("email");

      System.out.print("Enter Phone (" + rs.getString("phone") + ") : ");
      String phone = sc.nextLine();
      if(phone.isEmpty())
          phone = rs.getString("phone");

      System.out.print("Enter Address (" + rs.getString("address") + ") : ");
      String address = sc.nextLine();
      if(address.isEmpty())
          address = rs.getString("address");

      System.out.print("Enter Department (" + rs.getString("department") + ") : ");
      String dept = sc.nextLine();
      if(dept.isEmpty())
          dept = rs.getString("department");

      System.out.print("Enter Year (" + rs.getInt("year") + ") : ");
      String yearInput = sc.nextLine();

      int year;

      if(yearInput.isEmpty())
          year = rs.getInt("year");
      else
          year = Integer.parseInt(yearInput);

      PreparedStatement update = con.prepareStatement(

          "UPDATE student SET " +
          "name=?,father_name=?,mother_name=?,email=?,phone=?,address=?,department=?,year=? " +
          "WHERE sid=?"

      );

      update.setString(1, name);
      update.setString(2, father);
      update.setString(3, mother);
      update.setString(4, email);
      update.setString(5, phone);
      update.setString(6, address);
      update.setString(7, dept);
      update.setInt(8, year);
      update.setInt(9, sid);

      int n = update.executeUpdate();

      if(n > 0)
          System.out.println("\nStudent Updated Successfully.");
      else
          System.out.println("\nUpdate Failed.");

  }

  catch(Exception e) {

      e.printStackTrace();

  }

}
//===================== ADD COURSE =====================

public void addCourse(Scanner sc) {

  try {

      Connection con = DBConnection.getConnection();

      sc.nextLine();

      System.out.print("\nEnter Course Name : ");
      String cname = sc.nextLine();

      System.out.print("Enter Credits : ");
      int credits = sc.nextInt();

      PreparedStatement check = con.prepareStatement(
              "SELECT * FROM courses WHERE course_name=?");

      check.setString(1, cname);

      ResultSet rs = check.executeQuery();

      if(rs.next()) {

          System.out.println("\nCourse Already Exists.");
          return;

      }

      PreparedStatement ps = con.prepareStatement(
              "INSERT INTO courses(course_name,credits) VALUES(?,?)");

      ps.setString(1, cname);
      ps.setInt(2, credits);

      int n = ps.executeUpdate();

      if(n>0)
          System.out.println("\nCourse Added Successfully.");
      else
          System.out.println("\nFailed to Add Course.");

  }

  catch(Exception e) {

      e.printStackTrace();

  }
}
//===================== UPDATE COURSE =====================

public void updateCourse(Scanner sc) {

  try {

      Connection con = DBConnection.getConnection();

      System.out.print("\nEnter Course ID : ");
      int cid = sc.nextInt();
      sc.nextLine();

      PreparedStatement check = con.prepareStatement(
              "SELECT * FROM courses WHERE course_id=?");

      check.setInt(1, cid);

      ResultSet rs = check.executeQuery();

      if (!rs.next()) {

          System.out.println("\nCourse Not Found.");
          return;

      }

      System.out.print("Enter New Course Name (" +
              rs.getString("course_name") + ") : ");

      String cname = sc.nextLine();

      if (cname.isEmpty())
          cname = rs.getString("course_name");

      System.out.print("Enter Credits (" +
              rs.getInt("credits") + ") : ");

      String credit = sc.nextLine();

      int credits;

      if (credit.isEmpty())
          credits = rs.getInt("credits");
      else
          credits = Integer.parseInt(credit);

      PreparedStatement ps = con.prepareStatement(

              "UPDATE courses SET course_name=?,credits=? WHERE course_id=?"

      );

      ps.setString(1, cname);
      ps.setInt(2, credits);
      ps.setInt(3, cid);

      int n = ps.executeUpdate();

      if (n > 0)
          System.out.println("\nCourse Updated Successfully.");
      else
          System.out.println("\nUpdate Failed.");

  }

  catch (Exception e) {

      e.printStackTrace();

  }

}
//===================== DELETE COURSE =====================

public void deleteCourse(Scanner sc) {

  try {

      Connection con = DBConnection.getConnection();

      System.out.print("\nEnter Course ID : ");
      int cid = sc.nextInt();

      PreparedStatement check = con.prepareStatement(
              "SELECT * FROM courses WHERE course_id=?");

      check.setInt(1, cid);

      ResultSet rs = check.executeQuery();

      if (!rs.next()) {

          System.out.println("\nCourse Not Found.");
          return;

      }

      System.out.print("Are You Sure (Y/N) : ");

      char ch = Character.toUpperCase(sc.next().charAt(0));

      if (ch != 'Y') {

          System.out.println("\nDeletion Cancelled.");
          return;

      }

      con.setAutoCommit(false);

      PreparedStatement ps1 = con.prepareStatement(
              "DELETE FROM student_courses WHERE course_id=?");

      ps1.setInt(1, cid);
      ps1.executeUpdate();

      PreparedStatement ps2 = con.prepareStatement(
              "DELETE FROM courses WHERE course_id=?");

      ps2.setInt(1, cid);

      int n = ps2.executeUpdate();

      if (n > 0) {

          con.commit();
          System.out.println("\nCourse Deleted Successfully.");

      } else {

          con.rollback();
          System.out.println("\nDeletion Failed.");

      }

      con.setAutoCommit(true);

  }

  catch (Exception e) {

      e.printStackTrace();

  }

}
//===================== ADD MARKS =====================

public void addMarks(Scanner sc) {

  try {

      Connection con = DBConnection.getConnection();

      System.out.print("\nEnter Student ID : ");
      int sid = sc.nextInt();

      PreparedStatement check = con.prepareStatement(
              "SELECT * FROM student WHERE sid=?");

      check.setInt(1, sid);

      ResultSet rs = check.executeQuery();

      if (!rs.next()) {

          System.out.println("\nStudent Not Found.");
          return;

      }

      PreparedStatement exist = con.prepareStatement(
              "SELECT * FROM marks WHERE sid=?");

      exist.setInt(1, sid);

      if (exist.executeQuery().next()) {

          System.out.println("\nMarks Already Exist.");
          return;

      }

      System.out.print("Java : ");
      int java = sc.nextInt();

      System.out.print("Advanced Java : ");
      int adv = sc.nextInt();

      System.out.print("SQL : ");
      int sql = sc.nextInt();

      System.out.print("DBMS : ");
      int dbms = sc.nextInt();

      System.out.print("Python : ");
      int python = sc.nextInt();

      System.out.print("Web Technology : ");
      int web = sc.nextInt();

      PreparedStatement ps = con.prepareStatement(
              "INSERT INTO marks VALUES(?,?,?,?,?,?,?)");

      ps.setInt(1, sid);
      ps.setInt(2, java);
      ps.setInt(3, adv);
      ps.setInt(4, sql);
      ps.setInt(5, dbms);
      ps.setInt(6, python);
      ps.setInt(7, web);

      int n = ps.executeUpdate();

      if (n > 0)
          System.out.println("\nMarks Added Successfully.");
      else
          System.out.println("\nFailed.");

  }

  catch (Exception e) {

      e.printStackTrace();

  }
}
//===================== UPDATE MARKS =====================

public void updateMarks(Scanner sc) {

  try {

      Connection con = DBConnection.getConnection();

      System.out.print("\nEnter Student ID : ");
      int sid = sc.nextInt();

      PreparedStatement check = con.prepareStatement(
              "SELECT * FROM marks WHERE sid=?");

      check.setInt(1, sid);

      ResultSet rs = check.executeQuery();

      if(!rs.next()) {

          System.out.println("\nMarks Record Not Found.");
          return;

      }

      System.out.print("Java : ");
      int java = sc.nextInt();

      System.out.print("Advanced Java : ");
      int adv = sc.nextInt();

      System.out.print("SQL : ");
      int sql = sc.nextInt();

      System.out.print("DBMS : ");
      int dbms = sc.nextInt();

      System.out.print("Python : ");
      int python = sc.nextInt();

      System.out.print("Web Technology : ");
      int web = sc.nextInt();

      PreparedStatement ps = con.prepareStatement(

              "UPDATE marks SET java=?,adv_java=?,sql_marks=?,dbms=?,python=?,web_tech=? WHERE sid=?"

      );

      ps.setInt(1, java);
      ps.setInt(2, adv);
      ps.setInt(3, sql);
      ps.setInt(4, dbms);
      ps.setInt(5, python);
      ps.setInt(6, web);
      ps.setInt(7, sid);

      int n = ps.executeUpdate();

      if(n>0)
          System.out.println("\nMarks Updated Successfully.");
      else
          System.out.println("\nUpdate Failed.");

  }

  catch(Exception e) {

      e.printStackTrace();

  }

}
//===================== ADD CREDITS =====================

public void addCredits(Scanner sc) {

  try {

      Connection con = DBConnection.getConnection();

      System.out.print("\nEnter Student ID : ");
      int sid = sc.nextInt();

      PreparedStatement check =
              con.prepareStatement("SELECT * FROM student WHERE sid=?");

      check.setInt(1, sid);

      if(!check.executeQuery().next()) {

          System.out.println("\nStudent Not Found.");
          return;

      }

      PreparedStatement exist =
              con.prepareStatement("SELECT * FROM credits WHERE sid=?");

      exist.setInt(1, sid);

      if(exist.executeQuery().next()) {

          System.out.println("\nCredits Already Assigned.");
          return;

      }

      System.out.print("Java Credit : ");
      int java = sc.nextInt();

      System.out.print("Advanced Java Credit : ");
      int adv = sc.nextInt();

      System.out.print("SQL Credit : ");
      int sql = sc.nextInt();

      System.out.print("DBMS Credit : ");
      int dbms = sc.nextInt();

      System.out.print("Python Credit : ");
      int python = sc.nextInt();

      System.out.print("Web Technology Credit : ");
      int web = sc.nextInt();

      PreparedStatement ps = con.prepareStatement(
              "INSERT INTO credits VALUES(?,?,?,?,?,?,?)");

      ps.setInt(1, sid);
      ps.setInt(2, java);
      ps.setInt(3, adv);
      ps.setInt(4, sql);
      ps.setInt(5, dbms);
      ps.setInt(6, python);
      ps.setInt(7, web);

      int n = ps.executeUpdate();

      if(n>0)
          System.out.println("\nCredits Added Successfully.");
      else
          System.out.println("\nFailed.");

  }

  catch(Exception e) {

      e.printStackTrace();

  }

}
//===================== UPDATE CREDITS =====================

public void updateCredits(Scanner sc) {

  try {

      Connection con = DBConnection.getConnection();

      System.out.print("\nEnter Student ID : ");
      int sid = sc.nextInt();

      PreparedStatement check =
              con.prepareStatement("SELECT * FROM credits WHERE sid=?");

      check.setInt(1, sid);

      ResultSet rs = check.executeQuery();

      if (!rs.next()) {

          System.out.println("\nCredits Record Not Found.");
          return;

      }

      System.out.print("Java Credit : ");
      int java = sc.nextInt();

      System.out.print("Advanced Java Credit : ");
      int adv = sc.nextInt();

      System.out.print("SQL Credit : ");
      int sql = sc.nextInt();

      System.out.print("DBMS Credit : ");
      int dbms = sc.nextInt();

      System.out.print("Python Credit : ");
      int python = sc.nextInt();

      System.out.print("Web Technology Credit : ");
      int web = sc.nextInt();

      PreparedStatement ps = con.prepareStatement(

          "UPDATE credits SET java_credit=?,adv_java_credit=?,sql_credit=?,dbms_credit=?,python_credit=?,web_credit=? WHERE sid=?"

      );

      ps.setInt(1, java);
      ps.setInt(2, adv);
      ps.setInt(3, sql);
      ps.setInt(4, dbms);
      ps.setInt(5, python);
      ps.setInt(6, web);
      ps.setInt(7, sid);

      int n = ps.executeUpdate();

      if (n > 0)
          System.out.println("\nCredits Updated Successfully.");
      else
          System.out.println("\nUpdate Failed.");

  }

  catch (Exception e) {

      e.printStackTrace();

  }

}
//===================== ADD ATTENDANCE =====================

public void addAttendance(Scanner sc) {

  try {

      Connection con = DBConnection.getConnection();

      System.out.print("\nEnter Student ID : ");
      int sid = sc.nextInt();

      PreparedStatement check =
              con.prepareStatement("SELECT * FROM student WHERE sid=?");

      check.setInt(1, sid);

      if (!check.executeQuery().next()) {

          System.out.println("\nStudent Not Found.");
          return;

      }

      PreparedStatement exist =
              con.prepareStatement("SELECT * FROM attendance WHERE sid=?");

      exist.setInt(1, sid);

      if (exist.executeQuery().next()) {

          System.out.println("\nAttendance Already Exists.");
          return;

      }

      System.out.print("Java Attendance : ");
      double java = sc.nextDouble();

      System.out.print("Advanced Java Attendance : ");
      double adv = sc.nextDouble();

      System.out.print("SQL Attendance : ");
      double sql = sc.nextDouble();

      System.out.print("DBMS Attendance : ");
      double dbms = sc.nextDouble();

      System.out.print("Python Attendance : ");
      double python = sc.nextDouble();

      System.out.print("Web Attendance : ");
      double web = sc.nextDouble();

      PreparedStatement ps = con.prepareStatement(

              "INSERT INTO attendance VALUES(?,?,?,?,?,?,?)"

      );

      ps.setInt(1, sid);
      ps.setDouble(2, java);
      ps.setDouble(3, adv);
      ps.setDouble(4, sql);
      ps.setDouble(5, dbms);
      ps.setDouble(6, python);
      ps.setDouble(7, web);

      int n = ps.executeUpdate();

      if (n > 0)
          System.out.println("\nAttendance Added Successfully.");
      else
          System.out.println("\nFailed.");

  }

  catch (Exception e) {

      e.printStackTrace();

  }
}

//===================== UPDATE ATTENDANCE =====================

public void updateAttendance(Scanner sc) {

  try {

      Connection con = DBConnection.getConnection();

      System.out.print("\nEnter Student ID : ");
      int sid = sc.nextInt();

      PreparedStatement check =
              con.prepareStatement("SELECT * FROM attendance WHERE sid=?");

      check.setInt(1, sid);

      ResultSet rs = check.executeQuery();

      if (!rs.next()) {

          System.out.println("\nAttendance Record Not Found.");
          return;

      }

      System.out.print("Java Attendance : ");
      double java = sc.nextDouble();

      System.out.print("Advanced Java Attendance : ");
      double adv = sc.nextDouble();

      System.out.print("SQL Attendance : ");
      double sql = sc.nextDouble();

      System.out.print("DBMS Attendance : ");
      double dbms = sc.nextDouble();

      System.out.print("Python Attendance : ");
      double python = sc.nextDouble();

      System.out.print("Web Attendance : ");
      double web = sc.nextDouble();

      PreparedStatement ps = con.prepareStatement(

              "UPDATE attendance SET java_att=?,adv_java_att=?,sql_att=?,dbms_att=?,python_att=?,web_att=? WHERE sid=?"

      );

      ps.setDouble(1, java);
      ps.setDouble(2, adv);
      ps.setDouble(3, sql);
      ps.setDouble(4, dbms);
      ps.setDouble(5, python);
      ps.setDouble(6, web);
      ps.setInt(7, sid);

      int n = ps.executeUpdate();

      if (n > 0)
          System.out.println("\nAttendance Updated Successfully.");
      else
          System.out.println("\nUpdate Failed.");

  }

  catch (Exception e) {

      e.printStackTrace();

  }
}
//===================== DELETE STUDENT =====================

public void deleteStudent(Scanner sc) {

  try {

      Connection con = DBConnection.getConnection();

      System.out.print("\nEnter Student ID : ");
      int sid = sc.nextInt();

      PreparedStatement check = con.prepareStatement(
              "SELECT * FROM student WHERE sid=?");

      check.setInt(1, sid);

      ResultSet rs = check.executeQuery();

      if (!rs.next()) {

          System.out.println("\nStudent Not Found.");
          return;

      }

      System.out.print("Are You Sure (Y/N) : ");

      char ch = Character.toUpperCase(sc.next().charAt(0));

      if (ch != 'Y') {

          System.out.println("\nDeletion Cancelled.");
          return;

      }

      con.setAutoCommit(false);

      PreparedStatement ps1 = con.prepareStatement(
              "DELETE FROM student_courses WHERE sid=?");
      ps1.setInt(1, sid);
      ps1.executeUpdate();

      PreparedStatement ps2 = con.prepareStatement(
              "DELETE FROM marks WHERE sid=?");
      ps2.setInt(1, sid);
      ps2.executeUpdate();

      PreparedStatement ps3 = con.prepareStatement(
              "DELETE FROM credits WHERE sid=?");
      ps3.setInt(1, sid);
      ps3.executeUpdate();

      PreparedStatement ps4 = con.prepareStatement(
              "DELETE FROM attendance WHERE sid=?");
      ps4.setInt(1, sid);
      ps4.executeUpdate();

      PreparedStatement ps5 = con.prepareStatement(
              "DELETE FROM fees WHERE sid=?");
      ps5.setInt(1, sid);
      ps5.executeUpdate();

      PreparedStatement ps6 = con.prepareStatement(
              "DELETE FROM student WHERE sid=?");
      ps6.setInt(1, sid);

      int n = ps6.executeUpdate();

      if (n > 0) {

          con.commit();
          System.out.println("\nStudent Deleted Successfully.");

      } else {

          con.rollback();
          System.out.println("\nDeletion Failed.");

      }

      con.setAutoCommit(true);

  }

  catch (Exception e) {

      e.printStackTrace();

  }

}
//===================== UPDATE FEES =====================

public void updateFees(Scanner sc) {

  try {

      Connection con = DBConnection.getConnection();

      System.out.print("\nEnter Student ID : ");
      int sid = sc.nextInt();

      PreparedStatement check =
              con.prepareStatement("SELECT * FROM fees WHERE sid=?");

      check.setInt(1, sid);

      ResultSet rs = check.executeQuery();

      if (!rs.next()) {

          System.out.println("\nFee Record Not Found.");

          return;

      }

      System.out.print("Enter Total Fee : ");
      double total = sc.nextDouble();

      System.out.print("Enter Paid Fee : ");
      double paid = sc.nextDouble();

      double pending = total - paid;

      String status;

      if (pending == 0)

          status = "PAID";

      else

          status = "PENDING";

      sc.nextLine();

      System.out.print("Enter Payment Date (YYYY-MM-DD) : ");
      String date = sc.nextLine();

      PreparedStatement ps = con.prepareStatement(

              "UPDATE fees SET total_fee=?,paid_fee=?,pending_fee=?,payment_date=?,status=? WHERE sid=?"

      );

      ps.setDouble(1, total);
      ps.setDouble(2, paid);
      ps.setDouble(3, pending);
      ps.setString(4, date);
      ps.setString(5, status);
      ps.setInt(6, sid);

      int n = ps.executeUpdate();

      if (n > 0)

          System.out.println("\nFee Updated Successfully.");

      else

          System.out.println("\nUpdate Failed.");

  }

  catch (Exception e) {

      e.printStackTrace();

  }

}
//===================== VIEW FEES =====================

public void viewFees(Scanner sc) {

  try {

      Connection con = DBConnection.getConnection();

      System.out.print("\nEnter Student ID : ");

      int sid = sc.nextInt();

      PreparedStatement ps = con.prepareStatement(

              "SELECT * FROM fees WHERE sid=?"

      );

      ps.setInt(1, sid);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {

          System.out.println("\n==================================");

          System.out.println("          FEE DETAILS");

          System.out.println("==================================");

          System.out.println("Student ID    : " + rs.getInt("sid"));

          System.out.println("Total Fee     : " + rs.getDouble("total_fee"));

          System.out.println("Paid Fee      : " + rs.getDouble("paid_fee"));

          System.out.println("Pending Fee   : " + rs.getDouble("pending_fee"));

          System.out.println("Payment Date  : " + rs.getString("payment_date"));

          System.out.println("Status        : " + rs.getString("status"));

      }

      else {

          System.out.println("\nNo Fee Record Found.");

      }

  }

  catch (Exception e) {

      e.printStackTrace();

  }

}
//===================== STUDENT REPORT =====================

public void studentReport() {

  try {

      Connection con = DBConnection.getConnection();

      PreparedStatement ps = con.prepareStatement(
              "SELECT sid,name,department,year,phone,email FROM student");

      ResultSet rs = ps.executeQuery();

      System.out.println("\n================ STUDENT REPORT ================");

      System.out.printf("%-5s %-20s %-15s %-6s %-15s %-25s\n",
              "ID","Name","Department","Year","Phone","Email");

      System.out.println("--------------------------------------------------------------------------");

      while(rs.next()) {

          System.out.printf("%-5d %-20s %-15s %-6d %-15s %-25s\n",

                  rs.getInt("sid"),

                  rs.getString("name"),

                  rs.getString("department"),

                  rs.getInt("year"),

                  rs.getString("phone"),

                  rs.getString("email"));

      }

  }

  catch(Exception e){

      e.printStackTrace();

  }
}
//===================== COURSE REPORT =====================

public void courseReport() {

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(

                "SELECT * FROM courses"

        );

        ResultSet rs = ps.executeQuery();

        System.out.println("\n============== COURSE REPORT ==============");

        System.out.printf("%-10s %-25s %-10s\n",

                "Course ID",

                "Course Name",

                "Credits");

        System.out.println("-----------------------------------------------");

        while(rs.next()) {

            System.out.printf("%-10d %-25s %-10d\n",

                    rs.getInt("course_id"),

                    rs.getString("course_name"),

                    rs.getInt("credits"));

        }

    }

    catch(Exception e){

        e.printStackTrace();

    }
}
  //===================== TOPPER REPORT =====================

    public void topperReport() {

        try {

            Connection con = DBConnection.getConnection();

            String sql =

            "SELECT s.sid,s.name," +

            "(m.java+m.adv_java+m.sql_marks+m.dbms+m.python+m.web_tech) total " +

            "FROM student s JOIN marks m ON s.sid=m.sid " +

            "ORDER BY total DESC LIMIT 5";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n============= TOP 5 STUDENTS =============");

            System.out.printf("%-5s %-20s %-10s\n",

                    "ID","Name","Total");

            System.out.println("-----------------------------------------");

            while(rs.next()) {

                System.out.printf("%-5d %-20s %-10d\n",

                        rs.getInt("sid"),

                        rs.getString("name"),

                        rs.getInt("total"));

            }

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

//===================== ATTENDANCE REPORT =====================

public void attendanceReport() {

  try {

      Connection con = DBConnection.getConnection();

      String sql =

      "SELECT s.sid,s.name,a.java_att,a.adv_java_att,a.sql_att,a.dbms_att,a.python_att,a.web_att " +

      "FROM student s JOIN attendance a ON s.sid=a.sid";

      PreparedStatement ps = con.prepareStatement(sql);

      ResultSet rs = ps.executeQuery();

      System.out.println("\n================ ATTENDANCE REPORT ================");

      while(rs.next()) {

          System.out.println("\nStudent ID : "+rs.getInt("sid"));

          System.out.println("Name : "+rs.getString("name"));

          System.out.println("Java : "+rs.getDouble("java_att")+"%");

          System.out.println("Advanced Java : "+rs.getDouble("adv_java_att")+"%");

          System.out.println("SQL : "+rs.getDouble("sql_att")+"%");

          System.out.println("DBMS : "+rs.getDouble("dbms_att")+"%");

          System.out.println("Python : "+rs.getDouble("python_att")+"%");

          System.out.println("Web Technology : "+rs.getDouble("web_att")+"%");

          System.out.println("---------------------------------------------");

      }

  }

  catch(Exception e){

      e.printStackTrace();
  }
}
//===================== FEE REPORT =====================

public void feeReport() {

  try {

      Connection con = DBConnection.getConnection();

      String sql =

      "SELECT s.sid,s.name,f.total_fee,f.paid_fee,f.pending_fee,f.status " +

      "FROM student s JOIN fees f ON s.sid=f.sid";

      PreparedStatement ps = con.prepareStatement(sql);

      ResultSet rs = ps.executeQuery();

      System.out.println("\n================ FEE REPORT ================");

      System.out.printf("%-5s %-20s %-12s %-12s %-12s %-10s\n",

              "ID","Name","Total","Paid","Pending","Status");

      System.out.println("---------------------------------------------------------------------");

      while(rs.next()) {

          System.out.printf("%-5d %-20s %-12.2f %-12.2f %-12.2f %-10s\n",

                  rs.getInt("sid"),

                  rs.getString("name"),

                  rs.getDouble("total_fee"),

                  rs.getDouble("paid_fee"),

                  rs.getDouble("pending_fee"),

                  rs.getString("status"));

      }

  }

  catch(Exception e){

      e.printStackTrace();

  }

}
}