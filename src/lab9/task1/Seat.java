package lab9.task1;

import java.util.Objects;

/** Місце в залі: ряд і номер місця. */
public final class Seat {
    private final int row;
    private final int number;

    public Seat(int row, int number) {
        if (row <= 0 || number <= 0) {
            throw new IllegalArgumentException("Ряд і номер місця мають бути додатними.");
        }
        this.row = row;
        this.number = number;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Seat)) {
            return false;
        }
        Seat seat = (Seat) other;
        return row == seat.row && number == seat.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, number);
    }

    @Override
    public String toString() {
        return row + "-" + number;
    }
}
