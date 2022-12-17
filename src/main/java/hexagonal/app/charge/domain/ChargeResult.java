package hexagonal.app.charge.domain;

import static hexagonal.app.charge.domain.ChargeResult.ReservationStatus.REJECTED;
import static hexagonal.app.charge.domain.ChargeResult.ReservationStatus.SUCCESS;

public class ChargeResult {

    private final ReservationStatus chargeStatus;

    private ChargeResult(ReservationStatus chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    enum ReservationStatus {
        SUCCESS,
        REJECTED;
    }

    boolean isSuccessful() {
        return chargeStatus == SUCCESS;
    }

    boolean isRejected() {
        return chargeStatus == REJECTED;
    }

    static ChargeResult success() {
        return new ChargeResult(SUCCESS);
    }

    static ChargeResult rejected() {
        return new ChargeResult(REJECTED);
    }
}