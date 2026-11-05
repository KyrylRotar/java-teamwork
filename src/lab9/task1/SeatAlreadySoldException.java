package lab9.task1;

/** Повідомляє, що місце вже продано іншим каналом. */
public final class SeatAlreadySoldException extends IllegalStateException {
    public SeatAlreadySoldException(Seat seat, SalesChannel soldBy) {
        super("Місце " + seat + " уже продано (" + soldBy.getDisplayName() + ").");
    }
}
