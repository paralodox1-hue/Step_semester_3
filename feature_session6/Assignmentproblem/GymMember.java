import java.util.Arrays;

public class GymMember {
    private static final int COUNTER_START = 2000;
    private static int counter = COUNTER_START;

    public final String membershipNumber;
    protected final String memberId;
    protected final int monthlyFee;
    private int sessionsAttended;
    private int feesPaid;
    private String lastPaymentMode;
    private final int[] lateFeeHistory = new int[10];
    private int lateFeeCount;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.membershipNumber = "GYM-" + (++counter);
    }

    public GymMember(int monthlyFee) {
        this.membershipNumber = "GYM-" + (++counter);
        this.memberId = this.membershipNumber;
        this.monthlyFee = monthlyFee;
    }

    void attendSession() {
        sessionsAttended++;
    }

    int getSessionsAttended() {
        return sessionsAttended;
    }

    protected void chargeLateFee(int amount) {
        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount++] = amount;
        }
    }

    int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }

    int getTotalLateFees() {
        int total = 0;
        for (int i = 0; i < lateFeeCount; i++) {
            total += lateFeeHistory[i];
        }
        return total;
    }

    void payFee(int amount) {
        feesPaid += amount;
    }

    void payFee(int amount, String mode) {
        lastPaymentMode = mode;
        payFee(amount);
    }

    int getFeesPaid() {
        return feesPaid;
    }

    public String getLastPaymentMode() {
        return lastPaymentMode;
    }

    String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
    }

    static int getMembersEnrolled() {
        return counter - COUNTER_START;
    }

    static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;
        for (String id : memberIds) {
            try {
                GymMember member = new GymMember(id, monthlyFee);
                signedUp++;
                // Keep the instance in a variable to avoid the "new instance ignored" warning.
                if (member == null) {
                    throw new IllegalStateException("Unexpected null member");
                }
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof PremiumMember) {
            return "Single inheritance child (2 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }
        return "Base class (root of hierarchy)";
    }

    static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        for (GymMember member : members) {
            total += member.getSessionsAttended();
        }
        return total;
    }

    static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();
        for (GymMember member : members) {
            sb.append(member.displayInfo());
            if (member instanceof PremiumMember premium) {
                sb.append(" [Trainer via downcast: ").append(premium.getTrainerName()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'G'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;
        for (GymMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (member instanceof GroupClassMember) {
                group++;
            } else {
                individual++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | "
                + group + " group | " + individual + " individual";
    }
}