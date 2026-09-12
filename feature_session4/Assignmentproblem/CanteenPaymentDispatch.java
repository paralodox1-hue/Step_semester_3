class Payment {
    void pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
    }
}

class CardPayment extends Payment {
    double totalCharged;

    void payWithProcessingFee(double amount) {
        totalCharged = amount + (amount * 0.02);
        System.out.println("Charged (card, incl. fee): Rs " + totalCharged);
    }
}

class TransactionProcessor {
    double totalCollected = 0;

    void processTransaction(Payment payment, double amount) {
        if (payment instanceof CardPayment) {
            CardPayment cardPayment = (CardPayment) payment;
            cardPayment.payWithProcessingFee(amount);
            totalCollected += cardPayment.totalCharged;
        } else {
            payment.pay(amount);
            totalCollected += amount;
        }
    }

    void printTotal() {
        System.out.println("Total Collected: Rs " + totalCollected);
    }
}

public class CanteenPaymentDispatch {
    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {100, 50, 200, 75, 120};

        TransactionProcessor processor = new TransactionProcessor();

        for (int i = 0; i < payments.length; i++) {
            processor.processTransaction(payments[i], amounts[i]);
        }

        processor.printTotal();
    }
}