package lab10.task1;

import java.util.List;

/** Незмінне замовлення глядача після успішного формування. */
public final class CinemaOrder {
    private final String orderName;
    private final MovieSession session;
    private final List<String> seats;
    private final int popcornPortions;
    private final int drinkCount;
    private final int glasses3dCount;
    private final String promoCode;
    private final boolean vipService;

    CinemaOrder(CinemaOrderBuilder builder) {
        orderName = builder.getOrderName();
        session = builder.getSession();
        seats = List.copyOf(builder.getSeats());
        popcornPortions = builder.getPopcornPortions();
        drinkCount = builder.getDrinkCount();
        glasses3dCount = builder.getGlasses3dCount();
        promoCode = builder.getPromoCode();
        vipService = builder.isVipService();
    }

    @Override
    public String toString() {
        return "Замовлення «" + orderName + "» {сеанс: " + session
                + ", місця: " + String.join(", ", seats)
                + ", попкорн: " + popcornPortions + " порц."
                + ", напої: " + drinkCount
                + ", 3D-окуляри: " + glasses3dCount
                + ", промокод: " + (promoCode == null ? "не застосовано" : promoCode)
                + ", VIP-обслуговування: " + (vipService ? "так" : "ні")
                + "}";
    }
}
