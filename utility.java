package Student;

import java.util.Scanner;

public class utility {

    // ========================= HEADING =========================

    public static void heading(String title) {

        System.out.println("\n==================================================");
        System.out.println("             " + title.toUpperCase());
        System.out.println("==================================================");

    }

    // ========================= PAUSE =========================

    public static void pause(Scanner sc) {

        System.out.println("\nPress Enter to Continue...");
        sc.nextLine();
        sc.nextLine();

    }

    // ========================= EMAIL VALIDATION =========================

    public static boolean validateEmail(String email) {

        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    }

    // ========================= PHONE VALIDATION =========================

    public static boolean validatePhone(String phone) {

        return phone.matches("[0-9]{10}");

    }

    // ========================= PASSWORD VALIDATION =========================

    public static boolean validatePassword(String password) {

        return password.length() >= 6;

    }

    // ========================= MARK VALIDATION =========================

    public static boolean validateMarks(int marks) {

        return marks >= 0 && marks <= 100;

    }

    // ========================= CREDIT VALIDATION =========================

    public static boolean validateCredits(int credits) {

        return credits >= 0 && credits <= 10;

    }

    // ========================= ATTENDANCE VALIDATION =========================

    public static boolean validateAttendance(double attendance) {

        return attendance >= 0 && attendance <= 100;

    }

    // ========================= FEE VALIDATION =========================

    public static boolean validateFee(double fee) {

        return fee >= 0;

    }

    // ========================= GRADE CALCULATION =========================

    public static String calculateGrade(double percentage) {

        if (percentage >= 90)
            return "A+";

        else if (percentage >= 80)
            return "A";

        else if (percentage >= 70)
            return "B";

        else if (percentage >= 60)
            return "C";

        else if (percentage >= 50)
            return "D";

        else

            return "FAIL";

    }

    // ========================= PERCENTAGE =========================

    public static double calculatePercentage(

            int java,

            int advJava,

            int sql,

            int dbms,

            int python,

            int web) {

        int total = java + advJava + sql + dbms + python + web;

        return total / 6.0;

    }

    // ========================= TOTAL MARKS =========================

    public static int totalMarks(

            int java,

            int advJava,

            int sql,

            int dbms,

            int python,

            int web) {

        return java + advJava + sql + dbms + python + web;

    }

}
