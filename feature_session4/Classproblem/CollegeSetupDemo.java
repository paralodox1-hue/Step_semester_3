class SrmStudent {
    static String collegeName;
    static int academicYear;
    String name;

    static {
        collegeName = "SRM";
        academicYear = 2026;
        System.out.println("College info loaded");
    }

    SrmStudent(String name) {
        this.name = name;
    }

    void display() {
        System.out.println("Student record created: " + name);
    }
}

public class CollegeSetupDemo {
    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        for (String name : names) {
            SrmStudent student = new SrmStudent(name);
            student.display();
        }
    }
}