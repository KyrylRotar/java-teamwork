package lab9.task1;

public enum SalesChannel {
    CASHIER("каса"),
    WEBSITE("сайт"),
    MOBILE_APP("застосунок");

    private final String displayName;

    SalesChannel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
