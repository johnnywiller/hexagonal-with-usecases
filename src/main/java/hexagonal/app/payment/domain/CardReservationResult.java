package hexagonal.app.payment.domain;

import static hexagonal.app.payment.domain.CardReservationResult.ReservationStatus.REJECTED;
import static hexagonal.app.payment.domain.CardReservationResult.ReservationStatus.SUCCESS;

public class CardReservationResult {

    private final ReservationStatus reservationStatus;

    private CardReservationResult(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    enum ReservationStatus {
        SUCCESS,
        REJECTED;
    }

    boolean isSuccessful() {
        return reservationStatus == SUCCESS;
    }

    boolean isRejected() {
        return reservationStatus == REJECTED;
    }

    public static CardReservationResult success() {
        return new CardReservationResult(SUCCESS);
    }

    public static CardReservationResult rejected() {
        return new CardReservationResult(REJECTED);
    }
}
