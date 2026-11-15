package lab11.task1;

import lab11.common.EventLog;

import java.math.BigDecimal;

public final class Task1 {
    private static final BigDecimal TICKET_PRICE = new BigDecimal("180.00");

    private Task1() {
    }

    public static void main(String[] args) {
        demonstrateTerminalAdapter();
    }

    private static void demonstrateTerminalAdapter() {
        EventLog.log("Раннер завдання 1", "Початок демонстрації Adapter для платіжних терміналів.");

        CinemaCashDesk cashDesk = new CinemaCashDesk(
                new NewBankTerminalAdapter(new NewBankTerminal("Термінал банку «Дніпро»", 50_000))
        );
        cashDesk.payForTicket(TICKET_PRICE);

        EventLog.log("Раннер завдання 1", "Емулюємо відмову термінала через недостатній доступний баланс.");
        cashDesk.setPaymentGateway(
                new NewBankTerminalAdapter(new NewBankTerminal("Термінал банку «Дніпро»", 0))
        );
        cashDesk.payForTicket(TICKET_PRICE);

        EventLog.log("Раннер завдання 1", "Підключаємо термінал іншого банку без змін у коді каси.");
        cashDesk.setPaymentGateway(
                new OtherBankTerminalAdapter(new OtherBankTerminal(50_000))
        );
        cashDesk.payForTicket(TICKET_PRICE);
    }
}
