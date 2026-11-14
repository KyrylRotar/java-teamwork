package lab10.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Покроково формує замовлення та перевіряє його перед створенням. */
public final class CinemaOrderBuilder {
    private String orderName = "Звичайне";
    private MovieSession session;
    private final List<String> seats = new ArrayList<>();
    private int popcornPortions;
    private int drinkCount;
    private int glasses3dCount;
    private String promoCode;
    private boolean vipService;

    public CinemaOrderBuilder setOrderName(String orderName) {
        this.orderName = requireText(orderName, "Назва замовлення");
        return this;
    }

    public CinemaOrderBuilder setSession(MovieSession session) {
        this.session = Objects.requireNonNull(session, "Сеанс обов'язковий.");
        return this;
    }

    public CinemaOrderBuilder addSeat(String seat) {
        seats.add(requireText(seat, "Місце"));
        return this;
    }

    public CinemaOrderBuilder addPopcorn(int portions) {
        popcornPortions += requirePositive(portions, "Кількість порцій попкорну");
        return this;
    }

    public CinemaOrderBuilder addDrinks(int count) {
        drinkCount += requirePositive(count, "Кількість напоїв");
        return this;
    }

    public CinemaOrderBuilder add3dGlasses(int count) {
        glasses3dCount += requirePositive(count, "Кількість 3D-окулярів");
        return this;
    }

    public CinemaOrderBuilder applyPromoCode(String promoCode) {
        this.promoCode = requireText(promoCode, "Промокод");
        return this;
    }

    public CinemaOrderBuilder enableVipService() {
        vipService = true;
        return this;
    }

    public CinemaOrder build() {
        if (session == null) {
            throw new IllegalStateException("Неможливо сформувати замовлення: не обрано сеанс");
        }
        if (seats.isEmpty()) {
            throw new IllegalStateException("Неможливо сформувати замовлення: не обрано жодного місця");
        }
        return new CinemaOrder(this);
    }

    String getOrderName() {
        return orderName;
    }

    MovieSession getSession() {
        return session;
    }

    List<String> getSeats() {
        return seats;
    }

    int getPopcornPortions() {
        return popcornPortions;
    }

    int getDrinkCount() {
        return drinkCount;
    }

    int getGlasses3dCount() {
        return glasses3dCount;
    }

    String getPromoCode() {
        return promoCode;
    }

    boolean isVipService() {
        return vipService;
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " не може бути порожнім.");
        }
        return value;
    }

    private static int requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " має бути додатною.");
        }
        return value;
    }
}
