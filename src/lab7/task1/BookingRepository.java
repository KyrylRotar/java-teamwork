package lab7.task1;

import java.io.IOException;

/** Контракт для збереження бронювань; спосіб зберігання визначається реалізацією. */
public interface BookingRepository {
    void save(Booking booking) throws IOException;
}
