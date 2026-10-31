package lab7.task1;

import java.time.LocalDate;
import java.util.Objects;

/** Єдине місце для правил перевірки заявки на бронювання. */
public final class BookingValidator {

    public void validate(BookingRequest request) {
        Objects.requireNonNull(request, "Заявка на бронювання не може бути null.");

        if (request.getGuestName() == null || request.getGuestName().isBlank()) {
            throw new IllegalArgumentException("Вкажіть ім'я гостя.");
        }
        if (request.getGuestPhone() == null || request.getGuestPhone().isBlank()) {
            throw new IllegalArgumentException("Вкажіть контактний номер телефону.");
        }
        if (request.getRoomType() == null) {
            throw new IllegalArgumentException("Оберіть категорію номера.");
        }
        if (request.getCheckInDate() == null || request.getCheckOutDate() == null) {
            throw new IllegalArgumentException("Вкажіть дати заїзду та виїзду.");
        }
        if (request.getCheckInDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Дата заїзду не може бути в минулому.");
        }
        if (!request.getCheckOutDate().isAfter(request.getCheckInDate())) {
            throw new IllegalArgumentException("Дата виїзду має бути пізніше за дату заїзду.");
        }
        if (request.getExtraServices().stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Список додаткових послуг містить некоректне значення.");
        }
    }
}
