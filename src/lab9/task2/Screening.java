package lab9.task2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/** Самостійний сеанс, створений копіюванням конфігурації прототипу. */
public final class Screening {
    private final String movieTitle;
    private final int roomNumber;
    private final LocalDateTime startsAt;
    private final Map<Integer, BigDecimal> rowPrices;
    private final AdvertisingBlock advertisingBlock;
    private final Map<Seat, SeatStatus> seatStatuses;

    Screening(
            String movieTitle,
            int roomNumber,
            LocalDateTime startsAt,
            Map<Integer, BigDecimal> rowPrices,
            AdvertisingBlock advertisingBlock,
            Map<Seat, SeatStatus> seatStatuses
    ) {
        this.movieTitle = Objects.requireNonNull(movieTitle, "movieTitle");
        this.roomNumber = roomNumber;
        this.startsAt = Objects.requireNonNull(startsAt, "startsAt");
        this.rowPrices = new LinkedHashMap<>(rowPrices);
        this.advertisingBlock = advertisingBlock.copy();
        this.seatStatuses = new LinkedHashMap<>(seatStatuses);
    }

    public void changeRowPrice(int row, BigDecimal price) {
        Objects.requireNonNull(price, "price");
        if (price.signum() <= 0) {
            throw new IllegalArgumentException("Ціна має бути більшою за нуль.");
        }
        if (!rowPrices.containsKey(row)) {
            throw new IllegalArgumentException("Ряд " + row + " відсутній у схемі залу.");
        }
        rowPrices.put(row, price);
    }

    public void replaceAdvertisingClip(int index, String clip) {
        advertisingBlock.replaceClip(index, clip);
    }

    public void sellSeat(Seat seat) {
        Objects.requireNonNull(seat, "seat");
        SeatStatus currentStatus = seatStatuses.get(seat);
        if (currentStatus == null) {
            throw new IllegalArgumentException("Місце " + seat + " відсутнє у схемі залу.");
        }
        if (currentStatus == SeatStatus.SOLD) {
            throw new IllegalStateException("Місце " + seat + " уже продано на цей сеанс.");
        }
        seatStatuses.put(seat, SeatStatus.SOLD);
    }

    public BigDecimal getRowPrice(int row) {
        BigDecimal price = rowPrices.get(row);
        if (price == null) {
            throw new IllegalArgumentException("Ряд " + row + " відсутній у схемі залу.");
        }
        return price;
    }

    public AdvertisingBlock getAdvertisingBlock() {
        return advertisingBlock.copy();
    }

    public Map<Seat, SeatStatus> getSeatStatuses() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(seatStatuses));
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    @Override
    public String toString() {
        return "Сеанс: " + movieTitle
                + ", зал " + roomNumber
                + ", " + startsAt
                + "\n  Ціни за рядами: " + rowPrices
                + "\n  Реклама: " + advertisingBlock
                + "\n  Статус місць: " + seatStatuses;
    }
}
