package lab11.task4;

import lab11.EventLogger;

public class TicketSalesSystem {
    public void openSales(String sessionId) {
        EventLogger.log("TicketSalesSystem", "Відкриття продажу квитків на сеанс #" + sessionId);
    }
}