package lab10.task1;

import java.util.List;

/** Зберігає однакову послідовність кроків для типових замовлень. */
public final class CinemaOrderDirector {
    public CinemaOrder createComboForTwo(MovieSession session) {
        return buildTypicalOrder(
                "Комбо на двох", session, List.of("B5", "B6"), 2, 2, 0
        );
    }

    public CinemaOrder createFamilyEvening(MovieSession session) {
        return buildTypicalOrder(
                "Сімейний вечір", session, List.of("C4", "C5", "C6", "C7"), 2, 4, 4
        );
    }

    private CinemaOrder buildTypicalOrder(
            String name,
            MovieSession session,
            List<String> seats,
            int popcornPortions,
            int drinkCount,
            int glasses3dCount
    ) {
        CinemaOrderBuilder builder = new CinemaOrderBuilder()
                .setOrderName(name)
                .setSession(session);
        seats.forEach(builder::addSeat);
        builder.addPopcorn(popcornPortions)
                .addDrinks(drinkCount);
        if (glasses3dCount > 0) {
            builder.add3dGlasses(glasses3dCount);
        }
        return builder.build();
    }
}
