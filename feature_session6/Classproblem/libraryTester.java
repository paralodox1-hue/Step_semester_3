import java.util.Arrays;

public class libraryTester {
    public static void main(String[] args) {
        System.out.println("--- Problem 5 ---");
        LibraryMember m1 = new LibraryMember(3);
        System.out.println(m1.memberNumber);
        System.out.println(LibraryMember.getMembersEnrolled());
        System.out.println(LibraryMember.isValidRenewalCode("R12A"));
        System.out.println(LibraryMember.isValidRenewalCode("R1A"));
        System.out.println(LibraryMember.isValidRenewalCode("X12A"));
        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());
        LibraryMember[] nightly = { new FacultyMember(5, "Physics"), null, new LibraryMember(3) };
        System.out.println(LibraryMember.processNightlyAudit(nightly));

        System.out.println("--- Problem 1 ---");
        try {
            LibraryMember invalidMember = new LibraryMember("LB1", 3);
            System.out.println(invalidMember);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
        StudentMember s = new StudentMember("STU10", 3, "CSE");
        s.borrowBook();
        s.borrowBook();
        System.out.println(s.getBooksBorrowed());
        System.out.println(LibraryMember.enrollBatch(new String[] { "STU1", "LB1", "STU2", " ", "STU3" }, 3));

        System.out.println("--- Problem 2 ---");
        LibraryMember general = new LibraryMember("STU1", 3);
        StudentMember student = new StudentMember("STU2", 3, "CSE");
        HonorsStudentMember honors = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember faculty = new FacultyMember("STU4", 5, "Physics");
        System.out.println(general.displayInfo());
        System.out.println(student.displayInfo());
        System.out.println(honors.displayInfo());
        System.out.println(faculty.displayInfo());
        System.out.println(LibraryMember.classifyGeneration(honors));
        System.out.println(LibraryMember.classifyGeneration(faculty));
        for (int i = 0; i < 2; i++) student.borrowBook();
        honors.borrowBook();
        for (int i = 0; i < 3; i++) faculty.borrowBook();
        System.out.println(LibraryMember.getTotalBooksBorrowed(
                new LibraryMember[] { student, honors, faculty }));

        System.out.println("--- Problem 3 ---");
        StudentMember fined = new StudentMember("STU5", 3, "CSE");
        fined.chargeFine(100);
        System.out.println(fined.getTotalFine());
        int[] history = fined.getFineHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(history));

        System.out.println("--- Problem 4 ---");
        System.out.println(LibraryMember.batchPrint(new LibraryMember[] {
                new LibraryMember("LB5", 3),
                new StudentMember("STU6", 3, "ECE")
        }));
        try {
            LibraryMember plain = new LibraryMember("LB6", 3);
            StudentMember bad = (StudentMember) plain;
            System.out.println(bad.getCourse());
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}