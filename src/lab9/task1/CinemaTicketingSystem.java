package lab9.task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/** Єдина система продажу квитків для всіх каналів кінотеатру. */
public final class CinemaTicketingSystem {
    private static final CinemaTicketingSystem INSTANCE = new CinemaTicketingSystem();

    private final String systemId = UUID.randomUUID().toString();
    private final Map<Screening, Map<Seat, SalesChannel>> soldSeats = new HashMap<>();

    private CinemaTicketingSystem() {
        System.out.println("Систему продажу ініціалізовано.");
    }

    public static CinemaTicketingSystem getInstance() {
        return INSTANCE;
    }

    public String getSystemId() {
        return systemId;
    }

    public synchronized void sellSeat(Screening screening, Seat seat, SalesChannel channel) {
        Objects.requireNonNull(screening, "screening");
        Objects.requireNonNull(seat, "seat");
        Objects.requireNonNull(channel, "channel");

        Map<Seat, SalesChannel> seatsForScreening =
                soldSeats.computeIfAbsent(screening, ignored -> new HashMap<>());
        SalesChannel previousChannel = seatsForScreening.get(seat);
        if (previousChannel != null) {
            throw new SeatAlreadySoldException(seat, previousChannel);
        }

        seatsForScreening.put(seat, channel);
    }

    public synchronized boolean isSeatAvailable(Screening screening, Seat seat) {
        Objects.requireNonNull(screening, "screening");
        Objects.requireNonNull(seat, "seat");

        Map<Seat, SalesChannel> seatsForScreening = soldSeats.get(screening);
        return seatsForScreening == null || !seatsForScreening.containsKey(seat);
    }
}
