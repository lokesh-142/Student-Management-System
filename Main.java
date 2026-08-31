package Student;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentDAO student = new StudentDAO();
        adminDAO admin = new adminDAO();

        while (true) {

            System.out.println("\n================================================");
            System.out.println("     STUDENT INFORMATION MANAGEMENT SYSTEM");
            System.out.println("================================================");
            System.out.println("1. Student Module");
            System.out.println("2. Admin Module");
            System.out.println("3. Exit");
            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

            // ================= STUDENT MODULE =================

            case 1:

                while (true) {

                    System.out.println("\n======================================");
                    System.out.println("          STUDENT MODULE");
                    System.out.println("======================================");
                    System.out.println("1. Student Registration");
                    System.out.println("2. Student Login");
                    System.out.println("3. Back");

                    System.out.print("Enter Choice : ");

                    int ch = sc.nextInt();

                    switch (ch) {

                    case 1:

                        student.registerStudent(sc);

                        break;

                    case 2:

                        student.studentLogin(sc);

                        break;

                    case 3:

                        break;

                    default:

                        System.out.println("Invalid Choice.");

                    }

                    if (ch == 3)
                        break;

                }

                break;

            // ================= ADMIN MODULE =================

            case 2:

                while (true) {

                    System.out.println("\n======================================");
                    System.out.println("            ADMIN MODULE");
                    System.out.println("======================================");
                    System.out.println("1. Admin Login");
                    System.out.println("2. Back");

                    System.out.print("Enter Choice : ");

                    int ch = sc.nextInt();

                    switch (ch) {

                    case 1:

                        admin.adminLogin(sc);

                        break;

                    case 2:

                        break;

                    default:

                        System.out.println("Invalid Choice.");

                    }

                    if (ch == 2)
                        break;

                }

                break;

            // ================= EXIT =================

            case 3:

                System.out.println("\n======================================");
                System.out.println("Thank You For Using SIMS");
                System.out.println("Visit Again...");
                System.out.println("======================================");

                sc.close();
                System.exit(0);

                break;

            default:

                System.out.println("Invalid Choice.");

            }

        }

    }

}