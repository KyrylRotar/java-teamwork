package lab7.task1;

import java.io.IOException;

/** Канал надсилання підтвердження гостю. */
public interface BookingNotifier {
    void sendConfirmation(Booking booking) throws IOException;
}
