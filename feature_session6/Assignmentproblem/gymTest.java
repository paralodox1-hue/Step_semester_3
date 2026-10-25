import java.util.Arrays;

public class gymTest {
    public static void main(String[] args) {
        System.out.println("--- Problem 5 ---");
        GymMember m1 = new GymMember(1000);
        System.out.println(m1.membershipNumber);
        System.out.println(GymMember.getMembersEnrolled());
        System.out.println(GymMember.isValidReferralCode("G45B"));
        System.out.println(GymMember.isValidReferralCode("G4B"));
        System.out.println(GymMember.isValidReferralCode("X45B"));
        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println(m1.getFeesPaid());
        GymMember[] weekly = { new GroupClassMember(1500, "Zumba"), null, new GymMember(1000) };
        System.out.println(GymMember.processWeeklyCheckIn(weekly));

        System.out.println("--- Problem 1 ---");
        try {
            System.out.println(new GymMember("GM1", 1000).displayInfo());
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
        PremiumMember p = new PremiumMember("MEM01", 2000, "Coach Riya");
        p.attendSession();
        p.attendSession();
        System.out.println(p.getSessionsAttended());
        System.out.println(GymMember.signUpBatch(new String[] { "MEM1", "GM1", "MEM2", " ", "MEM3" }, 1000));

        System.out.println("--- Problem 2 ---");
        GymMember basic = new GymMember("MEM1", 1000);
        PremiumMember premium = new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember elite = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember groupClass = new GroupClassMember("MEM4", 1500, "Zumba");
        System.out.println(basic.displayInfo());
        System.out.println(premium.displayInfo());
        System.out.println(elite.displayInfo());
        System.out.println(groupClass.displayInfo());
        System.out.println(GymMember.classifyGeneration(elite));
        System.out.println(GymMember.classifyGeneration(groupClass));
        for (int i = 0; i < 3; i++) premium.attendSession();
        for (int i = 0; i < 2; i++) elite.attendSession();
        for (int i = 0; i < 4; i++) groupClass.attendSession();
        System.out.println(GymMember.getTotalSessionsAttended(
                new GymMember[] { premium, elite, groupClass }));

        System.out.println("--- Problem 3 ---");
        PremiumMember fee = new PremiumMember("MEM5", 2000, "Coach Riya");
        fee.chargeLateFee(200);
        System.out.println(fee.getTotalLateFees());
        int[] history = fee.getLateFeeHistory();
        history[0] = 999;
        System.out.println(history[0]);
        System.out.println(Arrays.toString(fee.getLateFeeHistory()));

        System.out.println("--- Problem 4 ---");
        System.out.println(GymMember.batchPrint(new GymMember[] {
                new GymMember("MEM6", 1000),
                new PremiumMember("MEM7", 2000, "Coach Riya")
        }));
        try {
            GymMember plain = new GymMember("MEM8", 1000);
            PremiumMember bad = (PremiumMember) plain;
            System.out.println(bad.getTrainerName());
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}