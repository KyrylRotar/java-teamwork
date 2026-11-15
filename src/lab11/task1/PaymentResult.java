package lab11.task1;

public final class PaymentResult {
    private final boolean successful;
    private final String message;

    private PaymentResult(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public static PaymentResult success(String message) {
        return new PaymentResult(true, message);
    }

    public static PaymentResult declined(String reason) {
        return new PaymentResult(false, reason);
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }
}
