package lab7.task1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

/** Додає записи бронювань у текстовий журнал у форматі UTF-8. */
public final class FileBookingRepository implements BookingRepository {
    private final Path file;

    public FileBookingRepository(Path file) {
        this.file = file;
    }

    @Override
    public void save(Booking booking) throws IOException {
        Path parent = file.toAbsolutePath().getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        BookingRequest request = booking.getRequest();
        BookingPriceBreakdown price = booking.getPrice();
        String services = request.getExtraServices().stream()
                .map(ExtraService::getDisplayName)
                .collect(Collectors.joining(","));
        String line = String.join("|",
                escape(booking.getId()),
                escape(booking.getCreatedAt().toString()),
                escape(request.getGuestName()),
                escape(request.getGuestPhone()),
                request.getRoomType().getDisplayName(),
                request.getCheckInDate().toString(),
                request.getCheckOutDate().toString(),
                Long.toString(price.getNights()),
                escape(services),
                price.getGrandTotal().toPlainString()
        ) + System.lineSeparator();

        Files.writeString(
                file,
                line,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }

    // Екранування не дозволяє роздільникам і перенесенням рядка пошкодити структуру журналу.
    private static String escape(String value) {
        return value.replace("\\", "\\\\")
                .replace("|", "\\|")
                .replace("\r", "\\r")
                .replace("\n", "\\n");
    }
}
