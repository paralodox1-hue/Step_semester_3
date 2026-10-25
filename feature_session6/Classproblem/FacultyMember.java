public class FacultyMember extends LibraryMember {
    private final String department;

    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }

    String getDepartment() {
        return department;
    }

    @Override
    String displayInfo() {
        return "Faculty Member | Department: " + getDepartment() + " | Books Borrowed: " + getBooksBorrowed();
    }
}