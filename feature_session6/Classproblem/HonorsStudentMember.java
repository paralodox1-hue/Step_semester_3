public class HonorsStudentMember extends StudentMember {
    private final int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    int getBonusLimit() {
        return bonusLimit;
    }

    @Override
    int getBooksBorrowed() {
        return 0;
    }

    @Override
    String displayInfo() {
        return "Honors Student Member | Course: " + getCourse() + " | Bonus Limit: " + getBonusLimit()
                + " | Books Borrowed: " + getBooksBorrowed();
    }
}