package lab9.task2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** Налаштований зразок сеансу; створює незалежні копії для розкладу. */
public final class ScreeningPrototype {
    private final String movieTitle;
    private final int roomNumber;
    private final Map<Integer, BigDecimal> rowPrices;
    private final AdvertisingBlock advertisingBlock;
    private final Map<Seat, SeatStatus> seatStatuses;

    ScreeningPrototype(
            String movieTitle,
            int roomNumber,
            Map<Integer, BigDecimal> rowPrices,
            AdvertisingBlock advertisingBlock,
            Map<Seat, SeatStatus> seatStatuses
    ) {
        this.movieTitle = Objects.requireNonNull(movieTitle, "movieTitle");
        this.roomNumber = roomNumber;
        this.rowPrices = Collections.unmodifiableMap(new LinkedHashMap<>(rowPrices));
        this.advertisingBlock = advertisingBlock.copy();
        this.seatStatuses = Collections.unmodifiableMap(new LinkedHashMap<>(seatStatuses));
    }

    public Screening createScreening(LocalDateTime startsAt) {
        return new Screening(
                movieTitle,
                roomNumber,
                startsAt,
                rowPrices,
                advertisingBlock,
                seatStatuses
        );
    }

    @Override
    public String toString() {
        return "Зразок сеансу: " + movieTitle
                + ", зал " + roomNumber
                + "\n  Ціни за рядами: " + rowPrices
                + "\n  Реклама: " + advertisingBlock
                + "\n  Статус місць: " + seatStatuses;
    }

    static ScreeningPrototype configuredFor(String movieTitle, int roomNumber) {
        Map<Integer, BigDecimal> prices = new LinkedHashMap<>();
        prices.put(1, new BigDecimal("180.00"));
        prices.put(2, new BigDecimal("150.00"));
        prices.put(3, new BigDecimal("120.00"));

        List<String> advertisingClips = new ArrayList<>();
        advertisingClips.add("Трейлер майбутньої прем'єри");
        advertisingClips.add("Реклама напоїв");
        AdvertisingBlock advertisingBlock = new AdvertisingBlock(advertisingClips);

        Map<Seat, SeatStatus> seats = new LinkedHashMap<>();
        for (int row = 1; row <= 3; row++) {
            for (int number = 1; number <= 5; number++) {
                seats.put(new Seat(row, number), SeatStatus.AVAILABLE);
            }
        }

        return new ScreeningPrototype(movieTitle, roomNumber, prices, advertisingBlock, seats);
    }
}
