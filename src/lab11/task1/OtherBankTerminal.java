package lab11.task1;

import lab11.common.EventLog;

public final class OtherBankTerminal {
    private final long availableBalanceInKopiykas;

    public OtherBankTerminal(long availableBalanceInKopiykas) {
        if (availableBalanceInKopiykas < 0) {
            throw new IllegalArgumentException("Баланс термінала не може бути від'ємним.");
        }
        this.availableBalanceInKopiykas = availableBalanceInKopiykas;
    }

    public String executeTransaction(long amountInKopiykas) {
        EventLog.log("Термінал банку «Обрій»", "Виконано executeTransaction(" + amountInKopiykas + ").");
        String result = amountInKopiykas <= availableBalanceInKopiykas ? "APPROVED" : "LIMIT_EXCEEDED";
        EventLog.log("Термінал банку «Обрій»", "Повернуто результат " + result);
        return result;
    }
}
