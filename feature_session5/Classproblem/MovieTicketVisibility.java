class MovieTicket {
    private String seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;

    MovieTicket(String seatNumber, String screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }
}

class AccessChecker {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (accessorContext.equals("SAME_CLASS")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return "DENIED";
        }

        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;

        for (int i = 0; i < attempts.length; i++) {
            String result = classifyAccess(attempts[i][0], attempts[i][1]);

            if (result.equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }
}

public class MovieTicketVisibility {
    public static void main(String[] args) {
        System.out.println(
            AccessChecker.classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
            AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE")
        );

        String[][] attempts = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(AccessChecker.summarizeBatch(attempts));
    }
}