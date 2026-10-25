import java.util.Arrays;

public class LibraryMember {
    private static final int COUNTER_START = 100;
    private static int counter = COUNTER_START;

    public final String memberNumber;
    protected final String memberId;
    protected final int borrowLimit;
    private int booksBorrowed;
    private String lastGenre;
    private final int[] fineHistory = new int[10];
    private int fineCount;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.memberNumber = "LIB-" + (++counter);
    }

    public LibraryMember(int borrowLimit) {
        this.memberNumber = "LIB-" + (++counter);
        this.memberId = this.memberNumber;
        this.borrowLimit = borrowLimit;
    }

    void borrowBook() {
        booksBorrowed++;
    }

    void borrowBook(String genre) {
        lastGenre = genre;
        borrowBook();
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    String getLastGenre() {
        return lastGenre;
    }

    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
        }
    }

    int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    int getTotalFine() {
        int total = 0;
        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }
        return total;
    }

    String displayInfo() {
        String genre = getLastGenre();
        if (genre == null || genre.trim().isEmpty()) {
            return "General Member | Books Borrowed: " + booksBorrowed;
        }
        return "General Member | Books Borrowed: " + booksBorrowed + " | Last Genre: " + genre;
    }

    static int getMembersEnrolled() {
        return counter - COUNTER_START;
    }

    static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;
        for (String id : memberIds) {
            if (id != null && !id.trim().isEmpty() && id.length() >= 4) {
                counter++;
                enrolled++;
            } else {
                rejected++;
            }
        }
        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }

    static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof StudentMember) {
            return "Single inheritance child (2 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }
        return "Base class (root of hierarchy)";
    }

    static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
        }
        return total;
    }

    static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();
        for (LibraryMember member : members) {
            sb.append(member.displayInfo());
            if (member instanceof StudentMember student) {
                sb.append(" [Course via downcast: ").append(student.getCourse()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;
        for (LibraryMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | "
                + faculty + " faculty | " + regular + " regular";
    }
}