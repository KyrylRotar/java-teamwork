package lab11.task1;

import lab11.common.EventLog;

public final class NewBankTerminal {
    private final String terminalName;
    private final long availableBalanceInKopiykas;

    public NewBankTerminal(String terminalName, long availableBalanceInKopiykas) {
        if (availableBalanceInKopiykas < 0) {
            throw new IllegalArgumentException("Баланс термінала не може бути від'ємним.");
        }
        this.terminalName = terminalName;
        this.availableBalanceInKopiykas = availableBalanceInKopiykas;
    }

    public int payInKopiykas(long amountInKopiykas) {
        EventLog.log(terminalName, "Отримано запит на " + amountInKopiykas + " коп.");
        int responseCode = amountInKopiykas <= availableBalanceInKopiykas ? 0 : 51;
        EventLog.log(terminalName, "Повернуто код відповіді " + responseCode);
        return responseCode;
    }
}
